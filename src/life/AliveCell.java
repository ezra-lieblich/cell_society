package life;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class AliveCell extends Cell{
	private ArrayList<Cell> neighbors;
	public boolean nextState; 
	// Dead Cell is false. Alive Cell is true.

	// Initializes alive cell to the x and y coordinates, sets color to green.
	public AliveCell(int x, int y) {
		super(x, y, Color.GREEN);
	}
	
	// String "A" represents an alive cell.
	@Override
	public String toString(){
		return "A";
	}
	
	@Override
	public void calculateNextState(ArrayList<Cell> neighborList) {
		neighbors = neighborList;
		nextState = determineNextState();
	}
		
	// This method, which is called by calculateNextState, returns true if 2 or 3 neighboring cells are alive, setting the next state of the cell to alive (true).
	// Returns false if less than 2 or more than 3 neighboring cells are alive, setting the next state of the cell to dead (false).
	private boolean determineNextState() {
		int numAlive = 0;
		for (Cell c: neighbors) {
			if (c instanceof AliveCell ) {
				numAlive++;
			}
		}
		// If less than 2 neighboring cells are alive, cell dies of under population.
		if (numAlive < 2) {
			return false;
		}
		// If cell has 2 or 3 neighboring alive cells, cell stays alive.
		if (numAlive == 2 || numAlive == 3) {
			return true;
		}
		// If cell has more than 3 neighboring alive cells, cell dies of over population.
		else {
			return false;
		}
	}
	
	//getters
		public boolean getNextState() {
			return nextState;
		}

}

