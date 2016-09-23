package cellsociety_team01;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Toolbar {
	private BorderPane root;
	private GridController controller;
	private HBox toolbar;
	private Button startButton;
	private Button stopButton;
	private Button stepButton;
	private HBox speedSlider;
	
	
	public Toolbar(BorderPane root, GridController controller) {
		this.root = root;
		this.controller = controller;
		toolbar = new HBox(20);
		root.setBottom(toolbar);
		setupButtons();
	}
	
	private void setupButtons() {
		makeButton("Start", event -> controller.startSimulation());
		makeButton("Stop", event -> controller.stopSimulation());
		makeButton("Step", event -> controller.stepSimulation());
		makeSlider();
		makeButton("Reset", event -> controller.resetSimulation());
		makeButton("Change Simulation", event -> controller.changeSimulation());
	}

	private void makeSlider() {
		HBox result = new HBox(5);
		result.getChildren().add(new Label("Simulation Speed"));
		Slider slider = new Slider(0, 2, 1);
		result.getChildren().add(slider);
		Label simulation_speed = new Label(Double.toString(slider.getValue()));
		result.getChildren().add(simulation_speed);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);
		slider.setSnapToTicks(true);
		slider.setOnMouseReleased(e -> updateSlider(simulation_speed, slider.getValue()));
		toolbar.getChildren().add(result);
	}



	private Object updateSlider(Label simulation_speed, double value) {
		simulation_speed.setText(Double.toString(value));
		controller.updateSpeed(value);
		return null;
	}

	/**
	 * Creates a button and adds it to the toolbar.
	 * @param text Button Text
	 * @param handler Event to be called when the button is clicked
	 */
	private void makeButton(String text, EventHandler<ActionEvent> handler) {
		Button result = new Button();
		result.setText(text);
		result.setOnAction(handler);
		toolbar.getChildren().add(result);
	}
}
