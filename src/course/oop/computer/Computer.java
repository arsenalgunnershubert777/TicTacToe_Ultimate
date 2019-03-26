package course.oop.computer;
import java.util.*;
public class Computer {
	public int[] getNextMove(int computerNumber, int[][] board) {
		int[] nextMove = new int[2];
		ArrayList<Integer> positions = new ArrayList<Integer>();
		Random rand = new Random(); 
		for (int i = 0; i < 9; i++) {
			if (board[i/3][i%3] == 0) {
				positions.add(i);
			}
		}
		int positionIndex = rand.nextInt(positions.size());
		int positionNumber = positions.get(positionIndex);
		nextMove[0] = positionNumber/3;
		nextMove[1] = positionNumber%3;
		return nextMove;
	}
}
