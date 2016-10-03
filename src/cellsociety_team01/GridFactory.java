package cellsociety_team01;

import java.util.Map;
import java.util.ResourceBundle;

import grids.*;

public class GridFactory {
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

    protected ResourceBundle myViewResources;
    protected ResourceBundle myDefaultValResources;
	private int rows;
	private int columns;
	private BasicFiniteGrid grid; 
	private String bounds;
	private String cellShape;

	public GridFactory(String ce, String b, String r, String c, String neighbors) {
		myViewResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		myDefaultValResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DefaultValues");
		rows = intParseErrors(r);
		columns = intParseErrors(c);
		bounds = b;
		cellShape = ce;
		setGridType(cellShape, bounds,neighbors);
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

	private void setGridType(String cellShape, String bounds,String neighbors) {
		if(bounds.equals("finite")){
			if(cellShape.equals("hex"))
				grid = new HexagonalFiniteGrid(columns,rows,neighbors);
			else
				grid = new BasicFiniteGrid(columns,rows,neighbors);
		}
		else{
			if(cellShape.equals("hex"))
				grid = new HexagonalToroidalGrid(columns,rows,neighbors);
			else
				grid = new BasicToroidalGrid(columns,rows,neighbors);
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
		return totalPercent > 1.00 || totalPercent<0;
	}
	
	protected BasicFiniteGrid makeGrid(Map<String, String> map) {
		return null;
	}

}
