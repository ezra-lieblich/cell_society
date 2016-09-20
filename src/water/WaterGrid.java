package water;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import cellsociety_team01.Grid;

public class WaterGrid extends Grid {

	@Override
	public ArrayList<Cell> getNeighbors(int x, int y) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				neighbors.add(getGridIndex(adjustIndex(i, getRows()),adjustIndex(j, getColumns())));
			}
		}
		return neighbors;
	}

	private int adjustIndex(int index, int maxIndex) {
		if (index >= maxIndex)
			return index % maxIndex;
		if (index < 0)
			return index + maxIndex;
		return index;
	}
}
