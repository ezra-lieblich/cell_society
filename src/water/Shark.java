package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class Shark extends Cell {

	private int currentReproduceTurn;
	private int turnsNoFish;

	private ArrayList<Cell> neighbors;
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
	public void calculateNextState(ArrayList<Cell> neighborList) {
		neighbors = neighborList;

		isReproducing = handleTurn();
		nextLocation = determineNextLocation();
		isDead = checkDeath();

	}

	private boolean checkDeath() {
		return turnsNoFish >= turnsPerSharkDeath;
	}

	private boolean handleTurn() {
		currentReproduceTurn++;
		if (currentReproduceTurn == turnsPerSharkReproduce) {
			currentReproduceTurn = 0;
			return true;
		}
		return false;
	}

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
