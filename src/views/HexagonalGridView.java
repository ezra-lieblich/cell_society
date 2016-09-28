package views;

import cellsociety_team01.GridView;
import grids.BasicFiniteGrid;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

/**
 * HexagonalGridView extends GridView and specifically creates a grid with
 * hexagonal cells
 * 
 * @author eric
 */
public class HexagonalGridView extends GridView {

	private static final double HEX_HEIGHT_MULTIPLIER = Math.sqrt(3);
	private static final double HEX_WIDTH_MULTIPLIER = 1.5;

	public HexagonalGridView(VBox root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		super(root, grid, screenWidth, screenHeight);
	}

	@Override
	protected void visualizeGrid() {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Polygon polygon = new Polygon();
				Double[] rootPoint;
				double currIterWidth = c*pixelWidth*HEX_WIDTH_MULTIPLIER;
				double offset = 0;
				if(c%2==1){
				 offset = pixelWidth*HEX_HEIGHT_MULTIPLIER/2;
				}
					rootPoint = new Double[]{
							currIterWidth-pixelWidth/2,r*pixelWidth*HEX_HEIGHT_MULTIPLIER-offset,
							currIterWidth-pixelWidth,(r+0.5)*pixelWidth*HEX_HEIGHT_MULTIPLIER-offset,
							currIterWidth-pixelWidth/2,(r+1)*pixelWidth*HEX_HEIGHT_MULTIPLIER-offset,
							currIterWidth+pixelWidth/2,(r+1)*pixelWidth*HEX_HEIGHT_MULTIPLIER-offset,
							currIterWidth+pixelWidth,(r+0.5)*pixelWidth*HEX_HEIGHT_MULTIPLIER-offset,
							currIterWidth+pixelWidth/2,r*pixelWidth*HEX_HEIGHT_MULTIPLIER-offset,
							
							};

				polygon.getPoints().addAll(rootPoint);
				polygon.setFill(grid.getGridIndex(r, c).getColor());
				gridView.getChildren().add(polygon);
			}
		}
	}

	/**
	 * Calculates the pixel size of each cell based on the size of the grid
	 */
	@Override
	protected void setupPixelSize() {

		double minPixels = Math.min(gridSizeX / HEX_HEIGHT_MULTIPLIER / grid.getRows(),
				gridSizeY / HEX_WIDTH_MULTIPLIER / grid.getColumns());
		pixelWidth = minPixels;
	}

}
