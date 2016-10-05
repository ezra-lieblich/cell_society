package factories;

import java.util.Map;

import cellsociety_team01.AlertBox;
import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import life.AliveCell;
import life.DeadCell;

/**
 * Creates grid object with Life-type cells from given input. Also has error checking and in the form of a pop up box when values are either missing or invalid. Sets random default values if values are missing/invalid.
 *
 * @author Christopher Lu
 * @author Eric Song
 */
public class LifeGridFactory extends GridFactory {

	public LifeGridFactory(String cellShape, String bounds, String r, String c, String neighbors) {
		super(cellShape, bounds, r, c, neighbors);
	}

	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentAlive = doubleParseErrors(map.get(myViewResources.getString("PercentAlive")));
		if (checkPercentError(percentAlive)) {
			percentAlive = doubleParseErrors(myDefaultValResources.getString("percentAlive"));
			String message = String.format("Invalid or missing user values: percentAlive. Default values percentAlive = %f will be used.", percentAlive);
			AlertBox.displayError(message);
		}
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