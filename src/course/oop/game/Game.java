package course.oop.game;

import course.oop.player.Player;

public class Game {
	private int[][] board;
	private Player[] players;
	
	//private boolean againstComputer = false;
	
	
	public Game(Player[] players) {
		board = new int[3][3];
		
		this.players = players;
	
	}
	
	
	
	public boolean setSelection(int row, int col, int currentPlayer) {
		
		if (board[row][col] == 0) {
			board[row][col] = currentPlayer;
			return true;
		}
		else {
			return false;
		}
	
		
		
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int checkState() {
		int gameState = 0;
		for (int j = 0; j < 3; j++) {
			int start = board[j][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
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
		
		
		for (int j = 0; j < 3; j++) {
			int start = board[0][j];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
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
				for (int i = 1; i < 3; i++) {
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
			int start = board[2][0];
			if (start != 0) {
				gameState = start;
				for (int i = 1; i < 3; i++) {
					if (board[2-i][i] != start) {
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
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) {
					displayString += " ";
					
				}
				else {
					displayString += players[board[i][j] - 1].getMarker();
				}
				if (j < 2) {
					displayString += "|";
				}
			}
			displayString += "\n";
			if (i < 2) {
				displayString += "-----\n";
			}
		}
		return displayString;
	}
	
}
