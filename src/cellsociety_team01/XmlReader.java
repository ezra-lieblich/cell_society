package cellsociety_team01;

import javax.xml.parsers.DocumentBuilder;
import water.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import water.EmptyCell;
import water.Fish;
import water.Shark;
import xo.Clear;
import xo.Group1;
import xo.Group2;
import grids.BasicFiniteGrid;
import grids.HexagonalFiniteGrid;
import life.*;
import tree.*;

import java.io.File;
import java.io.IOException;

public class XmlReader {
	// Width of grid array, height of grid array, type of sim, data in each cell of the grid
	private BasicFiniteGrid grid;
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
	private XMLFactory factory = new XMLFactory();
	
	public XmlReader(){factory = new XMLFactory();}

	public static Element getRootElement(File xmlFile) {
		System.out.println(xmlFile.getAbsolutePath());
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile.getAbsolutePath());
			root = xmlDocument.getDocumentElement();
			return root;
		}
		catch (SAXException | IOException e) {
			throw new XMLParserException(e);
		}
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			throw new XMLParserException(e);
		}
	}
	
	public BasicFiniteGrid simChooser(String simulationName) {
		if (simulationName.equals("Game Of Life")) {
			return initiateLife();
		}
		if (simulationName.equals("Spread Of Fire")) {
			return initiateTree();
		}
		if (simulationName.equals("WaTor World")) {
			return initiateWaTor();
		}
		if (simulationName.equals("XO Segregation")) {
			return initiateXO();
		}
		else {
			System.out.println("not a valid simulation");
			return initiateLife();
		}
	}
	
	public String getSim() {
		String Sim = root.getAttribute("simulation_name");
		return Sim;
	}
	
	private BasicFiniteGrid initiateLife() {
		LifeXMLFactory makeLife = new LifeXMLFactory();
		BasicFiniteGrid temp = makeLife.makeGrid(root);
		return temp;
	}
	
	private BasicFiniteGrid initiateTree() {
		String strRows = factory.getTextValue(root, "XGridSize");
		String strColumns = factory.getTextValue(root, "YGridSize");
		String strTree = factory.getTextValue(root, "percentTree");
		String strBurn = factory.getTextValue(root, "percentBurn");
		Double probCatch = Double.parseDouble(factory.getTextValue(root, "probCatch"));
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double tree = Double.parseDouble(strTree);
		double burn = Double.parseDouble(strBurn);
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				double ranGen = Math.random();
				if (ranGen<=burn) {
					temp.setGridIndex(new tree.BurningCell(r, c), r, c);
				}
				else if (burn < ranGen && ranGen <= tree) {
					temp.setGridIndex(new tree.TreeCell(r, c, probCatch), r, c);
				}
				else {
					temp.setGridIndex(new tree.EmptyCell(r, c), r, c);
				}
			}
		}
		return temp;
	}
	
	public int getFishReproduce(){
		return Integer.parseInt(factory.getTextValue(root, "fishReproduce"));
	}
	public int getSharkDeath(){
		return Integer.parseInt(factory.getTextValue(root, "sharkDeath"));
	}
	public int getSharkReproduce(){
		return Integer.parseInt(factory.getTextValue(root, "sharkReproduce"));
	}
	
	private BasicFiniteGrid initiateWaTor() {
		String strColumns = factory.getTextValue(root, "XGridSize");
		String strRows = factory.getTextValue(root, "YGridSize");
		String strFish = factory.getTextValue(root, "percentFish");
		String strShark = factory.getTextValue(root, "percentShark");
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double fish = Double.parseDouble(strFish);
		double shark = Double.parseDouble(strShark);
		BasicFiniteGrid temp = new HexagonalFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				double ranGen = Math.random();
				if(ranGen<=shark)
					temp.setGridIndex(new Shark(r, c), r, c);
				else if(shark<ranGen&&ranGen<=fish)
					temp.setGridIndex(new Fish(r, c), r, c);
				else
					temp.setGridIndex(new EmptyCell(r, c), r, c);
			}
		}
		return temp;
	}
	
	public double getPercentSimilar() {
		return Double.parseDouble(factory.getTextValue(root, "similarPercentage"));
	}
	private BasicFiniteGrid initiateXO() {
		String strRows = factory.getTextValue(root, "XGridSize");
		String strColumns = factory.getTextValue(root, "YGridSize");
		String strX = factory.getTextValue(root, "percentX");
		String strO = factory.getTextValue(root, "percentO");
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double X = Double.parseDouble(strX);
		double O = Double.parseDouble(strO);
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				double ranGen = Math.random();
				if (ranGen < X) {

					temp.setGridIndex(new Group1(r, c), r, c);
				}
				else if (ranGen<X+O ) {
					temp.setGridIndex(new Group2(r, c), r, c);
				}
				else {
					temp.setGridIndex(new Clear(r, c), r, c);
				}
			}
		}
		return temp;
	}
	
}
