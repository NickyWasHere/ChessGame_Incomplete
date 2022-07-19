package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	
	private Piece[][] pieces; //it's not a list because it needs to show the board as a matrix for the user

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //initiates a matrix of Pieces of null value and determined size
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece(int row, int column) { //gets a specific place on the board by giving it row and column
		return pieces[row][column];
	}
	
	//overloading the method above
	public Piece piece(Position position) { //gets a specific place on the board by giving it a position
		return pieces[position.getRow()][position.getColumn()];
	}
}
