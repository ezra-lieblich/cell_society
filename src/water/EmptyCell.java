package water;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends Cell {
	

	public EmptyCell(int x, int y) {
		super.init(x, y);
		super.setColor(Color.BLUE);
	}
	
	@Override
	public String toString(){
		return "E";
	}

}
