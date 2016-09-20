package cellsociety_team01;

public class GridController {
	private MainMenu menu;
	private GridLogic logic;
	private GridView view;
	private XMLReader reader;

	private boolean isSetupFinished;
	

	public GridController() {
		menu = new MainMenu();
		isSetupFinished = false;
	}

	public void init() {
		menu.init();
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
		reader = new XMLReader(path);
		logic = reader.getGridLogic();
		view = reader.getGridView();
		isSetupFinished = true;
	}

}
