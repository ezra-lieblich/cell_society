package cellsociety_team01;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

public class XmlReader {
	// Width of grid array, height of grid array, type of sim, data in each cell of the grid
	private BasicGrid grid;
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
	
	private String getSimName() {
		String simName = root.getAttribute("simulation_name");
		return simName;
	}
	
	private void simChooser() {
		if (XmlReader.getSimName().equals("GameOfLife")) {
			
		}
	}
	
	
}
