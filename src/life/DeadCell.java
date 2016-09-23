package life;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class DeadCell extends Cell {
	private ArrayList<Cell> neighbors;
	public boolean nextState;

	// Dead Cell is false. Alive Cell is true.

	public DeadCell(int x, int y) {
		super.init(x, y);
		color = Color.RED;
	}

	@Override
	public String toString() {
		return "D";
	}

	@Override
	public void calculateNextState(ArrayList<Cell> neighborList) {
		neighbors = neighborList;
		nextState = determineNextState();
	}

	private boolean determineNextState() {
		int numAlive = 0;
		for (Cell c : neighbors) {
			if (c instanceof AliveCell) {
				numAlive++;
			}
		}
		// If less than 2 neighboring cells are alive, cell dies of under
		// population.
		if (numAlive < 2) {
			return false;
		}
		// If cell has 2 or 3 neighboring alive cells, cell stays alive.
		if (numAlive == 2 || numAlive == 3) {
			return true;
		}
		// If cell has more than 3 neighboring alive cells, cell dies of over
		// population.
		else {
			return false;
		}
	}

	// getters
	public boolean getNextState() {
		return nextState;
	}

}
