//This entire file is part of my masterpiece
//Ezra Lieblich
package factories;

import cellsociety_team01.GridView;
import views.HexagonalGridView;
import views.SquareGridView;
import views.TriangleGridView;

/**
 * Factory to determine what type of View to create
 * @author ezra
 *
 */
public class ViewFactory {
	public ViewFactory() {
		
	}
	
	/**
	 * Returns a view object for the controller
	 * @param shape
	 * @param width
	 * @param height
	 * @return
	 */
	public GridView setupView(String shape) {
		if (shape.equals("squ")) {
			return new SquareGridView();
		}
		else if (shape.equals("hex")){
			return new HexagonalGridView();
		}
		else if (shape.equals("tri")) {
			return new TriangleGridView();
		}
		//should never go here
		else {
			return null;
	
		}
	}
}
