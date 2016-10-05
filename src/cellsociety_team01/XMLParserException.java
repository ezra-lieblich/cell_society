package cellsociety_team01;


/**
 * This class creates XMLParserExceptions which can be thrown when exceptions are encounterd while parsing data from XML File.
 * For example, if the file is a pdf instead of an xml, an XMLParserException will be thrown.
 * 
 * @author Christopher Lu
 */
public class XMLParserException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * Create an exception message.
     * @param message
     */
    
    public XMLParserException(String message) {
    	super(String.format(message));
    }
    
    /**
     * Create an exception based on an issue in our code.
     */
    public XMLParserException (String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public XMLParserException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLParserException (Throwable cause) {
        super(cause);
    }
}