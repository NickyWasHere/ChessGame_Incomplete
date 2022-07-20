package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u265a"; //White
			
		} else {
			return "\u2654"; //Black
		}

	}
}
