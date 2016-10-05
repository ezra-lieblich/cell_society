package life;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import life.DeadCell;
import life.AliveCell;

/**
@author Christopher Lu
Checks current states and then updates the grid based on the states. Uses the NeighborList created in LifeGrid to calculate the next states.
**/

public class LifeGridLogic extends GridLogic {
	
	public LifeGridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
		cellSizes = new HashMap<String, Integer>();
		cellSizes.put(AliveCell.class.getName(), 0);
	}

	@Override
	public void step() {
		cellSizes.clear();
		
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
	
	// Calculates the next state of the cell based on neighbors.
	private void checkState(Cell cell, List<Cell> neighbors) {
		cell.calculateNextState(neighbors);
	}
	
	private void updateGrid(Cell cell) {
		if (cell instanceof DeadCell) {
			updateDeadCell((DeadCell) cell);
			return;
		}
		if (cell instanceof AliveCell) {
			AliveCell alive = (AliveCell) cell;
			updateAliveCell(alive);
			updateCellSizes(alive.getClass().getName());
			return;
		}
	}
	
	// If cell is a dead cell and next state is false, the cell stays dead. If the cell is a dead cell and next state is true, the dead cell is replaced by an alive cell.
	private void updateDeadCell(DeadCell dead) {
		
		if (dead.nextState == true) {
			setAliveGridIndex(dead.getCoordsX(), dead.getCoordsY());
		}
		if (dead.nextState == false) {
			return;
		}
	}
	
	// If cell is an alive cell and next state is true, the cell stays alive. If the cell is a dead cell and next state is false, the alive cell is replaced by a dead cell.
	private void updateAliveCell(AliveCell alive) {
		
		if (alive.nextState == true) {
			return;
		}
		
		if (alive.nextState == false) {
			setDeadGridIndex(alive.getCoordsX(), alive.getCoordsY());
		}
		
	}
	
	// set methods for all classes
	private void setAliveGridIndex(int x, int y) {
		grid.setGridIndex(new AliveCell(x, y), x, y);
	}

	private void setDeadGridIndex(int x, int y) {
		grid.setGridIndex(new DeadCell(x, y), x, y);
	}

}
