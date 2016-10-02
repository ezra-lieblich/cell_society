package xo;

import javafx.scene.paint.Color;

/**
 * Second group of neighbors and is set to default color of red. It extends the Neighbors
 * class
 * @author ezra
 *
 */
public class Group2 extends Neighbor {
	public Group2(int x, int y, double p) {
		super(x, y, p, Color.RED);
	}
}
