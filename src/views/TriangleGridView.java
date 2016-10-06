package views;

import cellsociety_team01.GridView;
import grids.BasicFiniteGrid;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * TriangleGridView extends GridView and specifically creates a grid with
 * triangle cells
 * 
 * @author eric
 */
public class TriangleGridView extends GridView {

	private static final double TRIANGLE_HEIGHT_MULTIPLIER = Math.sqrt(3) / 2.0;
	private static final double TRIANGLE_WIDTH_MULTIPLIER = 0.5;

	public TriangleGridView(VBox root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		super(root, grid, screenWidth, screenHeight);
	}

	/**
	 * Loops through each Cell in grid and draws the three points of the
	 * triangle, adds to a Polygon object and adds to the root
	 */
	@Override
	protected void visualizeGrid() {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Polygon polygon = new Polygon();
				Double[] rootPoint;
				if (c % 2 == 0) {
					rootPoint = new Double[] { c * pixelWidth * TRIANGLE_WIDTH_MULTIPLIER,
							r * pixelWidth * TRIANGLE_HEIGHT_MULTIPLIER,
							(c + 2) * pixelWidth * TRIANGLE_WIDTH_MULTIPLIER,
							r * pixelWidth * TRIANGLE_HEIGHT_MULTIPLIER,
							(c + 1) * pixelWidth * TRIANGLE_WIDTH_MULTIPLIER,
							(r + 1) * pixelWidth * TRIANGLE_HEIGHT_MULTIPLIER };
				} else {
					rootPoint = new Double[] { (c + 1) * pixelWidth * TRIANGLE_WIDTH_MULTIPLIER,
							r * pixelWidth * TRIANGLE_HEIGHT_MULTIPLIER, c * pixelWidth * TRIANGLE_WIDTH_MULTIPLIER,
							(r + 1) * pixelWidth * TRIANGLE_HEIGHT_MULTIPLIER,
							(c + 2) * pixelWidth * TRIANGLE_WIDTH_MULTIPLIER,
							(r + 1) * pixelWidth * TRIANGLE_HEIGHT_MULTIPLIER };
				}
				polygon.getPoints().addAll(rootPoint);
				polygon.setFill(grid.getGridIndex(r, c).getColor());
				// polygon.setStroke(Color.BLACK);
				gridView.getChildren().add(polygon);
			}
		}
		// Rectangle temp = new Rectangle(10,10,10,10);
		// temp.setFill);
		// gridView.getChildren().add(temp);
	}

	/**
	 * Calculates the pixel size of each cell based on the size of the grid
	 */
	@Override
	protected void setupPixelSize() {
		// equalateral triangle has height multiplier of sqrt(3)/2
		double minPixels = Math.min(gridHeight / TRIANGLE_HEIGHT_MULTIPLIER / grid.getRows(),
				gridWidth / TRIANGLE_WIDTH_MULTIPLIER / grid.getColumns());
		pixelWidth = minPixels;
	}

}
