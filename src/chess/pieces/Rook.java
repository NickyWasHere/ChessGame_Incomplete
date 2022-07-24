package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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

	@Override
	public boolean[][] possibleMoves() {
		
		//creates a boolean matrix with the same size as the board
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0); //auxiliary position to test
		
		//UP
		p.setValues(position.getRow()-1, position.getColumn());
		mat = possibleMove(p, mat, "up");
		
		//DOWN
		p.setValues(position.getRow()+1, position.getColumn());
		mat = possibleMove(p, mat, "down");
		
		//LEFT
		p.setValues(position.getRow(), position.getColumn()-1);
		mat = possibleMove(p, mat, "left");
		
		//RIGHT
		p.setValues(position.getRow(), position.getColumn()+1);
		mat = possibleMove(p, mat, "right");
		
		return mat;
		
	}
	
	private boolean[][] possibleMove(Position p, boolean[][] mat, String move) {
		
		//checks if position exists and there isn't a piece
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { 
			
			mat[p.getRow()][p.getColumn()] = true; //marks this position as a possible move
			
			switch(move) {
			case "up":
				p.setValues(p.getRow()-1, p.getColumn()); //changes position p to continue in a direction
				break;
				
			case "down":
				p.setValues(p.getRow()+1, p.getColumn());
				break;
				
			case "left":
				p.setValues(p.getRow(), p.getColumn()-1);
				break;
				
			case "right":
				p.setValues(p.getRow(), p.getColumn()+1);
				break;
			
			}
			
		}
		
		//checks if position exists and if the piece found when the while stopped is a opponent piece or not
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;

	}
}
