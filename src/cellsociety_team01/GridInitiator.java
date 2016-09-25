package cellsociety_team01;
import java.io.File;

import org.w3c.dom.Element;

import cellsociety_team01.BasicGrid;
import cellsociety_team01.XmlReader;
import life.LifeXMLFactory;

public class GridInitiator {
	private BasicGrid grid;
	private static int xSize;
	private static int ySize;
	private double stateOne;
	private double stateTwo;
	private double stateThree;
	private double stateFour;
	private double stateFive;
	private static Element root;
	private static String ans;
	
	public GridInitiator(BasicGrid grd, int xDim, int yDim, double stateA, double stateB, double stateC, double stateD, double stateE, Element root, String xmlFilename) {
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
	
	public static int getXSize() {
		String stringX = root.getAttribute(ans);
		xSize = Integer.parseInt(stringX);
		return xSize;
	}
	
	public static int getYSize() {
		String stringY = root.getAttribute(ans);
		ySize = Integer.parseInt(stringY);
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
