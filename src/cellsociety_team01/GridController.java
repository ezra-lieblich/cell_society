package cellsociety_team01;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.w3c.dom.Element;

import factories.*;
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
import life.*;
import sliders.LifeSliders;
import sliders.SliderProperties;
import sliders.TreeSliders;
import sliders.WaterSliders;
import sliders.XOSliders;
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
	private GridFactory factory;
	private String simulationName;
	private BasicFiniteGrid grid;
	private SliderProperties slider;

	public GridController(Stage stage) {
		this.stage = stage;

		MainMenu menu = new MainMenu(this, stage);
		mainMenu = menu.init();
		stage.setScene(mainMenu);
		stage.show();
		setupScreenResolution();
		// temporary code
		// TODO: get title from xml
		title = "Test";
		//Controller should create initial scene with  BorderPane Root and HBox for views and create scene but not set it
	}

	public void parseFile(File file) {
		if (file.isFile() && file.getName().endsWith(XML_SUFFIX)) {
			reader = new XmlReader();
			reader.getRootElement(file);
			grid = reader.makeGrid();
			factory = reader.getGridFactory();
			setupLogicObject();
			init();
		}
		else {
			AlertBox.displayError("The file you selected is not an XML file. Please select an XML file.");
		}
	}

	private void setupLogicObject() {
		String simulationName = reader.getSim();
		if (simulationName.equals("Game Of Life")) {
			logic = new LifeGridLogic(grid);
			slider = new LifeSliders(this);
		} else if (simulationName.equals("Spread Of Fire")) {
			logic = new TreeGridLogic(grid);
			slider = new TreeSliders(this);
		} else if (simulationName.equals("WaTor World")) {

			logic = new WaterGridLogic(grid, ((WaterGridFactory)factory).getFishReproduce(), ((WaterGridFactory)factory).getSharkDeath(),
					((WaterGridFactory)factory).getSharkReproduce());
			slider = new WaterSliders(this);

		} else if (simulationName.equals("XO Segregation")) {
			logic = new XOGridLogic(grid, ((XOGridFactory)factory).getSimilarPercentage());
			slider = new XOSliders(this);

		} else {
			// TODO: throw error

		}

	}

	private void setupViewObject(VBox vbox) {
		
		String cellShape = factory.getCellShape();
		if (cellShape.equals("squ"))
			view = new SquareGridView(vbox, grid, screenWidth, screenHeight);
		else if (cellShape.equals("hex"))
			view = new HexagonalGridView(vbox, grid, screenWidth, screenHeight);
		else if (cellShape.equals("tri"))
			view = new TriangleGridView(vbox, grid, screenWidth, screenHeight);
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
		VBox vbox = new VBox(5);
		BorderPane root = new BorderPane();
		root.setLeft(vbox);
		graph = new CellGraph(vbox, logic.getCells());
		setupViewObject(vbox);
		toolbar = new Toolbar(root, this);
		slider.addBoxtoRoot(vbox);
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

	public void startSimulation() {
		animation.play();
	}

	public void stopSimulation() {
		animation.pause();
	}

	public void stepSimulation() {
		animation.pause();
		this.step();
		//graph.setupPlots(logic.getCells());
	}

	public void updateSpeed(double value) {
		double new_rate = value;
		animation.setRate(new_rate);
	}

	public void resetSimulation(Map<String, String> values) {
		animation.pause();
		graph.resetGraph();
		factory.makeGrid(values);
		graph.setupPlots(logic.getCells());
		view.step();
	}

	public void changeSimulation() {
		stage.setScene(mainMenu);
	}
}
