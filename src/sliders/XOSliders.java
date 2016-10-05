package sliders;

import cellsociety_team01.GridController;

/**
 * Creates sliders for the XO simulation
 * @author ezra
 *
 */
public class XOSliders extends SliderProperties{
	public XOSliders(GridController control) {
		super(control);
		createSliders();
	}
	
	/**
	 * Create Sliders for %X %O and %Similar
	 */
	protected void createSliders() {
		super.addSlider(0, 1, myResources.getString("PercentX"));
		super.addSlider(0, 1, myResources.getString("PercentO"));
		super.addSlider(0, 1, myResources.getString("SimilarPercentage"));
	}
}
