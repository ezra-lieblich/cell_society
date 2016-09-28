package cellsociety_team01;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import grids.BasicFiniteGrid;

public class GridLogic {
	protected BasicFiniteGrid grid;
	protected Map<String, Integer> cellSizes;
	
	public GridLogic(){}
	
	public GridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
	}
	public void step() {
		
	}
	public Map<String, Integer> getCells() {
		return Collections.unmodifiableMap(cellSizes);
	}
	
	protected void updateCellSizes(String name) {
		if (!cellSizes.containsKey(name)) {
			cellSizes.put(name, 0);
		}
		cellSizes.put(name, cellSizes.get(name) + 1);
	}
}
