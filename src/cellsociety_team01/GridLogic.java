package cellsociety_team01;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import grids.BasicFiniteGrid;
/**
 * GridLogic is a parent class that handles performs logic on the based off of the grid.
 * Each simulation extends this and handles the logic of the grid differently
 * @author ezra
 *
 */
public class GridLogic {
	protected BasicFiniteGrid grid;
	protected Map<String, Integer> cellSizes;
	
	/**
	 * Empty constructor
	 */
	public GridLogic(){}
	
	/**
	 * Sets the grid instance variable to the grid passed in
	 * @param grid
	 */
	public GridLogic(BasicFiniteGrid grid) {
		this.grid = grid;
	}
	
	/**
	 * called by the grid controller and in child class it will perform some logic each time the 
	 * timeline steps
	 */
	public void step() {
		
	}
	
	/**
	 * Returns a unmodifiable map of the size of all the cells in the grid. Unmodified so
	 * people can't alter the map. This will be passed to CellGraph so it can plot the cell sizes
	 * @return unmodifiable map
	 */
	public Map<String, Integer> getCells() {
		return Collections.unmodifiableMap(cellSizes);
	}
	
	/** Adds a cell to the map or increments the size by 1
	 * 
	 * @param name name of Cell we are updating
	 */
	protected void updateCellSizes(String name) {
		if (!cellSizes.containsKey(name)) {
			cellSizes.put(name, 0);
		}
		cellSizes.put(name, cellSizes.get(name) + 1);
	}
}
