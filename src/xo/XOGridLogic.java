package xo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cellsociety_team01.Cell;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;

public class XOGridLogic extends GridLogic {
	private List<Neighbor> upsetNeighbors;
	private List<Cell> availableSpaces;
	private double percentSimilar;

	public XOGridLogic(BasicFiniteGrid grid, double p) {
		this.grid = grid;
		cellSizes = new HashMap<String, Integer>();
		cellSizes.put(Group1.class.getName(), 0);
		cellSizes.put(Group2.class.getName(), 0);
		percentSimilar = p;
	}

	/**
	 * Calculates the next state of each cell and then updates the grid by
	 * moving unsatisfied cells to random empty cells
	 */
	public void step() {
		cellSizes.clear();
		upsetNeighbors = new ArrayList<Neighbor>();
		availableSpaces = new ArrayList<Cell>();
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				updateStatus(r, c);
			}
		}
		updateGrid();
	}

	/**
	 * Calculates the next state and adds unsatisfied cells to the list and also
	 * keeps track of the empty cells
	 * 
	 * @param row
	 *            row of the cell that we are looking at
	 * @param col
	 *            column of the cell that we are looking at
	 */
	private void updateStatus(int row, int col) {
		Cell cell = grid.getGridIndex(row, col);
		cell.calculateNextState(grid.getNeighbors(row, col));
		if (cell instanceof Neighbor) {
			Neighbor person = (Neighbor) cell;
			String name = person.getClass().getName();
			if (!cellSizes.containsKey(name)) {
				cellSizes.put(name, 0);
			}
			cellSizes.put(name, cellSizes.get(name) + 1);
			if (!person.Satisfied())
				upsetNeighbors.add(person);
		} else {
			availableSpaces.add(cell);
		}
	}

	/**
	 * Moves all the neighbors that are not satisfied. Chooses a random open
	 * cell and moves there and updates list of open spaces
	 */
	private void updateGrid() {
		for (Neighbor neighbor : upsetNeighbors) {
			Clear vacated_cell = new Clear(neighbor.getCoordsX(), neighbor.getCoordsY());
			grid.setGridIndex(vacated_cell, vacated_cell.getCoordsX(), vacated_cell.getCoordsY());
			Cell new_location = availableSpaces.get((int) (Math.random() * (availableSpaces.size() - 1)));
			int new_x = new_location.getCoordsX();
			int new_y = new_location.getCoordsY();
			if (neighbor.getClass().equals(Group1.class))
				grid.setGridIndex(new Group1(new_x, new_y, percentSimilar), new_x, new_y);
			else
				grid.setGridIndex(new Group2(new_x, new_y, percentSimilar), new_x, new_y);
			availableSpaces.remove(new_location);
			availableSpaces.add(vacated_cell);
		}
	}
}
