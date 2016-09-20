package cellsociety_team01;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class GridView {
	
	private Group root;
	private Grid grid;
	
	private int pixelWidth =60; 

	public GridView(Group root, Grid grid) {
		this.root = root;
		this.grid = grid;
	}

	public void step(){
		for(int r= 0 ;r<grid.getRows();r++){
			for(int c = 0;c<grid.getColumns(); c++){
				Rectangle temp = new Rectangle(r*pixelWidth,c*pixelWidth,pixelWidth,pixelWidth);
				temp.setFill(grid.getGridIndex(r, c).getColor());
				root.getChildren().add(temp);
			}
		}
	}
}
