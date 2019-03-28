package course.oop.view;



import java.util.HashMap;

import course.oop.controller.TTTControllerImpl;
import course.oop.storage.RecordManager;
import course.oop.storage.Records;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class SettingsView extends ViewState {
	private final int windowWidth = 1000;
    private final int windowHeight = 900;
    
    
	public SettingsView(StateMachine machine, RecordManager records) {
		super(machine, records);
		
	}

	public GridPane constructPane() {
		
		
		
		Text numberPlayersLabel = new Text("Number of Players (1: vs computer, 2: vs player):");
		TextField numberPlayersTextField = new TextField();
		
		Text playerOneNameLabel = new Text("Player One Name:");
		TextField playerOneNameTextField = new TextField();
		
		Text playerOneMarkerLabel = new Text("Player One Marker:");
		TextField playerOneMarkerTextField = new TextField();
		
		
		Text playerTwoNameLabel = new Text("Player Two Name:");
		TextField playerTwoNameTextField = new TextField();
		
		Text playerTwoMarkerLabel = new Text("Player Two Marker:");
		TextField playerTwoMarkerTextField = new TextField();
		
		Text playerOrderLabel = new Text("Order vs Computer (1: player first, 2: computer first): ");
		TextField playerOrderTextField = new TextField();
		
		Text timerLabel = new Text("Timer in seconds (0 for no timer): ");
		TextField timerTextField = new TextField();
		
        Button button1 = new Button("Start Game"); 
        Button button2 = new Button("Return to Menu"); 
 
        
        //Creating the mouse event handler 
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) { 

        	   String numPlayers = "1"; //default
        	   if (!numberPlayersTextField.getText().trim().isEmpty()) {
        		   numPlayers = numberPlayersTextField.getText();
        	   }
        	   String playerOneName = playerOneNameTextField.getText();
        	   String playerOneMarker = playerOneMarkerTextField.getText();
        	   String playerTwoName = playerTwoNameTextField.getText();
        	   String playerTwoMarker = playerTwoMarkerTextField.getText();
        	   
        	   String playerOrder = "1"; //default
        	   if (!playerOrderTextField.getText().trim().isEmpty()) {
        		   playerOrder = playerOrderTextField.getText();
        	   }
        	   
        	   String time_ = "0"; //default
        	   if (!timerTextField.getText().trim().isEmpty()) {
        		   time_ = timerTextField.getText();
        	   }
  
        	   StateMachine machine = getMachine();
        	   RecordManager records = getRecordManager();
        	   
        	   TTTControllerImpl TTTController = new TTTControllerImpl();
        	   
        	   
        	   boolean computerPlaying = false;
        	   int computerNumber = 0;
        	   try {

       	        
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
	       	        
	       	        
	       	        

	       	   }catch(NumberFormatException nfe) {
	       			//text = "Please enter integer values!";
	       			
	       	   }
	   	       TTTController.startNewGame(Integer.parseInt(numPlayers), Integer.parseInt(time_));
        	   machine.removeStateWithReplace(new GameView(machine, records, TTTController, computerPlaying, computerNumber));
        	  
           } 
        };  
        
        
        EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 

         	   StateMachine machine = getMachine();
         	   RecordManager records = getRecordManager();
         	   machine.removeState();
         	   
            } 
         };
        //Registering the event filter 
        button1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        button2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);   

        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        gridPane.getStyleClass().add("settingsPane");
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
      
        gridPane.add(button1, 0, 0); 
        gridPane.add(button2, 1, 0);
        
        
        
        Text recordsLabel = new Text("Player Records:");
        gridPane.add(recordsLabel, 0, 8);
        
        RecordManager records = getRecordManager();
        Text[] dataLabels = new Text[records.getRecords().size()];
		int i = 0;
		for (String s : records.getRecords().keySet()) {
			Records r = records.getRecords().get(s);
			dataLabels[i] = new Text("Name: " + r.getName() + " Marker: " + r.getMarker() + " Wins: " + r.getWins() + " Losses: " + r.getLosses() + " Ties: " + r.getTies());
			gridPane.add(dataLabels[i], 0, i + 9);
			i++;
			/*if (i == 10) {
				break;
			}*/
		}
		
		Text playerOneSelect = new Text("Player One Select:");
		gridPane.add(playerOneSelect, 0, i+10);
		
		HashMap<String, Records> map = records.getRecords();

		map.remove("Computer");
		
		ComboBox combo_box1 = new ComboBox(FXCollections.observableArrayList(map.keySet())); 
        gridPane.add(combo_box1, 1, i+10);
        
        
        Text playerTwoSelect = new Text("Player Two Select:");
		gridPane.add(playerTwoSelect, 0, i+11);
        
		ComboBox combo_box2 = new ComboBox(FXCollections.observableArrayList(map.keySet())); 
        gridPane.add(combo_box2, 1, i+11);
		
        
       
	
		
		String[] emojis = {"❤","😃","👍","😁","😂","😘"};
		
        ComboBox combo_box3 = new ComboBox(FXCollections.observableArrayList(emojis)); 
        gridPane.add(combo_box3, 2, i+12);
        
      
        
        Button emojiSelect1 = new Button("Player One Emoji Select");
        Button emojiSelect2 = new Button("Player Two Emoji Select");
        
        
		gridPane.add(emojiSelect1, 0, i+12);
		gridPane.add(emojiSelect2, 1, i+12);
		
		
        
        
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
              String name = (String) combo_box1.getValue();
              playerOneNameTextField.setText(name);
              playerOneMarkerTextField.setText(records.getRecords().get(name).getMarker());
          } 
        }; 
        
        
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                String name = (String) combo_box2.getValue();
                playerTwoNameTextField.setText(name);
                playerTwoMarkerTextField.setText(records.getRecords().get(name).getMarker());
            } 
          }; 
          
          
         
          EventHandler<MouseEvent> eventHandlerEmoji1 = new EventHandler<MouseEvent>() { 
              @Override 
              public void handle(MouseEvent e) { 
              	playerOneMarkerTextField.setText((String)combo_box3.getValue());
           	  
           	   
              } 
           };
  		
           EventHandler<MouseEvent> eventHandlerEmoji2 = new EventHandler<MouseEvent>() { 
               @Override 
               public void handle(MouseEvent e) { 
            	   playerTwoMarkerTextField.setText((String)combo_box3.getValue());
            	   
               } 
            };
          
          
        emojiSelect1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerEmoji1);
        emojiSelect2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerEmoji2);
        combo_box1.setOnAction(event1);
        combo_box2.setOnAction(event2);
        
        return gridPane;
	}
	
	
}
