package cellsociety_team01;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import grids.BasicFiniteGrid;

public class GridLogic {
	protected BasicFiniteGrid grid;
	protected HashMap<String, Integer> cellSizes;
	
	public GridLogic(){}
	
	public GridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
	}
	public void step() {
		
	}
	public Map<String, Integer> getCells() {
		return Collections.unmodifiableMap(cellSizes);
	}
}
