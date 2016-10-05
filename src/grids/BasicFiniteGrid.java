package grids;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team01.AlertBox;
import cellsociety_team01.Cell;
import water.EmptyCell;

/**
 * Standard Grid class where there are 8 neighbors, 3 top and bottom and one on each side.
 * Uses an array of arrays of cells
 * @author ezra
 * @author Eric
 */
public class BasicFiniteGrid {
	protected Cell[][] grid;
	protected ArrayList<Cell> neighbors;
	protected String neighborType;

	public BasicFiniteGrid() {
	}

	/**
	 * Creates the initial grid
	 * @param x #of x cells
	 * @param y #of y cells
	 * @param neighbor Type of neighbors
	 */
	public BasicFiniteGrid(int x, int y, String neighbor) {
		if (!neighbor.equals("all") && !neighbor.equals("cardinal") && !neighbor.equals("diagonal")) {
			AlertBox.displayError("Missing or invalid neighbor parameter. Default is set to all neighbors");
			neighborType = "all";
		} else
			neighborType = neighbor;
		grid = new Cell[x][y];
	}

	/**
	 * Gets the cell at a particular index
	 * @param x x coord
	 * @param y y coord
	 * @return Cell at that index
	 */
	public Cell getGridIndex(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Logic calls this to change its state at particular index based off of simulation
	 * @param cell New cell to be set
	 * @param x x coord
	 * @param y y coord
	 */
	public void setGridIndex(Cell cell, int x, int y) {
		grid[x][y] = cell;
	}
	
	/**
	 * Gets the 8 neighbors pending they are not out of bounds
	 * @param x x coord
	 * @param y y coord
	 * @return Lis of neighbors
	 */
	public List<Cell> getNeighbors(int x, int y) {
		neighbors = new ArrayList<Cell>();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (validIndex(i, j) && !(i == x && j == y))
					addIndex(i, j);
			}
		}
		return neighbors;
	}

	public void moveCellGridIndex(int fromX, int fromY, int toX, int toY) {
		Cell temp = getGridIndex(fromX, fromY);
		temp.setCoords(toX, toY);
		setGridIndex(temp, toX, toY);
		setGridIndex(new EmptyCell(fromX, fromY), fromX, fromY);
	}

	/**
	 * Adds cell at index x,y to neighbor list
	 * @param x
	 * @param y
	 */
	void addIndex(int x, int y) {
		neighbors.add(grid[x][y]);
	}

	/**
	 * Checks if it is a valiid index
	 * @param x
	 * @param y
	 * @return
	 */
	boolean validIndex(int x, int y) {
		return (x >= 0 && x < getColumns()) && (y >= 0 && y < getRows());
	}

	/**
	 * Gets the number of columns in the grid
	 * @return
	 */
	public int getColumns() {
		return grid[0].length;
	}

	/**
	 * Gets the number of rows in the grid
	 * @return
	 */
	public int getRows() {
		return grid.length;
	}
}
