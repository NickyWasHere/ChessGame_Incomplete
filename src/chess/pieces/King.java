package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u265a"; //White King
			
		} else {
			return "\u2654"; //Black King
		}

	}

	@Override
	public boolean[][] possibleMoves() {
		
		//creates a boolean matrix with the same size as the board
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0); //auxiliary position to test
		
		//UP
		p.setValues(position.getRow()-1, position.getColumn());
		mat = possibleMove(p, mat);
		
		//DOWN
		p.setValues(position.getRow()+1, position.getColumn());
		mat = possibleMove(p, mat);
		
		//LEFT
		p.setValues(position.getRow(), position.getColumn()-1);
		mat = possibleMove(p, mat);
		
		//RIGHT
		p.setValues(position.getRow(), position.getColumn()+1);
		mat = possibleMove(p, mat);
		
		//UPLEFT
		p.setValues(position.getRow()-1, position.getColumn()-1);
		mat = possibleMove(p, mat);
		
		//UPRIGHT
		p.setValues(position.getRow()-1, position.getColumn()+1);
		mat = possibleMove(p, mat);
		
		//DOWNLEFT
		p.setValues(position.getRow()+1, position.getColumn()-1);
		mat = possibleMove(p, mat);
		
		//DOWNRIGHT
		p.setValues(position.getRow()+1, position.getColumn()+1);
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
