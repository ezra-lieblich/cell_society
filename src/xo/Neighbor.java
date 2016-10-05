package xo;

import java.util.List;
import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

/**
 * Neighbor class extends Cell class and different is extended by Group 1 and Group 2.
 * Calculates its next state based off of the similar percentage
 * @author ezra
 *
 */
public class Neighbor extends Cell {
	private boolean isSatisfied;
	private double percentSimilar;
	
	/**
	 *Calls super to set up the color and position and also takes in the percent similar 
	 * @param x
	 * @param y
	 * @param p Percent of similar neighbors required to be satisfied
	 * @param color
	 */
	public Neighbor(int x, int y, double p, Color color) {
		super(x,y, color);
		percentSimilar = p;
	}
	
	/**
	 * called by Logic class to determine its next state
	 */
	@Override
	public void calculateNextState(List<Cell> neighborList) {
		isSatisfied = determineSimilarNeighbors(neighborList);
	}
	
	/**
	 * 
	 * @return true if the neighbor is satisfied
	 */
	public boolean Satisfied() {
		return isSatisfied;
	}
	
	/**
	 * Determines if the neighbor is satisfied if the neighbors exceed the similar
	 * percentage
	 * @param neighborList list of neighbors 
	 * @return
	 */
	private boolean determineSimilarNeighbors(List<Cell> neighborList){
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
