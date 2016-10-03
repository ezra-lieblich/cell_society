package cellsociety_team01;

import javafx.scene.Scene;
import java.io.File;
import java.util.ResourceBundle;

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
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final int menuWidth = 600;
	private static final int menuHeight = 400;
	private Scene scene;
	private BorderPane root;
	private GridController controller;
	private Stage stage;
    private ResourceBundle myResources;


	public MainMenu(GridController controller, Stage stage) {
		this.stage = stage;
		this.controller = controller;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.root = new BorderPane();
		this.scene = new Scene(root, menuWidth, menuHeight);
	}

	public Scene init() {
		root.getChildren().add(createBackgroundImage());
		root.setCenter(createText());
		
		Button b = createButton(myResources.getString("ChooseFile"));
		BorderPane.setAlignment(b, Pos.BASELINE_CENTER);
		root.setBottom(b);
		return scene;
	}
	
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
	


	private Text createText() {
		Text t = new Text(myResources.getString("Title"));
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
