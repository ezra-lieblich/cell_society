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

/**
 * 
 * @author Christopher Lu
 * Uses data fed from XMLReader to set all the cells and cell states in the simulation grid for Life.
 *
 */

public class CustomLifeGridFactory extends GridFactory {

	public CustomLifeGridFactory(String cellShape, String bounds, String r, String c, String neighbors) {
		super(cellShape, bounds, r, c, neighbors);
	}
	
	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		List<String> xList = new ArrayList<String>();
		List<String> yList = new ArrayList<String>();
		List<Integer> xIntList = new ArrayList<Integer>();
		List<Integer> yIntList = new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			xIntList.add(intParseErrors(map.get(myViewResources.getString("xValue"+Integer.toString(i)))));
			yIntList.add(intParseErrors(map.get(myViewResources.getString("yValue"+Integer.toString(i)))));
			System.out.println(i);
		}
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
