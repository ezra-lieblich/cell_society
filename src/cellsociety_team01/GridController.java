package cellsociety_team01;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import water.*;

public class GridController {
	private MainMenu menu;
	private GridLogic logic;
	private GridView view;
	private XmlReader reader;

	private String title;
	private Scene scene;

	private boolean isSetupFinished;

	public GridController() {
		menu = new MainMenu();
		isSetupFinished = false;

		// temporary code
		title = "Test";

	}

	public Scene init(int screenWidth, int screenHeight) {
		// menu.init();
		WaterGrid grid = createRandomWaterGrid(60,60);
		logic = new WaterGridLogic(grid);
		Group root = new Group();
		view = new GridView(root, grid);
		return new Scene(root, screenWidth, screenHeight, Color.WHITE);
	}

	public void step(double elapsedTime) {
		// if (!isSetupFinished) {
		// if (menu.getFileChosen()) {
		// setup(menu.getFilePath());
		// }
		// break;
		// }
		logic.step();
		view.step();
	}

	// private void setup(String path) {
	// reader = new XmlReader(path);
	// logic = reader.getGridLogic();
	// view = reader.getGridView();
	// isSetupFinished = true;
	// }

	public String getTitle() {
		return title;
	}

	// for testing, creates a water grid with random types of cells
	private WaterGrid createRandomWaterGrid(int rows, int columns) {
		WaterGrid temp = new WaterGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 10);
				if(ranGen<=1)
					temp.setGridIndex(new Shark(r, c), r, c);
				else if(1<ranGen&&ranGen<=8)
					temp.setGridIndex(new Fish(r, c), r, c);
				else
					temp.setGridIndex(new EmptyCell(r, c), r, c);


			}
		}
		return temp;
	}
}
