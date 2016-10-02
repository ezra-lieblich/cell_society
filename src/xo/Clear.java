package xo;

import cellsociety_team01.Cell;
import javafx.scene.paint.Color;
/**
 * A child of cell that just represents empty cells. Doesn't have
 * any special properties
 * @author ezra
 *
 */
public class Clear extends Cell{
	public Clear(int x, int y){
		super(x, y, Color.NAVAJOWHITE);
	}
}
