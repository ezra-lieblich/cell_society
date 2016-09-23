package cellsociety_team01;

import javafx.scene.Scene;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainMenu {

	private static final int menuWidth = 600;
	private static final int menuHeight = 400;
	private Scene scene;
	private BorderPane root;
	private GridController controller;
	private Stage stage;

	public MainMenu(GridController controller, Stage stage) {
		this.stage = stage;
		this.controller = controller;
		root = new BorderPane();
		scene = new Scene(root, menuWidth, menuHeight);
	}

	public Scene init() {
		root.getChildren().add(createBackgroundImage());
		root.setCenter(createText());
		
		Button b = createButton("Choose File");
		root.setAlignment(b, Pos.BASELINE_CENTER);
		root.setBottom(b);
		return scene;
	}
	
	//private Button createButton(String buttonText, EventHandler<ActionEvent> handler){
	private Button createButton(String buttonText){
		Button button = new Button(buttonText);
		FileChooser fileChooser = new FileChooser();
		
		button.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                    File file = fileChooser.showOpenDialog(stage);
	                    if (file != null) {
	                        controller.parseFile(file);
	                    }
	                }
	            });
		return button;
	}
	
//	private void fileChooser(){
//		
//		fileChooser.setTitle("Open Resource File");
//		fileChooser.showOpenDialog(stage);
//	}

	private Text createText() {
		Text t = new Text("Cell Society");
		t.setFont(Font.font ("Verdana", 70));
		t.setFill(Color.WHITE);
		return t;
	}

	private ImageView createBackgroundImage() {
		ImageView background = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream("resources/main_menu_background.jpg")));
		background.setX(0);
		background.setY(0);
		return background;
	}
}
