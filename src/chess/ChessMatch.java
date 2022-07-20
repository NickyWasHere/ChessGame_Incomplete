package chess;

import boardgame.Board;
import boardgame.Position;
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
				mat[i][j] = (ChessPiece) board.piece(i, j); //Downcasts from Piece to ChessPiece
			}
		}
		
		return mat;
	}
	
	private void initialSetup() { //Sets up the pieces for the game
		board.placePiece(new Position(2, 1), new Rook(board, Color.WHITE));
		board.placePiece(new Position(5, 3), new King(board, Color.BLACK));
	}
}
