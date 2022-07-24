package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
	
	//Background color taken from:
	//https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	
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
	
	//Main method that shows entire UI
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		
		System.out.println("Turn: " + chessMatch.getTurn());
		System.out.println("Waiting for player " + chessMatch.getCurrentPlayer());
	}
	
	//Shows the board to the user by receiving the matrix of pieces
	public static void printBoard(ChessPiece[][] pieces) {
		
		//Travels the entirety of the board to print every blank space or piece
		for (int i=0; i<pieces.length; i++) {
			
			System.out.print((8 - i) + "  "); //shows the numbers
			for (int j=0; j<pieces.length; j++) { //.length only works if the board is square
				printPiece(pieces[i][j], false);
			}
			
			System.out.println(); //breaks the line for the next row
			System.out.println();
		}
		
		System.out.println("   a  b  c  d  e  f  g  h"); //shows the letters
	}
	
	//Overloading the method above
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		
		//Travels the entirety of the board to print every blank space or piece
		for (int i=0; i<pieces.length; i++) {
			
			System.out.print((8 - i) + "  "); //shows the numbers
			for (int j=0; j<pieces.length; j++) { //.length only works if the board is square
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			
			System.out.println(); //breaks the line for the next row
			System.out.println();
		}
		
		System.out.println("   a  b  c  d  e  f  g  h"); //shows the letters
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		
		if (background) { //Will change the background color of possible moves to green
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		if (piece == null) { //Tests to see if there is a piece
			System.out.print("-" + ANSI_RESET); //blank space
			
		} else {
			System.out.print(piece + ANSI_RESET); //piece
			
		}
		
		System.out.print("  ");
		
	}
}
