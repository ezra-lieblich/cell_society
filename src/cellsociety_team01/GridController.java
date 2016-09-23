package cellsociety_team01;

import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import water.*;
import xo.Empty;
import xo.Group1;
import xo.Group2;
import xo.XOGridLogic;
import life.*;

public class GridController {
	private MainMenu menu;
	private GridLogic logic;
	private GridView view;
	private XmlReader reader;
	private Toolbar toolbar;
	private String title;
	private Stage stage;

	private boolean runSimulation;

	public GridController(Stage stage) {
		this.stage = stage;
		menu = new MainMenu(this, stage);
		runSimulation = false;
		stage.setScene(menu.init());
		stage.show();
		// temporary code
		title = "Test";
	}
	
	public void parseFile(File file){
		System.out.println(file.getAbsolutePath());
	}

	public void init(int screenWidth, int screenHeight) {
        
		// menu.init();
		//Grid grid = createXOGrid(20,20);
		//logic = new XOGridLogic(grid);
		WaterGrid grid = createRandomWaterGrid(60,60);
		logic = new WaterGridLogic(grid);
		BorderPane root = new BorderPane();
		view = new GridView(root, grid);
		//view = new GridView(root, grid);
		toolbar = new Toolbar(root, this);
		//stage.setScene(new Scene(root, screenWidth, screenHeight, Color.WHITE));
        
		//view = new GridView(root, grid);
//		WaterGrid grid = createRandomWaterGrid(60,60);
//		logic = new WaterGridLogic(grid);
//		Group root = new Group();
//		view = new GridView(root, grid);
//		LifeGrid grid = createLifeGrid(20,20);
//		logic = new LifeGridLogic(grid);
//		Group root = new Group();
//		view = new GridView(root, grid);
//		return new Scene(root, screenWidth, screenHeight, Color.WHITE);
	}

	public void step(double elapsedTime) {
		// if (!isSetupFinished) {
		// if (menu.getFileChosen()) {
		// setup(menu.getFilePath());
		// }
		// break;
		// }
		view.step();
		if (runSimulation){
		logic.step();
		}
	}

//	private void setup(String path) {
//		reader = new XmlReader("data/xml/GameOfLife1.xml");
//		logic = reader.getGridLogic();
//		view = reader.getGridView();
//		isSetupFinished = true;
//	}

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

	public void startSimulation() {
		runSimulation = true;
	}

	public void stopSimulation() {
		runSimulation = false;
	}

	public void stepSimulation() {
		runSimulation = false;
		logic.step();
	}
}
