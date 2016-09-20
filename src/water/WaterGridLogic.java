package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.Grid;
import cellsociety_team01.GridLogic;

public class WaterGridLogic extends GridLogic {

	@Override
	public void step(double elapsedTime) {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; r < grid.getColumns(); c++) {
				performLogic(grid.getGridIndex(r, c), grid.getNeighbors(r, c));
			}
		}
	}
	
	private void performLogic(Cell cell, ArrayList<Cell> neighbors){
		cell.calculateNextState(neighbors);
	}

}
