package views;

import cellsociety_team01.GridView;
import grids.BasicFiniteGrid;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;

/**
 * HexagonalGridView extends GridView and specifically creates a grid with
 * hexagonal cells
 * 
 * @author eric
 */
public class HexagonalGridView extends GridView {

	private double hexHeightMultiplier = Math.sqrt(3);
	private double hexWidthMultiplier = 1.5;

	public HexagonalGridView(BorderPane root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		super(root, grid, screenWidth, screenHeight);
	}

	@Override
	protected void visualizeGrid() {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Polygon polygon = new Polygon();
				Double[] rootPoint;
				double currIterWidth = c*pixelWidth*hexWidthMultiplier;
				double offset = 0;
				if(c%2==1){
				 offset = pixelWidth*hexHeightMultiplier/2;
				}
					rootPoint = new Double[]{
							currIterWidth-pixelWidth/2,r*pixelWidth*hexHeightMultiplier-offset,
							currIterWidth-pixelWidth,(r+0.5)*pixelWidth*hexHeightMultiplier-offset,
							currIterWidth-pixelWidth/2,(r+1)*pixelWidth*hexHeightMultiplier-offset,
							currIterWidth+pixelWidth/2,(r+1)*pixelWidth*hexHeightMultiplier-offset,
							currIterWidth+pixelWidth,(r+0.5)*pixelWidth*hexHeightMultiplier-offset,
							currIterWidth+pixelWidth/2,r*pixelWidth*hexHeightMultiplier-offset,
							
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

		double minPixels = Math.min(gridSizeX * hexHeightMultiplier / grid.getRows(),
				gridSizeY * hexWidthMultiplier / grid.getColumns());
		pixelWidth = minPixels;
	}

}
