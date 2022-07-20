package chess;

import boardgame.Position;

public class ChessPosition {
	
	//Transforms coordinates from the board matrix (5, 3) to chess coordinates (d3) and vice versa

	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		
		//prevents entering chess coordinates that don't exist
		if (column < 'a' || column > 'h' || row < 1 || row > 8) { //works with the alphabet as well
			throw new ChessException(
					"Error initiating ChessPosition: valid values are from a1 to h8 only");
		}
			
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		
		/* matrix position (number, number) -> chess position (8 - row, column - 'a')
		 * (0, 0) -> 8 - 8 (row) , 0
		 * (3, 0) -> 8 - 5 (row) , 0
		 * (0, 3) -> 0 , 100 (unicode d column) - 97 (unicode a)
		 * (4, 2) -> 8 - 4 (row) , 99 (unicode c column) - 97 (unicode a)
		 * (5, 7) -> 8 - 3 (row) , 104 (unicode h column) - 97 (unicode a)
		 */
		
		//Converts chess position to matrix position
		return new Position(8 - row, column - 'a'); //when subtracting char from char, it uses that char's unicode 
	}
	
	protected static ChessPosition fromPosition(Position position) {
		
		/* chess position (charNumber) -> matrix position (char) ('a' - column), 8 - row 
		 * (a8) -> 97 (unicode a) - 97 (column) , 8 - 8 (row)
		 * (b7) -> 97 (unicode a) - 98 (column) , 8 - 7 (row)
		 */
		
		//Converts matrix position to chess position 6, 5
		return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
	}

	@Override
	public String toString() {
		return "" //"" makes the compiler concatenate column and row next to each other as a string
				+ column 
				+ row;
	}
}
