package sliders;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import cellsociety_team01.GridController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class SliderProperties {
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	private Pane box;
    private ResourceBundle myResources;
    private GridController controller;
    private Map<String, Double> propertyValues;

	public SliderProperties(Pane root, GridController control) {
		controller = control;
		box = new VBox();
		propertyValues = new HashMap<String, Double>();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		addButton(myResources.getString("ResetButton"), event -> controller.resetSimulation(propertyValues));
		root.getChildren().add(box);
	}
	
	private void addButton(String text, EventHandler<ActionEvent> handler) {
		Button result = new Button();
		result.setText(text);
		result.setOnAction(handler);
		box.getChildren().add(result);
	}
	
	protected void addSlider(int min, int max, String slider_name) {
		HBox result = new HBox(2);
		result.getChildren().add(new Label(slider_name));
		Slider slider = new Slider(min, max, max/2);
		//initially add the slider to map
		updatePropertyFile(slider_name, slider.getValue());
		result.getChildren().add(slider);
		Label cell_percentage = new Label(Double.toString(slider.getValue()));
		result.getChildren().add(cell_percentage);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);
		slider.setOnMouseReleased(e -> {
			//value rounded to hundredths place
			double value = Math.round(slider.getValue() *100.0) / 100.0;
			cell_percentage.setText(Double.toString(value));
			updatePropertyFile(slider_name, value);
			System.out.println(propertyValues);
		});
		box.getChildren().add(result);
	}
	
	protected void addSpinner(String name) {
		HBox result = new HBox(2);
		result.getChildren().add(new Label(name));
		Spinner<Double> spinner = new Spinner<Double>();
		SpinnerValueFactory.DoubleSpinnerValueFactory spinnerValue = 
				new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 4);
		spinnerValue.setAmountToStepBy(1);
		spinner.setValueFactory(spinnerValue);
		updatePropertyFile(name, spinnerValue.getValue());
		spinner.setOnMouseReleased(e -> {
			double value = spinnerValue.getValue();
			updatePropertyFile(name, value);
			System.out.println(propertyValues);
		});
		result.getChildren().add(spinner);
		box.getChildren().add(result);
	}

	private void updatePropertyFile(String slider_name, double value) {
		propertyValues.put(slider_name, value);
	}
	protected abstract void createSliders();
}
