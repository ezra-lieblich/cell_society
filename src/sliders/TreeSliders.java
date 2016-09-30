package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

public class TreeSliders extends SliderProperties{
	public TreeSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
	}
	
	protected void createSliders() {
		super.addSlider(0, 1, "%Tree");
		super.addSlider(0, 1, "%Burn");
		super.addSlider(0, 1, "%catch");	
	}
}
