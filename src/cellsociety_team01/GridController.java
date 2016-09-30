package cellsociety_team01;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Element;
import grids.BasicFiniteGrid;
import grids.BasicToroidalGrid;
import grids.HexagonalFiniteGrid;
import grids.HexagonalToroidalGrid;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.stage.Stage;
import water.*;
import xo.*;
import water.EmptyCell;
import xo.Group1;
import xo.Group2;
import xo.XOGridLogic;
import xo.XOXMLFactory;
import life.*;
import tree.*;
import views.HexagonalGridView;
import views.SquareGridView;
import views.TriangleGridView;

public class GridController {
	// private
	private GridLogic logic;
	private GridView view;
	private XmlReader reader;
	private Toolbar toolbar;
	private CellGraph graph;
	private String title;
	private Scene scene;
	private Timeline animation;
	private Stage stage;
	private Scene mainMenu;
	private int screenWidth, screenHeight;
	private static final String XML_FILES_LOCATION = "data/xml/";
	private static final String XML_SUFFIX = ".xml";
	private SimulationXMLFactory factory;
	private String simulationName;
	private BasicFiniteGrid grid;

	public GridController(Stage stage) {
		this.stage = stage;

		MainMenu menu = new MainMenu(this, stage);
		mainMenu = menu.init();
		stage.setScene(mainMenu);
		stage.show();
		setupScreenResolution();
		// temporary code
		//TODO: get title from xml
		title = "Test";
	}

	public void parseFile(File file) {
		if (file.isFile() && file.getName().endsWith(XML_SUFFIX)) {
			Element root = reader.getRootElement(file);
			XMLFactory tempFactory = new XMLFactory();
			simulationName = tempFactory.getTextValue(root, "simulation_name");
		}
		reader = new XmlReader();
		grid = reader.simChooser(simulationName);
		setupLogicObject();

		init();
	}

	private void setupLogicObject() {
		// String simName = reader.getSim();
		if (simulationName.equals("Game Of Life")) {
			logic = new LifeGridLogic(grid);
		} else if (simulationName.equals("Spread Of Fire")) {
			logic = new TreeGridLogic(grid);
		} else if (simulationName.equals("WaTor World")) {
			logic = new WaterGridLogic(grid, reader.getFishReproduce(), reader.getSharkDeath(),
					reader.getSharkReproduce());
		} else if (simulationName.equals("XO Segregation")) {
			logic = new XOGridLogic(grid, reader.getPercentSimilar());
		} else {
			// TODO: throw error

		}

	}

	private void setupScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}

	public void init() {

		BorderPane root = setupView(grid);
		createTimeline();
		stage.setScene(scene = new Scene(root, screenWidth, screenHeight, Color.WHITE));
		// display the view initially before starting simulation
		view.step();
	}

	private BorderPane setupView(BasicFiniteGrid grid) {
		BorderPane root = new BorderPane();
		VBox vbox = new VBox(5);
		root.setLeft(vbox);
		graph = new CellGraph(vbox, logic.getCells());
		view = new HexagonalGridView(vbox, grid, screenWidth, screenHeight);
		toolbar = new Toolbar(root, this);
		return root;
	}

	private void createTimeline() {
		int MILLISECOND_DELAY = 500;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> this.step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}

	public void step() {
		view.step();
		graph.updateGraph();
		logic.step();
	}

	public String getTitle() {
		return title;
	}

	// // for testing, creates a water grid with random types of cells
	// private BasicFiniteGrid createRandomWaterGrid(int rows, int columns) {
	// BasicFiniteGrid temp = new HexagonalFiniteGrid(rows, columns);
	// for (int r = 0; r < rows; r++) {
	// for (int c = 0; c < columns; c++) {
	// int ranGen = (int) (Math.random() * 10);
	// if(ranGen<=1)
	// temp.setGridIndex(new Shark(r, c), r, c);
	// else if(1<ranGen&&ranGen<=8)
	// temp.setGridIndex(new Fish(r, c), r, c);
	// else
	// temp.setGridIndex(new water.EmptyCell(r, c), r, c);
	// }
	// }
	// return temp;
	// }

	private BasicFiniteGrid createXOGrid(int rows, int columns) {
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 3);
				switch (ranGen) {
				case 0:
					temp.setGridIndex(new Clear(r, c), r, c);
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
		animation.play();
	}

	public void stopSimulation() {
		animation.pause();
	}

	public void stepSimulation() {
		animation.pause();
		this.step();
	}

	public void updateSpeed(double value) {
		double new_rate = value;
		animation.setRate(new_rate);
	}

	public void resetSimulation() {
		// Controller will tell grid to reset itself to the original
		// implementation
		// need to set original grid to back to
		// grid.resetGrid();

	}

	public void changeSimulation() {
		stage.setScene(mainMenu);
	}
}
