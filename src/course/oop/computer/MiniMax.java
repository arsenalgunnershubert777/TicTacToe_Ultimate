package course.oop.computer;
import java.util.*;

public class MiniMax {
	int[][] board;
	int computerNum;
	boolean difficult = true;
	int n;
	private boolean[][] winningPatternArrays;
	public MiniMax(int[][] board, int computerNum, boolean difficult) {
		this.board = board;
		this.computerNum = computerNum;
		this.difficult = difficult;
		n = board.length;
		setUpWinningArrays();
	}
	

	public int[] move() {
		int[] result;
		int depth = 7;

		if (n >= 5) {
			depth = 5 + 5 - n;
		}
		if (depth < 1) {
			depth = 1;
		}
		if (difficult) {
			result = minimax(depth, computerNum, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		else {
			result = minimax(depth, computerNum, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
	    return new int[] {result[1], result[2]}; 
	}
	
	private int[] minimax(int depth, int playerNum, int alpha, int beta) {

      List<int[]> nextMoves = generateMoves();
 


      int currentScore = 0;
      int bestRow = -1;
      int bestCol = -1;
 
      if (nextMoves.isEmpty() || depth == 0) {

         currentScore = evaluate();
         return new int[] {currentScore, bestRow, bestCol};
      } 
      else {
         for (int[] move : nextMoves) {

            board[move[0]][move[1]] = playerNum;
            if (playerNum == computerNum) {  
               currentScore = minimax(depth - 1, 3-computerNum, alpha, beta)[0];
               
               if (difficult) {
	               if (currentScore > alpha) {
	                  alpha = currentScore;
	                  bestRow = move[0];
	                  bestCol = move[1];
	               }
               }
               else {
            	   if (currentScore < alpha) {
 	                  alpha = currentScore;
 	                  bestRow = move[0];
 	                  bestCol = move[1];
 	               }
               }
            } 
            else {  
               currentScore = minimax(depth - 1, computerNum, alpha, beta)[0];
               if (difficult) {
	               if (currentScore < beta) {
	                  beta = currentScore;
	                  bestRow = move[0];
	                  bestCol = move[1];
	               }
               }
               else {
            	   if (currentScore > beta) {
 	                  beta = currentScore;
 	                  bestRow = move[0];
 	                  bestCol = move[1];
 	               }
               }
            }
            // Undo move
            board[move[0]][move[1]] = 0;
            if (difficult) {
	            if (alpha >= beta) {
	            	break;
	            }
            }
            else {
            	if (beta >= alpha) {
	            	break;
	            }
            }
         }
      }


      return new int[] {(playerNum == computerNum) ? alpha: beta, bestRow, bestCol};
    }
	
	private List<int[]> generateMoves() {
      List<int[]> nextMoves = new ArrayList<int[]>(); 
      if (hasWon(computerNum) || hasWon(3-computerNum)) {
         return nextMoves; 
      }
 

      for (int row = 0; row < n; row++) {
         for (int col = 0; col < n; col++) {
            if (board[row][col] == 0) {
               nextMoves.add(new int[] {row, col});
            }
         }
      }
      return nextMoves;
   }
	
	
	private boolean hasWon(int numPlayer) {
	      
	      
	      boolean[] patternArray = new boolean[n*n];
	      for (int row = 0; row < n; row++) {
	         for (int col = 0; col < n; col++) {
	            if (board[row][col] == numPlayer) {
	               
	               patternArray[row*n+col] = true;
	            }
	         }
	      }
	      boolean foundWinning = true;
	      for (boolean[] winningArrays : winningPatternArrays) {
	    	  foundWinning = true;
	    	  for (int i =0; i < n*n; i++) {
	    		  if (winningArrays[i] == true) {
	    			  if (patternArray[i] != true) {
	    				  foundWinning = false;
	    				  break;
	    			  }
	    		  }
	    	  }
	    	  if (foundWinning) {
	    		  return true;
	    	  }
	    	  
	      }
	      return false;
	      

	   
	}
	
	private void setUpWinningArrays() {
		winningPatternArrays = new boolean[n*2 +2][n*n];
		int count = 0;
		for (int i = 0; i < n;i++) {
			for (int j = 0; j < n*n; j++) {
				if (j/n == i) {
					winningPatternArrays[count][j] = true;
				}
			
			}
			count++;
		}
		for (int i = 0; i < n;i++) {
			for (int j = 0; j < n*n; j++) {
				if (j%n == i) {
					winningPatternArrays[count][j] = true;
				}
			
			}
			count++;
		}
		
		for (int i = 0; i < n*n; i++) {
			if (i/n == i%n) {
				winningPatternArrays[count][i] = true;
			}
		}
		count++;
		for (int i = 0; i < n*n; i++) {
			if (i/n == n-1-(i%n)) {
				winningPatternArrays[count][i] = true;
			}
		}

	}


	
	private int evaluate() {
	      int score = 0;

	      int[][] line = new int[n][2];
	      
	      for (int k = 0; k < n; k++) {
		      for (int i = 0; i < n; i++) {
		    	  line[i][0] = k;
		    	  line[i][1] = i;
		      }
		      score += evaluateLine_Generic(line);
	      }
	      
	      for (int k = 0; k < n; k++) {
		      for (int i = 0; i < n; i++) {
		    	  line[i][0] = i;
		    	  line[i][1] = k;
		      }
		      score += evaluateLine_Generic(line);
	      }
	      
	      for (int i = 0; i < n; i++) {
	    	  line[i][0] = i;
	    	  line[i][1] = i;
	      }
	      score += evaluateLine_Generic(line);
	      
	      
	      for (int i = 0; i < n; i++) {
	    	  line[i][0] = i;
	    	  line[i][1] = n-1-i;
	      }
	      score += evaluateLine_Generic(line);
	      
	      return score;
	}
	
	
	
	private int evaluateLine_Generic(int[][] row_col) {
	      int score = 0;
	

	 
	      // First cell
	      if (board[row_col[0][0]][row_col[0][1]] == computerNum) {
	         score = 1;
	      } else if (board[row_col[0][0]][row_col[0][1]] == 3-computerNum) {
	         score = -1;
	      }
	      
	      for (int i = 1; i < n; i++) {
	    	  if (board[row_col[i][0]][row_col[i][1]]== computerNum) {
	 	         if (score > 0) {   
	 	            score *= 10;
	 	         } else if (score < 0) {  
	 	            return 0;
	 	         } else {  
	 	            score = 1;
	 	         }
	 	      } else if (board[row_col[i][0]][row_col[i][1]]==3-computerNum) {
	 	         if (score < 0) { 
	 	            score *= 10;
	 	         } else if (score > 0) { 
	 	            return 0;
	 	         } else {  
	 	            score = -1;
	 	         }
	 	      }
	 	 	  
	    	  
	      }
	      
	      
	      
	      return score;
	      
	      
 }
}
