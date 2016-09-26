package views;

import cellsociety_team01.GridView;
import grids.BasicFiniteGrid;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * TriangleGridView extends GridView and specifically creates a grid with
 * triangle cells
 * 
 * @author eric
 */
public class TriangleGridView extends GridView {

	public TriangleGridView(BorderPane root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		super(root, grid, screenWidth, screenHeight);
	}

	@Override
	protected void visualizeGrid() {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Polygon polygon = new Polygon();
				Double[] rootPoint;
				if(c%2==0){
					rootPoint = new Double[]{
						    c*pixelWidth, r*pixelWidth*Math.sqrt(3),
						    (c+2)*pixelWidth, r*pixelWidth*Math.sqrt(3),
						    (c+1)*pixelWidth, (r+1)*pixelWidth*Math.sqrt(3) };
				}
				else{
					rootPoint = new Double[]{
						    (c+1)*pixelWidth, r*pixelWidth*Math.sqrt(3),
						    c*pixelWidth, (r+1)*pixelWidth*Math.sqrt(3),
						    (c+2)*pixelWidth, (r+1)*pixelWidth*Math.sqrt(3) };
				}
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
		// equalateral triangle has height multiplier of sqrt(3)/2
		double triangleMultiplier = Math.sqrt(3) / 2;
		double tempHeight = gridSizeX / grid.getRows() / triangleMultiplier;
		double minPixels = Math.min(tempHeight, gridSizeY / grid.getColumns() / 2);
		pixelWidth = minPixels;
	}

}
