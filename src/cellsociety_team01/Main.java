package cellsociety_team01;



import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import org.w3c.dom.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import cellsociety_team01.XmlReader;
import factories.LifeGridFactory;
import cellsociety_team01.GridFactoryException;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Eric S. Song
 */
public class Main extends Application {
    private static final int MILLISECOND_DELAY = 200;
    private static final double SECOND_DELAY = MILLISECOND_DELAY/1000;

    private GridController controller;
    
    private static final String XML_FILES_LOCATION = "data/xml/";
    private static final String XML_SUFFIX = ".xml";

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	
    	
        // create your own game here
        controller = new GridController(s);
        s.setTitle(controller.getTitle());

        // attach game to the stage and display it
        


        // sets the game's loop
        /*
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> controller.step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        */
    }
    


    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
//        XmlReader reader = new XmlReader();
//        SimulationXMLFactory factory = new LifeXMLFactory();
//        File folder = new File(XML_FILES_LOCATION);
//        for (File f : folder.listFiles()) {
//        	if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
//        		try {
//        			Element root = reader.getRootElement(f.getAbsolutePath());
//        			Simulation s = factory.getSimulation(root);
//        			root.getAttribute("percentAlive");
//        			System.out.println(s);
//        		}
//        		catch (XMLFactoryException e) {
//        			System.err.println("Reading file " + f.getPath());
//        			e.printStackTrace();
//        		}
//        	}
//        }
    }
    
//    public void restartGame(){
//    	controller.init();
//    }
    
    
}
