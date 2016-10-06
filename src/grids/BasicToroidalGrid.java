package grids;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import cellsociety_team01.Cell;
import water.EmptyCell;

/**
 * This class extends the finite grid implementation to have wrapped grid edges,
 * and "out of bounds" indices are adjusted
 * 
 * @author ericsong
 *
 */
public class BasicToroidalGrid extends BasicFiniteGrid {

	public BasicToroidalGrid(int x, int y, String neighbor) {
		super(x, y, neighbor);
	}

	@Override
	protected void addIndex(int x, int y) {
		neighbors.add(getAdjustedNeighbor(x, y));
	}

	/**
	 * returns adjusted index values
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private Cell getAdjustedNeighbor(int i, int j) {
		return getGridIndex(adjustIndex(i, getRows()), adjustIndex(j, getColumns()));
	}

	/**
	 * changes index values to other side of grid if out of bounds
	 * 
	 * @param index
	 * @param maxIndex
	 * @return
	 */
	private int adjustIndex(int index, int maxIndex) {
		if (index >= maxIndex)
			return index % maxIndex;
		if (index < 0)
			return index + maxIndex;
		return index;
	}

}
