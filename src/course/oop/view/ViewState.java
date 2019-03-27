package course.oop.view;

import course.oop.storage.RecordManager;
import javafx.scene.layout.GridPane;

public abstract class ViewState {
	private StateMachine machine;
	private RecordManager records;
	public ViewState(StateMachine machine, RecordManager records) {
		this.machine = machine;
		this.records = records;
	}
	
	public StateMachine getMachine() {
		return this.machine;
	}
	public RecordManager getRecordManager() {
		return this.records;
	}
	
	public abstract GridPane constructPane(); 
}
