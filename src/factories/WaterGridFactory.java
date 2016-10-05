package factories;

import java.util.Map;

import cellsociety_team01.AlertBox;
import cellsociety_team01.GridFactory;
import grids.BasicFiniteGrid;
import water.EmptyCell;
import water.Fish;
import water.Shark;

/**
 * Creates grid object with Water-type cells from given input. Also generates default values and error boxes when XML file has invalid/missing data.
 *
 * @author Christopher Lu
 * @author Eric Song
 */
public class WaterGridFactory extends GridFactory {
	private int fishReproduce, sharkDeath, sharkReproduce;

	public WaterGridFactory(String cellShape, String bounds, String r, String c, String neighbor) {
		super(cellShape, bounds, r, c, neighbor);
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
	
	private int checkIntValues(int var, String varName, int defaultVar){
		if (var <= 0) {
			var = defaultVar;
			String message = String.format("Invalid or missing user values: "+varName+". Default values "+varName+" = %f will be used.", defaultVar);
			AlertBox.displayError(message);
		}
		return var;
	}

	public BasicFiniteGrid makeGrid(Map<String,String> map) {
		double percentFish = doubleParseErrors(map.get(myViewResources.getString("PercentFish")));
		double percentShark = doubleParseErrors(map.get(myViewResources.getString("PercentShark")));
		fishReproduce = intParseErrors(map.get(myViewResources.getString("FishReproduce")));
		sharkDeath = intParseErrors(map.get(myViewResources.getString("SharkDeath")));
		sharkReproduce = intParseErrors(map.get(myViewResources.getString("SharkReproduce")));
		
		

		if (checkPercentError(percentFish + percentShark)) {
			percentFish = Math.random() * .5;
			percentShark = Math.random() * .5;
			String message = String.format("Invalid or missing user values: PercentFish, PercentShark. Default values PercentFish = %f, PercentShark = %f will be used.", percentFish, percentShark);
			AlertBox.displayError(message);
		}
		fishReproduce = checkIntValues(fishReproduce,"fishReproduce", 2);
		sharkDeath = checkIntValues(sharkDeath,"sharkDeath", 2);
		sharkReproduce = checkIntValues(sharkReproduce,"sharkReproduce", 8);
		
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