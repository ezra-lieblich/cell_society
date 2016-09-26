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
import life.*;
import tree.*;

import java.io.IOException;

public class XmlReader {
	// Width of grid array, height of grid array, type of sim, data in each cell of the grid
	private BasicFiniteGrid grid;
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;

	public static Element getRootElement(String xmlFilename) {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFilename);
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
	
	public void simChooser(String simulationName) {
		if (simulationName.equals("GameOfLife")) {
			initiateLife();
		}
		if (simulationName.equals("SpreadOfFire")) {
			initiateTree();
		}
		if (simulationName.equals("WaTorWorld")) {
			initiateWaTor();
		}
		if (simulationName.equals("XO")) {
			initiateXO();
		}
	}
	
	private BasicFiniteGrid initiateLife() {
		String strRows = root.getAttribute("XGridSize");
		String strColumns = root.getAttribute("YGridSize");
		String strAlive = root.getAttribute("percentAlive");
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double alive = Double.parseDouble(strAlive);
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				double ranGen = Math.random();
				if(ranGen<=alive)
					temp.setGridIndex(new AliveCell(r, c), r, c);
				else {
					temp.setGridIndex(new DeadCell(r, c), r, c);
				}
			}
		}
		return temp;
	}
	
	private BasicFiniteGrid initiateTree() {
		String strRows = root.getAttribute("XGridSize");
		String strColumns = root.getAttribute("YGridSize");
		String strTree = root.getAttribute("percentTree");
		String strBurn = root.getAttribute("percentBurn");
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double tree = Double.parseDouble(strTree);
		double burn = Double.parseDouble(strBurn);
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				double ranGen = Math.random();
				if (ranGen<=burn) {
					temp.setGridIndex(new BurningCell(r, c), r, c);
				}
				if (burn < ranGen && ranGen <= tree) {
					temp.setGridIndex(new TreeCell(r, c), r, c);
				}
				else {
					temp.setGridIndex(new tree.EmptyCell(r, c), r, c);
				}
			}
		}
		return temp;
	}
	
	private BasicFiniteGrid initiateWaTor() {
		String strRows = root.getAttribute("XGridSize");
		String strColumns = root.getAttribute("YGridSize");
		String strFish = root.getAttribute("percentFish");
		String strShark = root.getAttribute("percentShark");
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double fish = Double.parseDouble(strFish);
		double shark = Double.parseDouble(strShark);
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
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
	
	private BasicFiniteGrid initiateXO() {
		String strRows = root.getAttribute("XGridSize");
		String strColumns = root.getAttribute("YGridSize");
		String strX = root.getAttribute("percentX");
		String strO = root.getAttribute("percentO");
		int rows = Integer.parseInt(strRows);
		int columns = Integer.parseInt(strColumns);
		double X = Double.parseDouble(strX);
		double O = Double.parseDouble(strO);
		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				double ranGen = Math.random();
				if (ranGen < X) {
					temp.setGridIndex(new Clear(r, c), r, c);
				}
				if (X <= ranGen && ranGen < O ) {
					temp.setGridIndex(new Group1(r, c), r, c);
				}
				else {
					temp.setGridIndex(new Group2(r, c), r, c);
				}
			}
		}
		return temp;
	}
	
}
