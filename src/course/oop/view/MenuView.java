package course.oop.view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import course.oop.storage.RecordManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class MenuView extends ViewState {
	private final int windowWidth = 1000;
    private final int windowHeight = 900;
	public MenuView(StateMachine machine, RecordManager records) {
		super(machine, records);
	}
	public GridPane constructPane() {
		
		//Text numberPlayersLabel = new Text("Number of Players:");
		//TextField numberPlayersTextField = new TextField();

		
        Button button1 = new Button("Play Game"); 
        Button button2 = new Button("Quit Game"); 
        Button buttonRecords = new Button("Clear Records"); 
        buttonRecords.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override public void handle(MouseEvent e) {
        		getRecordManager().clearRecords();
        	}
        });
        
        Text titleLabel = new Text("Welcome to TicTacToe!");
		titleLabel.getStyleClass().add("titleText");
        
        //Creating the mouse event handler 
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) { 
        	   StateMachine machine = getMachine();
        	   RecordManager records = getRecordManager();
        	   machine.addState(new SettingsView(machine, records));
        	   //machine.removeStateWithReplace(new SettingsView(machine, records));
        	           	
        	   
           } 
        };  
        
        
        EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 

         	   Platform.exit();
         	   
            } 
         };
        //Registering the event filter 
        button1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        button2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);   
        
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        gridPane.getStyleClass().add("menuPane");
        //Setting size for the pane 
        gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        
        
        
        ImageView image = null;
		try {
			image = new ImageView(new Image(new FileInputStream("resources/TTT.PNG")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        gridPane.add(image, 0, 1);
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(button1, 0, 2); 
        gridPane.add(button2, 0, 3);
        gridPane.add(buttonRecords, 1, 2);
      
              
        return gridPane;
	}
}
