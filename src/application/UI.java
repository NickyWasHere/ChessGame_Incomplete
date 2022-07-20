package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
	
	//Code for clearing the screen taken from:
	//https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
	}

	//Reads user input on the chess position
	public static ChessPosition readChessPosition(Scanner sc) {
		
		try { //Try and catch exceptions
			String pos = sc.nextLine();
			char column = pos.charAt(0);
			int row = Integer.parseInt(pos.substring(1)); //cuts the String on position 1 and converts it to int
			return new ChessPosition(column, row);
		
		} catch (RuntimeException e) { //any exception that occurs will go here
			throw new InputMismatchException(
					"Please input a value between a1 and h8");
			
		}
	}
	
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
