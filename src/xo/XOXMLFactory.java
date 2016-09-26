package xo;
import cellsociety_team01.Simulation;
import cellsociety_team01.SimulationXMLFactory;
import cellsociety_team01.XMLFactoryException;
import xo.XOXML;
import org.w3c.dom.Element;

/**
 * Creates LifeXML object from an XML file.
 *
 * @author Christopher Lu
 */
public class XOXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "GameOfLife";


    /**
     * Create factory capable of generating LifeXML objects.
     */
    public XOXMLFactory () {
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
        String X = getTextValue(root, "percentX");
        String O = getTextValue(root, "percentO");
        return new XOXML(simulationName, simulationTitle, simulationAuthor, xGridSize, yGridSize, X, O);
    }
}