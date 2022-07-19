package chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8); //initiates a new board matrix with determined size
	}
	
	public ChessPiece[][] getPieces() { //transforms the spaces on the board from Piece to ChessPiece
		
		//creates a matrix of Chess Pieces with the same size as the board
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		//travels the entirety of the Pieces matrix
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				
				//Changes every Piece to Chess Piece
				mat[i][j] = (ChessPiece) board.piece(i, j); //Downcasts from Piece to ChessPiece
			}
		}
		
		return mat;
	}
}
