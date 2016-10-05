//This entire file is part of my masterpiece
package factories;

import cellsociety_team01.GridController;
import cellsociety_team01.GridFactory;
import cellsociety_team01.GridLogic;
import grids.BasicFiniteGrid;
import life.LifeGridLogic;
import sliders.LifeSliders;
import sliders.SliderProperties;
import sliders.TreeSliders;
import sliders.WaterSliders;
import sliders.XOSliders;
import tree.TreeGridLogic;
import water.WaterGridLogic;
import xo.XOGridLogic;

/**
 * Factory class that is called by controller to create Sliders and Logic based off of the Simulation name
 * @author ezra
 *
 */
public class LogicFactory {
	private GridLogic logic;
	private SliderProperties slider;
	
	public LogicFactory() {
	}
	
	/**
	 * Determines what type of sim and sets logic and slider to proper subclass
	 * String sim name of the simulation
	 */
	public void setupLogic(String sim, BasicFiniteGrid grid, GridController controller, GridFactory factory) {
		if (sim.equals("GameOfLife")) {
			logic = new LifeGridLogic(grid);
			slider = new LifeSliders(controller);
		} else if (sim.equals("SpreadOfFire")) {
			logic = new TreeGridLogic(grid);
			slider = new TreeSliders(controller);
		} else if (sim.equals("WaTorWorld")) {
			logic = new WaterGridLogic(grid, ((WaterGridFactory)factory).getFishReproduce(), ((WaterGridFactory)factory).getSharkDeath(),
					((WaterGridFactory)factory).getSharkReproduce());
			slider = new WaterSliders(controller);
		} else if (sim.equals("XO")) {
			logic = new XOGridLogic(grid, ((XOGridFactory)factory).getSimilarPercentage());
			slider = new XOSliders(controller);
		} 
	}
	
	/**
	 * 
	 * @return Controller retrieves the logic created
	 */
	public GridLogic getLogic() {
		return logic;
	}
	
	/**
	 * 
	 * @return Controller retrieves the slider created
	 */
	public SliderProperties getSlider() {
		return slider;
	}
}
