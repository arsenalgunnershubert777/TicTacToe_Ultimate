package course.oop.view;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import course.oop.computer.Computer;
import course.oop.controller.TTTControllerImpl;
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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;




public class MainView {
	private BorderPane root;
	private Scene scene; 
    
    private final int windowWidth = 1600;
    private final int windowHeight = 900;
  
   
    private RecordManager records = new RecordManager();
    
    private StateMachine machine;
    
	public MainView() {
		this.root = new BorderPane();
		this.scene = new Scene(root, windowWidth, windowHeight);
		this.scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
		//this.statusNode = new Text("no status");
		
		this.machine = new StateMachine(root);
		machine.addState(new MenuView(machine, records));
		
		//this.root.setTop(this.buildSetupPane());
		
		//test22
	}
	
	public Scene getMainScene() {
		return this.scene;
	}

	
	
}
