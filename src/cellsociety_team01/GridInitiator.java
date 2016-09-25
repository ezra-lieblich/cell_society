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
	private double stateThree;
	private double stateFour;
	private double stateFive;
	private Element root;
	private String ans;
	
	public GridInitiator(BasicGrid grd, double xDim, double yDim, double stateA, double stateB, double stateC, double stateD, double stateE, Element root, String xmlFilename) {
		grid = grd;
		xDim = xSize;
		yDim = ySize;
		stateA = stateOne;
		stateB = stateTwo;
		stateC = stateThree;
		stateD = stateFour;
		stateE = stateFive;
		root = XmlReader.getRootElement(xmlFilename);
	}
	
	public String getAttTag(String tag) {
		ans = root.getAttribute(tag);
		return ans;
	}
	
	public double getXSize() {
		String stringX = root.getAttribute(ans);
		xSize = Double.parseDouble(stringX);
		return xSize;
	}
	
	public double getYSize() {
		String stringY = root.getAttribute(ans);
		ySize = Double.parseDouble(stringY);
		return ySize;
	}
	
	public double getStateOne() {
		String stringOne = root.getAttribute(ans);
		stateOne = Double.parseDouble(stringOne);
		return stateOne;
	}
	
	public double getStateTwo() {
		String stringTwo = root.getAttribute(ans);
		stateTwo = Double.parseDouble(stringTwo);
		return stateTwo;
	}
	
	public double getStateThree() {
		String stringThree = root.getAttribute(ans);
		stateThree = Double.parseDouble(stringThree);
		return stateThree;
	}
	
	public double getStateFour() {
		String stringFour = root.getAttribute(ans);
		stateFour = Double.parseDouble(stringFour);
		return stateFour;
	}
	
	public double getStateFive() {
		String stringFive = root.getAttribute(ans);
		stateFive = Double.parseDouble(stringFive);
		return stateFive;
	}
}
