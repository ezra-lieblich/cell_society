package factories;

import java.util.Map;

import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import tree.BurningCell;
import tree.EmptyCell;
import tree.TreeCell;

/**
 * Creates grid object with Tree-type cells from given input.
 *
 * @author Eric Song
 */
public class TreeGridFactory extends GridFactory {

	public TreeGridFactory(String cellShape, String bounds, int r, int c) {
		super(cellShape, bounds, r, c);
	}


	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentTree = doubleParseErrors(map.get("percentTree"));
		double percentBurn = doubleParseErrors(map.get("percentBurn"));
		double probCatch = doubleParseErrors(map.get("probCatch"));
		if (checkPercentError(percentTree + percentBurn)) {
			percentTree = Math.random() * .5;
			percentBurn = Math.random() *.5;
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