package water;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

/**
 * Fish class and logic for the water world simulation
 * 
 * @author ericsong
 *
 */
public class Fish extends Cell {
	private int currentReproduceTurn;
	private List<Cell> neighbors;
	private Cell nextLocation;

	private boolean isReproducing;
	private int turnsPerFishReproduce;

	public Fish(int x, int y, int reproduce) {
		super(x, y, Color.GREENYELLOW);
		turnsPerFishReproduce = reproduce;
		currentReproduceTurn = 0;
	}

	@Override
	public void calculateNextState(List<Cell> neighborList) {
		neighbors = neighborList;

		isReproducing = handleTurn();
		nextLocation = determineNextLocation();

	}

	/**
	 * checks to see if fish should reproduce
	 * 
	 * @return
	 */
	private boolean handleTurn() {
		currentReproduceTurn++;
		if (currentReproduceTurn == turnsPerFishReproduce) {
			currentReproduceTurn = 0;
			return true;
		}
		return false;
	}

	/**
	 * checks to see its next location based on current neighbors
	 * 
	 * @return
	 */
	private Cell determineNextLocation() {
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for (Cell c : neighbors) {
			if (c instanceof EmptyCell)
				temp.add(c);
		}
		// dont move if nowhere to move
		if (temp.size() == 0)
			return null;
		// move to random cell;
		return temp.get((int) (Math.random() * temp.size()));
	}

	// getters
	public Cell getNextLocation() {
		return nextLocation;
	}

	public boolean isReproducing() {
		return isReproducing;
	}

}
