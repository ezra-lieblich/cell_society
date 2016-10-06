package water;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

/**
 * Shark object and logic for water world simuation
 * @author ericsong
 *
 */
public class Shark extends Cell {

	private int currentReproduceTurn;
	private int turnsNoFish;

	private List<Cell> neighbors;
	private Cell nextLocation;

	private boolean isReproducing;
	private boolean isDead;
	
	private int turnsPerSharkDeath;
	private int turnsPerSharkReproduce;
	


	public Shark(int x, int y, int death, int reproduce) {
		super(x, y, Color.ORANGE);		
		turnsPerSharkDeath = death;
		turnsPerSharkReproduce = reproduce;
		currentReproduceTurn = 0;
		turnsNoFish = 0;
	}

	@Override
	public void calculateNextState(List<Cell> neighborList) {
		neighbors = neighborList;

		isReproducing = handleTurn();
		nextLocation = determineNextLocation();
		isDead = checkDeath();

	}

	/**
	 * checks and see if shark should die
	 * @return
	 */
	private boolean checkDeath() {
		return turnsNoFish >= turnsPerSharkDeath;
	}

	/**
	 * checks to see if shark reproduces
	 * @return
	 */
	private boolean handleTurn() {
		currentReproduceTurn++;
		if (currentReproduceTurn == turnsPerSharkReproduce) {
			currentReproduceTurn = 0;
			return true;
		}
		return false;
	}

	/**
	 * checks where its next location should be based on its neighbors
	 * @return
	 */
	private Cell determineNextLocation() {
		ArrayList<Cell> tempFish = new ArrayList<Cell>();
		ArrayList<Cell> tempEmpty = new ArrayList<Cell>();

		for (Cell c : neighbors) {
			if (c instanceof Fish)
				tempFish.add(c);
			if (c instanceof EmptyCell)
				tempEmpty.add(c);
		}
		// if no fish then move like fish
		if (tempFish.size() == 0) {
			turnsNoFish++;
			return checkEmptyMovement(tempEmpty);
		}
		// return random fish
		turnsNoFish = 0;
		return tempFish.get((int) (Math.random() * tempFish.size()));
	}

	/**
	 * move to empty cell
	 * @param tempEmpty
	 * @return
	 */
	private Cell checkEmptyMovement(ArrayList<Cell> tempEmpty) {
		// dont move if nowhere to move
		if (tempEmpty.size() == 0)
			return null;
		// move to random cell;
		return tempEmpty.get((int) (Math.random() * tempEmpty.size()));
	}
		

	//getters
	public Cell getNextLocation() {
		return nextLocation;
	}

	public boolean isReproducing() {
		return isReproducing;
	}

	public boolean isDead() {
		return isDead;
	}


}
