package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class Fish extends Cell {
	private int currentReproduceTurn;
	private ArrayList<Cell> neighbors;
	private Cell nextLocation;

	private boolean isReproducing;

	public Fish(int x, int y) {
		super(x, y, Color.GREENYELLOW);
		currentReproduceTurn = 0;
	}

	@Override
	public void calculateNextState(ArrayList<Cell> neighborList) {
		neighbors = neighborList;

		isReproducing = handleTurn();
		nextLocation = determineNextLocation();

	}

	private boolean handleTurn() {
		currentReproduceTurn++;
		if (currentReproduceTurn == WaterGridLogic.TURNS_PER_FISH_REPRODUCE) {
			currentReproduceTurn = 0;
			return true;
		}
		return false;
	}

	private Cell determineNextLocation() {
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for (Cell c : neighbors) {
			if (c instanceof EmptyCell)
				temp.add(c);
		}
		//dont move if nowhere to move
		if (temp.size() == 0)
			return null;
		//move to random cell;
		return temp.get((int) (Math.random() * temp.size()));
	}	
	
	//getters
	public Cell getNextLocation() {
		return nextLocation;
	}

	public boolean isReproducing() {
		return isReproducing;
	}


	
}
