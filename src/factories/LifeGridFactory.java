package factories;

import java.util.Map;

import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import life.AliveCell;
import life.DeadCell;

/**
 * Creates grid object with Life-type cells from given input.
 *
 * @author Eric Song
 */
public class LifeGridFactory extends GridFactory {

	public LifeGridFactory(String cellShape, String bounds, String r, String c) {
		super(cellShape, bounds, r, c);
	}

	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentAlive = doubleParseErrors(map.get(myResources.getString("PercentAlive")));
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				double ranGen = Math.random();
				if (ranGen <= percentAlive)
					setGridIndex(new AliveCell(c, r), r, c);
				else {
					setGridIndex(new DeadCell(c, r), r, c);
				}
			}
		}
		return getGrid();
	}
}