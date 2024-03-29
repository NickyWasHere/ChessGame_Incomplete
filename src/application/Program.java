package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) { //while checkmate is false
			try {
				
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				
				//Choosing source
				System.out.print("Piece to move: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				//Showing possible moves
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves); //Board showing possible moves
					
				//Choosing target
				System.out.print("Target position: ");
				ChessPosition target = UI.readChessPosition(sc);
				System.out.println();
					
				//Managing captured pieces
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
			} catch (ChessException e) { //Handling exceptions
				System.out.println(e.getMessage());
				System.out.println("Press ENTER to continue");
				sc.nextLine();
				
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Press ENTER to continue");
				sc.nextLine();
				
			}
		}
		
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		
	}
}
