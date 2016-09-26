package tree;
import cellsociety_team01.Simulation;
import cellsociety_team01.SimulationXMLFactory;
import cellsociety_team01.XMLFactoryException;
import tree.TreeXML;
import org.w3c.dom.Element;

/**
 * Creates TreeXML object from an XML file.
 *
 * @author Christopher Lu
 */
public class TreeXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "GameOfLife";


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
        String Tree = getTextValue(root, "percentTree");
        String Burn = getTextValue(root, "percentBurn");
        String Empty = getTextValue(root, "percentEmpty");
        String probCatch = getTextValue(root, "probCatch");
        return new TreeXML(simulationName, simulationTitle, simulationAuthor, xGridSize, yGridSize, Tree, Burn, Empty, probCatch);
    }
}