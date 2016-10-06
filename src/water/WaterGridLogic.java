// This entire file is part of my masterpiece.
// Eric Song
// I believe that this class efficiently implements the general parent grid class as well as 
// delegates the next state calculation to the Cell children (Fish and Shark). It then also 
// checks the interactions and collisions between different Cell types to perform the 
// necessary movements to update the grid to the next frame

package water;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import grids.BasicToroidalGrid;

/**
 * The logic class for the water world simulation
 * 
 * @author ericsong
 *
 */
public class WaterGridLogic extends GridLogic {

	private BasicFiniteGrid grid;
	private int fishReproduce;
	private int sharkDeath;
	private int sharkReproduce;

	public WaterGridLogic(BasicFiniteGrid grid, int fishReproduce, int sharkDeath, int sharkReproduce) {
		this.grid = grid;
		this.fishReproduce = fishReproduce;
		this.sharkDeath = sharkDeath;
		this.sharkReproduce = sharkReproduce;
		setupGraph();
	}

	/**
	 * sets up Map to count the number of each type of cell at each time point
	 */
	private void setupGraph() {
		cellSizes = new HashMap<String, Integer>();
		cellSizes.put(Fish.class.getName(), 0);
		cellSizes.put(Shark.class.getName(), 0);
	}

	/*
	 * loops through each cell to check the next state, and then perform the
	 * update
	 */
	@Override
	public void step() {
		cellSizes.clear();

		// check state
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				checkState(grid.getGridIndex(c, r), grid.getNeighbors(c, r));
			}
		}

		// doing in two separate loops ensures that all cells only act based on
		// previous state of neighbors

		// update grid based on states
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Cell cell = grid.getGridIndex(c, r);

				if (cell instanceof Fish)
					updateGrid((Fish) cell);

				else if (cell instanceof Shark)
					updateGrid((Shark) cell);
			}
		}

	}

	/**
	 * each cell knows how to calculate its next state based on its neighbors
	 * list
	 * 
	 * @param cell
	 * @param neighbors
	 */
	private void checkState(Cell cell, List<Cell> neighbors) {
		cell.calculateNextState(neighbors);
	}

	/**
	 * updates the fish and also adds to a counter for graph view
	 * 
	 * @param fish
	 */
	private void updateGrid(Fish fish) {
		updateFish(fish);
		updateCellSizes(fish.getClass().getName());
	}

	/**
	 * updates the shark and also adds to a counter for graph view
	 * 
	 * @param fish
	 */
	private void updateGrid(Shark shark) {
		updateShark(shark);
		updateCellSizes(shark.getClass().getName());
	}

	/**
	 * goes through all possibilities of a fish's next potential state, and also
	 * checks for collisions
	 * 
	 * @param fish
	 */
	private void updateFish(Fish fish) {
		Cell nextLocation = fish.getNextLocation();

		// check to see if movement is needed
		if (!checkMovement(fish, nextLocation))
			return;

		// resolve conflicts
		if (!(grid.getGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY()) instanceof EmptyCell)) {
			checkState(fish, grid.getNeighbors(fish.getCoordsX(), fish.getCoordsY()));
			updateFish(fish);
			return;
		}

		moveAndCheckReproduce(fish, nextLocation);

	}

	/**
	 * goes through all possibilities of a shark's next potential state, and
	 * also checks for collisions
	 * 
	 * @param shark
	 */
	private void updateShark(Shark shark) {

		// replace shark with empty cell
		if (shark.isDead()) {
			grid.setGridIndex(new EmptyCell(shark.getCoordsX(), shark.getCoordsY()), shark.getCoordsX(),
					shark.getCoordsY());
			return;
		}

		Cell nextLocation = shark.getNextLocation();

		// see if movement is needed
		if (!checkMovement(shark, nextLocation))
			return;

		// resolve conflicts
		if (grid.getGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY()) instanceof Shark) {
			checkState(shark, grid.getNeighbors(shark.getCoordsX(), shark.getCoordsY()));
			updateShark(shark);
			return;
		}

		moveAndCheckReproduce(shark, nextLocation);
	}

	/**
	 * check to see if movement is necessary
	 * 
	 * @param nextLocation
	 * @param currObj
	 * @return
	 */
	private boolean checkMovement(Cell currObj, Cell nextLocation) {
		return nextLocation != null && !(nextLocation.getCoordsX() == currObj.getCoordsX()
				&& nextLocation.getCoordsY() == currObj.getCoordsY());
	}

	private void moveAndCheckReproduce(Cell currObj, Cell nextLocation) {
		// save previous coords because it is about to change
		int objX = currObj.getCoordsX();
		int objY = currObj.getCoordsY();

		// move fish or shark
		grid.moveCellGridIndex(currObj.getCoordsX(), currObj.getCoordsY(), nextLocation.getCoordsX(),
				nextLocation.getCoordsY());

		// put a fish where the fish moved from
		if (currObj instanceof Fish && ((Fish) currObj).isReproducing()) {
			grid.setGridIndex(new Fish(objX, objY, fishReproduce), objX, objY);
		}

		if (currObj instanceof Shark && ((Shark) currObj).isReproducing()) {
			grid.setGridIndex(new Shark(objX, objY, sharkDeath, sharkReproduce), objX, objY);
		}
	}

}
