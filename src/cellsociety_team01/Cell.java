package cellsociety_team01;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;


/**
@author Eric Song
* A parent class that all types of objects for each simulation will inherit from
**/
public class Cell {
	private List<Cell> neighbors;
	private Color color;
	private int XPosition;
	private int YPosition;
	
	
	public Cell(){}
	
	
	/**
	 * Constructor with (x,y) coordinate in grid and color c
	 * @param x
	 * @param y
	 * @param c
	 */
	public Cell(int x, int y, Color c){
		XPosition = x;
		YPosition = y;
		color = c;
	}
	
	
	// Calculates the next state of individual cell (0, 1, 2? for the three states?)
	public void calculateNextState(List<Cell> neighborList) {
		neighbors = neighborList;
	}
	
	// Sets the x position of the cell.
	public void setCoords(int newXPosition, int newYPosition) {
		XPosition = newXPosition;
		YPosition = newYPosition;
	}
	
	// Gets the x coordinates of the cell
	public int getCoordsX() {
		return XPosition;
	}
	
	// Gets the y coordinates of the cell
	public int getCoordsY() {
		return YPosition;
	}
	
	// Gets the color of cell
	public Color getColor(){
		return color;
	}
	
	
}
