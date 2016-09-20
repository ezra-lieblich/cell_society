package water;
import java.util.ArrayList;

import cellsociety_team01.Cell;

public class Fish extends Cell {
	private int turnsPerReproduce;
	private int currentTurn;
	private ArrayList<Cell> neighbors;
	
	private boolean isReproducing;
	

	public Fish(int x, int y, int turnsPerReproduce) {
		super.init(x, y);
		this.turnsPerReproduce = turnsPerReproduce;
		currentTurn = 0;
	}
	
	@Override
	public void calculateNextState(ArrayList<Cell> neighborList) {
		neighbors = neighborList;
	}

}
