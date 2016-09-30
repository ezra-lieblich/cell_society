package cellsociety_team01;

import javax.xml.parsers.DocumentBuilder;
import water.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import factories.*;
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
import java.util.Objects;

public class XmlReader {
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
	private String Sim;
	private int rows;
	private int columns;
	private String shape;
	private String bounds;
	public XmlReader()	{
		
	}

	/**
	 * Throws XMLParserException if file is not XML File.
	 * @param xmlFile
	 * @return
	 */
	public Element getRootElement(File xmlFile) {
		if (xmlFile.isFile() && !(xmlFile.getName().endsWith(".xml"))) {
			throw new XMLParserException("" + xmlFile.getName() + " is not an xml file");
		}
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile.getAbsolutePath());
			root = xmlDocument.getDocumentElement();
			return root;
		} catch (SAXException | IOException e) {
			throw new XMLParserException(e);
		}
		
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLParserException(e);
		}
	}
	
	 /**
     * Get the text value of a node.
     * Assumes you want the textValue of the first node with this tagName.
     * Throws XMLParserException if XML file is empty.
     */
    public String getTextValue (Element root, String tagName) {
        NodeList nodeList = root.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
        	String a = nodeList.item(0).getTextContent();
            return a;
        }
        else {
            throw new XMLParserException("XML file has no data for parameter " + tagName);
        }
    }
	
	// Returns name of simulation
	public String getSim() {
		Sim = getTextValue(root, "simulation_name");
		return Sim;
	}
	
	public int rows() {
		rows = Integer.parseInt(getTextValue(root, "rows"));
		return rows;
	}
	
	public int getYSize() {
		columns = Integer.parseInt(getTextValue(root, "columns"));
		return columns;
	}
	
	public String getShape() {
		shape = getTextValue(root, "shape");
		return shape;
	}
	
	public String getBounds() {
		bounds = getTextValue(root, "bounds");
		return bounds;
	}
	
	public void makeFactories() {
		if (Sim.equals("Game Of Life")) {
			makeLife();
		}
		else if (Sim.equals("Spread of Fire")) {
			makeFire();
		}
		else if (Sim.equals("WaTor World")) {
			makeWaTor();
		}
		else if (Sim.equalsIgnoreCase("XO Segregation")) {
			makeXO();
		}
	}
	
	// Detects incorrectly formatted data.
	private void intDoubleParseErrors(String input) throws XMLParserException {
		try {
			Integer.parseInt(input);
		} catch (RuntimeException e) {
			throw new XMLParserException(""  + input + " in XML file needs to be a numerical value." , e);	
		}
		try {
			Double.parseDouble(input);
		} catch (RuntimeException e) {
			throw new XMLParserException(""  + input + " in XML file needs to be a numerical value." , e);	
		}
	}
	
	private void makeLife() {
		LifeGridFactory life= new LifeGridFactory(shape, bounds, rows, columns);
		String strAlive = getTextValue(root, "percentAlive");
		intDoubleParseErrors(strAlive);
		Double Alive = Double.parseDouble(strAlive);
		life.makeGrid(Alive);
	}
	
	private void makeFire() {
		TreeGridFactory tree = new TreeGridFactory(shape, bounds, rows, columns);
		String strTree = getTextValue(root, "percentTree");
		intDoubleParseErrors(strTree);
		
		String strBurn = getTextValue(root, "percentBurn");		
		intDoubleParseErrors(strBurn);
		
		String strProb = getTextValue(root, "probCatch");
		intDoubleParseErrors(strProb);
		
		Double Tree = Double.parseDouble(strTree);
		Double Burn = Double.parseDouble(strBurn);
		Double probCatch = Double.parseDouble(strProb);
		tree.makeGrid(Tree, Burn, probCatch);
	}
	
	private void makeWaTor() {
		WaterGridFactory wator = new WaterGridFactory(shape, bounds, rows, columns);
		String strFish = getTextValue(root, "percentFish");
		intDoubleParseErrors(strFish);
		
		String strShark = getTextValue(root, "percentShark");
		intDoubleParseErrors(strShark);
		
		String strFishRep = getTextValue(root, "fishReproduce");
		intDoubleParseErrors(strFishRep);
		
		String strSharkDea = getTextValue(root, "sharkDeath");
		intDoubleParseErrors(strSharkDea);
		
		String strSharkRep = getTextValue(root, "sharkReproduce");
		intDoubleParseErrors(strSharkRep);
		
		Double Fish = Double.parseDouble(strFish);
		Double Shark = Double.parseDouble(strShark);
		int fishRep = Integer.parseInt(strFishRep);
		int sharkDea = Integer.parseInt(strSharkDea);
		int sharkRep = Integer.parseInt(strSharkRep);
		wator.makeGrid(Fish, Shark, fishRep, sharkDea, sharkRep);
	}
	
	private void makeXO() {
		XOGridFactory xo = new XOGridFactory(shape, bounds, rows, columns);
		String strPerX = getTextValue(root, "percentX");
		intDoubleParseErrors(strPerX);
		
		String strPerO = getTextValue(root, "percentO");
		intDoubleParseErrors(strPerO);
		
		String strPerSim = getTextValue(root, "similarPercentage");
		intDoubleParseErrors(strPerSim);
		
		Double perX = Double.parseDouble(getTextValue(root, "percentX"));
		Double perO = Double.parseDouble(getTextValue(root, "percentO"));
		Double perSim = Double.parseDouble(getTextValue(root, "similarPercentage"));
		xo.makeGrid(perX, perO, perSim);
	}
}
