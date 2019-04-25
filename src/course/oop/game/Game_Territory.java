package course.oop.game;

import java.util.ArrayList;
import java.util.Random;

import course.oop.player.Player;

public class Game_Territory {
	private int[][] board = new int[5][5];
	private Player[] players;
	private int random = 0;
	private int randomCount = 0;
	//private boolean againstComputer = false;
	
	
	public Game_Territory(Player[] players, int random) {
		this.players = players;
		this.random = random;
		setSpecialTiles();
	
	}
	
	private void setSpecialTiles() {
		Random rand = new Random();
		for (int j = 0; j < 2; j++) {
			ArrayList<Integer> positions = new ArrayList<Integer>();
			for (int i = 0; i < 25; i++) {
				if (board[i/5][i%5] == 0) {
					positions.add(i);
				}
			}
			int positionIndex = rand.nextInt(positions.size());
			int positionNumber = positions.get(positionIndex);
			board[positionNumber/5][positionNumber%5] = -2; //-2 refers to neutral
		}
		
		for (int j = 0; j < 2; j++) {
			ArrayList<Integer> positions = new ArrayList<Integer>();
			for (int i = 0; i < 25; i++) {
				if (board[i/5][i%5] == 0) {
					positions.add(i);
				}
			}
			int positionIndex = rand.nextInt(positions.size());
			int positionNumber = positions.get(positionIndex);
			board[positionNumber/5][positionNumber%5] = -3; //-3 refers to not available
		}
		
		
	}
	
	
	
	public boolean setSelection(int row, int col, int currentPlayer) {
		
		
		
		if (board[row][col] == 0) {
			randomCount++;
			if (random != 0) {
				
				
				if (randomCount%random == 0) {
					//System.out.println("here");
					Random rand = new Random();
					ArrayList<Integer> positions = new ArrayList<Integer>();
					for (int i = 0; i < 5*5; i++) {
						if (board[i/5][i%5] == 0) {
							positions.add(i);
						}
					}
					int positionIndex = rand.nextInt(positions.size());
					int positionNumber = positions.get(positionIndex);
					row = positionNumber/5;
					col = positionNumber%5;
				}
				
				/*Random rand = new Random(); 
				
				int randFreq = (n/2)*2 + 1; //to keep it odd
				int randGeneratedNum = rand.nextInt(randFreq);
				if (randGeneratedNum == 0) {
					ArrayList<Integer> positions = new ArrayList<Integer>();
					for (int i = 0; i < board.length*board.length; i++) {
						if (board[i/board.length][i%board.length] == 0) {
							positions.add(i);
						}
					}
					int positionIndex = rand.nextInt(positions.size());
					int positionNumber = positions.get(positionIndex);
					row = positionNumber/n;
					col = positionNumber%n;
				
				}*/
				
			}
			
			
			
			board[row][col] = currentPlayer;
			return true;
		}
		else {
			return false;
		}
	
		
		
	}
	
	public int[][] getBoard() {
		return board.clone();
	}
	
	public int checkState() {
		int gameState = 0;
		for (int j = 0; j < 5; j++) {
			int start = board[j][0];
			
			if (start == -3) {
				continue;
			}
			if (start == -2) {
				for (int i = 1; i < 5; i++) {
					if (board[j][i] != -2) {
						start = board[j][i];
						break;
					}
				}
				
			}
			
			if (start != 0 && start != -3) {
				
				gameState = start;
				for (int i = 1; i < 5; i++) {
					if (board[j][i] == -2) {
						continue;
					}
					else if (board[j][i] == -3) {
						gameState = 0;
						break;
					}
					else if (board[j][i] != start) {
						gameState = 0;
						break;
					}

				}
				if (gameState != 0) {
					return gameState;
				}
			}
		}
		
		
		for (int j = 0; j < 5; j++) {
			int start = board[0][j];
			
			if (start == -3) {
				continue;
			}
			if (start == -2) {
				for (int i = 1; i < 5; i++) {
					if (board[i][j] != -2) {
						start = board[i][j];
						break;
					}
				}
				
			}
			
			if (start != 0 && start != -3) {
				
				gameState = start;
				for (int i = 1; i < 5; i++) {
					if (board[i][j] == -2) {
						continue;
					}
					else if (board[i][j] == -3) {
						gameState = 0;
						break;
					}
					else if (board[i][j] != start) {
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
			int start = board[0][0];
			
			if (start != -3) {
				
			
				if (start == -2) {
					for (int i = 1; i < 5; i++) {
						if (board[i][i] != -2) {
							start = board[i][i];
							break;
						}
					}
					
				}
				
				
				if (start != 0 && start != -3) {
					gameState = start;
					for (int i = 1; i < 5; i++) {
						if (board[i][i] == -2) {
							continue;
						}
						else if (board[i][i] == -3) {
							gameState = 0;
							break;
						}
						else if (board[i][i] != start) {
							gameState = 0;
							break;
						}
					}
					if (gameState != 0) {
						return gameState;
					}
				}
			}
		}
		
		{
			int start = board[5-1][0];
			if (start != -3) {
				if (start == -2) {
					for (int i = 1; i < 5; i++) {
						if (board[i][i] != -2) {
							start = board[i][i];
							break;
						}
					}
					
				}
				
				if (start != 0 && start != -3) {
					gameState = start;
					for (int i = 1; i < 5; i++) {
						if (board[5-1-i][i] == -2) {
							continue;
						}
						else if (board[5-1-i][i] == -3) {
							gameState = 0;
							break;
						}
						else if (board[5-1-i][i] != start) {
							gameState = 0;
							break;
						}
					}
					if (gameState != 0) {
						return gameState;
					}
				}
			}
		}
		{
			gameState = 3;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (board[i][j] == 0) {
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
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] == 0) {
					displayString += " ";
					
				}
				else {
					displayString += players[board[i][j] - 1].getMarker();
				}
				if (j < 5-1) {
					displayString += "|";
				}
			}
			displayString += "\n";
			if (i < 5-1) {
				displayString += "-----\n";
			}
		}
		return displayString;
	}
	
}

