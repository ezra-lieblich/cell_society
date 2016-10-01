package factories;

import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import water.EmptyCell;
import water.Fish;
import water.Shark;

/**
 * Creates grid object with Water-type cells from given input.
 *
 * @author Eric Song
 */
public class WaterGridFactory extends GridFactory {

	public WaterGridFactory(String cellShape, String bounds, int r, int c) {
		super(cellShape, bounds, r, c);
	}

	public BasicFiniteGrid makeGrid(double percentFish, double percentShark, int fishReproduce, int sharkDeath,
			int sharkReproduce) {
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				double ranGen = Math.random();
				if (ranGen <= percentShark)
					setGridIndex(new Shark(c, r,sharkDeath, sharkReproduce), r, c);
				else if (percentShark < ranGen && ranGen <= percentFish + percentShark)
					setGridIndex(new Fish(c, r, fishReproduce), r, c);
				else
					setGridIndex(new EmptyCell(c, r), r, c);
			}
		}
		return super.getGrid();
	}

}