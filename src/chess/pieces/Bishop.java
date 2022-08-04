package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public String toString() {
		if (color == Color.WHITE) {
			return "\u265d"; //White Bishop
			
		} else {
			return "\u2657"; //Black Bishop
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		
		//creates a boolean matrix with the same size as the board
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
				
		Position p = new Position(0, 0); //auxiliary position to test
				
		//UPLEFT
		p.setValues(position.getRow()-1, position.getColumn()-1);
		mat = possibleMove(p, mat, "upleft");
				
		//UPRIGHT
		p.setValues(position.getRow()-1, position.getColumn()+1);
		mat = possibleMove(p, mat, "upright");
				
		//DOWNLEFT
		p.setValues(position.getRow()+1, position.getColumn()-1);
		mat = possibleMove(p, mat, "downleft");
				
		//DOWNRIGHT
		p.setValues(position.getRow()+1, position.getColumn()+1);
		mat = possibleMove(p, mat, "downright");
				
		return mat;
	}
	
	//Assistant method for method above
	private boolean[][] possibleMove(Position p, boolean[][] mat, String move) {
		
		//checks if position exists and there isn't a piece
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { 
				
			mat[p.getRow()][p.getColumn()] = true; //marks this position as a possible move
					
			switch(move) {
				case "upleft":
				p.setValues(p.getRow()-1, p.getColumn()-1); //changes position p to continue in a direction
				break;
							
				case "upright":
				p.setValues(p.getRow()-1, p.getColumn()+1);
				break;
							
				case "downleft":
				p.setValues(p.getRow()+1, p.getColumn()-1);
				break;
							
				case "downright":
				p.setValues(p.getRow()+1, p.getColumn()+1);
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
