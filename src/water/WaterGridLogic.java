package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import grids.BasicToroidalGrid;

public class WaterGridLogic extends GridLogic {
	protected static int TURNS_PER_FISH_REPRODUCE = 2;
	protected static int TURNS_FOR_SHARK_DEATH = 2;
	protected static int TURNS_PER_SHARK_REPRODUCE = 10;

	private BasicFiniteGrid grid;

	public WaterGridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
	}

	@Override
	public void step() {

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
				checkState(grid.getGridIndex(r, c), grid.getNeighbors(r, c));
			}
		}

		// update grid based on states
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				updateGrid(grid.getGridIndex(r, c));
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
			updateFish((Fish) cell);
			return;
		}
		if (cell instanceof Shark) {
			updateShark((Shark) cell);
			return;
		}
	}

	private void updateFish(Fish fish) {
		Cell nextLocation = fish.getNextLocation();
		if (nextLocation == null)
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

		if (nextLocation == null)
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
