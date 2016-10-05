package cellsociety_team01;

/**
 * This class represents what might go wrong when using Grid Factories.
 * A GridFactoryException will be thrown when invalid data in the XML is input by the user.
 * For example, if the user is running a Game Of Life simulation and enters a percentAlive value greater than 1 (100%) or less than 0
 * a GridFactoryException will be thrown.
 * @author Christopher Lu
 */
public class GridFactoryException extends Exception {
    private static final long serialVersionUID = 1L;
    protected static String message = "";

    public GridFactoryException(String m) {
    	super(String.format(setMessage(m)));
    }
    /**
     * Create an exception based on an issue in our code.
     */
    
    /**
     * Sets error message
     * @param desiredMessage
     * @return
     */
    public static String setMessage(String desiredMessage) {
    	message = desiredMessage;
    	return message;
    }
    
    public GridFactoryException (String m, Object ... values) {
        super(String.format(setMessage(m), values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public GridFactoryException (Throwable cause, String m, Object ... values) {
        super(String.format(setMessage(m), values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public GridFactoryException (Throwable cause) {
        super(cause);
    }
    
    /**
     * Returns the error message, used later when creating pop up error boxes.
     * @return
     */
    public String getErrorMessage() {
    	return message;
    }
}