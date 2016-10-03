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
import java.util.HashMap;
import java.util.Objects;

public class XmlReader {
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
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

	public String getSim() {
		return getTextValue(root, "simulation_name");
	}

	public BasicFiniteGrid makeGrid() {

		String sim = getTextValue(root, "simulation_name");
		String shape = getTextValue(root, "shape");
		String bounds = getTextValue(root, "bounds");
		String rows = getTextValue(root, "rows");
		String columns = getTextValue(root, "columns");
		
		if (sim.equals("Game Of Life")) {
			return makeLife(shape,bounds,rows,columns);
		} else if (sim.equals("Spread Of Fire")) {
			return makeFire(shape,bounds,rows,columns);
		} else if (sim.equals("WaTor World")) {
			return makeWaTor(shape,bounds,rows,columns);
		} else if (sim.equalsIgnoreCase("XO Segregation")) {
			return makeXO(shape,bounds,rows,columns);
		}
		// should never go here
		else {
			return null;
		}
	}



	private BasicFiniteGrid makeLife(String shape, String bounds, String rows, String columns){
		factory = new LifeGridFactory(shape, bounds, rows, columns);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("percentAlive", getTextValue(root, "percentAlive"));
		return ((LifeGridFactory) factory).makeGrid(map);
	}

	private BasicFiniteGrid makeFire(String shape, String bounds, String rows, String columns) {
		factory = new TreeGridFactory(shape, bounds, rows, columns);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("percentTree", getTextValue(root, "percentTree"));
		map.put("percentBurn", getTextValue(root, "percentBurn"));
		map.put("probCatch", getTextValue(root, "probCatch"));
		return ((TreeGridFactory) factory).makeGrid(map);
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

	private BasicFiniteGrid makeWaTor(String shape, String bounds, String rows, String columns) {
		factory = new WaterGridFactory(shape, bounds, rows, columns);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("percentFish", getTextValue(root, "percentFish"));
		map.put("percentShark", getTextValue(root, "percentShark"));
		map.put("fishReproduce", getTextValue(root, "fishReproduce"));
		map.put("sharkDeath", getTextValue(root, "sharkDeath"));
		map.put("sharkReproduce", getTextValue(root, "sharkReproduce"));
		return ((WaterGridFactory) factory).makeGrid(map);
	}

	public double getPercentSimilar(String shape, String bounds, String rows, String columns) {
		return Double.parseDouble(getTextValue(root, "similarPercentage"));
	}

	private BasicFiniteGrid makeXO(String shape, String bounds, int rows, int columns) {
		factory = new XOGridFactory(shape, bounds, rows, columns);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("percentX", getTextValue(root, "percentX"));
		map.put("percentO", getTextValue(root, "percentO"));
		map.put("similarPercentage", getTextValue(root, "similarPercentage"));
		return ((XOGridFactory) factory).makeGrid(map);
	}
}
