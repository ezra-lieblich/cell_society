package cellsociety_team01;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Toolbar {
	private BorderPane root;
	private GridController controller;
	private Button startButton;
	private Button stopButton;
	private Button stepButton;
	
	
	public Toolbar(BorderPane root, GridController controller) {
		this.root = root;
		setupButtons();
		this.controller = controller;
	}
	
	private void setupButtons() {
		HBox bar = new HBox(20);
		root.setBottom(bar);
		startButton = makeButton("Start", event -> controller.startSimulation());
		bar.getChildren().add(startButton);
		stopButton = makeButton("Stop", event -> controller.stopSimulation());
		bar.getChildren().add(stopButton);
		stepButton = makeButton("Step", event -> controller.stepSimulation());
		bar.getChildren().add(stepButton);
	}

	private Button makeButton(String text, EventHandler<ActionEvent> handler) {
		Button result = new Button();
		result.setText(text);
		result.setOnAction(handler);
		return result;
	}
}
