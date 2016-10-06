package grids;

import java.util.ArrayList;

import cellsociety_team01.AlertBox;
import cellsociety_team01.Cell;

/**
 * Hexagonal grid has slightly different logic to determine its neighbors, but
 * still uses a 2D array
 * 
 * @author ericsong
 *
 */
public class HexagonalFiniteGrid extends BasicFiniteGrid {

	/**
	 * checks to make sure that the neighbor parameter requested is not cardinal
	 * or diagonal, displays error to user if so
	 * 
	 * @param x
	 * @param y
	 * @param neighbor
	 */
	public HexagonalFiniteGrid(int x, int y, String neighbor) {
		super(x, y, "all");
		if (neighbor.equals("cardinal") || neighbor.equals("diagonal"))
			AlertBox.displayError("Hexagonal grids may not implement cardinal nor diagonal neighbors");
		if (!neighbor.equals("all"))
			AlertBox.displayError("Missing or invalid neighbor parameter. Default is set to all neighbors");

	}

	public ArrayList<Cell> getNeighbors(int x, int y) {
		int[] xModifier = { 0, 1, 1, 0, -1, 1 };
		if (x % 2 == 0) {
			// from 12 o clock clockwise
			int[] yModifier = { -1, 0, 1, 1, 1, 0 };
			return getHexagonalNeighbors(x, y, xModifier, yModifier);
		} else {
			// from 12 o clock clockwise
			int[] yModifier = { -1, -1, 0, 1, 0, -1 };
			return getHexagonalNeighbors(x, y, xModifier, yModifier);
		}
	}

	/**
	 * the modifier values change the indices of the neighbors based on the
	 * column and row the cell is in
	 * 
	 * @param x
	 * @param y
	 * @param xModifier
	 * @param yModifier
	 * @return
	 */
	ArrayList<Cell> getHexagonalNeighbors(int x, int y, int[] xModifier, int[] yModifier) {
		neighbors = new ArrayList<Cell>();
		for (int i = 0; i < xModifier.length; i++) {
			if (validIndex(x + xModifier[i], y + yModifier[i]))
				addIndex(x + xModifier[i], y + yModifier[i]);
		}
		return neighbors;
	}

}
