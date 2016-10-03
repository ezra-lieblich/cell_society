package factories;

import java.util.Map;

import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import xo.Clear;
import xo.Group1;
import xo.Group2;

/**
 * Creates grid object with XO-type cells from given input.
 *
 * @author Eric Song
 */
public class XOGridFactory extends GridFactory {

	public XOGridFactory(String cellShape, String bounds, String r, String c) {
		super(cellShape, bounds, r, c);
	}


	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentX = doubleParseErrors(map.get("percentX"));
		double percentO = doubleParseErrors(map.get("percentO"));
		double similarPercentage = doubleParseErrors(map.get("similarPercentage"));
		if (checkPercentError(percentX + percentO)) {
			percentX = Math.random() * .5;
			percentO = Math.random() * .5;
		}
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				double ranGen = Math.random();
				if (ranGen <= percentX)
					setGridIndex(new Group1(c,r, similarPercentage), r, c);
				else if (percentX < ranGen && ranGen <= percentX + percentO)
					setGridIndex(new Group2(c,r, similarPercentage), r, c);
				else
					setGridIndex(new Clear(c,r), r, c);
			}
		}
		return getGrid();
	}

}