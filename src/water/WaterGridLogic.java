package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.Grid;
import cellsociety_team01.GridLogic;

public class WaterGridLogic extends GridLogic {
	public static int TURNS_PER_FISH_REPRODUCE = 2;
	public static int TURNS_FOR_SHARK_DEATH = 2;
	public static int TURNS_PER_SHARK_REPRODUCE = 5;

	public WaterGridLogic(Grid grid) {
		this.grid = grid;
	}

	@Override
	public void step() {
		
		// test print
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				System.out.print(grid.getGridIndex(r, c).toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
		
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

		setFishGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY());

		if (!fish.isReproducing()) {
			setEmptyGridIndex(fish.getCoordsX(), fish.getCoordsY());
		}
	}

	private void updateShark(Shark shark) {
		if (shark.isDead()) {
			setEmptyGridIndex(shark.getCoordsX(), shark.getCoordsY());
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

		setSharkGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY());

		if (!shark.isReproducing()) {
			setEmptyGridIndex(shark.getCoordsX(), shark.getCoordsY());
		}

	}

	// set methods for all classes
	private void setFishGridIndex(int x, int y) {
		grid.setGridIndex(new Fish(x, y), x, y);
	}

	private void setEmptyGridIndex(int x, int y) {
		grid.setGridIndex(new EmptyCell(x, y), x, y);
	}

	private void setSharkGridIndex(int x, int y) {
		grid.setGridIndex(new Shark(x, y), x, y);
	}

}
