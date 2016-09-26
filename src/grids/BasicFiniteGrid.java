package grids;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import water.EmptyCell;


public class BasicFiniteGrid {
	protected Cell[][] grid;
	protected ArrayList<Cell> neighbors;
	//need to set originalGrid to the first implementation of grid
	private Cell [][] originalGrid;
	private int x;
	private int y;
	
	public BasicFiniteGrid(){}

	public BasicFiniteGrid(int x, int y) {
		grid = new Cell[x][y];
	}

	public Cell getGridIndex(int x, int y) {
		return grid[x][y];
	}

	public void setGridIndex(Cell cell, int x, int y) {
		grid[x][y] = cell;
	}

	public ArrayList<Cell> getNeighbors(int x, int y) {
		neighbors = new ArrayList<Cell>();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i == x && j == y))
					addIndex(i,j);
			}
		}
		return neighbors;
	}
	
	public void moveCellGridIndex(int fromX, int fromY, int toX, int toY) {
		Cell temp = getGridIndex(fromX, fromY);
		temp.setCoords(toX, toY);
		setGridIndex(temp, toX, toY);
		setGridIndex(new EmptyCell(fromX,fromY),fromX,fromY);
	}
	
	void addIndex(int x, int y){
		neighbors.add(grid[x][y]);
	}

	boolean validIndex(int x, int y) {
		return (x >= 0 && x < getRows()) && (y >= 0 && y < getColumns());
	}
	
	public int getColumns(){
		return grid[0].length;
	}
	
	public int getRows(){
		return grid.length;
	}
	
	void setOriginalGrid() {
		originalGrid = grid.clone();
	}
}
