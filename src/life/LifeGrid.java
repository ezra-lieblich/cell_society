package life;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import grids.BasicFiniteGrid;

/**
 * Creates list of neighbors for each cell. This is used to determine next state.
 * @author Christopher Lu
 *
 */

public class LifeGrid extends BasicFiniteGrid{
	
	public LifeGrid(int x, int y) {
		grid = new Cell[x][y];
	}
	
	/**
	 * Adds all adjacent cells to the list of neighbors.
	 */
	@Override
	public ArrayList<Cell> getNeighbors(int x, int y) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();

		neighbors.add(getNeighbor(x, y + 1));
		neighbors.add(getNeighbor(x, y - 1));
		neighbors.add(getNeighbor(x + 1, y));
		neighbors.add(getNeighbor(x - 1, y));
		neighbors.add(getNeighbor(x+1, y+1));
		neighbors.add(getNeighbor(x-1, y-1));
		neighbors.add(getNeighbor(x+1, y-1));
		neighbors.add(getNeighbor(x-1, y+1));

		return neighbors;
	}

	private Cell getNeighbor(int i, int j) {
		if (addBoundaryEmpty(i, getRows()) == true || addBoundaryEmpty(j, getColumns()) == true) {
			DeadCell addthis = new DeadCell(i,j);
			return addthis;
		}
		return getGridIndex(i, j);
	}

	private boolean addBoundaryEmpty(int index, int maxIndex) {
		if (index >= maxIndex) {
			return true;
		}
		if (index < 0) {
			return true;
		}
		return false;
		}
	}

