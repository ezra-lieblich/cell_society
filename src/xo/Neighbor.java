package xo;

import java.util.ArrayList;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class Neighbor extends Cell {
	private boolean isSatisfied;
	private double percentSimilar;
	public Neighbor(int x, int y, double p, Color color) {
		super(x,y, color);
		percentSimilar = p;
	}
	@Override
	public void calculateNextState(ArrayList<Cell> neighborList) {
		isSatisfied = determineSimilarNeighbors(neighborList);
	}
	public boolean Satisfied() {
		return isSatisfied;
	}
	private boolean determineSimilarNeighbors(ArrayList<Cell> neighborList){
		int similar_neighbors = 0;
		int total_neighbors = 0;
		for(Cell neighbor: neighborList) {
			if (neighbor instanceof Neighbor) {
				total_neighbors++;
				if(this.getClass().equals(neighbor.getClass())){
					similar_neighbors++;
				}
			}
		}
		return ((double) similar_neighbors/total_neighbors) >= percentSimilar;
	}
}
