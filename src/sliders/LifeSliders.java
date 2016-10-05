package sliders;

import cellsociety_team01.GridController;
/**
 * Creates sliders for Game Of Life Simulation
 * @author ezra
 *
 */
public class LifeSliders extends SliderProperties{
	public LifeSliders(GridController control) {
		super(control);
		createSliders();
	}
	
	/**
	 * Adds a slider that controls the percent alive
	 */
	protected void createSliders() {
		super.addSlider(0, 1, myResources.getString("PercentAlive"));
	}
}
