package boardgame;

public abstract class Piece {
	
	//Piece and ChessPiece are basically the same 
	//and are only separated into 2 different classes for good practices

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	
	
}
