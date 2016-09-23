package cellsociety_team01;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.stage.Stage;
import water.*;
import xo.Empty;
import xo.Group1;
import xo.Group2;
import xo.XOGridLogic;
import life.*;

public class GridController {
	//private 
	private GridLogic logic;
	private GridView view;
	private XmlReader reader;
	private Toolbar toolbar;
	private String title;
	private Scene scene;
	private Timeline animation;
	private Stage stage;
	private Scene mainMenu;
	
    private int screenWidth, screenHeight;
    private boolean isSetupFinished = false;

	public GridController(Stage stage) {
		this.stage = stage;

		MainMenu menu = new MainMenu(this, stage);
		mainMenu = menu.init();
		stage.setScene(mainMenu);
		stage.show();
		// temporary code
		title = "Test";
	}
	
	public void parseFile(File file){
		//System.out.println(file.getAbsolutePath());
		
		
    	//assign screenWidth and screenHeight
		setupScreenResolution();
		init();
	}
	
    private void setupScreenResolution(){
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	screenWidth = (int) screenSize.getWidth();
    	screenHeight = (int) screenSize.getHeight();
    }

	public void init() {
        
		// menu.init();

		
		BasicGrid grid = createXOGrid(20,20);
		logic = new XOGridLogic(grid);
		
		//ToroidalGrid grid = createRandomWaterGrid(60,60);
		//logic = new WaterGridLogic(grid);
		
		BorderPane root = new BorderPane();
		view = new GridView(root, grid);
		//view = new GridView(root, grid);
		toolbar = new Toolbar(root, this);
		createTimeline();
		stage.setScene(new Scene(root, screenWidth, screenHeight, Color.WHITE));
		//display the view initially before starting simulation
		view.step();
     }

	private void createTimeline() {
		int MILLISECOND_DELAY = 500;
		//double SECOND_DELAY = MILLISECOND_DELAY/1000;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		//stage.setScene(new Scene(root, screenWidth, screenHeight, Color.WHITE));
        
		//view = new GridView(root, grid);
//		WaterGrid grid = createRandomWaterGrid(60,60);
//		logic = new WaterGridLogic(grid);
//		Group root = new Group();
//		view = new GridView(root, grid);
//		LifeGrid grid = createLifeGrid(20,20);
//		logic = new LifeGridLogic(grid);
//		Group root = new Group();
//		view = new GridView(root, grid);
//		return new Scene(root, screenWidth, screenHeight, Color.WHITE);
	}

	public void step() {
		// if (!isSetupFinished) {

		// if (menu.getFileChosen()) {
		// setup(menu.getFilePath());
		// }
		// break;
		// }
		view.step();
		logic.step();
		
	}

//	private void setup(String path) {
//		reader = new XmlReader("data/xml/GameOfLife1.xml");
//		logic = reader.getGridLogic();
//		view = reader.getGridView();
//		isSetupFinished = true;
//	}

	public String getTitle() {
		return title;
	}

	// for testing, creates a water grid with random types of cells
	private ToroidalGrid createRandomWaterGrid(int rows, int columns) {
		ToroidalGrid temp = new ToroidalGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 10);
				if(ranGen<=1)
					temp.setGridIndex(new Shark(r, c), r, c);
				else if(1<ranGen&&ranGen<=8)
					temp.setGridIndex(new Fish(r, c), r, c);
				else
					temp.setGridIndex(new EmptyCell(r, c), r, c);


			}
		}
		return temp;
	}
	private BasicGrid createXOGrid(int rows, int columns) {
		BasicGrid temp = new BasicGrid(rows, columns);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				int ranGen = (int) (Math.random() * 3);
				switch (ranGen) {
				case 0:
					temp.setGridIndex(new Empty(r, c), r, c);
					break;
				case 1:
					temp.setGridIndex(new Group1(r, c), r, c);
					break;
				case 2:
					temp.setGridIndex(new Group2(r, c), r, c);
					break;
				default:
					break;
				}
			}
		}
		return temp;
	}

	public void startSimulation() {
		animation.play();;
	}

	public void stopSimulation() {
		animation.stop();;
	}

	public void stepSimulation() {
		animation.stop();
		this.step();
	}

	public void updateSpeed(double value) {
		double new_rate = value;
		animation.setRate(new_rate);
	}

	public void resetSimulation() {
		// Controller will tell grid to reset itslef to the original implementation
		// grid.resetGrid();
	}

	public void changeSimulation() {
		// TODO Auto-generated method stub
	}
}
