package course.oop.game;

import course.oop.player.Player;

public class Game_Ultimate {
	private int[][][][] board = new int[3][3][3][3];
	private int[][] boardStatus = new int[3][3];
	private Player[] players;
	private int[] toPlay = {-1,-1};
	
	//private boolean againstComputer = false;
	
	
	public Game_Ultimate(Player[] players) {
		this.players = players;
	
	}
	
	
	
	public boolean setSelection(int sq_row, int sq_col, int pos_row, int pos_col, int currentPlayer) {
		/*if ((toPlay[0] != -1 && toPlay[1] != -1) && (boardStatus[toPlay[0]][toPlay[1]] != 0)) {
			toPlay[0] = -1;
			toPlay[1] = -1;
		}*/
		
		if ((sq_row == toPlay[0] && sq_col == toPlay[1]) || (toPlay[0] == -1 && toPlay[1] == -1) ) {
			
			if (board[sq_row][sq_col][pos_row][pos_col] == 0 && boardStatus[sq_row][sq_col] == 0) {
				board[sq_row][sq_col][pos_row][pos_col] = currentPlayer;
				

				toPlay[0] = pos_row;
				toPlay[1] = pos_col;

				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	
		
		
	}
	
	public int[][][][] getBoard() {
		return board;
	}
	public int[] getToPlay() {
		return toPlay;
	}
	
	public int[][] getBoardStatus() {
		return boardStatus;
	}
	
	public int checkState() {
		
		if ((toPlay[0] != -1 && toPlay[1] != -1) && (boardStatus[toPlay[0]][toPlay[1]] != 0)) {
			toPlay[0] = -1;
			toPlay[1] = -1;
		}
		
		
		
		for (int g = 0; g < 3; g++) {
			for (int h = 0; h < 3; h++) {
				if (boardStatus[g][h] != 0) {
					continue;
				}
				
				for (int j = 0; j < 3; j++) {
					int start = board[g][h][j][0];
					if (start != 0) {
						boardStatus[g][h] = start;
						for (int i = 1; i < 3; i++) {
							if (board[g][h][j][i] != start) {
								boardStatus[g][h] = 0;
								break;
							}
						}
						if (boardStatus[g][h] != 0) {
							break;
						}
					}
				}
				
				if (boardStatus[g][h] != 0) {
					continue;
				}
				
				
				
				for (int j = 0; j < 3; j++) {
					int start = board[g][h][0][j];
					if (start != 0) {
						boardStatus[g][h] = start;
						for (int i = 1; i < 3; i++) {
							if (board[g][h][i][j] != start) {
								boardStatus[g][h] = 0;
								break;
							}
						}
						if (boardStatus[g][h] != 0) {
							break;
						}
					}
				}
				
				if (boardStatus[g][h] != 0) {
					continue;
				}
				
				{
					int start = board[g][h][0][0];
					if (start != 0) {
						boardStatus[g][h] = start;
						for (int i = 1; i < 3; i++) {
							if (board[g][h][i][i] != start) {
								boardStatus[g][h] = 0;
								break;
							}
						}
					}
				}
				
				if (boardStatus[g][h] != 0) {
					continue;
				}
				
				{
					int start = board[g][h][3-1][0];
					if (start != 0) {
						boardStatus[g][h] = start;
						for (int i = 1; i < 3; i++) {
							if (board[g][h][3-1-i][i] != start) {
								boardStatus[g][h] = 0;
								break;
							}
						}

					}
				}
				
				if (boardStatus[g][h] != 0) {
					continue;
				}
				
				{
					boardStatus[g][h] = 3;
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (board[g][h][i][j] == 0) {
								boardStatus[g][h] = 0;
								break;
							}
						}
						if (boardStatus[g][h] == 0) {
							break;
						}
					}
				}

			}
		}
		
		
		int gameState = 0;
		
		for (int j = 0; j < 3; j++) {
			int start = boardStatus[j][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
					if (boardStatus[j][i] != start) {
						gameState = 0;
						break;
					}
				}
				if (gameState != 0) {
					return gameState;
				}
			}
		}
		
		
		for (int j = 0; j < 3; j++) {
			int start = boardStatus[0][j];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
					if (boardStatus[i][j] != start) {
						gameState = 0;
						break;
					}
				}
				if (gameState != 0) {
					return gameState;
				}
			}
		}
		
		{
			int start = boardStatus[0][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
					if (boardStatus[i][i] != start) {
						gameState = 0;
						break;
					}
				}
				if (gameState != 0) {
					return gameState;
				}
			}
		}
		
		{
			int start = boardStatus[2][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
					if (boardStatus[2-i][i] != start) {
						gameState = 0;
						break;
					}
				}
				if (gameState != 0) {
					return gameState;
				}
			}
		}
		{
			gameState = 3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (boardStatus[i][j] == 0) {
						gameState = 0;
						return gameState;
					}
				}
			}
		}
		
		
		return gameState;
		
	}
	
	public String display() {
		String displayString = "";
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					displayString += " ";
					
				}
				else {
					displayString += players[board[i][j] - 1].getMarker();
				}
				if (j < n-1) {
					displayString += "|";
				}
			}
			displayString += "\n";
			if (i < n-1) {
				displayString += "-----\n";
			}
		}*/
		return displayString;
	}
	
}


