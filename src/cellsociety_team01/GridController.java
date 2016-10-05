//This File from 99 to 113 is part of my masterpiece as it goes with refactored code
//Ezra Lieblich  
package cellsociety_team01;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import factories.*;
import grids.BasicFiniteGrid;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.stage.Stage;

import sliders.SliderProperties;

/**
 * GridController that handles the animation timeline of the loop/simulation and passes everything to each other
 *  It also switches scenes between the main menu and simulation scene 
 * @author ezra
 *
 */
public class GridController {

	private XmlReader reader;
	private Toolbar toolbar;
	private String title;
	private Scene scene;
	private Timeline animation;
	private Stage stage;
	private Scene mainMenu;
	private int screenWidth, screenHeight;
	private static final String XML_FILES_LOCATION = "data/xml/";
	private static final String XML_SUFFIX = ".xml";

	
	private List<BasicFiniteGrid> grids;
	private List<GridLogic> logics;
	private List<GridView> views;
	private List<CellGraph> graphs;
	private List<GridFactory> factories;
	private List<SliderProperties> sliders;
	private List<VBox> simulations;
	
	private BorderPane root;
	private Pane simSpace;
	
	/**
	 * Creates simulation space and BorderPane and also sets the screen size, and intially sets scene to main menu
	 * to pick a simulation.
	 * @param stage Stage passed from main when launched
	 */
	public GridController(Stage s) {
		stage = s;
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
		title = "Cell Society";
	}

	/**
	 * Creates all the lists for multiple simulations
	 */
	private void setupLists() {
		grids = new ArrayList<BasicFiniteGrid>();
		logics = new ArrayList<GridLogic>();
		views = new ArrayList<GridView>();
		graphs = new ArrayList<CellGraph>();
		factories = new ArrayList<GridFactory>();
		sliders = new ArrayList<SliderProperties>();
		simulations = new ArrayList<VBox>();
	}
	
	/**
	 * Based on the file, creates the proper grid and factory and then calls helper methods to
	 * set up logic and view of the simulation
	 * @param file Xml File selected
	 */
	public void parseFile(File file) {
		if (file.isFile() && file.getName().endsWith(XML_SUFFIX)) {
			reader.getRootElement(file);
			grids.add(reader.makeGrid());
			factories.add(reader.getGridFactory());
			setupLogicObject(factories.size() - 1);
			init(factories.size() - 1);
		}
		else {
			AlertBox.displayError("The file you selected is not an XML file. Please select an XML file.");
		}
	}

	/**
	 * Adds the sliders and logic to the corresponding lists based on the type of simulation.
	 * Relies on LogicFactory to get proper logic/sliders
	 * @param index
	 */
	private void setupLogicObject(int index) {
		String simType = reader.getSim();
		LogicFactory logicFactory = new LogicFactory();
		logicFactory.setupLogic(simType, grids.get(index), this, factories.get(index));
		logics.add(logicFactory.getLogic());
		sliders.add(logicFactory.getSlider());
	}

	/**
	 * Creates the proper view and adds it to the VBox holding the sim elements
	 * @param vbox Pane that holds individual sim, sliders, and graph
	 * @param index index to get proper grid to add to gridview
	 */
	private void setupViewObject(VBox vbox, int index) {
		String cellShape = factories.get(index).getCellShape();
		ViewFactory viewFactory = new ViewFactory();
		GridView view = viewFactory.setupView(cellShape);
		view.init(vbox, grids.get(index), screenWidth, screenHeight);
		views.add(view);
	}

	/**
	 * Sets up the view of the scene
	 */
	private void setupScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	/**
	 * Called by parse file after main menu has chosen an xml file. Sets up the view, switches the scene
	 * @param index proper index to add to reference things for the view
	 */
	public void init(int index) {
		setupView(index);
		createTimeline();
		stage.setScene(scene);
		// display the view initially before starting simulation
		gridViewsStep();
	}

	/**
	 * Sets up all the view components and  graphs
	 * @param index index to get corresponding logics and simulations for new components
	 */
	private void setupView(int index) {
		simulations.add(new VBox(5));
		graphs.add(new CellGraph(simulations.get(index), logics.get(index).getCells()));
		setupViewObject(simulations.get(index), index);
		sliders.get(index).addBoxtoRoot(simulations.get(index));
		simSpace.getChildren().add(simulations.get(index));
	}

	/**
	 * Creates a timeline for the simulation
	 */
	private void createTimeline() {
		int MILLISECOND_DELAY = 500;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> this.step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}

	/**
	 * Steps through all necessary views and logics, also updates logic
	 */
	public void step() {
		gridViewsStep();
		updateGraphs();
		gridLogicsStep();
	}

	/**
	 * Steps through all the grids
	 */
	private void gridViewsStep() {
		for (GridView view : views) {
			view.step();
		}
	}
	
	/**
	 * Steps through all the graphs
	 */
	private void updateGraphs() {
		for (CellGraph graph : graphs) {
			graph.updateGraph();
		}
	}
	
	/**
	 * Steps through all the gridlogics
	 */
	private void gridLogicsStep() {
		for (GridLogic logic : logics) {
			logic.step();
		}
	}
	public String getTitle() {
		return title;
	}

	/**
	 * Called by the toolbar and starts all the simulations
	 */
	public void startSimulation() {
		animation.play();
	}

	/**
	 * Called by the toolbar and pauses the simulation
	 */
	public void stopSimulation() {
		animation.pause();
	}

	/**
	 * Called by toolbar. Pauses simulation and steps through all the simulations on screen once.
	 */
	public void stepSimulation() {
		animation.pause();
		this.step();

	}

	/**
	 * Called by the toolbar and sets the speed of the simulation based off of slider value
	 * @param value new speed of simulation
	 */
	public void updateSpeed(double value) {
		double new_rate = value;
		animation.setRate(new_rate);
	}

	/**
	 * Called by Sliders
	 * Takes in values from the slider and resets the proper grid and also resets the graph
	 * @param values values passed from the slider that go into factory and resets the grid
	 * @param object Determine the index in list of sliders
	 */
	public void resetSimulation(Map<String, String> values, SliderProperties object) {
		animation.pause();
		int index = sliders.indexOf(object);
		graphs.get(index).resetGraph();
		factories.get(index).makeGrid(values);
		views.get(index).step();
	}

	/**
	 * Called by the sliders
	 * Finds the index inside all the maps and removes them from the list and also view
	 * @param object Need object to figure out what index inside simSpace we are removing
	 */
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
	
	/**
	 * Called by the toolbar
	 * Changes to main menu so we can simulation to view
	 */
	public void addSimulation() {
		animation.pause();
		stage.setScene(mainMenu);
	}
}
