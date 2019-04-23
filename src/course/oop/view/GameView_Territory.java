package course.oop.view;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import course.oop.computer.Computer;
import course.oop.controller.TTTControllerImpl;
import course.oop.controller.TTTControllerImpl_Territory;
import course.oop.storage.RecordManager;
import course.oop.storage.Records;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GameView_Territory extends ViewState {
	private final int windowWidth = 1000;
    private final int windowHeight = 900;
    private TTTControllerImpl_Territory TTTController;
    private boolean computerPlaying = false;
    private int computerNumber = 0;
    private Text statusLabel = new Text(10, 50,"");
    private Computer computer = new Computer();
    private int gameState = 0;
    private int gamePlayerTurn = 1;
    private Button[] buttons;
    private int time = 0;
    private Timer turnTimer;
    private TimerTask task;
    private boolean random;

    
	public GameView_Territory(StateMachine machine, RecordManager records, TTTControllerImpl_Territory TTTController, boolean ComputerPlaying, int ComputerNumber, boolean random) {
		super(machine, records);
		this.TTTController = TTTController;
		this.computerPlaying = ComputerPlaying;
		this.computerNumber = ComputerNumber;
		this.time = TTTController.getTimeOut();
		this.random = random;


		
	}
	public GridPane constructPane() {
		
		GridPane gridPane = new GridPane(); 
       
        Button button1 = new Button("Return to Menu"); 
        Button button2 = new Button("Play again with different Settings");
        Button button3 = new Button("Play again with same settings");
        //Creating the mouse event handler 
        gridPane.getStyleClass().add("gamePane");
        
        
        
        
        EventHandler<MouseEvent> eventHandlerb1 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 

         	   StateMachine machine = getMachine();
         	   RecordManager records = getRecordManager();
         	   machine.removeState();
        	   //machine.removeStateWithReplace(new MenuView(machine, records));
         	   
            } 
         };
        //Registering the event filter 

         EventHandler<MouseEvent> eventHandlerb2 = new EventHandler<MouseEvent>() { 
             @Override 
             public void handle(MouseEvent e) { 

          	   StateMachine machine = getMachine();
          	   RecordManager records = getRecordManager();
         	   machine.removeStateWithReplace(new SettingsView(machine, records));
          	   
             } 
          };
         
          EventHandler<MouseEvent> eventHandlerb3 = new EventHandler<MouseEvent>() { 
              @Override 
              public void handle(MouseEvent e) { 

           	   StateMachine machine = getMachine();
           	   RecordManager records = getRecordManager();
           	   int numPlayers = 2;
           	   if (computerPlaying) {
           		   numPlayers = 1;
           	   }
           	   TTTController.startNewGame(numPlayers, time, 5, random);
          	   machine.removeStateWithReplace(new GameView_Territory(machine, records, TTTController, computerPlaying, computerNumber, random));
           	   
              } 
           };
         
        button1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerb1);   
        button2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerb2);   
        button3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerb3);  
        
        
        gridPane.add(button1, 5+1, 2);
        gridPane.add(button2, 5+1, 1);
        gridPane.add(button3, 5+1, 0);
        //Creating a Grid Pane 
          
        
        //Setting size for the pane 
        gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        
        
        EventHandler<MouseEvent>[] buttonHandlers = new EventHandler[25];
        
        for (int i = 0; i < 25;i++) {
        	final int row = i/5;
        	final int col = i%5;
        	buttonHandlers[i] = new EventHandler<MouseEvent>() {
        		
        		public void handle(MouseEvent e) { 
        			
                	if (gamePlayerTurn != computerNumber && gameState == 1) {
                		if (TTTController.setSelection(row, col, gamePlayerTurn)) {
     	            	   gamePlayerTurn = 3 - gamePlayerTurn;
     		               if (time > 0) {
     		            	   timerCancel();
     		            	   timerSet();
     		               }
     	               }
                	}
                }
        	};
        }
       
        
       
        
        buttons =  new Button[25];
        
        
        for (int i = 0; i < 25; i++) {
			buttons[i] = new Button(" ");
			buttons[i].getStyleClass().add("tileButton");
			
			//buttons[i].setText(" ");
			gridPane.add(buttons[i], i%5, i/5);
			buttons[i].addEventFilter(MouseEvent.MOUSE_CLICKED, buttonHandlers[i]);
		}
        gridPane.add(statusLabel, 5, 0);
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
               update();
                
            }
        };
        timer.start();
        gameState = 1;
        if (time > 0) {
			
			timerSet();
			
		}
              
        return gridPane;
	}
	
	private void timerSet( ) {
		turnTimer = new Timer();
		
		task = new TimerTask() {
			public void run()
	        {
	           
				
				
	            gameState = 3;
	            
	            
	            
	        }
		};
		
		turnTimer.schedule(task,  1000*TTTController.getTimeOut());
	}
	
	private void timerCancel( ) {
		task.cancel();
		turnTimer.cancel();
		
	}
	
	
	private void update() {
		if (time > 0 && gameState == 3) {
			
			timerCancel();
			gameState = 2;
			statusLabel.setText(TTTController.getPlayers()[gamePlayerTurn - 1].getName() + " timed out! " + TTTController.getPlayers()[3- gamePlayerTurn - 1].getName() + " won!");
			//ticTacToePane.add(statusLabel, 4, 0);
			
			RotateTransition rotateTransition= new RotateTransition(); 
			//Setting the duration for the transition 
			rotateTransition.setDuration(Duration.millis(1000)); 
			//Setting the node for the transition 
			rotateTransition.setNode(statusLabel);       
			//Setting the angle of the rotation 
			rotateTransition.setByAngle(360); 
			//Setting the cycle count for the transition 
			rotateTransition.setCycleCount(5); 
			//Playing the animation 
			rotateTransition.play(); 
			int winner = 3 - gamePlayerTurn;
			
			playSound(winner);
			addRecords(winner);
			
			
		}
		
		int[][] board = TTTController.getBoard();
		if (gameState == 1) {
			
			for (int i = 0; i < 25; i++) {
				
				int state = (board[i/5][i%5]);
				String marker = " ";
				if (state == 0) {
					marker = " ";
				}
				else if (state == 1) {
					marker = TTTController.getPlayers()[0].getMarker();
				}
				else if (state == 2) {
					marker = TTTController.getPlayers()[1].getMarker();
				}
				else if (state == -3) {
					marker = "Dead";
					buttons[i].getStyleClass().removeAll("tileButton");
					buttons[i].getStyleClass().add("tileButtonNot");
				}
				else if (state == -2) {
					marker = "Neutral";
					buttons[i].getStyleClass().removeAll("tileButton");
					buttons[i].getStyleClass().add("tileButtonNeutral");
				}
				buttons[i].setText(marker); 
			}
		}
		int winner = TTTController.determineWinner();
		if (winner != 0 && gameState != 2) {
			
			if (time > 0) {
				timerCancel();
			}
			gameState = 2;
			
			//Text statusLabel = new Text("");
			
			switch (winner) {
				
			case 1:
				statusLabel.setText(TTTController.getPlayers()[0].getName() + " won!");
				
				
				break;
			case 2:
				statusLabel.setText(TTTController.getPlayers()[1].getName() + " won!");
				
				break;
			case 3:
				statusLabel.setText("It's a tie!");
				
				break;
			}
			
			RotateTransition rotateTransition= new RotateTransition(); 
			//Setting the duration for the transition 
			rotateTransition.setDuration(Duration.millis(1000)); 
			//Setting the node for the transition 
			rotateTransition.setNode(statusLabel);       
			//Setting the angle of the rotation 
			rotateTransition.setByAngle(360); 
			//Setting the cycle count for the transition 
			rotateTransition.setCycleCount(5); 
			//Playing the animation 
			rotateTransition.play(); 
			
			
			
			
			
			playSound(winner);
			
			//ticTacToePane.add(statusLabel, 4, 0);
			addRecords(winner);
			
		}
		
		
		
		if (computerPlaying && (gamePlayerTurn == computerNumber) && gameState == 1) {
			//int[] nextMove = computer.getNextMove(computerNumber, TTTController.getBoard());
			int[] nextMove = new int[2];
			nextMove = computer.getNextMove(computerNumber, TTTController.getBoard());

			TTTController.setSelection(nextMove[0], nextMove[1], computerNumber);
			gamePlayerTurn = 3 - gamePlayerTurn;
			if (time > 0) {
				timerCancel();
				timerSet();
			}
			
		}
		
		if (gameState == 1) {
			statusLabel.setText("Game on!");
			//ticTacToePane.add(statusLabel, 4, 0);
		}
		
		
		
		
		
		
		
        
    }
	
	private void addRecords(int winnerNum) {
		//HashMap<String, Records> hmap = records.getRecords();
		HashMap<String, Records> hmap = getRecordManager().getRecords();
		String playerOne = TTTController.getPlayers()[0].getName();
		String playerOneMarker = TTTController.getPlayers()[0].getMarker();
		
		int oneWins;
		int oneLosses;
		int oneTies;
		
		
		if (!hmap.containsKey(playerOne)) {
			if (winnerNum == 3) {
				oneWins = 0;
				oneLosses = 0;
				oneTies = 1;
			}
			else if (winnerNum == 1) {
				oneWins = 1;
				oneLosses = 0;
				oneTies = 0;
			}
			else {
				oneWins = 0;
				oneLosses = 1;
				oneTies = 0;
			}
			Records r = new Records(playerOne, playerOneMarker, oneWins, oneLosses, oneTies);
			hmap.put(playerOne, r);
			
		}
		else {
			Records r = hmap.get(playerOne);
			if (winnerNum == 3) {
				
				oneTies = r.getTies()+1;
				r.setTies(oneTies);
			}
			else if (winnerNum == 1) {
				oneWins = r.getWins() + 1;
				r.setWins(oneWins);
			}
			else {
				
				oneLosses = r.getLosses() + 1;
				r.setLosses(oneLosses);
				
			}
			r.setMarker(playerOneMarker);
		}
		
		
	
		String playerTwo = TTTController.getPlayers()[1].getName();
		String playerTwoMarker = TTTController.getPlayers()[1].getMarker();
		
		int twoWins;
		int twoLosses;
		int twoTies;
		
		
		if (!hmap.containsKey(playerTwo)) {
			if (winnerNum == 3) {
				twoWins = 0;
				twoLosses = 0;
				twoTies = 1;
			}
			else if (winnerNum == 2) {
				twoWins = 1;
				twoLosses = 0;
				twoTies = 0;
			}
			else {
				twoWins = 0;
				twoLosses = 1;
				twoTies = 0;
			}
			Records r = new Records(playerTwo, playerTwoMarker, twoWins, twoLosses, twoTies);
			hmap.put(playerTwo, r);
			
		}
		else {
			Records r = hmap.get(playerTwo);
			if (winnerNum == 3) {
				
				twoTies = r.getTies()+1;
				r.setTies(twoTies);
			}
			else if (winnerNum == 2) {
				twoWins = r.getWins() + 1;
				r.setWins(twoWins);
			}
			else {
				
				twoLosses = r.getLosses() + 1;
				r.setLosses(twoLosses);
				
			}
			r.setMarker(playerTwoMarker);
		}
		/*for (String s : hmap.keySet()) {
			System.out.println(s);
			System.out.println(hmap.get(s).getWins());
			System.out.println(hmap.get(s).getLosses());
			System.out.println(hmap.get(s).getTies());
		}*/
		
		
		
		
		getRecordManager().writeBack();
		
	}
	
	private void playSound(int winner) {
		if (winner == 3) {
			AudioClip plonkSound = new AudioClip(Paths.get("resources/Tied.mp3").toUri().toString());
			plonkSound.play();
		}
		else if (computerPlaying == true && winner == computerNumber) {
			AudioClip plonkSound = new AudioClip(Paths.get("resources/Sad.mp3").toUri().toString());
			plonkSound.play();
		}
		else if (computerPlaying == true) {
			AudioClip plonkSound = new AudioClip(Paths.get("resources/TaDa.mp3").toUri().toString());
			plonkSound.play();
		}
		else {
			AudioClip plonkSound = new AudioClip(Paths.get("resources/TaDa.mp3").toUri().toString());
			plonkSound.play();
		}
		
	}

}

