package course.oop.main;

import course.oop.computer.Computer;
import course.oop.controller.TTTControllerImpl;
//import course.oop.player.Player;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
public class TTTDriver {
	//comment
	public static int gameState = 0;
	public static void runGameLoop() {
		TTTControllerImpl ticTacToe = new TTTControllerImpl();
		Computer computer = new Computer();
		Scanner sc = new Scanner(System.in);
		//menu
		//play or exit
		//int gameState = 0;
		boolean playing = true;
		//boolean computerPlaying = false;
		boolean playedOnce = false;
		int computerNumber = 0;
		int winner = 0;
		int gamePlayNumber = 0;
		int time = 0;
		Timer timer = null;
		
		while (playing) {
			
			switch (gameState) {
			case 0:
				
				System.out.println("Menu: press 1 to play or 0 to exit.");
				
				int option = sc.nextInt();
				
				if (option == 0) {
					playing = false;
					
				}
				else if (option == 1) {
					gameState = 1;
				}
				
				break;
			case 1:
				
				if(playedOnce) {
					System.out.println("Play: press 1 to repeat last game settings or press 2 to change settings."); 
					int settings = sc.nextInt();
					if (settings == 1) {
						
						ticTacToe.startNewGame(gamePlayNumber, time);
						gameState = 2;
						break;
					}
					else if (settings == 2) {
						ticTacToe.deletePlayer(1);
						ticTacToe.deletePlayer(2);
						ticTacToe.clearSets();
						
						
					}
				}
				System.out.println("Play: press 1 for one user against computer or 2 for two users against one another.");
			
				option = sc.nextInt();
				gamePlayNumber = option;
				if (option == 1) {
					System.out.println("Play: Enter the name of the user.");
					String username = sc.next();
					
					System.out.println("Play: Enter the marker for the user.");
					String marker = sc.next();
					System.out.println("Play: Enter the order for the game, 1 for user first and 2 for computer first.");
					int order = sc.nextInt();
					ticTacToe.createPlayer(username, marker, order);
					computerNumber = 3 - order;
					
					
				}
				else if (option == 2) {
					computerNumber = 0;
					System.out.println("Play: Enter the name of the first user.");
					String username = sc.next();
					System.out.println("Play: Enter the marker for the first user.");
					String marker = sc.next();
					ticTacToe.createPlayer(username, marker, 1);
					
					System.out.println("Play: Enter the name of the second user.");
					username = sc.next();
					
					String newMarker = "";
					while (true) {
						System.out.println("Play: Enter the marker for the second user.");
						newMarker = sc.next();
						if (newMarker.contentEquals(marker)) {
							System.out.println("Play: Enter a different marker from the first user.");
						}
						else {
							break;
						}
					}
					ticTacToe.createPlayer(username, newMarker, 2);
					
				}
				gameState = 2;
				System.out.println("Enter time in seconds for timer or 0 for no timer.");
				time = sc.nextInt();
				ticTacToe.startNewGame(gamePlayNumber, time);
				break;
			case 2:
				playedOnce = true;
				if (time > 0) {
					
					timer = new Timer();
					
					TimerTask task = new TimerTask() {
						public void run()
				        {
				           
							
							
				            System.out.println(ticTacToe.getPlayers()[ticTacToe.getTurn()-1].getName() + " took too long!");
				            System.out.println("Enter 0 to proceed.");
				            gameState = 3;
				            
				            
				            
				        }
					};
					
					timer.schedule(task,  1000*ticTacToe.getTimeOut());
					
				}
				
				
				while (gameState == 2) {
					if (computerNumber == 1) {
						System.out.println("GamePlay: Computer played.");
						int[] nextMove = computer.getNextMove(computerNumber, ticTacToe.getBoard());
						ticTacToe.setSelection(nextMove[0], nextMove[1], computerNumber);
					}
					else {
						while (true) {
							
							int row, col;
							System.out.println("GamePlay: " + ticTacToe.getPlayers()[0].getName() + ", enter the row of the position to play, 0 index.");
							row = sc.nextInt();
							
							if (gameState != 3) {
								System.out.println("GamePlay: " + ticTacToe.getPlayers()[0].getName() + ", enter the col of the position to play, 0 index.");
								col = sc.nextInt();
							}
							else
								break;
							if (gameState !=3) {
								
								if (row < 0 || row > 2 || col < 0 || col > 2) {
									System.out.println("Incorrect position, reselect position.");
								}
								else {
									if (ticTacToe.setSelection(row, col, 1)) {
										break;
									}
									else {
										System.out.println("Incorrect position, reselect position.");
									}
								}
							}
							else 
								break;
						}
					}
					
					if (gameState != 3) {
						System.out.println(ticTacToe.getGameDisplay());
						winner = ticTacToe.determineWinner();
						if (winner != 0) {
							gameState = 3;
							if (time != 0) {
								timer.cancel();
							}
							break;
						}
					}
					else {
						
						timer.cancel();
						winner = 3  - ticTacToe.getTurn();
						break;
					}
					
					if (time > 0) {
						
						timer.cancel();
						
						if (gameState != 3) {
							timer = new Timer();
							TimerTask task = new TimerTask() {
								public void run()
						        {
						           
									System.out.println(ticTacToe.getPlayers()[ticTacToe.getTurn()-1].getName() + " took too long!");
									System.out.println("Enter 0 to proceed.");
									gameState = 3;
									
						            
						            
						        }
							};
							timer.schedule(task,  1000*ticTacToe.getTimeOut());
						}
						
					}
					
					
					if (computerNumber == 2) {
						System.out.println("GamePlay: Computer played.");
						int[] nextMove = computer.getNextMove(computerNumber, ticTacToe.getBoard());
						ticTacToe.setSelection(nextMove[0], nextMove[1], computerNumber);
					}
					else {
						while (true) {
							
							int row, col;
							System.out.println("GamePlay: " + ticTacToe.getPlayers()[1].getName() + ", enter the row of the position to play, 0 index.");
							row = sc.nextInt();
							
							if (gameState != 3) {
								System.out.println("GamePlay: " + ticTacToe.getPlayers()[1].getName() + ", enter the col of the position to play, 0 index.");
								col = sc.nextInt();
							}
							else
								break;
							if (gameState !=3) {
								
								if (row < 0 || row > 2 || col < 0 || col > 2) {
									System.out.println("Incorrect position, reselect position.");
								}
								else {
									if (ticTacToe.setSelection(row, col, 2)) {
										break;
									}
									else {
										System.out.println("Incorrect position, reselect position.");
									}
								}
							}
							else 
								break;
						}
					}
					
					if (gameState != 3) {
						System.out.println(ticTacToe.getGameDisplay());
						winner = ticTacToe.determineWinner();
						if (winner != 0) {
							gameState = 3;
							if (time != 0) {
								timer.cancel();
							}
							break;
						}
					}
					else {
						winner = 3  - ticTacToe.getTurn();
						timer.cancel();
						break;
					}
					
					if (time > 0) {
						
						
						timer.cancel();
						
						if (gameState != 3) {
							timer = new Timer();
							TimerTask task = new TimerTask() {
								public void run()
						        {
						           
									System.out.println(ticTacToe.getPlayers()[ticTacToe.getTurn()-1].getName() + " took too long!");
									System.out.println("Enter 0 to return to proceed.");
									gameState = 3;
									
						            
						            
						        }
							};
							timer.schedule(task,  1000*ticTacToe.getTimeOut());
						}
						
					}
					
				
				
				
				
				}
			
			
			
				
				break;
			case 3:
				switch (winner) {
				case 1:
					System.out.println(ticTacToe.getPlayers()[0].getName() + " won the game!");
					break;
				case 2:
					System.out.println(ticTacToe.getPlayers()[1].getName() + " won the game!");
					break;
				case 3:
					System.out.println("It's a tie!");
					break;
				}
				gameState = 0;
				
				
				break;
				
				
			}
		}
		sc.close();
	}
	
	public static void main(String[] args) {
		
		runGameLoop();
	}
	
}
