package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

public class WaterSliders extends SliderProperties{
	public WaterSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
		//createTextBox
	}
	
	protected void createSliders() {
		super.addSlider(0, 1, "%Fish");
		super.addSlider(0, 1, "%Shark");
	}
}
