package tree;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import tree.EmptyCell;
import tree.TreeCell;
import tree.BurningCell;

public class TreeGridLogic extends GridLogic {
		
	public TreeGridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
	}

	@Override
	public void step() {
		
		// test print
//		for (int r = 0; r < grid.getRows(); r++) {
//			for (int c = 0; c < grid.getColumns(); c++) {
//				System.out.print(grid.getGridIndex(r, c).toString() + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
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
	private void checkState(Cell cell, ArrayList<Cell> neighbors) {
		cell.calculateNextState(neighbors);
	}
	
	private void updateGrid(Cell cell) {
		if (cell instanceof TreeCell) {
			updateTreeCell((TreeCell) cell);
			return;
		}
		if (cell instanceof BurningCell) {
			updateBurningCell((BurningCell) cell);
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
