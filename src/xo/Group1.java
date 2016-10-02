package xo;

import javafx.scene.paint.Color;

/**
 * First group of neighbors and is set to default color of blue. It extends the Neighbors
 * class
 * @author ezra
 *
 */
public class Group1 extends Neighbor {
	public Group1(int x, int y, double p) {
		super(x, y, p, Color.BLUE);
	}
}
