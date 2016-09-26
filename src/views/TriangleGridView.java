package views;

import cellsociety_team01.GridView;
import grids.BasicFiniteGrid;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;

/**
 * TriangleGridView extends GridView and specifically creates a grid with
 * triangle cells
 * 
 * @author eric
 */
public class TriangleGridView extends GridView {
	
	private double triangleHeightMultiplier = Math.sqrt(3) / 2;
	private double triangleWidthMultiplier = 0.5;

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
						    c*pixelWidth*triangleWidthMultiplier, r*pixelWidth*triangleHeightMultiplier,
						    (c+2)*pixelWidth*triangleWidthMultiplier, r*pixelWidth*triangleHeightMultiplier,
						    (c+1)*pixelWidth*triangleWidthMultiplier, (r+1)*pixelWidth*triangleHeightMultiplier };
				}
				else{
					rootPoint = new Double[]{
						    (c+1)*pixelWidth*triangleWidthMultiplier, r*pixelWidth*triangleHeightMultiplier,
						    c*pixelWidth*triangleWidthMultiplier, (r+1)*pixelWidth*triangleHeightMultiplier,
						    (c+2)*pixelWidth*triangleWidthMultiplier, (r+1)*pixelWidth*triangleHeightMultiplier };
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
		
		double minPixels = Math.min(gridSizeX* triangleHeightMultiplier/ grid.getRows(), gridSizeY *triangleWidthMultiplier/ grid.getColumns());
		pixelWidth = minPixels;
	}

}
