package cellsociety_team01;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import water.*;
import xo.Empty;
import xo.Group1;
import xo.Group2;
import xo.XOGridLogic;

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
		//Grid grid = createRandomWaterGrid(10, 10);
		Grid grid = createXOGrid(20,20);
		logic = new XOGridLogic(grid);
		//logic = new WaterGridLogic(grid);
		Group root = new Group();
		view = new GridView(root, grid);
		//view = new GridView(root, grid);
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
	private Grid createRandomWaterGrid(int rows, int columns) {
		Grid temp = new WaterGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 3);
				switch (ranGen) {
				case 0:
					temp.setGridIndex(new EmptyCell(r, c), r, c);
					break;
				case 1:
					temp.setGridIndex(new Fish(r, c), r, c);
					break;
				case 2:
					temp.setGridIndex(new Shark(r, c), r, c);
					break;
				default:
					break;
				}

			}
		}
		return temp;
	}
	private Grid createXOGrid(int rows, int columns) {
		Grid temp = new Grid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 3);
				switch (ranGen) {
				case 0:
					temp.setGridIndex(new Empty(r, c), r, c);
					break;
				case 1:
					temp.setGridIndex(new Group1(r, c), r, c);
					break;
				case 2:
					temp.setGridIndex(new Group2(r, c), r, c);
					break;
				default:
					break;
				}
			}
		}
		return temp;
	}
}
