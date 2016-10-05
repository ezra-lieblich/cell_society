package factories;

import java.util.Map;

import cellsociety_team01.AlertBox;
import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import tree.BurningCell;
import tree.EmptyCell;
import tree.TreeCell;

/**
 * Creates grid object with Tree-type cells from given input. Also generates error boxes and random default values for invalid/missing XML values
 * @author Christopher Lu
 * @author Eric Song
 */
public class TreeGridFactory extends GridFactory {

	public TreeGridFactory(String cellShape, String bounds, String r, String c, String neighbors) {
		super(cellShape, bounds, r, c, neighbors);
	}


	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentTree = doubleParseErrors(map.get(myViewResources.getString("PercentTree")));
		double percentBurn = doubleParseErrors(map.get(myViewResources.getString("PercentBurn")));
		double probCatch = doubleParseErrors(map.get(myViewResources.getString("ProbCatch")));
		if (checkPercentError(percentTree + percentBurn)) {
			percentTree = doubleParseErrors(myDefaultValResources.getString("percentTree"));
			percentBurn = doubleParseErrors(myDefaultValResources.getString("percentBurn"));
			String message = String.format("Invalid or missing user values: percentTree, percentBurn. Default values percentTree = %f, percentBurn = %f will be used.", percentTree, percentBurn);
			AlertBox.displayError(message);
		}
		if (checkPercentError(probCatch)) {
			probCatch = doubleParseErrors(myDefaultValResources.getString("probCatch"));
			String message = String.format("Invalid or missing user values: probCatch. Default values probCatch = %f will be used.", probCatch);
			AlertBox.displayError(message);
		}
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				double ranGen = Math.random();
				if (ranGen <= percentBurn) {
					super.setGridIndex(new BurningCell(c, r), r, c);
				} else if (percentBurn < ranGen && ranGen <= percentTree) {
					super.setGridIndex(new TreeCell(c, r, probCatch), r, c);
				} else {
					setGridIndex(new EmptyCell(c, r), r, c);
				}
			}
		}
		return super.getGrid();
	}

}