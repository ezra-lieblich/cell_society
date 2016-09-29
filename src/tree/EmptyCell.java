package tree;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;

public class EmptyCell extends Cell{

	// Initializes empty cell to the x and y coordinates, sets color to yellow.
	public EmptyCell(int x, int y) {
		super.init(x, y);
		super.setColor(Color.YELLOW);
	}
}

