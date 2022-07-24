package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
	
	//actual pieces that will be used on the board

	protected Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPiece(position); //downcasts from Piece to ChessPiece
		return p!= null && p.getColor() != color; //checks if there is a piece and if it's a different color
	}
}
