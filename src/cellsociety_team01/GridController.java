package cellsociety_team01;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

import factories.*;
import grids.BasicFiniteGrid;
import grids.BasicToroidalGrid;
import grids.HexagonalFiniteGrid;
import grids.HexagonalToroidalGrid;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	
	private List<BasicFiniteGrid> grids;
	private List<GridLogic> logics;
	private List<GridView> views;
	private List<CellGraph> graphs;
	private List<GridFactory> factories;
	private List<SliderProperties> sliders;
	private List<VBox> simulations;
	
	private BorderPane root;
	private Pane simSpace;
	
	public GridController(Stage stage) {
		this.stage = stage;
		setupScreenResolution();
		reader = new XmlReader();
		root = new BorderPane();
		ScrollPane scroller = new ScrollPane(simSpace);		//scroller.setPrefSize(screenWidth, screenHeight);
		simSpace = new HBox(100);
		scroller.setContent(simSpace);
		scroller.setMaxWidth(screenWidth);
		scroller.setMaxHeight(screenHeight*.98);
		root.setLeft(scroller);
		toolbar = new Toolbar(root, this);
		setupLists();
		scene = new Scene(root, screenWidth, screenHeight, Color.WHITE);
		MainMenu menu = new MainMenu(this, stage);
		mainMenu = menu.init();
		stage.setScene(mainMenu);
		stage.show();
		title = "Test";
	}

	private void setupLists() {
		grids = new ArrayList<BasicFiniteGrid>();
		logics = new ArrayList<GridLogic>();
		views = new ArrayList<GridView>();
		graphs = new ArrayList<CellGraph>();
		factories = new ArrayList<GridFactory>();
		sliders = new ArrayList<SliderProperties>();
		simulations = new ArrayList<VBox>();
	}
	public void parseFile(File file) {
		if (file.isFile() && file.getName().endsWith(XML_SUFFIX)) {
			reader.getRootElement(file);
			grids.add(reader.makeGrid());
			factories.add(reader.getGridFactory());
			setupLogicObject(grids.size() - 1);
			init(grids.size() - 1);
		}
		else {
			AlertBox.displayError("The file you selected is not an XML file. Please select an XML file.");
		}
	}

	private void setupLogicObject(int index) {
		String simulationName = reader.getSim();
		if (simulationName.equals("Game Of Life")) {
			logics.add(new LifeGridLogic(grids.get(index)));
			sliders.add(new LifeSliders(this));
		} else if (simulationName.equals("Spread Of Fire")) {
			logics.add(new TreeGridLogic(grids.get(index)));
			sliders.add(new TreeSliders(this));
		} else if (simulationName.equals("WaTor World")) {
			logics.add(new WaterGridLogic(grids.get(index), ((WaterGridFactory)factories.get(index)).getFishReproduce(), ((WaterGridFactory)factories.get(index)).getSharkDeath(),
					((WaterGridFactory)factories.get(index)).getSharkReproduce()));
			sliders.add(new WaterSliders(this));

		} else if (simulationName.equals("XO Segregation")) {
			logics.add(new XOGridLogic(grids.get(index), ((XOGridFactory)factories.get(index)).getSimilarPercentage()));
			sliders.add(new XOSliders(this));

		} else {
			// TODO: throw error
		}

	}

	private void setupViewObject(VBox vbox, int index) {
		
		String cellShape = factories.get(index).getCellShape();
		if (cellShape.equals("squ")) {
			views.add(new SquareGridView(vbox, grids.get(index), screenWidth, screenHeight));
		}
		else if (cellShape.equals("hex")){
			views.add(new HexagonalGridView(vbox, grids.get(index), screenWidth, screenHeight));
		}
		else if (cellShape.equals("tri")) {
			views.add(new TriangleGridView(vbox, grids.get(index), screenWidth, screenHeight));
		}
	}

	private void setupScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}

	public void init(int index) {

		setupView(index);

		createTimeline();
		stage.setScene(scene);
		// display the view initially before starting simulation
		gridViewsStep();
	}

	private void setupView(int index) {
		simulations.add(new VBox(5));
		graphs.add(new CellGraph(simulations.get(index), logics.get(index).getCells()));
		setupViewObject(simulations.get(index), index);
		sliders.get(index).addBoxtoRoot(simulations.get(index));
		simSpace.getChildren().add(simulations.get(index));
	}

	private void createTimeline() {
		int MILLISECOND_DELAY = 500;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> this.step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}

	public void step() {
		gridViewsStep();
		updateGraphs();
		gridLogicsStep();
	}

	private void gridViewsStep() {
		for (GridView view : views) {
			view.step();
		}
	}
	
	private void updateGraphs() {
		for (CellGraph graph : graphs) {
			graph.updateGraph();
		}
	}
	
	private void gridLogicsStep() {
		for (GridLogic logic : logics) {
			logic.step();
		}
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

	}

	public void updateSpeed(double value) {
		double new_rate = value;
		animation.setRate(new_rate);
	}

	public void resetSimulation(Map<String, String> values, SliderProperties object) {
		animation.pause();
		int index = sliders.indexOf(object);
		graphs.get(index).resetGraph();
		factories.get(index).makeGrid(values);
		views.get(index).step();
	}

	public void removeSimulation(SliderProperties object) {
		animation.pause();
		int index = sliders.indexOf(object);
		grids.remove(index);
		logics.remove(index);
		views.remove(index);
		graphs.remove(index);
		factories.remove(index);
		sliders.remove(index);
		simSpace.getChildren().remove(simulations.get(index));
		simulations.remove(index);
	}
	
	public void addSimulation() {
		animation.pause();
		stage.setScene(mainMenu);
	}
}
