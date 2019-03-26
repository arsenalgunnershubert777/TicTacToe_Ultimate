package course.oop.player;

public class Player {
	private String name, marker;
	private int playerNum;
	public Player(String name, String marker, int playerNum) {
		this.name = name;
		this.marker = marker;
		this.playerNum = playerNum;
	}
	public String getMarker() {
		return marker;
	}
	public String getName() {
		return name;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	
}
