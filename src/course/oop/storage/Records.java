package course.oop.storage;

import java.io.Serializable;

public class Records implements Serializable {
	private String name = "";
	private String marker = "";
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	
	public Records(String name, String marker, int wins, int losses, int ties) {
		this.name = name;
		this.marker = marker;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
	}
	
	public String getName() {
		return name;
	}
	public String getMarker() {
		return marker;
	}
	public int getWins() {
		return wins;
	}
	public int getLosses() {
		return losses;	
	}
	public int getTies() {
		return ties;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public void setTies(int ties) {
		this.ties = ties;
	}
}
