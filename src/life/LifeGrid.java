package life;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.Grid;

public class LifeGrid extends Grid{
	
	public LifeGrid(int x, int y) {
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
		neighbors.add(getNeighbor(x+1, y+1));
		neighbors.add(getNeighbor(x-1, y-1));
		neighbors.add(getNeighbor(x+1, y-1));
		neighbors.add(getNeighbor(x-1, y+1));

		return neighbors;
	}

	// Returns cell at specified coordinates (i, j).
	private Cell getNeighbor(int i, int j) {
		return getGridIndex(i, j);
	}	
}
