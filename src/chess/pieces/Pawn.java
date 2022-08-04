package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u265f"; //White Pawn
			
		} else {
			return "\u2659"; //Black Pawn
		}
	}
	
	@Override
	public boolean[][] possibleMoves() {
		
		//creates a boolean matrix with the same size as the board
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0); //auxiliary position to test
		
		//White pawn
		if (color == color.WHITE) {
			
			//UP
			p.setValues(position.getRow()-1, position.getColumn());
			mat = possibleMove(p, mat);
			
			Position p2 = new Position(position.getRow()-1, position.getColumn()); 
			
			//DOUBLEUP
			p.setValues(position.getRow()-2, position.getColumn());
			mat = possibleMove(p, mat, p2, getMoveCount());
			
			//UPLEFT - Taking another piece
			p.setValues(position.getRow()-1, position.getColumn()-1);
			mat = possibleTake(p, mat);
			
			//UPRIGHT - Taking another piece
			p.setValues(position.getRow()-1, position.getColumn()+1);
			mat = possibleTake(p, mat);
			
		} else if (color == Color.BLACK) {
			
			//DOWN
			p.setValues(position.getRow()+1, position.getColumn());
			mat = possibleMove(p, mat);
			
			Position p2 = new Position(position.getRow()+1, position.getColumn()); 
			
			//DOUBLEDOWN
			p.setValues(position.getRow()+2, position.getColumn());
			mat = possibleMove(p, mat, p2, getMoveCount());
			
			//DOWNLEFT - Taking another piece
			p.setValues(position.getRow()+1, position.getColumn()-1);
			mat = possibleTake(p, mat);
			
			//DOWNRIGHT - Taking another piece
			p.setValues(position.getRow()+1, position.getColumn()+1);
			mat = possibleTake(p, mat);
			
		}
		
		return mat;
	}
	
	//Assistant method for possibleMoves
	private boolean[][] possibleMove(Position p, boolean[][] mat) {
		if (validPosition(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
	
	//Overloading the method possibleMove
	private boolean[][] possibleMove(Position p, boolean[][] mat, Position p2, int moveCount) {
		if (validPosition(p) & validPosition(p2) & moveCount == 0) {
			mat[p.getRow()][p.getColumn()] = true;
		}
			
		return mat;	
	}
	
	//Assistant method for possibleMove
	private boolean validPosition(Position p) {
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			return true;
		} else {
			return false;
		}
	}
	
	//Assistant method for possibleMoves
	private boolean[][] possibleTake(Position p, boolean[][] mat) {
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

}
