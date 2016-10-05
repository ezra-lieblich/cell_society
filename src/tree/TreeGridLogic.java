package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import tree.EmptyCell;
import tree.TreeCell;
import tree.BurningCell;

/**
 * 
 * @author Christopher Lu
 * @author Eric Song
 * Checks current states and then updates the grid based on the states. Uses the NeighborList created in TreeGrid to calculate the next states.
 */

public class TreeGridLogic extends GridLogic {
		
	public TreeGridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
		cellSizes = new HashMap<String, Integer>();
		cellSizes.put(BurningCell.class.getName(), 0);
		cellSizes.put(TreeCell.class.getName(), 0);
	}

	/**
	 * Updates grid based on states of each cell which is done by checking the neighbors. 
	 */
	@Override
	public void step() {
		cellSizes.clear();
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				checkState(grid.getGridIndex(r, c), grid.getNeighbors(r, c));
			}
		}
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
		if (cell instanceof TreeCell) {
			TreeCell tree = (TreeCell) cell;
			updateTreeCell(tree);
			updateCellSizes(tree.getClass().getName());
			return;
		}
		if (cell instanceof BurningCell) {
			BurningCell burn = (BurningCell) cell;
			updateBurningCell(burn);
			updateCellSizes(burn.getClass().getName());
			return;
		}
		if (cell instanceof EmptyCell) {
			updateEmptyCell((EmptyCell) cell);
		}
	}
	
	// If cell is a tree cell and next state is false, the cell stays tree. Otherwise, the tree starts burning.
	private void updateTreeCell(TreeCell tree) {
		
		if (tree.nextState == true) {
			setBurningGridIndex(tree.getCoordsX(), tree.getCoordsY());
		}
		if (tree.nextState == false) {
			return;
		}
	}
	
	// If cell is a burning cell its next state is empty.
	private void updateBurningCell(BurningCell burn) {
		setEmptyGridIndex(burn.getCoordsX(), burn.getCoordsY());
	}
	
	private void updateEmptyCell(EmptyCell empty) {
		setEmptyGridIndex(empty.getCoordsX(), empty.getCoordsY());
	}
	
	// set methods for all classes
	private void setBurningGridIndex(int x, int y) {
		grid.setGridIndex(new BurningCell(x, y), x, y);
	}

	private void setEmptyGridIndex(int x, int y) {
		grid.setGridIndex(new EmptyCell(x, y), x, y);
	}

}
