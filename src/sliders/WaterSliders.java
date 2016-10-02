package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

/**
 * Creates sliders and spinner for the WaTor World Simulation
 * @author ezra
 *
 */
public class WaterSliders extends SliderProperties{
	public WaterSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
		createSpinners();
	}
	
	/**
	 * Creates slider for %Fish and %Shark
	 */
	protected void createSliders() {
		super.addSlider(0, 1, "%Fish");
		super.addSlider(0, 1, "%Shark");
	}
	
	/**
	 * Creates Spinners to determine turns for fish reproduce
	 * shark reproduce and time for shark to die
	 */
	private void createSpinners() {
		super.addSpinner("Fish Reproduce");
		super.addSpinner("Shark Death");
		super.addSpinner("Shark Reproduce");
	}
	 
}
