package tree;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.Cell;

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