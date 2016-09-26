package water;

import cellsociety_team01.Simulation;
import cellsociety_team01.SimulationXMLFactory;
import cellsociety_team01.XMLFactoryException;
import life.LifeXML;
import org.w3c.dom.Element;

public class WaterXMLFactory extends SimulationXMLFactory {

	private static final String XML_TAG_NAME = "WaTorWorld";
	
	/**
     * Create factory capable of generating LifeXML objects.
     */
    public WaterXMLFactory () {
        super(XML_TAG_NAME);
    }

    /**
     * @see SimulationXMLFactory#getSimulation()
     */
    @Override
    public Simulation getSimulation (Element root) throws XMLFactoryException {
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        // BUGBUG: hard coding tagNames is a bad idea
        String simulationName = getTextValue(root, "simulation_name");
        String simulationTitle = getTextValue(root, "simulation_title");
        String simulationAuthor = getTextValue(root, "simulation_author");
        String xGridSize = getTextValue(root, "XGridSize");
        String yGridSize = getTextValue(root, "YGridSize");
        String Fish = getTextValue(root, "percentFish");
        String Shark = getTextValue(root, "percentShark");
        return new LifeXML(simulationName, simulationTitle, simulationAuthor, xGridSize, yGridSize, Fish, Shark);
    }
	
}
