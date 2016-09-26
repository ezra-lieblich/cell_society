package tree;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import cellsociety_team01.Cell;

public class TreeCell extends Cell{
	private ArrayList<Cell> neighbors;
	public boolean nextState;
	private double probCatch;
	
	// True = Burning, false = Tree
	
	// Initializes Tree cell to the x and y coordinates, sets color to green.
	public TreeCell(int x, int y, double probCatch) {
		super.init(x, y);
		color = Color.GREEN;
		this.probCatch = probCatch;
	}
	
	// String "T" represents a tree cell.
	@Override
	public String toString(){
		return "T";
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
	public void calculateNextState(ArrayList<Cell> neighborList) {
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


