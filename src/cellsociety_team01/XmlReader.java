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
import grids.BasicFiniteGrid;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XmlReader {
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
	private String sim;
	private int rows;
	private int columns;
	private String shape;
	private String bounds;
	private GridFactory factory;

	public XmlReader() {
	}

	/**
	 * Throws XMLParserException if file is not XML File.
	 * 
	 * @param xmlFile
	 * @return
	 */
	public void getRootElement(File xmlFile) {
		if (xmlFile.isFile() && !(xmlFile.getName().endsWith(".xml"))) {
			throw new XMLParserException("" + xmlFile.getName() + " is not an xml file");
		}
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile.getAbsolutePath());
			root = xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLParserException(e);
		}

	}
	
	public GridFactory getGridFactory(){
		return factory;
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLParserException(e);
		}
	}

	/**
	 * Get the text value of a node. Assumes you want the textValue of the first
	 * node with this tagName. Throws XMLParserException if XML file is empty.
	 */
	public String getTextValue(Element root, String tagName) {
		NodeList nodeList = root.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			String a = nodeList.item(0).getTextContent();
			return a;
		} else {
			throw new XMLParserException("XML file has no data for parameter " + tagName);
		}
	}

	// // Returns name of simulation
	// public String getSim() {
	// return sim;
	// }

//	public int rows() {
//		
//		return rows;
//	}
//
//	public int getYSize() {
//		
//		return columns;
//	}

	// public String getShape() {
	// return shape;
	// }
	//
	// public String getBounds() {
	// return bounds;
	// }
	public String getSim() {
		return getTextValue(root, "simulation_name");
	}

	public BasicFiniteGrid makeGrid() {
		sim = getTextValue(root, "simulation_name");
		shape = getTextValue(root, "shape");
		bounds = getTextValue(root, "bounds");
		rows = Integer.parseInt(getTextValue(root, "rows"));
		columns = Integer.parseInt(getTextValue(root, "columns"));
		if (sim.equals("Game Of Life")) {
			return makeLife();
		} else if (sim.equals("Spread Of Fire")) {
			return makeFire();
		} else if (sim.equals("WaTor World")) {
			return makeWaTor();
		} else if (sim.equalsIgnoreCase("XO Segregation")) {
			return makeXO();
		}
		// should never go here
		else {
			return null;
		}
	}

	// Detects incorrectly formatted data.
	private void doubleParseErrors(String input) throws XMLParserException {

		try {
			Double.parseDouble(input);
		} catch (RuntimeException e) {
			throw new XMLParserException("" + input + " in XML file needs to be a double.", e);
		}
	}

	private void intParseErrors(String input) throws XMLParserException {
		try {
			Integer.parseInt(input);
		} catch (RuntimeException e) {
			throw new XMLParserException("" + input + " in XML file needs to be an integer.", e);
		}
	}

	private BasicFiniteGrid makeLife() {
		factory = new LifeGridFactory(shape, bounds, rows, columns);
		String strAlive = getTextValue(root, "percentAlive");
		doubleParseErrors(strAlive);
		Double Alive = Double.parseDouble(strAlive);
		return ((LifeGridFactory) factory).makeGrid(Alive);
	}

	private BasicFiniteGrid makeFire() {
		factory = new TreeGridFactory(shape, bounds, rows, columns);
		String strTree = getTextValue(root, "percentTree");
		doubleParseErrors(strTree);

		String strBurn = getTextValue(root, "percentBurn");
		doubleParseErrors(strBurn);

		String strProb = getTextValue(root, "probCatch");
		doubleParseErrors(strProb);

		Double Tree = Double.parseDouble(strTree);
		Double Burn = Double.parseDouble(strBurn);
		Double probCatch = Double.parseDouble(strProb);
		return ((TreeGridFactory) factory).makeGrid(Tree, Burn, probCatch);
	}

	public int getFishReproduce() {
		return Integer.parseInt(getTextValue(root, "fishReproduce"));
	}

	public int getSharkDeath() {
		return Integer.parseInt(getTextValue(root, "sharkDeath"));
	}

	public int getSharkReproduce() {
		return Integer.parseInt(getTextValue(root, "sharkReproduce"));
	}

	private BasicFiniteGrid makeWaTor() {
		factory = new WaterGridFactory(shape, bounds, rows, columns);
		String strFish = getTextValue(root, "percentFish");
		doubleParseErrors(strFish);

		String strShark = getTextValue(root, "percentShark");
		doubleParseErrors(strShark);

		String strFishRep = getTextValue(root, "fishReproduce");
		intParseErrors(strFishRep);

		String strSharkDea = getTextValue(root, "sharkDeath");
		intParseErrors(strSharkDea);

		String strSharkRep = getTextValue(root, "sharkReproduce");
		intParseErrors(strSharkRep);

		Double Fish = Double.parseDouble(strFish);
		Double Shark = Double.parseDouble(strShark);
		int fishRep = Integer.parseInt(strFishRep);
		int sharkDea = Integer.parseInt(strSharkDea);
		int sharkRep = Integer.parseInt(strSharkRep);
		return ((WaterGridFactory) factory).makeGrid(Fish, Shark, fishRep, sharkDea, sharkRep);
	}

	public double getPercentSimilar() {
		return Double.parseDouble(getTextValue(root, "similarPercentage"));
	}

	private BasicFiniteGrid makeXO() {
		factory = new XOGridFactory(shape, bounds, rows, columns);
		String strPerX = getTextValue(root, "percentX");
		doubleParseErrors(strPerX);

		String strPerO = getTextValue(root, "percentO");
		doubleParseErrors(strPerO);

		String strPerSim = getTextValue(root, "similarPercentage");
		doubleParseErrors(strPerSim);

		Double perX = Double.parseDouble(getTextValue(root, "percentX"));
		Double perO = Double.parseDouble(getTextValue(root, "percentO"));
		Double perSim = Double.parseDouble(getTextValue(root, "similarPercentage"));
		return ((XOGridFactory) factory).makeGrid(perX, perO, perSim);
	}
}
