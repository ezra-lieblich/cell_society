package tree;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import grids.BasicFiniteGrid;

public class TreeGrid extends BasicFiniteGrid{
	
	public TreeGrid(int x, int y) {
		grid = new Cell[x][y];
	}
	
	// In game of life, ANY adjacent cell, including diagonal neighbors, is considered a neighbor, so there are 8 entries in the neighbors list.
	@Override
	public ArrayList<Cell> getNeighbors(int x, int y) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();

		neighbors.add(getNeighbor(x, y + 1));
		neighbors.add(getNeighbor(x, y - 1));
		neighbors.add(getNeighbor(x + 1, y));
		neighbors.add(getNeighbor(x - 1, y));

		return neighbors;
	}

	private int adjustIndex(int index, int maxIndex) {
		if (index >= maxIndex)
			return index % maxIndex;
		if (index < 0)
			return index + maxIndex;
		return index;
	}
	
	// Returns cell at specified coordinates (i, j).
	private Cell getNeighbor(int i, int j) {
		return getGridIndex(adjustIndex(i, getRows()), adjustIndex(j, getColumns()));
	}	
}
