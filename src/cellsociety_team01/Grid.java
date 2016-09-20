package cellsociety_team01;

import java.util.ArrayList;

import javafx.scene.control.Cell;

public class Grid {
	private Cell[][] grid;
	public Grid(int x, int y){
		grid = new Cell[x][y];
	}
	public Cell getGridIndex(int x, int y){
		return grid[x][y];
	}
	public void setGridIndex(Cell cell, int x, int y) {
		grid[x][y] = cell;
	}
	public ArrayList<Cell> getNeighbors(int x, int y) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for(int i = x-1; i<=x+1; i++){
			for(int j = y-1; j<=y+1; j++) {
				if(validIndex(i,j) && !(i==x && j == y)) neighbors.add(grid[x][y]);
			}
		}
		return neighbors;
	}
	private boolean validIndex(int x, int y) {
		return (x>=0 && x<grid.length) && (y>=0 && y<grid[0].length);
	}
}
