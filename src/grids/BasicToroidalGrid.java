package grids;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import water.EmptyCell;

public class BasicToroidalGrid extends BasicFiniteGrid {

	public BasicToroidalGrid(int x, int y) {
		super(x,y);
	}
	
	
	@Override
	protected void addIndex(int x, int y){
		neighbors.add(getAdjustedNeighbor(x,y));
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
