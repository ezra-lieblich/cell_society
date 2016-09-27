package cellsociety_team01;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class CellGraph {
	private static final int MAX_DATA_POINTS = 50;
	
	private BorderPane root;
	private List<Number> cellSizes;
	private LineChart<Number, Number> graph;
	private List<XYChart.Series<Number, Number>> cellPlots;
	private Number stepNumber;
	
	public CellGraph(BorderPane root, List<Number> cell_lengths) {
		this.root = root;
		this.cellSizes = cell_lengths;
		cellSizes = new ArrayList<Number>();
		cellSizes.add(3);
		cellSizes.add(5);
		setupGraph();
		stepNumber = 0;
	}
	
	private void setupGraph() {
		NumberAxis x_axis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS/10);
//		x_axis.setForceZeroInRange(false);
//		x_axis.setAutoRanging(false);
//	    x_axis.setTickLabelsVisible(false);
//	    x_axis.setTickMarkVisible(false);
//	    x_axis.setMinorTickVisible(false);
		Group group = new Group();
		NumberAxis y_axis = new NumberAxis();
		graph = new LineChart<Number, Number>(x_axis, y_axis);
		cellPlots = new ArrayList<XYChart.Series<Number, Number>>();
		for (int i = 0; i < cellSizes.size(); i++) {
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			cellPlots.add(series);
		}
		graph.setTitle("Cell Graph");
		cellPlots.get(0).getData().add(new XYChart.Data<Number, Number>(1, 5));
		cellPlots.get(0).getData().add(new XYChart.Data<Number, Number>(2, 10));
		group.getChildren().add(graph);
		//root.setRight(group);
		root.getChildren().add(group);
	}
	
	public void updateGraph() {
		for (int i = 0; i < cellPlots.size(); i++) {
			cellPlots.get(i).getData().add(new XYChart.Data<Number, Number>(stepNumber, cellSizes.get(i)));
			if (cellPlots.get(i).getData().size() > MAX_DATA_POINTS) {
				cellPlots.get(i).getData().remove(0);
			}
		}
		
	}
	
}
