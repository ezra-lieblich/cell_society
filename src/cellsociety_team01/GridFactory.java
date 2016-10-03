package cellsociety_team01;

import grids.*;

public class GridFactory {
	private int rows;
	private int columns;
	private BasicFiniteGrid grid; 
	private String bounds;
	private String cellShape;

	public GridFactory(String ce, String b, String r, String c) {
		rows = intParseErrors(r);
		columns = intParseErrors(c);
		bounds = b;
		cellShape = ce;
		setGridType(cellShape, bounds);
	}
	
	public String getCellShape(){
		return cellShape;
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
	
	// Detects incorrectly formatted data.
	protected double doubleParseErrors(String input) throws XMLParserException {

		try {
			return Double.parseDouble(input);
		} catch (RuntimeException e) {
			throw new XMLParserException("" + input + " in XML file needs to be a double.", e);
		}
	}

	protected int intParseErrors(String input) throws XMLParserException {
		try {
			return Integer.parseInt(input);
		} catch (RuntimeException e) {
			throw new XMLParserException("" + input + " in XML file needs to be an integer.", e);
		}
	}
	
	protected void setGridIndex(Cell cell, int row, int column){
		grid.setGridIndex(cell, column, row);
	}
	
	protected BasicFiniteGrid getGrid(){
		return grid;
	}
	
	/**
	 * Checks if user or XML accidently set total percentages over 1. Respective children handle
	 * error handling
	 * @param totalPercent Total percent of all Cells
	 * @return
	 */
	protected boolean checkPercentError(double totalPercent) {
		return totalPercent > 1.00;
	}
	

}
