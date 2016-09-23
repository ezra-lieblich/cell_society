package cellsociety_team01;

import cellsociety_team01.Simulation;
import org.w3c.dom.Element;
import java.util.Objects;


/**
 * An XMLFactory that gives back a Simulation object.
 *
 * @author Christopher Lu
 */
public abstract class SimulationXMLFactory extends XMLFactory {
    private String mySimulationType;


    /**
     * Create a factory for making Simulation objects.  
     */
    protected SimulationXMLFactory (String simulationType) {
        mySimulationType = simulationType;
    }

    /**
     * @return the type of Simulation this file represents
     */
    public String getSimulationType () {
        return mySimulationType;
    }

    /**
     * Get the actual Simulation contained in this XML File.
     */
    public abstract Simulation getSimulation (Element root) throws XMLFactoryException;

    /**
     * @see XMLFactory#isValidFile()
     */
    @Override
    protected boolean isValidFile (Element root) {
        return Objects.equals(getAttribute(root, "PersonType"), getSimulationType());
    }
}