package cellsociety_team01;

import java.util.Map;
import java.util.ResourceBundle;

import grids.*;

/**
 * @author Christopher Lu
 * @author Eric Song
 * 
 *         This class creates a BasicFiniteGrid based on data that XMLReader
 *         passes along to it. The Grid Factory sets the type of Grid. This
 *         includes both shape and border type. It also throws
 *         XMLParserExceptions if the format of XML data is incorrect (e.g. a
 *         non-numerical value in a percent parameter.
 **/

public class GridFactory {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	protected ResourceBundle myViewResources;
	protected ResourceBundle myDefaultValResources;
	private int rows;
	private int columns;
	private BasicFiniteGrid grid;
	private String bounds;
	private String cellShape;

	/**
	 * Constructor class for a Grid Factory
	 * 
	 * @param ce
	 * @param b
	 * @param r
	 * @param c
	 * @param neighbors
	 */
	public GridFactory(String ce, String b, String r, String c, String neighbors) {
		myViewResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		myDefaultValResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DefaultValues");
		rows = intParseErrors(r);
		columns = intParseErrors(c);
		bounds = b;
		cellShape = ce;
		setGridType(cellShape, bounds, neighbors);
	}

	/**
	 * Determines which child class of the grid to create for backend purposes
	 * (interactions between neighbors are different for hex vs non-hex and
	 * toroidal vs finite
	 * 
	 * @param cellShape
	 * @param bounds
	 * @param neighbors
	 */
	private void setGridType(String cellShape, String bounds, String neighbors) {
		if (bounds.equals("finite")) {
			if (cellShape.equals("hex"))
				grid = new HexagonalFiniteGrid(columns, rows, neighbors);
			else
				grid = new BasicFiniteGrid(columns, rows, neighbors);
		} else {
			if (cellShape.equals("hex"))
				grid = new HexagonalToroidalGrid(columns, rows, neighbors);
			else
				grid = new BasicToroidalGrid(columns, rows, neighbors);
		}
	}

	/**
	 * Checks to see if the string to double conversion is valid, throws
	 * exception when necessary
	 * 
	 * @param input
	 * @return
	 * @throws XMLParserException
	 */
	protected double doubleParseErrors(String input) throws XMLParserException {

		try {
			return Double.parseDouble(input);
		} catch (RuntimeException e) {
			throw new XMLParserException("" + input + " in XML file needs to be a double.", e);
		}
	}

	/**
	 * Checks to see if the string to int conversion is valid, throws exception
	 * when necessary
	 * 
	 * @param input
	 * @return
	 * @throws XMLParserException
	 */
	protected int intParseErrors(String input) throws XMLParserException {
		try {
			return Integer.parseInt(input);
		} catch (RuntimeException e) {
			throw new XMLParserException("" + input + " in XML file needs to be an integer.", e);
		}
	}

	/**
	 * Checks if user or XML accidently set total percentages over 1. Respective
	 * children handle error handling
	 * 
	 * @param totalPercent
	 *            Total percent of all Cells
	 * @return
	 */
	protected boolean checkPercentError(double totalPercent) {
		return totalPercent > 1.00 || totalPercent < 0;
	}

	/**
	 * to be overridden by children classes
	 * 
	 * @param map
	 * @return
	 */
	protected BasicFiniteGrid makeGrid(Map<String, String> map) {
		return null;
	}

	// getters and setters

	protected void setGridIndex(Cell cell, int row, int column) {
		grid.setGridIndex(cell, column, row);
	}

	protected BasicFiniteGrid getGrid() {
		return grid;
	}

	public String getCellShape() {
		return cellShape;
	}

	protected int getRows() {
		return rows;
	}

	protected int getColumns() {
		return columns;
	}

}
