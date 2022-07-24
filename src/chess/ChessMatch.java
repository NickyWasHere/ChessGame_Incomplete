package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	private int turn;
	private Color currentPlayer;

	public ChessMatch() {
		board = new Board(8, 8); //initiates a new board matrix with determined size
		turn = 1;
		currentPlayer = Color.WHITE;
		
		//Calls the method initialSetup on the Constructor to place all the pieces when a new ChessMatch initiates
		initialSetup(); 
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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
		nextTurn(); //switches turns
		return (ChessPiece) capturedPiece; //downcasts from Piece to ChessPiece
		
	}
	
	//Assistant method for method performChessMove
	private Piece makeMove(Position source, Position target) {
		Piece piece = board.removePiece(source); //removes piece from source
		Piece capturedPiece = board.removePiece(target); //removes captured piece
		board.placePiece(target, piece); //places pieces on target position
		return capturedPiece;
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
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; //if statement to change colors
	}
	
	//allows the initialSetup to set pieces up using chess coordinates
	private void placeNewPiece(char column, int row, ChessPiece piece) { 
		
		//Creates a new ChessPosition and then changes it to a matrix position
		ChessPosition chessPosition = new ChessPosition(column, row); 
		board.placePiece(chessPosition.toPosition(), piece);
		
	}
	
	private void initialSetup() { //Sets up the pieces for the game
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
