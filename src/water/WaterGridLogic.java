package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.Grid;
import cellsociety_team01.GridLogic;

public class WaterGridLogic extends GridLogic {
	public static int TURNS_PER_FISH_REPRODUCE;
	public static int TURNS_FOR_SHARK_DEATH;
	public static int TURNS_PER_SHARK_REPRODUCE;

	@Override
	public void step(double elapsedTime) {
		// check state
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; r < grid.getColumns(); c++) {
				checkState(grid.getGridIndex(r, c), grid.getNeighbors(r, c));
			}
		}

		// update grid based on states
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; r < grid.getColumns(); c++) {
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

		//resolve conflicts
		if(!(grid.getGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY()) instanceof EmptyCell)){
			checkState(fish,grid.getNeighbors(fish.getCoordsX(), fish.getCoordsY()));
			updateFish(fish);
			return;
		}
		
		setFishGridIndex(nextLocation.getCoordsX(), nextLocation.getCoordsY());

		if (!fish.isReproducing()) {
			setEmptyGridIndex(fish.getCoordsX(), fish.getCoordsY());
		}
	}

	private void setFishGridIndex(int x, int y) {
		grid.setGridIndex(new Fish(x, y), x, y);
	}

	private void setEmptyGridIndex(int x, int y) {
		grid.setGridIndex(new EmptyCell(x, y), x, y);
	}

	private void setSharkGridIndex(int x, int y) {
		grid.setGridIndex(new Shark(x, y), x, y);
	}

	private void updateShark(Shark shark) {
		if (shark.isDead()) {
			int x = shark.getCoordsX();
			int y = shark.getCoordsY();
			grid.setGridIndex(new EmptyCell(x, y), x, y);
			return;
		}

	}

	private void updateCell(Cell cell) {

	}

}
