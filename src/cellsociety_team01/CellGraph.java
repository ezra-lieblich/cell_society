package cellsociety_team01;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class CellGraph {
	private static final int MAX_DATA_POINTS = 50;
	
	private BorderPane root;
	private List<Integer> cellSizes;
	private LineChart<Number, Number> graph;
	private List<XYChart.Series<Number, Number>> cellPlots;
	private int stepNumber;
	
	public CellGraph(BorderPane root, List<Integer> cell_lengths) {
		this.root = root;
		this.cellSizes = cell_lengths;
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
		NumberAxis y_axis = new NumberAxis();
		graph = new LineChart<Number, Number>(x_axis, y_axis);
		cellPlots = new ArrayList<XYChart.Series<Number, Number>>();
		for (int i = 0; i < cellSizes.size(); i++) {
			XYChart.Series<Number, Number> series = new XYChart.Series<>();
			cellPlots.add(series);
		}
		graph.setTitle("Cell Graph");
		root.getChildren().add(graph);
	}
	
	public void updateGraph() {
		for (int i = 0; i < cellPlots.size(); i++) {
			cellPlots.get(i).getData().add(new XYChart.Data<>(stepNumber, cellSizes.get(i)));
			if (cellPlots.get(i).getData().size() > MAX_DATA_POINTS) {
				cellPlots.get(i).getData().remove(0);
			}
		}
	}
}
