package cellsociety_team01;

import javafx.geometry.Pos;
import grids.BasicFiniteGrid;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

/**
 * GridView is called on by gridController and updates the view. It determines
 * the size of each pixel as well for the view. It updates the view based on
 * what is in each cell value in grid
 * 
 * @author ezra
 *
 */
public class GridView {

	private BorderPane root;
	private BasicFiniteGrid grid;

	private int gridSizeX, gridSizeY;
	private Group gridView;
	private int pixelWidth;
	private int pixelHeight;

	/**
	 * Sets the grid and root and also determines the size of the pixels
	 * 
	 * @param root
	 *            Root is so we can add the Grid Cells to the view and scene
	 * @param grid
	 *            We need the grid so GridView can read it and update the View.
	 */
	public GridView(BorderPane root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		gridSizeX = screenWidth * 9 / 10;
		gridSizeY = screenHeight * 8 / 10;
		this.root = root;
		this.grid = grid;
		setupPixelSize();
	}

	/**
	 * Loops through each Cell in grid and adds it to the root to change the
	 * view
	 */
	public void step() {
		gridView = new Group();
		BorderPane.setAlignment(gridView, Pos.CENTER_LEFT);
		root.setCenter(gridView);
		createSquareGrid();
	}

	public void createSquareGrid() {
		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getColumns(); c++) {
				Rectangle temp = new Rectangle(r * pixelWidth, c * pixelHeight, pixelWidth, pixelHeight);
				temp.setFill(grid.getGridIndex(r, c).getColor());
				gridView.getChildren().add(temp);
			}
		}
	}

	/**
	 * Calculates the pixel size of each cell based on the size of the grid
	 */
	private void setupPixelSize() {
		int minPixels = Math.min(gridSizeX / grid.getColumns(), gridSizeY / grid.getRows());
		pixelWidth = minPixels;
		pixelHeight = minPixels;
	}
}
