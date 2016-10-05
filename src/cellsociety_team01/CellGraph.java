package cellsociety_team01;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 * Graphs the amount of each type of cell. Called by GridLogic and is dependent on GridLogic to get the sizes of each cell
 * Note that GridLogic needs to be initialized before CellGraph since it depends on it
 * @author ezra
 *
 */
public class CellGraph {
	private static final int MAX_DATA_POINTS = 50;
	
	private VBox root;
	private Map<String, Integer> cellSizes;
	private LineChart<Number, Number> graph;
	private Map<String, XYChart.Series<Number, Number>> cellPlots;
	private int stepNumber;
	private NumberAxis x_axis;
	
	/**
	 * 
	 * @param root Adds to VBox with the other elements of simulation
	 * @param cell_lengths Taken from controller, the amount of each type of cell
	 */
	public CellGraph(VBox root, Map<String, Integer> cell_lengths) {
		this.root = root;
		setupGraph(cell_lengths);
		this.stepNumber = 0;
		this.root.getChildren().add(graph);
	}
	
	/**
	 * Creates the graph and sets up the axis.
	 * @param cell_lengths
	 */
	private void setupGraph(Map<String, Integer> cell_lengths) {
		x_axis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS/10);
		x_axis.setForceZeroInRange(false);
        x_axis.setAutoRanging(false);
		Group group = new Group();
		NumberAxis y_axis = new NumberAxis();
		y_axis.setForceZeroInRange(false);
        y_axis.setAutoRanging(true);
		graph = new LineChart<Number, Number>(x_axis, y_axis);
        setupPlots(cell_lengths);
		graph.setTitle("Cell Graph");
		graph.setPrefSize(300, 300);
		group.getChildren().add(graph);
		//root.setRight(group);
		root.getChildren().add(group);
	}
	/**
	 * public because have to reset plots we have to redo data. Also needs to take in
	 * in CellSizes again in case the simulation has changed
	 */
	public void setupPlots(Map<String, Integer> cell_lengths) {
		cellSizes = cell_lengths;
		cellPlots = new HashMap<String, XYChart.Series<Number, Number>>();
		for (String name : cellSizes.keySet()) {
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName(name);
			cellPlots.put(name, series);
			graph.getData().add(series);
		}
	}
	
	/**
	 * Updates the graph after every step. Also called when we restart a graph
	 */
	public void updateGraph() {
		for (String name : cellSizes.keySet()) {
			cellPlots.get(name).getData().add(new XYChart.Data<Number, Number>(stepNumber, cellSizes.get(name)));
			//Updates the bounds if it goes past the X Axis
			if (cellPlots.get(name).getData().size() > MAX_DATA_POINTS) {
				x_axis.setLowerBound(stepNumber - MAX_DATA_POINTS);
				x_axis.setUpperBound(stepNumber - 1);
			}
		}
		stepNumber++;
	}
	
	/**
	 * called by controller when the user resets the simulation
	 */
	public void resetGraph() {
		for (String name : cellPlots.keySet()) {
			cellPlots.get(name).getData().clear();
		}
		stepNumber = 0;
		x_axis.setLowerBound(0);
		x_axis.setUpperBound(MAX_DATA_POINTS);
	}
	
}
