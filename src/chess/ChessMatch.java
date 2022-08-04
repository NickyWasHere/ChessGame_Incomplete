package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8); //initiates a new board matrix with determined size
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
		
		//Calls the method initialSetup on the Constructor to place all the pieces when a new ChessMatch initiates
		initialSetup(); 
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece[][] getPieces() { //transforms the spaces on the board from Piece to ChessPiece
		
		//creates a matrix of Chess Pieces with the same size as the board
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		//travels the entirety of the Pieces matrix
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				
				//Changes every Piece to Chess Piece
				mat[i][j] = (ChessPiece) board.getPiece(i, j); //Downcasts from Piece to ChessPiece
			}
		}
		
		return mat;
	}
	
	//Method to help in showing the user a piece's possible moves
	public boolean[][] possibleMoves(ChessPosition source) {
		Position position = source.toPosition(); //Converts from chess position to matrix position
		validateSourcePosition(position);
		return board.getPiece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		
		//Converts the positions from chess position to matrix position
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source); //checks if there was a correct piece on the source position
		validateTargetPosition(source, target); //checks if there was a correct piece on the target position
		Piece capturedPiece = makeMove(source, target);
		
		//Tests if the player put himself in check
		if (testCheck(currentPlayer)) { 
			undoMove(source, target, capturedPiece);
			throw new ChessException(
					"You can't put yourself in check");
			
		}
		
		//Tests if the opponent was put in check
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		//Tests checkmate before going to next turn
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;	
		} else {
			nextTurn(); //switches turns
		}
		
		return (ChessPiece) capturedPiece; //downcasts from Piece to ChessPiece
		
	}
	
	//Assistant method for method performChessMove
	private Piece makeMove(Position source, Position target) {
		ChessPiece piece = (ChessPiece) board.removePiece(source); //removes piece from source
		piece.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target); //removes captured piece
		board.placePiece(target, piece); //places pieces on target position
		
		if (capturedPiece != null) { //if there's a captured piece
			piecesOnBoard.remove(capturedPiece); //remove from board
			capturedPieces.add(capturedPiece); //add to captured pieces
		}
		
		return capturedPiece;
	}
	
	//Undoes the method makeMove after the king has moved to prevent moving to a Check
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece piece = (ChessPiece) board.removePiece(target); //takes moved piece
		piece.decreaseMoveCount();
		board.placePiece(source, piece); //puts it back in it's original place
		
		if (capturedPiece != null) {
			board.placePiece(target, capturedPiece); //Puts the captured piece back in place
			piecesOnBoard.add(capturedPiece);
			capturedPieces.remove(capturedPiece);
		}
	}
	
	//Assistant method for method performChessMove
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) { //checks if there's a piece on position
			throw new ChessException(
					"There is no piece on source position");
		}
		
		if (!board.getPiece(position).isThereAnyPossibleMove()) { //checks if this piece has any moves
			throw new ChessException(
					"There are no possible moves for chosen piece");
		}
		
		//checks if the piece's color is equal to the current player's
		if (currentPlayer != ((ChessPiece) board.getPiece(position)).getColor()) { //downcasts from Piece to ChessPiece
			throw new ChessException(
					"Chosen piece isn't yours");
			
		}
		
	}
	
	//Assistant method for method performChessMove
	private void validateTargetPosition(Position source, Position target) {
		
		//checks if the target position is a possible move for a specific piece
		if (!board.getPiece(source).possibleMove(target)) { 
			throw new ChessException(
					"Chosen piece can't move to target position");
		}
		
	}
	
	//Changes turns
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; //if statement to change colors
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) { //you can't be checkmated if you're not in check
			return false;
		}
		
		//Makes a list of pieces by filtering current player's color
		List<Piece> list = piecesOnBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		
		for (Piece p : list) {
			
			boolean[][] mat = p.possibleMoves(); //Creates a matrix of possible moves for every piece
			
			//runs through the entire matrix
			for (int i=0; i<mat.length; i++) {
				for (int j=0; j<mat.length; j++) {
					
					if (mat[i][j]) { //checks if it's a possible move
						
						Position source = ((ChessPiece) p).getChessPosition().toPosition(); //gets the original position
						Position target = new Position(i, j); //gets the possible position to stop check
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color); //assistant var to test check
						undoMove(source, target, capturedPiece); //undoes move made only to test checkMate
						
						//Tests if current player's king is still in check after making a move
						if (!testCheck) {
							return false;
							
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = findKing(color).getChessPosition().toPosition(); //Gets this specific king's position
		
		//Makes a list of opponent's pieces by filtering with opponent's color
		List<Piece> opponentPieces = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		//Goes through every single possible opponent move to test if it's possible to get to the king
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			
			//Sees if capturing the king is a possible move for all of the opponent's pieces
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) { 
				return true;
			}	
		}
		
		return false;
	}
	
	//Assistant method for method testCheck
	private ChessPiece findKing(Color color) {
			
		//Filters piece by color
		List<Piece> list = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) { //Goes through all pieces of the same color to find the King
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException(
				"There is no " + color + "king on the board");
	}
	
	//Assistant method for method testCheck
	private Color opponent(Color color) {
		return (color == color.WHITE) ? color.BLACK : color.WHITE;
	}
	
	//allows the initialSetup to set pieces up using chess coordinates
	private void placeNewPiece(char column, int row, ChessPiece piece) { 
		
		//Creates a new ChessPosition and then changes it to a matrix position
		ChessPosition chessPosition = new ChessPosition(column, row); 
		board.placePiece(chessPosition.toPosition(), piece);
		piecesOnBoard.add(piece);
		
	}
	
	private void initialSetup() { //Sets up the pieces for the game
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
		
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
}
