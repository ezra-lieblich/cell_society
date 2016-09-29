package cellsociety_team01;

import java.util.Objects;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Base class for all XMLFactories, regardless of what kind of object they actually create.
 * <p>
 * Most this class factors out common methods from more specific XML factories.
 * </p>
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class XMLFactory {
	private String mySimulationType;
    /**
     * @return if this is a valid XML file for this specific XML object type
     */
	 protected XMLFactory (String simulationType) {
	        mySimulationType = simulationType;
	    }

    /**
     * @return the type of Simulation this file represents
     */
    public String getSimulationType () {
        return mySimulationType;
    }

    public boolean isValidFile (Element root){
    	return Objects.equals(getAttribute(root, "PersonType"), getSimulationType());
    }

    /**
     * Get the value of an attribute.
     * 
     * Why might it not be good design to include this and getTextValue in this class?
     * What happens when you need more transformation methods?
     */
    public String getAttribute (Element root, String attributeName) {
        return root.getAttribute(attributeName);
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
}