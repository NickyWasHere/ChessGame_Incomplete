package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u265c"; //White King
			
		} else {
			return "\u2656"; //Black King
		}

	}

	@Override
	public boolean[][] possibleMoves() {
		
		//creates a boolean matrix with the same size as the board
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}

}
