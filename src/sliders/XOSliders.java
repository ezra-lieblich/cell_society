package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;

public class XOSliders extends SliderProperties{
	public XOSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
	}
	
	protected void createSliders() {
		super.addSlider(0, 1, "%X");
		super.addSlider(0, 1, "%Y");
		super.addSlider(0, 1, "%Similar");
	}
}
