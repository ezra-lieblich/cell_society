package cellsociety_team01;

import java.util.ArrayList;

import water.EmptyCell;

public class ToroidalGrid extends BasicGrid {

	public ToroidalGrid(int x, int y) {
		grid = new Cell[x][y];
	}
	
//	@Override
//	public ArrayList<Cell> getNeighbors(int x, int y) {
//		ArrayList<Cell> neighbors = new ArrayList<Cell>();
//
//		neighbors.add(getAdjustedNeighbor(x, y + 1));
//		neighbors.add(getAdjustedNeighbor(x, y - 1));
//		neighbors.add(getAdjustedNeighbor(x + 1, y));
//		neighbors.add(getAdjustedNeighbor(x - 1, y));
//
//		return neighbors;
//	}
	
	@Override
	protected void addIndex(int x, int y){
		neighbors.add(getAdjustedNeighbor(x,y));
	}

	private Cell getAdjustedNeighbor(int i, int j) {
		return getGridIndex(adjustIndex(i, getRows()), adjustIndex(j, getColumns()));
	}

	private int adjustIndex(int index, int maxIndex) {
		if (index >= maxIndex)
			return index % maxIndex;
		if (index < 0)
			return index + maxIndex;
		return index;
	}
	
	public void moveCellGridIndex(int fromX, int fromY, int toX, int toY) {
		Cell temp = getGridIndex(fromX, fromY);
		temp.setCoords(toX, toY);
		setGridIndex(temp, toX, toY);
		setGridIndex(new EmptyCell(fromX,fromY),fromX,fromY);
	}
	
//	public void setEmptyGridIndex(int x, int y) {
//		setGridIndex(new EmptyCell(x, y), x, y);
//	}
//	
//	public void setSharkGridIndex(int x, int y) {
//		setGridIndex(new Shark(x, y), x, y);
//	}
//	
//	public void setFishGridIndex(int x, int y) {
//		setGridIndex(new Fish(x, y), x, y);
//	}
}