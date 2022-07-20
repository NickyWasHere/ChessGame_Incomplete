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

}
