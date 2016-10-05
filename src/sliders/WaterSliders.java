package sliders;

import cellsociety_team01.GridController;

/**
 * Creates sliders and spinner for the WaTor World Simulation
 * @author ezra
 *
 */
public class WaterSliders extends SliderProperties{
	public WaterSliders(GridController control) {
		super(control);
		createSliders();
		createSpinners();
	}
	
	/**
	 * Creates slider for %Fish and %Shark
	 */
	protected void createSliders() {
		super.addSlider(0, 1, myResources.getString("PercentFish"));
		super.addSlider(0, 1, myResources.getString("PercentShark"));
	}
	
	/**
	 * Creates Spinners to determine turns for fish reproduce
	 * shark reproduce and time for shark to die
	 */
	private void createSpinners() {
		super.addSpinner(myResources.getString("FishReproduce"));
		super.addSpinner(myResources.getString("SharkDeath"));
		super.addSpinner(myResources.getString("SharkReproduce"));
	}
	 
}
