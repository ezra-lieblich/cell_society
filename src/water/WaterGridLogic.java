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

		//
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
		if(cell instanceof EmptyCell) return;
		if(cell instanceof Fish){
			return;
		}
		if(cell instanceof Shark){
			return;
		}
	}

}
