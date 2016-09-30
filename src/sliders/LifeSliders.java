package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

public class LifeSliders extends SliderProperties{
	public LifeSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
	}
	
	protected void createSliders() {
		super.addSlider(0, 1, "%Alive");
	}
}
