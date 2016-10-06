package grids;

import java.util.ArrayList;

import cellsociety_team01.Cell;

/**
 * This class implements the hexagonal finite grid and adds wrapped boundaries
 * 
 * @author ericsong
 *
 */
public class HexagonalToroidalGrid extends HexagonalFiniteGrid {

	public HexagonalToroidalGrid(int x, int y, String neighbor) {
		super(x, y, neighbor);
	}

	@Override
	ArrayList<Cell> getHexagonalNeighbors(int x, int y, int[] xModifier, int[] yModifier) {
		neighbors = new ArrayList<Cell>();
		for (int i = 0; i < xModifier.length; i++) {
			addIndex(x + xModifier[i], y + yModifier[i]);
		}
		return neighbors;
	}

	@Override
	protected void addIndex(int x, int y) {
		neighbors.add(getAdjustedNeighbor(x, y));
	}

	private Cell getAdjustedNeighbor(int i, int j) {
		return getGridIndex(adjustIndex(i, getRows()), adjustIndex(j, getColumns()));
	}

	private int adjustIndex(int index, int maxIndex) {
		if (index >= maxIndex)
			return index % maxIndex;
		if (index < 0)
			return index + maxIndex;
		return index;
	}

}
