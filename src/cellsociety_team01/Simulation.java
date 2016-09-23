package cellsociety_team01;

/**
 * A value object for a Simulation.
 *
 * @author Christopher Lu
 */
public class Simulation {
    private String mySimulationName;
    private String mySimulationTitle;
    private String mySimulationAuthor;
    private String percentXGridSize;
    private String percentYGridSize;
    

    public Simulation (String simulationName, String simulationTitle, String simulationAuthor, String percentX, String percentY) {
        mySimulationName = simulationName;
        mySimulationTitle = simulationTitle;
        mySimulationAuthor = simulationAuthor;
        percentXGridSize = percentX;
        percentYGridSize = percentY;
    }

    public String getName () {
        return mySimulationName;
    }

    public String getTitle () {
        return mySimulationTitle;
    }
    
    public String getAuthor () {
    	return mySimulationAuthor;
    }
    
    public String getXSize () {
    	return percentXGridSize;
    }
    
    public String getYSize () {
    	return percentYGridSize;
    }
}