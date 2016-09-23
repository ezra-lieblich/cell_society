package tree;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import cellsociety_team01.Cell;

public class BurningCell extends Cell{
	private boolean nextState;
	
	// False = Burning, true = Empty.
	
	// Initializes Burning cell to the x and y coordinates, sets color to green.
	public BurningCell(int x, int y) {
		super.init(x, y);
		color = Color.RED;
	}
	
	// String "T" represents a tree cell.
	@Override
	public String toString(){
		return "B";
	}
	
	@Override
	public void calculateNextState(ArrayList<Cell> neighborList) {
		nextState = false;
	}
	
	//getters
	public boolean getNextState() {
		return nextState;
	}
}


