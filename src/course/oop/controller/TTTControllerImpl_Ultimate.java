package course.oop.controller;
import java.util.*;


import course.oop.game.Game_Ultimate;
import course.oop.player.Player;
public class TTTControllerImpl_Ultimate {
	//private Game game;
	private Game_Ultimate game;
	private Player[] players = new Player[2];
	private HashSet<Integer> playerSet = new HashSet<>();
	private HashSet<String> markerSet = new HashSet<>();
	private int turn = 1;
	private int timeOut;
	
	

	public void startNewGame(int numPlayers, int timeoutInSecs, int random) {
		// TODO Auto-generated method stub
		
		if (numPlayers == 1) {
			for (int i = 1; i < 3; i++) {
				if (!playerSet.contains(i)) {
					
					String marker = "";
					if (markerSet.contains("X")) {
						marker = "O";
					}
					else {
						marker = "X";
					}
					
					players[i-1] = new Player("Computer", marker, i);
					break;
				}
			}
		}
		//game = new Game(players);
		game = new Game_Ultimate(players, random);
		turn = 1;
		timeOut = timeoutInSecs;
		
		
		
	}
	
	public void clearSets() {
		playerSet.clear();
		markerSet.clear();
	}


	public void createPlayer(String username, String marker, int playerNum) {
		// TODO Auto-generated method stub
		players[playerNum -1] = new Player(username, marker, playerNum);
		playerSet.add(playerNum);
		markerSet.add(marker);
	}

	public boolean setSelection(int sq_row, int sq_col, int pos_row, int pos_col, int currentPlayer) {
		// TODO Auto-generated method stub
		if (game.setSelection(sq_row, sq_col, pos_row, pos_col, currentPlayer)) {
			turn = currentPlayer%2 + 1;
			
			return true;
		}
		else {
			return false;
		}
	
		
	}


	public int determineWinner() {
		// TODO Auto-generated method stub
		return game.checkState();
	}

	public String getGameDisplay() {
		// TODO Auto-generated method stub
		return game.display();
	}
	
	public void deletePlayer(int playerNum) {
		playerSet.remove(playerNum);
		markerSet.remove(players[playerNum-1].getMarker());
		players[playerNum -1] = null;
		
	}
	
	public Player[] getPlayers() {
		return players.clone();
	}
	public int[][][][] getBoard() {
		return game.getBoard();
	}
	public int getTurn() {
		return turn;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public int[] getToPlay() {
		return game.getToPlay();
	}
	public int[][] getBoardStatus() {
		return game.getBoardStatus();
	}
}
