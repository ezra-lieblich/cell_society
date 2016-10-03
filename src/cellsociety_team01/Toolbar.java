package cellsociety_team01;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/**
 * Toolbar is called on by the GridController when a simulation is chosen. It allows
 * the user to start, stop, and step through the simulation. It also allows the user
 * to change the speed of the simulation, the type of simulation, and reset the simulation
 * @author ezra
 *
 */
public class Toolbar {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private BorderPane root;
	private GridController controller;
	private Pane toolbar;
    private ResourceBundle myResources;

	
	/**
	 * The Constructor sets the parameters passed and creates an HBox that is set to the bottom of the 
	 * BorderPane. It also calls setup Buttons which creates all the buttons.
	 * @param root The BorderPane root
	 * @param controller Controller so we can call events back through the controller
	 */
	public Toolbar(BorderPane root, GridController controller) {
		this.root = root;
		this.controller = controller;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.toolbar = new HBox(20);
		setupButtons();
		this.root.setBottom(toolbar);
		BorderPane.setAlignment(toolbar, Pos.BOTTOM_CENTER);
	}
	
	/**
	 * Creates all the buttons on the toolbar and also creates the slider to change the speed
	 * 
	 */
	private void setupButtons() {
		makeButton(myResources.getString("StartButton"), event -> controller.startSimulation());
		makeButton(myResources.getString("StopButton"), event -> controller.stopSimulation());
		makeButton(myResources.getString("StepButton"), event -> controller.stepSimulation());
		makeSlider();
		makeButton(myResources.getString("AddButton"), event -> controller.addSimulation());
		BorderPane.setAlignment(toolbar, Pos.BOTTOM_RIGHT);
	}

	/**
	 * Creates a another HBox that contains the slider itself as well as a label to show the current simulation
	 * speed
	 * 
	 */
	private void makeSlider() {
		HBox result = new HBox(5);
		result.getChildren().add(new Label(myResources.getString("SimulationSpeed")));
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


	/**
	 * Updates the slider whenever the user changes the speed on the slider. Also tells controller
	 * to adjust simulation rate accordingly
	 * @param simulation_speed The label that displays the current simulation rate
	 * @param value The new value of the slider after the user releases
	 */
	private void updateSlider(Label simulation_speed, double value) {
		simulation_speed.setText(Double.toString(value));
		controller.updateSpeed(value);
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
