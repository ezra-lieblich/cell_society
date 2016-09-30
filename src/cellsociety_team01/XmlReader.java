package cellsociety_team01;

import javax.xml.parsers.DocumentBuilder;
import water.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
import java.util.Objects;

public class XmlReader {
	// Width of grid array, height of grid array, type of sim, data in each cell
	// of the grid
	private BasicFiniteGrid grid;
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
	private String Sim;
	public XmlReader()	{
		
	}

	public Element getRootElement(File xmlFile) {
		System.out.println(xmlFile.getAbsolutePath());
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
     * <p>
     * Assumes you want the textValue of the first node with this tagName.
     * </p>
     * 
     * Why might it not be good design to include this and getAttribute in this class?
     * What happens when I need more transformation methods?
     */
    public String getTextValue (Element root, String tagName) {
        NodeList nodeList = root.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
        	String a = nodeList.item(0).getTextContent();
            return nodeList.item(0).getTextContent();
        }
        else {
            // BUGBUG: return empty string or null, is it an error to not find the text value?
            return "";
        }
    }
	
	// Returns name of simulation
	public String getSim() {
		Sim = getTextValue(root, "simulation_name");
		return Sim;
	}
	
	public int getXSize() {
		int xSize = Integer.parseInt(getTextValue(root, "XGridSize"));
		return xSize;
	}
	
	public int getYSize() {
		int ySize =Integer.parseInt(getTextValue(root, "YGridSize"));
		return ySize;
	}
	
	private void parseSpecific() {
		if (Sim.equals("Game Of Life")) {
			parseLife();
		}
		else if (Sim.equals("Spread of Fire")) {
			parseFire();
		}
		else if (Sim.equals("WaTor World")) {
			parseWaTor();
		}
		else if (Sim.equalsIgnoreCase("XO Segregation")) {
			parseXO();
		}
	}
	
	private void parseLife() {
		String strAlive = getTextValue(root, "percentAlive");
	}
	
	private void parseFire() {
		Double strTree = Double.parseDouble(getTextValue(root, "percentTree"));
		Double strBurn = Double.parseDouble(getTextValue(root, "percentBurn"));
		Double probCatch = Double.parseDouble(getTextValue(root, "probCatch"));
	}
	
	private void parseWaTor() {
		Double strFish = Double.parseDouble(getTextValue(root, "percentFish"));
		Double strShark = Double.parseDouble(getTextValue(root, "percentShark"));
	}
	
	private void parseXO() {
		Double strX = Double.parseDouble(getTextValue(root, "percentX"));
		Double strO = Double.parseDouble(getTextValue(root, "percentO"));
	}
	
//	public BasicFiniteGrid simChooser(String simulationName) {
//		if (simulationName.equals("Game Of Life")) {
//			return initiateLife();
//		}
//		if (simulationName.equals("Spread Of Fire")) {
//			return initiateTree();
//		}
//		if (simulationName.equals("WaTor World")) {
//			return initiateWaTor();
//		}
//		if (simulationName.equals("XO Segregation")) {
//			return initiateXO();
//		}
//		else {
//			System.out.println("not a valid simulation");
//			return initiateLife();
//		}
//	}
	
//	private BasicFiniteGrid initiateLife() {
//		LifeXMLFactory makeLife = new LifeXMLFactory();
//		BasicFiniteGrid temp = makeLife.makeGrid(root);
//		return temp;
//	}
//	
//	private BasicFiniteGrid initiateTree() {
//		String strRows = factory.getTextValue(root, "XGridSize");
//		String strColumns = factory.getTextValue(root, "YGridSize");
//		String strTree = factory.getTextValue(root, "percentTree");
//		String strBurn = factory.getTextValue(root, "percentBurn");
//		Double probCatch = Double.parseDouble(factory.getTextValue(root, "probCatch"));
//		int rows = Integer.parseInt(strRows);
//		int columns = Integer.parseInt(strColumns);
//		double tree = Double.parseDouble(strTree);
//		double burn = Double.parseDouble(strBurn);
//		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < columns; c++) {
//				double ranGen = Math.random();
//				if (ranGen<=burn) {
//					temp.setGridIndex(new tree.BurningCell(r, c), r, c);
//				}
//				else if (burn < ranGen && ranGen <= tree) {
//					temp.setGridIndex(new tree.TreeCell(r, c, probCatch), r, c);
//				}
//				else {
//					temp.setGridIndex(new tree.EmptyCell(r, c), r, c);
//				}
//			}
//		}
//		return temp;
//	}
//	
//	public int getFishReproduce(){
//		return Integer.parseInt(factory.getTextValue(root, "fishReproduce"));
//	}
//	public int getSharkDeath(){
//		return Integer.parseInt(factory.getTextValue(root, "sharkDeath"));
//	}
//	public int getSharkReproduce(){
//		return Integer.parseInt(factory.getTextValue(root, "sharkReproduce"));
//	}
//	
//	private BasicFiniteGrid initiateWaTor() {
//		String strColumns = factory.getTextValue(root, "XGridSize");
//		String strRows = factory.getTextValue(root, "YGridSize");
//		String strFish = factory.getTextValue(root, "percentFish");
//		String strShark = factory.getTextValue(root, "percentShark");
//		int rows = Integer.parseInt(strRows);
//		int columns = Integer.parseInt(strColumns);
//		double fish = Double.parseDouble(strFish);
//		double shark = Double.parseDouble(strShark);
//		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < columns; c++) {
//				double ranGen = Math.random();
//				if(ranGen<=shark)
//					temp.setGridIndex(new Shark(r, c), r, c);
//				else if(shark<ranGen&&ranGen<=fish)
//					temp.setGridIndex(new Fish(r, c), r, c);
//				else
//					temp.setGridIndex(new EmptyCell(r, c), r, c);
//			}
//		}
//		return temp;
//	}
//	
//	public double getPercentSimilar() {
//		return Double.parseDouble(factory.getTextValue(root, "similarPercentage"));
//	}
//	private BasicFiniteGrid initiateXO() {
//		String strRows = factory.getTextValue(root, "XGridSize");
//		String strColumns = factory.getTextValue(root, "YGridSize");
//		String strX = factory.getTextValue(root, "percentX");
//		String strO = factory.getTextValue(root, "percentO");
//		int rows = Integer.parseInt(strRows);
//		int columns = Integer.parseInt(strColumns);
//		double X = Double.parseDouble(strX);
//		double O = Double.parseDouble(strO);
//		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < columns; c++) {
//				double ranGen = Math.random();
//				if (ranGen < X) {
//
//					temp.setGridIndex(new Group1(r, c), r, c);
//				}
//				else if (ranGen<X+O ) {
//					temp.setGridIndex(new Group2(r, c), r, c);
//				}
//				else {
//					temp.setGridIndex(new Clear(r, c), r, c);
//				}
//			}
//		}
//		return temp;
//	}

//	private BasicFiniteGrid initiateLife() {
//		LifeXMLFactory makeLife = new LifeXMLFactory();
//		BasicFiniteGrid temp = makeLife.makeGrid(root);
//		return temp;
//	}
//
//	private BasicFiniteGrid initiateTree() {
//		String strRows = factory.getTextValue(root, "XGridSize");
//		String strColumns = factory.getTextValue(root, "YGridSize");
//		String strTree = factory.getTextValue(root, "percentTree");
//		String strBurn = factory.getTextValue(root, "percentBurn");
//		Double probCatch = Double.parseDouble(factory.getTextValue(root, "probCatch"));
//		int rows = Integer.parseInt(strRows);
//		int columns = Integer.parseInt(strColumns);
//		double tree = Double.parseDouble(strTree);
//		double burn = Double.parseDouble(strBurn);
//		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < columns; c++) {
//				double ranGen = Math.random();
//				if (ranGen <= burn) {
//					temp.setGridIndex(new tree.BurningCell(r, c), r, c);
//				} else if (burn < ranGen && ranGen <= tree) {
//					temp.setGridIndex(new tree.TreeCell(r, c, probCatch), r, c);
//				} else {
//					temp.setGridIndex(new tree.EmptyCell(r, c), r, c);
//				}
//			}
//		}
//		return temp;
//	}
//
//	public int getFishReproduce() {
//		return Integer.parseInt(factory.getTextValue(root, "fishReproduce"));
//	}
//
//	public int getSharkDeath() {
//		return Integer.parseInt(factory.getTextValue(root, "sharkDeath"));
//	}
//
//	public int getSharkReproduce() {
//		return Integer.parseInt(factory.getTextValue(root, "sharkReproduce"));
//	}
//
//	private BasicFiniteGrid initiateWaTor() {
//		String strColumns = factory.getTextValue(root, "XGridSize");
//		String strRows = factory.getTextValue(root, "YGridSize");
//		String strFish = factory.getTextValue(root, "percentFish");
//		String strShark = factory.getTextValue(root, "percentShark");
//		int rows = Integer.parseInt(strRows);
//		int columns = Integer.parseInt(strColumns);
//		double fish = Double.parseDouble(strFish);
//		double shark = Double.parseDouble(strShark);
//		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < columns; c++) {
//				double ranGen = Math.random();
//				if (ranGen <= shark)
//					temp.setGridIndex(new Shark(r, c), r, c);
//				else if (shark < ranGen && ranGen <= fish)
//					temp.setGridIndex(new Fish(r, c), r, c);
//				else
//					temp.setGridIndex(new EmptyCell(r, c), r, c);
//			}
//		}
//		return temp;
//	}
//
//	public double getPercentSimilar() {
//		return Double.parseDouble(factory.getTextValue(root, "similarPercentage"));
//	}
//
//	private BasicFiniteGrid initiateXO() {
//		String strRows = factory.getTextValue(root, "XGridSize");
//		String strColumns = factory.getTextValue(root, "YGridSize");
//		String strX = factory.getTextValue(root, "percentX");
//		String strO = factory.getTextValue(root, "percentO");
//		int rows = Integer.parseInt(strRows);
//		int columns = Integer.parseInt(strColumns);
//		double X = Double.parseDouble(strX);
//		double O = Double.parseDouble(strO);
//		BasicFiniteGrid temp = new BasicFiniteGrid(rows, columns);
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < columns; c++) {
//				double ranGen = Math.random();
//				if (ranGen < X) {
//
//					temp.setGridIndex(new Group1(r, c), r, c);
//				} else if (ranGen < X + O) {
//					temp.setGridIndex(new Group2(r, c), r, c);
//				} else {
//					temp.setGridIndex(new Clear(r, c), r, c);
//				}
//			}
//		}
//		return temp;
//	}
}
