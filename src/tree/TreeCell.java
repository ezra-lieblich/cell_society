package tree;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.Cell;

/**
 * 
 * @author Christopher Lu
 * Sets properties like color, probCatch, and nextState for the Tree cell. 
 */

public class TreeCell extends Cell{
	private List<Cell> neighbors;
	public boolean nextState;
	private double probCatch;
	
	// True = Burning, false = Tree
	
	// Initializes Tree cell to the x and y coordinates, sets color to green.
	public TreeCell(int x, int y, double probCatch) {
		super(x, y, Color.GREEN);
		this.probCatch = probCatch;
	}
	
	/**
	 * A TreeCell can only catch fire if one of its neighbors is burning. This method checks if one of its neighbors is burning.
	 * @return
	 */
	private boolean nearFire() {
		for (Cell c: neighbors) {
			if (c instanceof BurningCell)  {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void calculateNextState(List<Cell> neighborList) {
		neighbors = neighborList;
		nextState = determineNextState();
	}
	
	/**
	 * Based on the probCatch value, this tree cell will either burn or not burn the next turn.
	 * @return
	 */
	private boolean determineNextState() {
		if (nearFire()) {
			double rand = Math.random();
			return (rand < probCatch);
		}
		else {
			return false;
		}
	}
	
	//getters
	public boolean getNextState() {
		return nextState;
	}
	
}