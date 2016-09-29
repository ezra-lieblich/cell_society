package tree;
import cellsociety_team01.Simulation;
import cellsociety_team01.SimulationXMLFactory;
import cellsociety_team01.XMLFactory;
import cellsociety_team01.XMLFactoryException;
import grids.BasicFiniteGrid;
import tree.TreeXML;
import org.w3c.dom.Element;

/**
 * Creates TreeXML object from an XML file.
 *
 * @author Christopher Lu
 */
public class TreeXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "GameOfLife";
    private XMLFactory factory = new XMLFactory();

    /**
     * Create factory capable of generating TreeXML objects.
     */
    public TreeXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * @see SimulationXMLFactory#getSimulation()
     */
    @Override
    public BasicFiniteGrid makeGrid (Element root) {
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
}