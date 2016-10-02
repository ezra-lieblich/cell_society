package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

/**
 * Creates sliders for the XO simulation
 * @author ezra
 *
 */
public class XOSliders extends SliderProperties{
	public XOSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
	}
	
	/**
	 * Create Sliders for %X %O and %Similar
	 */
	protected void createSliders() {
		super.addSlider(0, 1, "%X");
		super.addSlider(0, 1, "%O");
		super.addSlider(0, 1, "%Similar");
	}
}
