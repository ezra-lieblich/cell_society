package tree;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.Cell;

/**
@author Christopher Lu
* Sets properties like the color and its nextState boolean. In this simulation, false means empty. Because a cell burns for only one turn and then becomes empty, the calculateNextState returns false to represent this mechanic.
**/

public class BurningCell extends Cell{
	private boolean nextState;
	
	// False = empty.
	
	// Initializes Burning cell to the x and y coordinates, sets color to red.
	public BurningCell(int x, int y) {
		super(x, y, Color.RED);
	}
	
	// The tree can only burn for one round. After one round, it turns into empty cell.
	@Override
	public void calculateNextState(List<Cell> neighborList) {
		nextState = false;
	}
	
	//getters
	public boolean getNextState() {
		return nextState;
	}
}


