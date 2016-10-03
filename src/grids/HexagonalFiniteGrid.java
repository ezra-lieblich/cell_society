package grids;

import java.util.ArrayList;

import cellsociety_team01.AlertBox;
import cellsociety_team01.Cell;

public class HexagonalFiniteGrid extends BasicFiniteGrid {

	public HexagonalFiniteGrid(int x, int y, String neighbor) {
		super(x, y, "all");
		if(neighbor.equals("cardinal")||neighbor.equals("diagonal"))
			AlertBox.displayError("Hexagonal grids may not implement cardinal nor diagonal neighbors");
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

	ArrayList<Cell> getHexagonalNeighbors(int x, int y, int[] xModifier, int[] yModifier) {
		neighbors = new ArrayList<Cell>();
		for (int i = 0; i < xModifier.length; i++) {
			if (validIndex(x + xModifier[i], y + yModifier[i]))
				addIndex(x + xModifier[i], y + yModifier[i]);
		}
		return neighbors;
	}

}
