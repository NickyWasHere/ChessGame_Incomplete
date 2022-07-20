package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	
	private Piece[][] pieces; //it's not a list because it needs to show the board as a matrix for the user

	public Board(int rows, int columns) {
		
		//prevents creating a board with less than 1 row and 1 column
		if (rows <1 || columns<1) {
			throw new BoardException(
					"Error creating board: there must be at least 1 row and 1 column");
			
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //initiates a matrix of Pieces of null value and determined size
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece getPiece(int row, int column) { //gets a specific place on the board by giving it row and column
		
		//prevents getting a piece in a position that doesn't exists
		if (!positionExists(row, column)) {
			throw new BoardException(
					"Position not on the board");
		}
		
		return pieces[row][column];
	}
	
	//overloading the method above
	public Piece getPiece(Position position) { //gets a specific place on the board by giving it a position
		
		//prevents getting a piece in a position that doesn't exists
				if (!positionExists(position)) {
					throw new BoardException(
							"Position not on the board");
				}
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Position position, Piece piece) {
		
		//prevents placing a piece where there's already a piece in place
		if (thereIsAPiece(position)) {
			throw new BoardException(
					"There is already a piece on position " + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece; //Puts the piece in a specific place on the matrix
		piece.position = position; //changes the position of said piece from null to a specific position
	}
	
	public Piece removePiece(Position position) {
		
		//prevents removing a piece from a position that doesn't exists
		if (!positionExists(position)) {
			throw new BoardException(
					"Position not on the board");
		}
		
		if (getPiece(position) == null) { //checks if there's a piece in this position
			return null;
		}
		
		Piece aux = getPiece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null; //Removes piece from Piece matrix
		return aux; //return the piece that was removed
		
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//Assistant method for the method above (also overloading the method)
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column>= 0 && column < columns; //Works as a if returning true or false
	}
	
	public boolean thereIsAPiece(Position position) {
		
		//checks if the position is valid
		if (!positionExists(position)) {
			throw new BoardException(
					"Position not on the board");
		}
		
		return getPiece(position) != null;
	}
}
