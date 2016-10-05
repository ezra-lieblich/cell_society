package cellsociety_team01;

import javax.xml.parsers.DocumentBuilder;
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
import java.util.ResourceBundle;

/**
 * 
 * @author Christopher Lu
 * @author Eric Song
 *
 * This class is responsible for reading XML file data and using the simulation name value to pass in all parameters needed to make 
 * the grid for the  correct simulation into a map. This class employs error checking written in separate classes to check for common errors
 * such as incorrect file format, missing file data, or incorrectly formatted data.
 * This class depends on BasicFiniteGrid as it should return a certain grid based on the data in the XML File.
 * This class also depends on factories as the xml data must first be passed into a grid factory before the grid factory can actually make the BasicFiniteGrid.
 * makeLifeSpecific does not work, as it is simply an attempt at a Game Of Life Simulation that allows its users to specify 
 * initial conditions via XML File manipulation.
 *
 */

public class XmlReader {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
	private GridFactory factory;
	private ResourceBundle myDefaultValResources;

	public XmlReader() {
		myDefaultValResources = ResourceBundle.getBundle(GridFactory.DEFAULT_RESOURCE_PACKAGE + "DefaultValues");
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

	/**
	 * GridFactory and its subclasses are the ones that actually build the grid based on the XML file data passed into it.
	 * The XML File passes in shape, bounds, rows, columns, and neighbors to Grid Factory, and the corresponding subclasses
	 * for grid factory fill in the rest of the simmulation speciic data.
	 * @return
	 */
	
	public GridFactory getGridFactory() {
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
			AlertBox.displayError(String.format("Missing parameter %s. The default value for parameter %s will be %s.",
					tagName, tagName, myDefaultValResources.getString(tagName)));
			return myDefaultValResources.getString(tagName);
		}
	}

	/**
	 * This method is needed later on in the logic tree to determine the correct simulation grid to initialize.
	 * @return
	 */
	public String getSim() {
		return getTextValue(root, "SimType");
	}

	/**
	 * Makes a grid based on what XMLReader reads from the simulation_name tag
	 * in XML file.
	 * 
	 * @return
	 */
	public BasicFiniteGrid makeGrid() {

		String sim = getTextValue(root, "simulation_name");
		String shape = getTextValue(root, "shape");
		if (!shape.equals("squ") && !shape.equals("tri") && !shape.equals("hex")) {
			shape = "squ";
			AlertBox.displayError("Invalid simulation shape, default is set to square.");
		}
		String bounds = getTextValue(root, "bounds");
		if (!bounds.equals("finite") && !bounds.equals("toroidal")) {
			bounds = "finite";
			AlertBox.displayError("Invalid bounds type, default is set to finite.");
		}
		String rows = getTextValue(root, "rows");
		String columns = getTextValue(root, "columns");
		String neighbors = getTextValue(root, "neighbors");

		if (sim.equals("Game Of Life")) {
			return makeLife(shape, bounds, rows, columns, neighbors);
		} else if (sim.equals("Spread Of Fire")) {
			return makeFire(shape, bounds, rows, columns, neighbors);
		} else if (sim.equals("WaTor World")) {
			return makeWaTor(shape, bounds, rows, columns, neighbors);
		} else if (sim.equals("XO Segregation")) {
			return makeXO(shape,bounds,rows,columns, neighbors);
		} else if (sim.equals("Game Of Life Specific")) {
			return makeLifeSpecific(shape, bounds, rows, columns, neighbors);
		}
		else {
			return null;
		}
	}

	/**
	 * These rest of the methods pass in XML file data into a map that the factories use to construct the grid.
	 * @param shape: Hexagonal, Square, Triangular (shape of each cell).
	 * @param bounds: Finite, toroidal (bound behavior of the grid).
	 * @param rows
	 * @param columns
	 * @param neighbors— Cardinal, Diagonal, All (Which surrounding cells should be considered neighbors).
	 * @return
	 */
	
	private BasicFiniteGrid makeLife(String shape, String bounds, String rows, String columns, String neighbors) {
		factory = new LifeGridFactory(shape, bounds, rows, columns, neighbors);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("percentAlive", getTextValue(root, "percentAlive"));
		return ((LifeGridFactory) factory).makeGrid(map);
	}

	private BasicFiniteGrid makeFire(String shape, String bounds, String rows, String columns, String neighbors) {
		factory = new TreeGridFactory(shape, bounds, rows, columns, neighbors);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("percentTree", getTextValue(root, "percentTree"));
		map.put("percentBurn", getTextValue(root, "percentBurn"));
		map.put("probCatch", getTextValue(root, "probCatch"));
		return ((TreeGridFactory) factory).makeGrid(map);
	}

	private BasicFiniteGrid makeWaTor(String shape, String bounds, String rows, String columns, String neighbors) {
		factory = new WaterGridFactory(shape, bounds, rows, columns, neighbors);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("percentFish", getTextValue(root, "percentFish"));
		map.put("percentShark", getTextValue(root, "percentShark"));
		map.put("fishReproduce", getTextValue(root, "fishReproduce"));
		map.put("sharkDeath", getTextValue(root, "sharkDeath"));
		map.put("sharkReproduce", getTextValue(root, "sharkReproduce"));
		return ((WaterGridFactory) factory).makeGrid(map);
	}

	private BasicFiniteGrid makeXO(String shape, String bounds, String rows, String columns, String neighbors) {
		factory = new XOGridFactory(shape, bounds, rows, columns, neighbors);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("percentX", getTextValue(root, "percentX"));
		map.put("percentO", getTextValue(root, "percentO"));
		map.put("similarPercentage", getTextValue(root, "similarPercentage"));
		return ((XOGridFactory) factory).makeGrid(map);
	}

	private BasicFiniteGrid makeLifeSpecific(String shape, String bounds, String rows, String columns,
			String neighbors) {
		factory = new CustomLifeGridFactory(shape, bounds, rows, columns, neighbors);
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 1; i < 13; i++) {
			map.put("xValue" + i, getTextValue(root, "xValue" + Integer.toString(i)));
			map.put("yValue" + i, getTextValue(root, "yValue" + Integer.toString(i)));
			System.out.println(i);
		}
		map.put("percentAlive", getTextValue(root, "percentAlive"));
		System.out.println(map);
		return ((CustomLifeGridFactory) factory).makeGrid(map);
	}
}
