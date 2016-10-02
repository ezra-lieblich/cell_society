package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

public class WaterSliders extends SliderProperties{
	public WaterSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
		createSpinners();
		//createTextBox
	}
	
	protected void createSliders() {
		super.addSlider(0, 1, "%Fish");
		super.addSlider(0, 1, "%Shark");
	}
	
	private void createSpinners() {
		super.addSpinner("Fish Reproduce");
		super.addSpinner("Shark Death");
		super.addSpinner("Shark Reproduce");
	}
	 
}
