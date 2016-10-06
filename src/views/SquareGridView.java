package views;

import cellsociety_team01.GridView;
import grids.BasicFiniteGrid;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * GridView is called on by gridController and updates the view. It determines
 * the size of each pixel as well for the view. It updates the view based on
 * what is in each cell value in grid
 * 
 * @author ezra
 * @author ericsong
 */
public class SquareGridView extends GridView{

	/**
	 * Sets the grid and root and also determines the size of the pixels
	 * 
	 * @param root
	 *            Root is so we can add the Grid Cells to the view and scene
	 * @param grid
	 *            We need the grid so GridView can read it and update the View.
	 */
	public SquareGridView(VBox root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		super(root,grid,screenWidth,screenHeight);
	}

	/**
	 * Loops through each Cell in grid and adds it to the root to change the
	 * view
	 */
	@Override
	protected void visualizeGrid() {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Rectangle temp = new Rectangle(c * pixelWidth, r * pixelWidth, pixelWidth, pixelWidth);
				temp.setFill(grid.getGridIndex(r, c).getColor());
				temp.setStroke(Color.BLACK);
				gridView.getChildren().add(temp);
			}
		}
	}

	/**
	 * Calculates the pixel size of each cell based on the size of the grid
	 */
	@Override
	protected void setupPixelSize() {
		double minPixels = Math.min(gridWidth / grid.getColumns(), gridHeight / grid.getRows());
		pixelWidth = minPixels;
	}
}
