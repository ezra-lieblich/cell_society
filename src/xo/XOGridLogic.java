package xo;

import java.util.ArrayList;
import java.util.List;
import cellsociety_team01.Cell;
import cellsociety_team01.Grid;
import cellsociety_team01.GridLogic;

public class XOGridLogic extends GridLogic{
	private List<Neighbor> upsetNeighbors;
	private List<Cell> availableSpaces;
	public XOGridLogic(Grid grid) {
		this.grid = grid;
	}
	public void step() {
		upsetNeighbors = new ArrayList<Neighbor>();
		availableSpaces = new ArrayList<Cell>();
		for(int r = 0; r < grid.getRows(); r++){
			for(int c = 0; c < grid.getColumns(); c++) {
				updateStatus(r, c);
			}
		}
		updateGrid();
	}
	
	private void updateStatus(int row, int col) {
		Cell cell = grid.getGridIndex(row, col);
		cell.calculateNextState(grid.getNeighbors(row, col));
		if (cell instanceof Neighbor){
			Neighbor person = (Neighbor) cell;
			if (!person.Satisfied()) 
				upsetNeighbors.add(person);
		}
		else{
			availableSpaces.add(cell);
		}		
	}
	/**
	 * Moves all the neighbors that are not satisfied. Chooses a random open cell
	 * and moves there and updates list of open spaces
	 */
	private void updateGrid() {
		for(Neighbor neighbor : upsetNeighbors) {
			Empty vacated_cell = new Empty(neighbor.getCoordsX(), neighbor.getCoordsY());
			grid.setGridIndex(vacated_cell, vacated_cell.getCoordsX(), vacated_cell.getCoordsY());
			Cell new_location = availableSpaces.get((int)(Math.random() * (availableSpaces.size()-1)));
			//availableSpaces.remove(new_location);
			int new_x = new_location.getCoordsX();
			int new_y = new_location.getCoordsY();
			if(neighbor.getClass().equals(Group1.class)) 
				grid.setGridIndex(new Group1(new_x, new_y), new_x, new_y);
			else
				grid.setGridIndex(new Group2(new_x, new_y), new_x, new_y);
			availableSpaces.remove(new_location);
			availableSpaces.add(vacated_cell);
		}
	}
}
