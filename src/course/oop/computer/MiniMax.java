package course.oop.computer;
import java.util.*;

public class MiniMax {
	int[][] board;
	int computerNum;
	boolean difficult = true;
	//int n;
	public MiniMax(int[][] board, int computerNum, boolean difficult) {
		this.board = board;
		this.computerNum = computerNum;
		this.difficult = difficult;
		//n = board.length;
	}
	

	public int[] move() {
		int[] result = minimax(5, computerNum); // depth, max turn
	    return new int[] {result[1], result[2]};   // row, col
	}
	
	private int[] minimax(int depth, int playerNum) {
	  // Generate possible next moves in a List of int[2] of {row, col}.
      List<int[]> nextMoves = generateMoves();
 
      // mySeed is maximizing; while oppSeed is minimizing
      int bestScore;
      if (difficult)  {
    	  bestScore = (playerNum == computerNum) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }
      else {
    	  bestScore = (playerNum == (3-computerNum)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }

      int currentScore;
      int bestRow = -1;
      int bestCol = -1;
 
      if (nextMoves.isEmpty() || depth == 0) {
         // Gameover or depth reached, evaluate score
         bestScore = evaluate();
      } 
      else {
         for (int[] move : nextMoves) {
            // Try this move for the current "player"
            board[move[0]][move[1]] = playerNum;
            if (playerNum == computerNum) {  // mySeed (computer) is maximizing player
               currentScore = minimax(depth - 1, 3-computerNum)[0];
               
               if (difficult) {
	               if (currentScore > bestScore) {
	                  bestScore = currentScore;
	                  bestRow = move[0];
	                  bestCol = move[1];
	               }
               }
               else {
            	   if (currentScore < bestScore) {
 	                  bestScore = currentScore;
 	                  bestRow = move[0];
 	                  bestCol = move[1];
 	               }
               }
            } 
            else {  // oppSeed is minimizing player
               currentScore = minimax(depth - 1, computerNum)[0];
               if (difficult) {
	               if (currentScore < bestScore) {
	                  bestScore = currentScore;
	                  bestRow = move[0];
	                  bestCol = move[1];
	               }
               }
               else {
            	   if (currentScore > bestScore) {
 	                  bestScore = currentScore;
 	                  bestRow = move[0];
 	                  bestCol = move[1];
 	               }
               }
            }
            // Undo move
            board[move[0]][move[1]] = 0;
         }
      }
      //return new int[] {bestScore, bestRow, bestCol};
      return new int[] {bestScore, bestRow, bestCol};
    }
	
	private List<int[]> generateMoves() {
      List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List
 
      // If gameover, i.e., no next move
      if (hasWon(computerNum) || hasWon(3-computerNum)) {
         return nextMoves;   // return empty list
      }
 
      // Search for empty cells and add to the List
      for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
            if (board[row][col] == 0) {
               nextMoves.add(new int[] {row, col});
            }
         }
      }
      return nextMoves;
   }
	
	
	private boolean hasWon(int numPlayer) {
	      int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
	      for (int row = 0; row < 3; row++) {
	         for (int col = 0; col < 3; col++) {
	            if (board[row][col] == numPlayer) {
	               pattern |= (1 << (row * 3 + col));
	            }
	         }
	      }
	      for (int winningPattern : winningPatterns) {
	         if ((pattern & winningPattern) == winningPattern) return true;
	      }
	      return false;
	   
	}

	private int[] winningPatterns = {
	        0b111000000, 0b000111000, 0b000000111, // rows
	        0b100100100, 0b010010010, 0b001001001, // cols
	        0b100010001, 0b001010100               // diagonals
	};
	
	private int evaluate() {
	      int score = 0;
	      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
	      score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
	      score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
	      score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
	      score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
	      score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
	      score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
	      score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
	      score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
	      return score;
	}
	
	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
	      int score = 0;
	 
	      // First cell
	      if (board[row1][col1] == computerNum) {
	         score = 1;
	      } else if (board[row1][col1] == 3-computerNum) {
	         score = -1;
	      }
	 
	      // Second cell
	      if (board[row2][col2]== computerNum) {
	         if (score == 1) {   // cell1 is mySeed
	            score = 10;
	         } else if (score == -1) {  // cell1 is oppSeed
	            return 0;
	         } else {  // cell1 is empty
	            score = 1;
	         }
	      } else if (board[row2][col2] == 3-computerNum) {
	         if (score == -1) { // cell1 is oppSeed
	            score = -10;
	         } else if (score == 1) { // cell1 is mySeed
	            return 0;
	         } else {  // cell1 is empty
	            score = -1;
	         }
	      }
	 
	      // Third cell
	      if (board[row3][col3] == computerNum) {
	         if (score > 0) {  // cell1 and/or cell2 is mySeed
	            score *= 10;
	         } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
	            return 0;
	         } else {  // cell1 and cell2 are empty
	            score = 1;
	         }
	      } else if (board[row3][col3] == 3-computerNum) {
	         if (score < 0) {  // cell1 and/or cell2 is oppSeed
	            score *= 10;
	         } else if (score > 1) {  // cell1 and/or cell2 is mySeed
	            return 0;
	         } else {  // cell1 and cell2 are empty
	            score = -1;
	         }
	      }
	      return score;
   }
}
