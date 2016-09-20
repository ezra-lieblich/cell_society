package cellsociety_team01;

import javafx.scene.Scene;

public class GridController {
	private MainMenu menu;
	private GridLogic logic;
	private GridView view;
	private XmlReader reader;
	
	private String title;

	private boolean isSetupFinished;

	public GridController() {
		menu = new MainMenu();
		isSetupFinished = false;
		
		//temporary code
		title = "Test";
	}

	public Scene init(int screenWidth, int screenHeight) {
		menu.init();
		
		
//		return new ;
	}

	public void step(double elapsedTime) {
		if (!isSetupFinished) {
			if (menu.getFileChosen()) {
				setup(menu.getFilePath());
			}
			break;
		}
		logic.step(elapsedTime);
		view.step(elapsedTime);
	}

	private void setup(String path) {
		reader = new XmlReader(path);
		logic = reader.getGridLogic();
		view = reader.getGridView();
		isSetupFinished = true;
	}

	public String getTitle(){
		return title;
	}
}
