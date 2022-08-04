package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	
	//Background color taken from:
	//https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
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
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		String showCheck = "";
		printBoard(chessMatch.getPieces());
		printCapturedPiece(captured);
		System.out.println();
		
		if (chessMatch.getCheck()) {
			showCheck = "CHECK";
			
		}
		
		if (chessMatch.getCheckMate()) {
			System.out.println(ANSI_YELLOW + "CHECKMATE");
			System.out.println(ANSI_RESET + "Winner: " + chessMatch.getCurrentPlayer());
			
		} else {
			System.out.println("Turn " + chessMatch.getTurn() + " " + ANSI_YELLOW + showCheck);
			
			System.out.println(ANSI_RESET);
			System.out.println(chessMatch.getCurrentPlayer() + "'s turn");
			
		}
		
	}
	
	//Shows the board to the user by receiving the matrix of pieces
	private static void printBoard(ChessPiece[][] pieces) {
		
		//Travels the entirety of the board to print every blank space or piece
		for (int i=0; i<pieces.length; i++) {
			
			System.out.print(ANSI_YELLOW + (8 - i) + ANSI_RESET + "    "); //shows the numbers
			for (int j=0; j<pieces.length; j++) { //.length only works if the board is square
				printPiece(pieces[i][j], false);
			}
			
			System.out.println(); //breaks the line for the next row
			System.out.println();
		}
		
		System.out.println(ANSI_YELLOW + "     a     b     c     d     e     f     g     h"); //shows the letters
		System.out.println(ANSI_RESET);
	}
	
	//Overloading the method above
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		
		//Travels the entirety of the board to print every blank space or piece
		for (int i=0; i<pieces.length; i++) {
			
			System.out.print(ANSI_YELLOW + (8 - i) + ANSI_RESET + "    "); //shows the numbers
			for (int j=0; j<pieces.length; j++) { //.length only works if the board is square
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			
			System.out.println(); //breaks the line for the next row
			System.out.println();
		}
		
		System.out.println(ANSI_YELLOW + "     a     b     c     d     e     f     g     h"); //shows the letters
		System.out.println(ANSI_RESET);
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
		
		System.out.print("     ");
		
	}
	
	private static void printCapturedPiece(List<ChessPiece> captured) {
		
		//Filtering White and Black captured pieces using Lambda expressions
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor()==Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor()==Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured pieces from");
		System.out.print("WHITE: ");
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print("BLACK: ");
		System.out.println(Arrays.toString(black.toArray()));
	}
}
