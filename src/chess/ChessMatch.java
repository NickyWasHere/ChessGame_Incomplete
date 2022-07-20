package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8); //initiates a new board matrix with determined size
		
		//Calls the method initialSetup on the Constructor to place all the pieces when a new ChessMatch initiates
		initialSetup(); 
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
	
	//allows the initialSetup to set pieces up using chess coordinates
	private void placeNewPiece(char column, int row, ChessPiece piece) { 
		
		//Creates a new ChessPosition and then changes it to a matrix position
		ChessPosition chessPosition = new ChessPosition(column, row); 
		board.placePiece(chessPosition.toPosition(), piece);
		
	}
	
	private void initialSetup() { //Sets up the pieces for the game
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('d', 3, new King(board, Color.BLACK));
	}
}
