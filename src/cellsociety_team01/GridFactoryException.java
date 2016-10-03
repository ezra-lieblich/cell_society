package cellsociety_team01;


/**
 * This class represents what might go wrong when using XML files.
 * 
 * @author Robert Duvall
 */
public class GridFactoryException extends Exception {
    // for serialization
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
     * Gets the error message.
     * @return
     */
    public String getErrorMessage() {
    	return message;
    }
}