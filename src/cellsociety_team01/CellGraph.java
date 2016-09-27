package cellsociety_team01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

/**
 * Graphs the amount of each type of cell. Called by GridLogic and is dependent on GridLogic to get the sizes of each cell
 * Note that GridLogic needs to be initialized before CellGraph since it depends on it
 * @author ezra
 *
 */
public class CellGraph {
	private static final int MAX_DATA_POINTS = 50;
	
	private BorderPane root;
	private Map<String, Integer> cellSizes;
	private LineChart<Number, Number> graph;
	private Map<String, XYChart.Series<Number, Number>> cellPlots;
	private int stepNumber;
	private XYChart.Series<Number, Number> series;
	private NumberAxis x_axis;
	
	public CellGraph(BorderPane root, Map<String, Integer> cell_lengths) {
		this.root = root;
		this.cellSizes = cell_lengths;
		setupGraph();
		stepNumber = 0;
	}
	
	private void setupGraph() {
		x_axis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS/10);
		x_axis.setForceZeroInRange(false);
        x_axis.setAutoRanging(false);

       //x_axis.setTickLabelsVisible(false);
       //x_axis.setTickMarkVisible(false);
       //x_axis.setMinorTickVisible(false);
		Group group = new Group();
		NumberAxis y_axis = new NumberAxis();
		y_axis.setForceZeroInRange(false);
        y_axis.setAutoRanging(false);

        //y_axis.setTickLabelsVisible(false);
        //y_axis.setTickMarkVisible(false);
        //y_axis.setMinorTickVisible(false);
		graph = new LineChart<Number, Number>(x_axis, y_axis);
		cellPlots = new HashMap<String, XYChart.Series<Number, Number>>();
		for (String name : cellSizes.keySet()) {
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName(name);
			cellPlots.put(name, series);
			graph.getData().add(series);
		}
		graph.setTitle("Cell Graph");
		
		group.getChildren().add(graph);
		//root.setRight(group);
		root.getChildren().add(group);
	}
	
	public void updateGraph() {
		for (String name : cellSizes.keySet()) {
			cellPlots.get(name).getData().add(new XYChart.Data<Number, Number>(stepNumber, cellSizes.get(name)));
			if (cellPlots.get(name).getData().size() > MAX_DATA_POINTS) {
				//cellPlots.get(name).getData().remove(0);
				x_axis.setLowerBound(stepNumber - MAX_DATA_POINTS);
				x_axis.setUpperBound(stepNumber - 1);
			}
		}
		stepNumber++;
	}
	
}
