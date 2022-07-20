package application;

import chess.ChessPiece;

public class UI {

	//Shows the board to the user by receiving the matrix of pieces
	public static void printBoard(ChessPiece[][] pieces) {
		
		//Travels the entirety of the board to print every blank space or piece
		for (int i=0; i<pieces.length; i++) {
			
			System.out.print((8 - i) + "  "); //shows the numbers
			for (int j=0; j<pieces.length; j++) { //.length only works if the board is square
				printPiece(pieces[i][j]);
			}
			
			System.out.println(); //breaks the line for the next row
			System.out.println();
		}
		
		System.out.println("   a  b  c  d  e  f  g  h"); //shows the letters
	}
	
	private static void printPiece(ChessPiece piece) {
		
		if (piece == null) { //Tests to see if there is a piece
			System.out.print("-"); //blank space
			
		} else {
			System.out.print(piece); //piece
			
		}
		
		System.out.print("  ");
		
	}
}
