package course.oop.computer;
import java.util.*;
public class Computer {
	public int[] getNextMove(int computerNumber, int[][] board) {
		
		int[] nextMove = new int[2];
		ArrayList<Integer> positions = new ArrayList<Integer>();
		Random rand = new Random(); 
		for (int i = 0; i < board.length*board.length; i++) {
			if (board[i/board.length][i%board.length] == 0) {
				positions.add(i);
			}
		}
		int positionIndex = rand.nextInt(positions.size());
		int positionNumber = positions.get(positionIndex);
		nextMove[0] = positionNumber/board.length;
		nextMove[1] = positionNumber%board.length;
		return nextMove;
	}
	public int[][] getNextMove(int computerNumber, int[][][][] board, int[] toPlay, int[][] boardStatus) {
		Random rand = new Random();
		int boardRow;
		int boardCol;
		if (toPlay[0] == -1 && toPlay[1] == -1) {
			ArrayList<Integer> boardPositions = new ArrayList<Integer>();
			
			for (int i = 0; i < 9; i++) {
				if (boardStatus[i/3][i%3] == 0) {
					boardPositions.add(i);
				}
				
			}
			int boardPositionIndex = rand.nextInt(boardPositions.size());
			int boardPositionNumber = boardPositions.get(boardPositionIndex);
			boardRow = boardPositionNumber/3;
			boardCol = boardPositionNumber%3;
			
			
		}
		else {
			boardRow = toPlay[0];
			boardCol = toPlay[1];
		}
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			if (board[boardRow][boardCol][i/3][i%3] == 0) {
				positions.add(i);
			}
			
		}
		
		int positionIndex = rand.nextInt(positions.size());
		int positionNumber = positions.get(positionIndex);
		
		int[][] returnArray = new int[2][2];
		returnArray[0][0] = boardRow;
		returnArray[0][1] = boardCol;
		returnArray[1][0] = positionNumber/3;
		returnArray[1][1] = positionNumber%3;
		return returnArray;
		

		
	}
	
	public int[] getNextMove(int computerNumber, int[][] board, boolean difficultyLevel) {
		MiniMax smartComputer = new MiniMax(board.clone(), computerNumber, difficultyLevel);
		int[] nextMove = smartComputer.move();
		
		return nextMove;
	}
}
