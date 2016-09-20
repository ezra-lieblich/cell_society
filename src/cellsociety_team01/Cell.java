package cellsociety_team01;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Cell {
	private ArrayList<Cell> Neighbors;
	private Color color;
	private int XPosition;
	private int YPosition;
	
	// Calculates the next state of individual cell (0, 1, 2? for the three states?)
	private void CalculateNextState(ArrayList<Cell> neighborList) {
		Neighbors = neighborList;
	}
	
	// Sets the color of the cell.
	private void setColor(Color newColor)
	{
		color = newColor;
	}
	
	// Sets the x position of the cell.
	private void setCoords(int newXPosition, int newYPosition) {
		XPosition = newXPosition;
		YPosition = newYPosition;
	}
	
	// Gets the x coordinates of the cell
	private int getCoordsX() {
		return XPosition;
	}
	
	// Gets the y coordinates of the cell
	private int getCoordsY() {
		return YPosition;
	}
	
	
}
