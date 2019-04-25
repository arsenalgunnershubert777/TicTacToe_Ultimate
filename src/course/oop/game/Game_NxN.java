package course.oop.game;

import java.util.ArrayList;
import java.util.Random;

import course.oop.player.Player;

public class Game_NxN {
	private int[][] board;
	private Player[] players;
	private int n;
	private int random = 0;
	private int randomCount = 0;
	//private boolean againstComputer = false;
	
	
	public Game_NxN(Player[] players, int n, int random) {
		board = new int[n][n];
		this.n = n;
		this.players = players;
		this.random = random;
	
	}
	
	
	
	public boolean setSelection(int row, int col, int currentPlayer) {
		
		
		/*System.out.println("row: " + row);
		System.out.println("col: " + col);*/
		if (board[row][col] == 0) {
			randomCount++;
			if (random != 0) {
				
				
				if (randomCount%random == 0) {
					//System.out.println("here");
					Random rand = new Random();
					ArrayList<Integer> positions = new ArrayList<Integer>();
					for (int i = 0; i < n*n; i++) {
						if (board[i/n][i%n] == 0) {
							positions.add(i);
						}
					}
					int positionIndex = rand.nextInt(positions.size());
					int positionNumber = positions.get(positionIndex);
					row = positionNumber/n;
					col = positionNumber%n;
				}
				

				
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
		for (int j = 0; j < n; j++) {
			int start = board[j][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < n; i++) {
					if (board[j][i] != start) {
						gameState = 0;
						break;
					}
				}
				if (gameState != 0) {
					return gameState;
				}
			}
		}
		
		
		for (int j = 0; j < n; j++) {
			int start = board[0][j];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < n; i++) {
					if (board[i][j] != start) {
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
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < n; i++) {
					if (board[i][i] != start) {
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
			int start = board[n-1][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < n; i++) {
					if (board[n-1-i][i] != start) {
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
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
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
		for (int i = 0; i < n; i++) {
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
		}
		return displayString;
	}
	
}

