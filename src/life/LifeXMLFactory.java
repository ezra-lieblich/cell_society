package life;
import cellsociety_team01.Simulation;
import cellsociety_team01.SimulationXMLFactory;
import cellsociety_team01.XMLFactory;
import cellsociety_team01.XMLFactoryException;
import grids.BasicFiniteGrid;
import life.LifeXML;
import org.w3c.dom.Element;

/**
 * Creates LifeGrid object from an XML file.
 *
 * @author Christopher Lu
 */
public class LifeXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "GameOfLife";
	private XMLFactory factory = new XMLFactory();

    /**
     * Create factory capable of generating LifeXML objects.
     */
    public LifeXMLFactory () {
        super(XML_TAG_NAME);
    }
    
    public BasicFiniteGrid makeGrid (Element root) {
    	String simulationName = getTextValue(root, "simulation_name");
        String simulationTitle = getTextValue(root, "simulation_title");
        String simulationAuthor = getTextValue(root, "simulation_author");
    	String strRows = factory.getTextValue(root, "XGridSize");
		String strColumns = factory.getTextValue(root, "YGridSize");
		String strAlive = factory.getTextValue(root, "percentAlive");
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
    /**
     * @see SimulationXMLFactory#getSimulation()
     */
//    @Override
//    public Simulation getSimulation (Element root) throws XMLFactoryException {
//        if (! isValidFile(root)) {
//            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
//        }
//        String simulationName = getTextValue(root, "simulation_name");
//        String simulationTitle = getTextValue(root, "simulation_title");
//        String simulationAuthor = getTextValue(root, "simulation_author");
//        String xGridSize = getTextValue(root, "XGridSize");
//        String yGridSize = getTextValue(root, "YGridSize");
//        String Alive = getTextValue(root, "percentAlive");
//        String Dead = getTextValue(root, "percentDead");
//        return new LifeXML(simulationName, simulationTitle, simulationAuthor, xGridSize, yGridSize, Alive, Dead);
//    }
}