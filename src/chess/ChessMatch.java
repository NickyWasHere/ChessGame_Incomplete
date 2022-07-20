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
