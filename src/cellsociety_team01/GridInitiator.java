package cellsociety_team01;
import java.io.File;

import org.w3c.dom.Element;

import cellsociety_team01.BasicGrid;
import cellsociety_team01.XmlReader;
import life.LifeXMLFactory;

public class GridInitiator {
	private BasicGrid grid;
	private double xSize;
	private double ySize;
	private double stateOne;
	private double stateTwo;
	private Element root;
	
	public GridInitiator(BasicGrid grd, double xDim, double yDim, double stateA, double stateB, Element root, String xmlFilename) {
		grid = grd;
		xDim = xSize;
		yDim = ySize;
		stateA = stateOne;
		stateB = stateTwo;
		root = XmlReader.getRootElement(xmlFilename);
	}
	
	public double xSizeToDouble() {
		String stringX = root.getAttribute("percentXGridSize");
		xSize = Double.parseDouble(stringX);
		return xSize;
	}
	
	public double ySizeToDouble() {
		String stringY = root.getAttribute("percentYGridSize");
		ySize = Double.parseDouble(stringY);
		return ySize;
	}
	
	public double stateOneToDouble() {
		String stringOne = root.getAttribute("percentAlive");
		stateOne = Double.parseDouble(stringOne);
		return stateOne;
	}
	
	public double stateTwotoDouble() {
		String stringTwo = root.getAttribute("percentDead");
		stateTwo = Double.parseDouble(stringTwo);
		return stateTwo;
	}
	
	
	
	
		
	
	
}
