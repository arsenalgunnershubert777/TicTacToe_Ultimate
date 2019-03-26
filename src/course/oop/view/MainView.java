package course.oop.view;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import course.oop.computer.Computer;
import course.oop.controller.TTTControllerImpl;
import course.oop.storage.RecordManager;
import course.oop.storage.Records;
import javafx.animation.AnimationTimer;
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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;




public class MainView {
	private BorderPane root;
	private Scene scene; 
    private TTTControllerImpl TTTController;
	private Text statusNode;
    private final int windowWidth = 800;
    private final int windowHeight = 900;
    private int gameState = 0;
    private int gamePlayerTurn = 1;
    private Button[] buttons =  new Button[9];
    private boolean computerPlaying = false;
    private int computerNumber = 0;
    private Computer computer = new Computer();
    private int time = 0;
    private Timer turnTimer;
    private TimerTask task;
    private GridPane ticTacToePane;
    private Text statusLabel = new Text(10, 50,"");
    private RecordManager records = new RecordManager();
    
	public MainView() {
		this.root = new BorderPane();
		this.scene = new Scene(root, windowWidth, windowHeight);
		this.scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
		//this.statusNode = new Text("no status");
		this.root.setTop(this.buildSetupPane());
		
		
	}
	
	public Scene getMainScene() {
		return this.scene;
	}
	
	public GridPane buildSetupPane() {
	    /*Text sizeLabel = new Text("Number rows & columns:");  
	    Text defaultValLabel = new Text("Default value:");       
        TextField sizeTextField = new TextField();
        //TODO #1: Add a text field for a user to input a default value to init array
        TextField defaultValueTextField = new TextField();*/
		Text numberPlayersLabel = new Text("Number of Players:");
		TextField numberPlayersTextField = new TextField();
		
		Text playerOneNameLabel = new Text("Player One Name:");
		TextField playerOneNameTextField = new TextField();
		
		Text playerOneMarkerLabel = new Text("Player One Marker:");
		TextField playerOneMarkerTextField = new TextField();
		
		
		Text playerTwoNameLabel = new Text("Player Two Name:");
		TextField playerTwoNameTextField = new TextField();
		
		Text playerTwoMarkerLabel = new Text("Player Two Marker:");
		TextField playerTwoMarkerTextField = new TextField();
		
		Text playerOrderLabel = new Text("Player Order (for vs Computer):");
		TextField playerOrderTextField = new TextField();
		
		Text timerLabel = new Text("Timer in seconds (0 for no timer):");
		TextField timerTextField = new TextField();
		
        Button button1 = new Button("Start Game"); 
        Button button2 = new Button("Quit Game"); 
        Line line = new Line();
        
        
        line.setStartX(0.0f); 
        line.setStartY(0.0f);         
        line.setEndX((float) windowWidth); 
        line.setEndY(0.0f);
        
        //Creating the mouse event handler 
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) { 
               //String size = sizeTextField.getText();
               
               //TODO #2: Read the default input from the text field you created above
               //String defaultVal = defaultValueTextField.getText();
        	   String numPlayers = numberPlayersTextField.getText();
        	   String playerOneName = playerOneNameTextField.getText();
        	   String playerOneMarker = playerOneMarkerTextField.getText();
        	   String playerTwoName = playerTwoNameTextField.getText();
        	   String playerTwoMarker = playerTwoMarkerTextField.getText();
        	   String playerOrder = playerOrderTextField.getText();
        	   String time_ = timerTextField.getText();
        	   buildTicTacToePane(numPlayers, playerOneName, playerOneMarker, playerTwoName, playerTwoMarker, playerOrder, time_);
               
           } 
        };  
        
        
        EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
                //String size = sizeTextField.getText();
                
                //TODO #2: Read the default input from the text field you created above
                //String defaultVal = defaultValueTextField.getText();
         	   Platform.exit();
         	   
            } 
         };
        //Registering the event filter 
        button1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        button2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);   

        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        
        //Setting size for the pane 
        gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        
        gridPane.add(numberPlayersLabel, 0, 1); 
        gridPane.add(numberPlayersTextField, 1, 1);
        
        gridPane.add(playerOneNameLabel, 0, 2);
        gridPane.add(playerOneNameTextField, 1, 2);
        
        
        gridPane.add(playerOneMarkerLabel, 0, 3);
        gridPane.add(playerOneMarkerTextField, 1, 3);
        
        
        gridPane.add(playerTwoNameLabel, 0, 4);
        gridPane.add(playerTwoNameTextField, 1, 4);
        
        
        gridPane.add(playerTwoMarkerLabel, 0, 5);
        gridPane.add(playerTwoMarkerTextField, 1, 5);
        
        gridPane.add(playerOrderLabel, 0, 6);
        gridPane.add(playerOrderTextField, 1, 6);
        
        
        gridPane.add(timerLabel, 0, 7);
        gridPane.add(timerTextField, 1, 7);
        //TODO #3: Remove comment so that the label will show
        //gridPane.add(defaultValLabel, 1, 0); 
        
        //gridPane.add(sizeTextField, 0, 1); 
        
        //TODO #4: Add the text field for the default value
        //gridPane.add(defaultValueTextField, 1, 1);

        gridPane.add(button1, 0, 0); 
        gridPane.add(button2, 1, 0);
        //gridPane.add(line, 0, 2, 3, 1); 
              
        return gridPane;
	}
	
	public GridPane buildTicTacToePane(String numPlayers, String playerOneName, String playerOneMarker, String playerTwoName, String playerTwoMarker, String playerOrder,String time_) {
		
		ticTacToePane = new GridPane();
		 
        
		gameState = 1;
		gamePlayerTurn = 1;
		String text = "";
		//Clear other panes
		root.setLeft(new Text());
		root.setRight(new Text());
		root.setCenter(ticTacToePane);
		ticTacToePane.setAlignment(Pos.CENTER);
		ticTacToePane.setHgap(10);
		ticTacToePane.setVgap(12);
		try {
			//int intSize =Integer.parseInt(size);
			//int intDefaultVal = Integer.parseInt(defaultVal);
	        //twoDArr = new TwoDArray(intSize,intSize, intDefaultVal);
	        TTTController = new TTTControllerImpl();
	        time = Integer.parseInt(time_);
	        if (Integer.parseInt(numPlayers) == 1) {
				computerPlaying = true;
				computerNumber = 3 - Integer.parseInt(playerOrder);
				
			}
			else {
				computerPlaying = false;
				computerNumber = 0;
			}
	        
	        if (computerPlaying) {
	        	TTTController.createPlayer(playerOneName, playerOneMarker, Integer.parseInt(playerOrder));
	        }
	        else {
	        	TTTController.createPlayer(playerOneName, playerOneMarker, 1);
	        }
	        if (Integer.parseInt(numPlayers) == 2) {
	        	TTTController.createPlayer(playerTwoName, playerTwoMarker, 2);
	        }
	        
	        
	        TTTController.startNewGame(Integer.parseInt(numPlayers), time);
			//text = TTTController.getGameDisplay();
	        //System.out.println("Hello World " + intSize +", "+intDefaultVal); //this will print out on the command line, not the GUI
			//root.setLeft(build2DArrayInputPane());
		}catch(NumberFormatException nfe) {
			text = "Please enter integer values!";
			
		}
		
		
		//Text TTTDisplay= new Text(text);
		//TTTDisplay.setId("TTTDisplay");
		//MotionBlur mb= new MotionBlur();
		//mb.setRadius(5.0f);
		//mb.setAngle(15.0f);
		//arrDisplay.setEffect(mb);
		//creating the rotation transformation 
		//Rotate rotate= new Rotate(); 
		//Setting the angle for the rotation 
		//rotate.setAngle(20); 
		//arrDisplay.getTransforms().addAll(rotate);
		//root.setCenter(TTTDisplay);
		
		 
		
		
		
		for (int i = 0; i < 9; i++) {
			buttons[i] = new Button(" ");
			buttons[i].getStyleClass().add("tileButton");
			
			//buttons[i].setText(" ");
			ticTacToePane.add(buttons[i], i%3, i/3);
		}
		
		
		ticTacToePane.add(statusLabel, 4, 0);
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
               update();
                
            }
        };
        timer.start();
        
        EventHandler<MouseEvent> eventHandler0 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(0, 0, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            } 
        };
        EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
	               if (TTTController.setSelection(0, 1, gamePlayerTurn)) {
	            	   gamePlayerTurn = 3 - gamePlayerTurn;
		               if (time > 0) {
		            	   timerCancel();
		            	   timerSet();
		               }
	               }
	               
            	}
            }  
        };
        EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(0, 2, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            }  
        };
        EventHandler<MouseEvent> eventHandler3 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(1, 0, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            }  
        };
       
        EventHandler<MouseEvent> eventHandler4 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(1, 1, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            } 
        };
       
        EventHandler<MouseEvent> eventHandler5 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(1, 2, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            } 
        };
        EventHandler<MouseEvent> eventHandler6 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(2, 0, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            } 
        };
        EventHandler<MouseEvent> eventHandler7 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(2, 1, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            }  
        };
        EventHandler<MouseEvent> eventHandler8 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
            	if (gamePlayerTurn != computerNumber && gameState == 1) {
            		if (TTTController.setSelection(2, 2, gamePlayerTurn)) {
 	            	   gamePlayerTurn = 3 - gamePlayerTurn;
 		               if (time > 0) {
 		            	   timerCancel();
 		            	   timerSet();
 		               }
 	               }
            	}
            } 
        };
        buttons[0].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler0);
        buttons[1].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler1);
        buttons[2].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);
        buttons[3].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler3);
        buttons[4].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler4);
        buttons[5].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler5);
        buttons[6].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler6);
        buttons[7].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler7);
        buttons[8].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler8);
        
       
        
        if (time > 0) {
			
			timerSet();
			
		}
        return ticTacToePane;

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
			statusLabel.setText(TTTController.getPlayers()[gamePlayerTurn - 1].getName() + " took too long! " + TTTController.getPlayers()[3- gamePlayerTurn - 1].getName() + " won!");
			//ticTacToePane.add(statusLabel, 4, 0);
			addRecords(3- gamePlayerTurn);
		
			
		}
		
		int[][] board = TTTController.getBoard();
		if (gameState == 1) {
			for (int i = 0; i < 9; i++) {
				
				int state = (board[i/3][i%3]);
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
				//System.out.println(TTTController.getPlayers()[0].getName() + " won!");
				//System.out.println("here1");
				
				break;
			case 2:
				statusLabel.setText(TTTController.getPlayers()[1].getName() + " won!");
				
				break;
			case 3:
				statusLabel.setText("It's a tie!");
				
				break;
			}
			//ticTacToePane.add(statusLabel, 4, 0);
			addRecords(winner);
			
		}
		
		
		
		if (computerPlaying && (gamePlayerTurn == computerNumber) && gameState == 1) {
			int[] nextMove = computer.getNextMove(computerNumber, TTTController.getBoard());
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
		HashMap<String, Records> hmap = records.getRecords();
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
		for (String s : hmap.keySet()) {
			System.out.println(s);
			System.out.println(hmap.get(s).getWins());
		}
		records.writeBack();
		
	}
	
}
