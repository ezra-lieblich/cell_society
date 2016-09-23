package life;
import cellsociety_team01.Simulation;
import cellsociety_team01.SimulationXMLFactory;
import cellsociety_team01.XMLFactoryException;
import life.LifeXML;
import org.w3c.dom.Element;

/**
 * Creates Professor object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class LifeXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "GameOfLife";


    /**
     * Create factory capable of generating LifeXML objects.
     */
    public LifeXMLFactory () {
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
        String xGridSize = getTextValue(root, "percentXGridSize");
        String yGridSize = getTextValue(root, "percentYGridSize");
        String Alive = getTextValue(root, "percentAlive");
        String Dead = getTextValue(root, "percentDead");
        return new LifeXML(simulationName, simulationTitle, simulationAuthor, xGridSize, yGridSize, Alive, Dead);
    }
}