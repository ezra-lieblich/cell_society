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

import java.io.IOException;

public class XmlReader {
	// Width of grid array, height of grid array, type of sim, data in each cell of the grid
	private BasicGrid grid;
	// Reset DOCUMENT_BUILDER before every parse
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static Element root;
	private int rows;
	private int columns;

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
	
	private String getSimName() {
		String simName = root.getAttribute("simulation_name");
		return simName;
	}
	
	private void simChooser() {
		if (getSimName().equals("GameOfLife")) {
			initiateLife();
		}
		if (getSimName().equals("SpreadOfFire")) {
			initiateTree();
		}
		if (getSimName().equals("WaTorWorld")) {
			initiateWaTor();
		}
		if (getSimName().equals("XO")) {
			initiateXO();
		}
	}
	
	private BasicFiniteGrid initiateLife() {
		
	}
	
	private BasicFiniteGrid initiateTree() {
		
	}
	
	private BasicFiniteGrid initiateWaTor() {
		BasicFiniteGrid temp = new ToroidalGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 10);
				if(ranGen<=1)
					temp.setGridIndex(new Shark(r, c), r, c);
				else if(1<ranGen&&ranGen<=8)
					temp.setGridIndex(new Fish(r, c), r, c);
				else
					temp.setGridIndex(new EmptyCell(r, c), r, c);
			}
		}
		return temp;
	}
	
	private BasicFiniteGrid initiateXO() {
		
	}
	
}
