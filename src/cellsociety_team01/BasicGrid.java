package cellsociety_team01;

import java.util.ArrayList;


public class BasicGrid {
	protected Cell[][] grid;
	protected ArrayList<Cell> neighbors;
	//need to set originalGrid to the first implementation of grid
	private Cell [][] originalGrid;
	
	public BasicGrid(){}

	public BasicGrid(int x, int y) {
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
				if (validIndex(i, j) && !(i == x && j == y))
					addIndex(i,j);
			}
		}
		return neighbors;
	}
	
	protected void addIndex(int x, int y){
		neighbors.add(grid[x][y]);
	}
	
	

	private boolean validIndex(int x, int y) {
		return (x >= 0 && x < getRows()) && (y >= 0 && y < getColumns());
	}
	
	public int getColumns(){
		return grid[0].length;
	}
	
	public int getRows(){
		return grid.length;
	}
	
	private void setOriginalGrid() {
		originalGrid = grid.clone();
	}
}
