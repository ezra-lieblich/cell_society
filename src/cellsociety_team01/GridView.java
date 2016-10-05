package cellsociety_team01;

import javafx.geometry.Pos;
import grids.BasicFiniteGrid;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * GridView is called on by gridController and updates the view. It determines
 * the size of each pixel as well for the view. It updates the view based on
 * what is in each cell value in grid
 * 
 * @author ezra
 *
 */
public abstract class GridView {

	protected VBox root;
	protected BasicFiniteGrid grid;

	protected double gridWidth, gridHeight;
	protected Group gridView;
	protected double pixelWidth;

	/**
	 * Sets the grid and root and also determines the size of the pixels
	 * 
	 * @param root
	 *            Root is so we can add the Grid Cells to the view and scene
	 * @param grid
	 *            We need the grid so GridView can read it and update the View.
	 */
	public GridView(VBox root, BasicFiniteGrid grid, int screenWidth, int screenHeight) {
		gridView = new Group();
		gridWidth = 400;
		gridHeight = 400;
		this.root = root;
		this.grid = grid;
		setupPixelSize();
		this.root.getChildren().add(gridView);
	}

	/**
	 * Loops through each Cell in grid and adds it to the root to change the
	 * view
	 */
	public void step() {
		gridView.getChildren().clear();
		BorderPane.setAlignment(gridView, Pos.CENTER_LEFT);
		visualizeGrid();
	}

	protected void visualizeGrid() {
		
	}

	/**
	 * Calculates the pixel size of each cell based on the size of the grid
	 */
	protected void setupPixelSize() {
	}
}
