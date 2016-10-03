package factories;

import java.util.Map;

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

	public WaterGridFactory(String cellShape, String bounds, String r, String c) {
		super(cellShape, bounds, r, c);
	}




	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentFish =doubleParseErrors(map.get("percentFish"));
		double percentShark = doubleParseErrors(map.get("percentShark"));
		int fishReproduce = intParseErrors(map.get("fishReproduce"));
		int sharkDeath = intParseErrors(map.get("sharkDeath"));
		int sharkReproduce = intParseErrors(map.get("sharkReproduce"));
		if (checkPercentError(percentFish + percentShark)) {
			percentFish = Math.random() * .5;
			percentShark = Math.random() * .5;
		}
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