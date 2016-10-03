package factories;

import java.util.Map;

import cellsociety_team01.AlertBox;
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
	private int fishReproduce, sharkDeath, sharkReproduce;

	public WaterGridFactory(String cellShape, String bounds, String r, String c) {
		super(cellShape, bounds, r, c);
	}

	public int getFishReproduce() {
		return fishReproduce;
	}

	public int getSharkDeath() {
		return sharkDeath;
	}

	public int getSharkReproduce() {
		return sharkReproduce;
	}

	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentFish = doubleParseErrors(map.get(myResources.getString("PercentFish")));
		double percentShark = doubleParseErrors(map.get(myResources.getString("PercentShark")));
		fishReproduce = intParseErrors(map.get(myResources.getString("FishReproduce")));
		sharkDeath = intParseErrors(map.get(myResources.getString("SharkDeath")));
		sharkReproduce = intParseErrors(map.get(myResources.getString("SharkReproduce")));
		if (checkPercentError(percentFish + percentShark)) {
			percentFish = Math.random() * .5;
			percentShark = Math.random() * .5;
			String message = String.format("Invalid user values: PercentFish, PercentShark. Default values PercentFish = %f, PercentShark = %f will be used.", percentFish, percentShark);
			AlertBox.displayError(message);
		}
		if (fishReproduce < 0) {
			fishReproduce = 1;
			String message = String.format("Invalid user values: fishReproduce. Default values fishReproduce = %f will be used.", fishReproduce);
			AlertBox.displayError(message);
		}
		if (sharkDeath < 0) {
			sharkDeath = 3;
			String message = String.format("Invalid user values: sharkDeath. Default values sharkDeath = %f will be used.", sharkDeath);
			AlertBox.displayError(message);
		}
		if (sharkReproduce < 0) {
			sharkReproduce = 2;
			String message = String.format("Invalid user values: sharkReproduce. Default values sharkReproduce = %f will be used.", sharkReproduce);
			AlertBox.displayError(message);
		}
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				double ranGen = Math.random();
				if (ranGen <= percentShark)
					setGridIndex(new Shark(c, r, sharkDeath, sharkReproduce), r, c);
				else if (percentShark < ranGen && ranGen <= percentFish + percentShark)
					setGridIndex(new Fish(c, r, fishReproduce), r, c);
				else
					setGridIndex(new EmptyCell(c, r), r, c);
			}
		}
		return super.getGrid();
	}

}