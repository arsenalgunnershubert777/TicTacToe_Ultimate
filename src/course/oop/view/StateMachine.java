package course.oop.view;

import javafx.scene.layout.BorderPane;
import java.util.*;
public class StateMachine {
	BorderPane root;
	Stack<ViewState> stack = new Stack<ViewState>();
	public StateMachine(BorderPane root) {
		this.root = root;
	}
	public void addState(ViewState state) {
		stack.push(state);
		
		root.setCenter(stack.peek().constructPane());
	}
	public void removeState( ) {
		stack.pop();
		root.setCenter(stack.peek().constructPane());
	}
	
	public void removeStateWithReplace(ViewState state) {
		stack.pop();
		stack.add(state);
		root.setCenter(stack.peek().constructPane());
	}
}
