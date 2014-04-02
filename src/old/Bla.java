package old;

import java.util.ArrayList;
import java.util.List;

import colley.chisholm.diploma.fxparticle.Particle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Bla extends Stage {

	// Constant data members.
	private final String STAGE_TITLE = "Particle Stage";
	private final double SCENE_WIDTH = 800;
	private final double SCENE_HEIGHT = 600;

	// Variable data members.

	private FlowPane root;
	private Scene scene;
	private Canvas canvas;

	private  Paint[] colors = null;
	private GraphicsContext graphicsContext;
	private static AnimationTimer timer = null;

	private int countDownTillNextParticle = 40;

	private int particleWidth = 5;
	private int particleHeight = 5;
	private int buffer = 0;

	private final List<Particle> particles = new ArrayList<Particle>();
	private List<Particle>[] checkList;


	public Bla() {
		
		
		
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		

	}

	private void AddParticle() {

	}

	private void drawParticles(GraphicsContext gc) {

	}

	public void updateParticles() {

	}

	public boolean checkForColision(Particle p) {
		return false;
	}

	private int makeMinXCoord() {
		return 0;
	}

	private int makeMinYCoord() {
		return 0;
	}

	private int makeMaxXCoord() {
		return 0;
	}

	private int makeMaxYCoord() {
		return 0;
	}

	private int makeRandomNumInRange(int min, int max) {
		return 0;
	}

	public void makeColors() {
		// create a color palette of 180 colors
		colors = new Paint[181];
		colors[0] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, 
				new Stop(0, Color.WHITE),
				new Stop(0.2, Color.hsb(59, 0.38, 1)),
				new Stop(0.6, Color.hsb(59, 0.38, 1,0.1)),
				new Stop(1, Color.hsb(59, 0.38, 1,0))
				);
		for (int h=0;h<360;h+=2) {
			colors[1+(h/2)] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, 
					new Stop(0, Color.WHITE),
					new Stop(0.2, Color.hsb(h, 1, 1)),
					new Stop(0.6, Color.hsb(h, 1, 1,0.1)),
					new Stop(1, Color.hsb(h, 1, 1,0))
					);
		}
	}

	private void initialiseMyStage() {

		this.setResizable(false);
		this.setTitle(STAGE_TITLE);

		root = new FlowPane();
		root.setStyle("-fx-background-color: #336699;");
		root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

		canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);

		root.getChildren().add(canvas);

		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);	

		this.setScene(scene);
	}


}
