package cellsociety_team01;

import grids.*;

public class GridFactory {
	private int rows;
	private int columns;
	private BasicFiniteGrid grid;

	public GridFactory(String cellShape, String bounds, int r, int c) {
		rows = r;
		columns = c;
		setGridType(cellShape, bounds);
	}
	
	protected int getRows(){
		return rows;
	}
	
	protected int getColumns(){
		return columns;
	}

	private void setGridType(String cellShape, String bounds) {
		if(bounds.equals("finite")){
			if(cellShape.equals("hex"))
				grid = new HexagonalFiniteGrid(columns,rows);
			else
				grid = new BasicFiniteGrid(columns,rows);
		}
		else{
			if(cellShape.equals("hex"))
				grid = new HexagonalToroidalGrid(columns,rows);
			else
				grid = new BasicToroidalGrid(columns,rows);
		}
	}
	
	protected void setGridIndex(Cell cell, int row, int column){
		grid.setGridIndex(cell, column, row);
	}
	
	protected BasicFiniteGrid getGrid(){
		return grid;
	}
	

}
