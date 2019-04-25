package course.oop.controller;
import java.util.*;


import course.oop.game.Game_NxN;
import course.oop.game.Game_Territory;
import course.oop.player.Player;
public class TTTControllerImpl_Territory implements TTTControllerInterface {
	//private Game game;
	private Game_Territory game;
	private Player[] players = new Player[2];
	private HashSet<Integer> playerSet = new HashSet<>();
	private HashSet<String> markerSet = new HashSet<>();
	private int turn = 1;
	private int timeOut;
	
	
	@Override
	public void startNewGame(int numPlayers, int timeoutInSecs, int n, int random) {
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
		game = new Game_Territory(players, random);
		turn = 1;
		timeOut = timeoutInSecs;
		
		
		
	}
	
	public void clearSets() {
		playerSet.clear();
		markerSet.clear();
	}

	@Override
	public void createPlayer(String username, String marker, int playerNum) {
		// TODO Auto-generated method stub
		players[playerNum -1] = new Player(username, marker, playerNum);
		playerSet.add(playerNum);
		markerSet.add(marker);
	}

	@Override
	public boolean setSelection(int row, int col, int currentPlayer) {
		// TODO Auto-generated method stub
		if (game.setSelection(row, col, currentPlayer)) {
			turn = currentPlayer%2 + 1;
			
			return true;
		}
		else {
			return false;
		}
	
		
	}

	@Override
	public int determineWinner() {
		// TODO Auto-generated method stub
		return game.checkState();
	}

	@Override
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
	public int[][] getBoard() {
		return game.getBoard();
	}
	public int getTurn() {
		return turn;
	}
	public int getTimeOut() {
		return timeOut;
	}
}
