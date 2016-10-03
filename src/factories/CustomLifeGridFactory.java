package factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team01.AlertBox;
import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import life.AliveCell;
import life.DeadCell;

public class CustomLifeGridFactory extends GridFactory {

	public CustomLifeGridFactory(String cellShape, String bounds, String r, String c) {
		super(cellShape, bounds, r, c);
	}
	
	public BasicFiniteGrid makeCustomGrid(Map<String,String> map) {
		List<String> xList = new ArrayList<String>();
		List<String> yList = new ArrayList<String>();
		List<Integer> xIntList = new ArrayList<Integer>();
		List<Integer> yIntList = new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			xIntList.add(intParseErrors(map.get(myResources.getString("xValue"+i))));
			yIntList.add(intParseErrors(map.get(myResources.getString("yValue"+i))));
		}	
	
//		if (checkPercentError(percentAlive)) {
//			percentAlive = Math.random() * .5;
//			String message = String.format("Invalid user values: percentAlive. Default values percentAlive = %f will be used.", percentAlive);
//			AlertBox.displayError(message);
//		}
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				if ((xIntList.indexOf(r) != -1) && (xIntList.indexOf(r) == yIntList.indexOf(c))) {
					setGridIndex(new AliveCell(c, r), r, c);
				}
				else {
					setGridIndex(new DeadCell(c, r), r, c);
				}
			}
		}
		return getGrid();
	}
}
