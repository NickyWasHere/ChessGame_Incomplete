package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u265e"; //White Knight
			
		} else {
			return "\u2658"; //Black Knight
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		
		//creates a boolean matrix with the same size as the board
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
				
		Position p = new Position(0, 0); //auxiliary position to test
		
		//UPLEFT
		p.setValues(position.getRow()-2, position.getColumn()-1);
		mat = possibleMove(p, mat);
		
		//UPRIGHT
		p.setValues(position.getRow()-2, position.getColumn()+1);
		mat = possibleMove(p, mat);
		
		//DOWNLEFT
		p.setValues(position.getRow()+2, position.getColumn()-1);
		mat = possibleMove(p, mat);
		
		//DOWNRIGHT
		p.setValues(position.getRow()+2, position.getColumn()+1);
		mat = possibleMove(p, mat);
		
		//LEFTUP
		p.setValues(position.getRow()-1, position.getColumn()-2);
		mat = possibleMove(p, mat);
		
		//LEFTDOWN
		p.setValues(position.getRow()+1, position.getColumn()-2);
		mat = possibleMove(p, mat);
		
		//RIGHTUP
		p.setValues(position.getRow()-1, position.getColumn()+2);
		mat = possibleMove(p, mat);
		
		//RIGHTDOWN
		p.setValues(position.getRow()+1, position.getColumn()+2);
		mat = possibleMove(p, mat);
		
		return mat;
	}
	
	//Assistant method for method possibleMoves
	private boolean[][] possibleMove(Position p, boolean[][] mat) {
		if (getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || canMove(p))) {
			mat[p.getRow()][p.getColumn()] = true;
				
		}
			
		return mat;
	}
	
	//Assistant method for method above
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPiece(position); //Piece on target position
		return p.getColor() != getColor(); //checks if king can go to target by capturing a piece
	}

}
