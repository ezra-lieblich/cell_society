package life;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

/**
 * Defines the dead cell and sets logic for determining its future state.
 * @author Christopher Lu
 *
 */

public class DeadCell extends Cell {
	private List<Cell> neighbors;
	public boolean nextState;

	public DeadCell(int x, int y) {
		super(x, y, Color.RED);
	}

	@Override
	public void calculateNextState(List<Cell> neighborList) {
		neighbors = neighborList;
		nextState = determineNextState();
	}

	/**
	 * Sets logic for determining next state. If less than 2 or more than 3 alive neighbors, the cell will be dead.
	 * @return
	 */
	private boolean determineNextState() {
		int numAlive = 0;
		for (Cell c : neighbors) {
			if (c instanceof AliveCell) {
				numAlive++;
			}
		}
		if (numAlive < 2) {
			return false;
		}
		if (numAlive == 2 || numAlive == 3) {
			return true;
		}
		else {
			return false;
		}
	}

	// getters
	public boolean getNextState() {
		return nextState;
	}

}
