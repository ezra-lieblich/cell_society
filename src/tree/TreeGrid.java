package tree;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.BasicGrid;

public class TreeGrid extends BasicGrid{
	
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

	private Cell getNeighbor(int i, int j) {
		if (addBoundaryEmpty(i, getRows()) == true || addBoundaryEmpty(j, getColumns()) == true) {
			EmptyCell addthis = new EmptyCell(i,j);
			return addthis;
		}
		return getGridIndex(i, j);
	}
	
	private boolean addBoundaryEmpty(int index, int maxIndex) {
		if (index >= maxIndex) {
			return true;
		}
		if (index <= 0) {
			return true;
		}
		return false;
		}
	}	

