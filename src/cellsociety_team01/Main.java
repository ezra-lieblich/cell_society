package cellsociety_team01;



import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Eric S. Song
 */
public class Main extends Application {
    private int screenWidth, screenHeight;
    private static final int MILLISECOND_DELAY = 200;
    private static final double SECOND_DELAY = MILLISECOND_DELAY/1000;

    private GridController controller;


    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	
    	//assign screenWidth and screenHeight
    	setupScreenResolution();
    	
        // create your own game here
        controller = new GridController();
        s.setTitle(controller.getTitle());

        // attach game to the stage and display it
        Scene scene = controller.init(screenWidth, screenHeight);
        s.setScene(scene);
        s.show();

        // sets the game's loop
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> controller.step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    private void setupScreenResolution(){
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	screenWidth = (int) screenSize.getWidth();
    	screenHeight = (int) screenSize.getHeight();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
    
    public void restartGame(){
    	controller.init(screenWidth, screenHeight);
    }
    
    
}
