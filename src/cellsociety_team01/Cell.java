package cellsociety_team01;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Cell {
	private ArrayList<Cell> neighbors;
	private Color color;
	private int XPosition;
	private int YPosition;
	
	public Cell(){}
	
	public Cell(int x, int y){
		init(x,y);
	}
	
	public void init(int x, int y){
		XPosition = x;
		YPosition = y;
	}
	
	// Calculates the next state of individual cell (0, 1, 2? for the three states?)
	public void calculateNextState(ArrayList<Cell> neighborList) {
		neighbors = neighborList;
	}
	
	// Sets the color of the cell.
	private void setColor(Color newColor)
	{
		color = newColor;
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
	private int getCoordsY() {
		return YPosition;
	}
	
	
}
