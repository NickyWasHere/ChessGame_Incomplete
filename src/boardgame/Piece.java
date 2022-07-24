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
	
	//Returns a matrix with the same size as the board checking if every position can (true) or can't (false) be moved to
	public abstract boolean[][] possibleMoves(); //Abstract since Piece is a generic Class with no actual moves
	
	//Checks if a specific position can be moved to using the Method above
	//Hook Method -> a method that uses the logic of an abstract method
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//Checks if there are any moves that can be made at all
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat.length; j++) { //only works if the board is square
				if (mat[i][j]) { //same thing as mat[i][j]==true
					return true;
					
				}
			}
		}
		
		return false; //if there are any trues on mat
	}
	
}
