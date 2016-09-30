package water;

import java.util.ArrayList;
import java.util.HashMap;

import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import grids.BasicToroidalGrid;

public class WaterGridLogic extends GridLogic {


	private BasicFiniteGrid grid;

	public WaterGridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
		cellSizes = new HashMap<String, Integer>();
		cellSizes.put(Fish.class.getName(), 0);
		cellSizes.put(Shark.class.getName(), 0);
	}

	@Override
	public void step() {
		cellSizes.clear();
		// // test print
		// for (int r = 0; r < grid.getRows(); r++) {
		// for (int c = 0; c < grid.getColumns(); c++) {
		// System.out.print(grid.getGridIndex(r, c).toString() + " ");
		// }
		// System.out.println();
		// }
		// System.out.println();

		// check state
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				checkState(grid.getGridIndex(c, r), grid.getNeighbors(c, r));
			}
		}

		// update grid based on states
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				updateGrid(grid.getGridIndex(c, r));
			}
		}

	}

	private void checkState(Cell cell, ArrayList<Cell> neighbors) {
		cell.calculateNextState(neighbors);
	}

	private void updateGrid(Cell cell) {
		if (cell instanceof EmptyCell)
			return;
		if (cell instanceof Fish) {
			Fish fish = (Fish) cell;
			updateFish(fish);
			updateCellSizes(fish.getClass().getName());
			return;
		}
		if (cell instanceof Shark) {
			Shark shark = (Shark) cell;
			updateShark(shark);
			updateCellSizes(shark.getClass().getName());
			return;
		}
	}


	private void updateFish(Fish fish) {
		Cell nextLocation = fish.getNextLocation();
		if (nextLocation == null || (nextLocation.getCoordsX()==fish.getCoordsX()&&nextLocation.getCoordsY()==fish.getCoordsY()))
			return;

		// resolve conflicts
		if (!(grid.getGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY()) instanceof EmptyCell)) {
			checkState(fish, grid.getNeighbors(fish.getCoordsX(), fish.getCoordsY()));
			updateFish(fish);
			return;
		}

		// save previous coords because it is about to change
		int fishX = fish.getCoordsX();
		int fishY = fish.getCoordsY();

		grid.moveCellGridIndex(fish.getCoordsX(), fish.getCoordsY(), nextLocation.getCoordsX(),
				nextLocation.getCoordsY());

		if (fish.isReproducing()) {
			grid.setGridIndex(new Fish(fishX, fishY), fishX, fishY);
		}
	}

	private void updateShark(Shark shark) {
		if (shark.isDead()) {
			grid.setGridIndex(new EmptyCell(shark.getCoordsX(), shark.getCoordsY()), shark.getCoordsX(), shark.getCoordsY());
			return;
		}

		Cell nextLocation = shark.getNextLocation();

		if (nextLocation == null || (nextLocation.getCoordsX()==shark.getCoordsX()&&nextLocation.getCoordsY()==shark.getCoordsY()))
			return;

		// resolve conflicts
		if (grid.getGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY()) instanceof Shark) {
			checkState(shark, grid.getNeighbors(shark.getCoordsX(), shark.getCoordsY()));
			updateShark(shark);
			return;
		}
		// save previous coords because it is about to change
		int sharkX = shark.getCoordsX();
		int sharkY = shark.getCoordsY();

		grid.moveCellGridIndex(shark.getCoordsX(), shark.getCoordsY(), nextLocation.getCoordsX(),
				nextLocation.getCoordsY());

		if (shark.isReproducing()) {
			grid.setGridIndex(new Shark(sharkX, sharkY), sharkX, sharkY);
		}

	}

	// set methods for all classes

}
