package sliders;

import cellsociety_team01.GridController;
import javafx.scene.layout.Pane;
/**
 * Creates slider properties for Burning Tree simulation 
 * @author ezra
 *
 */
public class TreeSliders extends SliderProperties{
	public TreeSliders(Pane root, GridController control) {
		super(root, control);
		createSliders();
	}
	
	/**
	 * Creates sliders for %Tree %Burn and %Catch rate
	 */
	protected void createSliders() {
		super.addSlider(0, 1, "%Tree");
		super.addSlider(0, 1, "%Burn");
		super.addSlider(0, 1, "%Catch");	
	}
}
