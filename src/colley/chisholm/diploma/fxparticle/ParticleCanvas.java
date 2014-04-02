// Rachael Colley 2014

package colley.chisholm.diploma.fxparticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class ParticleCanvas extends Stage {

	// Constant data members.
	private final String STAGE_TITLE = "Particle Stage";
	private final double SCENE_WIDTH = 800;
	private final double SCENE_HEIGHT = 600;

	// Variable data members.

	private FlowPane root;
	private Scene scene;
	private Canvas canvas;

	private Paint[] colors = null;
	private GraphicsContext graphicsContext;
	private static AnimationTimer timer = null;

	private int countDownTillNextParticle = 40;

	private int particleWidth = 5;
	private int particleHeight = 5;
	private int buffer = 0;

	private final List<Particle> particles = new ArrayList<Particle>();
	private List<Particle>[] checkList;

	public ParticleCanvas() {

		initialiseMyStage();

		// Make the color array.
		makeColors();

		// Get the graphics context for drawing.
		graphicsContext = canvas.getGraphicsContext2D();
		// Set the graphics fill color to black.
		graphicsContext.setFill(Color.BLACK);

		// Get an AnimationTimer instance.
		timer = new AnimationTimer() {

			// ******* The handle method executes on each frame (roughly 60
			// times per second) *****
			@Override
			public void handle(long now) {

				//graphicsContext.setFill(Color.rgb(0, 0, 0, 0.2));
				graphicsContext.setFill(Color.BLACK);
				graphicsContext.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);

				checkList = new List[(int) canvas.getHeight()];

				drawParticles(graphicsContext);
				updateParticles();

				// Adds a new particle at specified interval.
				if (countDownTillNextParticle == 0) {
					countDownTillNextParticle = 5 + (int) (Math.random() * 15);
					AddParticle();
				}
				countDownTillNextParticle--;
			}
		};
		// Start the timer.
		timer.start();
	}

	private void AddParticle() {

		int randomx = makeRandomNumInRange(makeMinXCoord(), makeMaxXCoord())
				+ buffer;
		int randomy = makeRandomNumInRange(makeMinYCoord(), makeMaxYCoord())
				+ buffer;
		int randomTargetx = makeRandomNumInRange(makeMinXCoord(),
				makeMaxXCoord()) + buffer;
		int randomTargety = makeRandomNumInRange(makeMinYCoord(),
				makeMaxYCoord()) + buffer;
		// int color = (int)(Math.random()* colors.length);
		// Particle p = new Particle(randomx, randomy, randomTargetx,
		// randomTargety, particleWidth, particleHeight, colors[color]);
		Color color = Color.WHITE;
		Particle p = new Particle(randomx, randomy, randomTargetx,
				randomTargety, particleWidth, particleHeight, color);

		particles.add(p);
		System.out.println("List size:  " + particles.size());
	}

	private void drawParticles(GraphicsContext gc) {
		for (Particle p : particles) {
			gc.setFill(p.getColor());
			gc.fillOval(p.getPosx(), p.getPosy(), p.getWidth(), p.getHeight());
		}
	}

	public void updateParticles() {
		for (Particle p : particles) {
			p.update();
			// Check if the current particle's y does not yet exist as an index
			// of the array
			// and create a list of Particle at that index, if need be.
			if (checkList[(int) p.getPosy()] == null) {
				checkList[(int) p.getPosy()] = new ArrayList<Particle>();
			}
			// Add the current particle to the array of lists at the index of y.
			checkList[(int) p.getPosy()].add(p);
			checkForColision(p);
		}
	}

	public boolean checkForColision(Particle p) {
		// Field to scan is the Particle's current y
		// minus particleSize and plus particleSize.
		int startRowIndex = (int) (p.getPosy() - particleHeight);
		if (startRowIndex < 0) {
			startRowIndex = 0;
		}
		int endRowIndex = (int) (p.getPosy() + particleHeight);
		if (endRowIndex > checkList.length - 2) {
			endRowIndex = checkList.length - 2;
		}

		for (int x = startRowIndex; x <= endRowIndex; x++) {

			// Get current list and check if it's not null.
			List<Particle> l = checkList[x];
			if (l != null) {

				// Loop through each Particle in the list.
				for (int i = 0; i < l.size(); i++) {
					// Get current Particle in the list.
					Particle cp = l.get(i);
					// If the current Particle in the list is the one
					// we are checking against (self), return.
					if (cp == p) {
						return false;
					}

					// if ((cp.getPosx() + particleWidth) >= (p.getPosx()) &&
					// (cp.getPosx()) <= (p.getPosx() + particleWidth)) {
					//
					// p.setWidth(50);
					// p.setHeight(50);
					// cp.setWidth(50);
					// cp.setHeight(50);
					// System.out.println("*****************COLLIDE");
					// return true;
					// }

					if ((cp.getPosx() + cp.getWidth()) >= (p.getPosx())
							&& (cp.getPosx()) <= (p.getPosx() + p.getWidth())) {

						p.setWidth(10);
						p.setHeight(10);
						cp.setWidth(10);
						cp.setHeight(10);
						System.out
								.println("***************************************************COLLIDE********");
						return true;
					}
				}

			}

		}
		// Nothing found, return false.
		System.out.println("*****************NO COLLISION");

		return false;
	}

	private int makeMinXCoord() {
		int min = 0;
		min = buffer;
		return min;
	}

	private int makeMinYCoord() {
		int min = 0;
		min = buffer;
		return min;
	}

	private int makeMaxXCoord() {
		int max = 0;
		max = (int) canvas.getWidth() - buffer;
		return max;
	}

	private int makeMaxYCoord() {
		int max = 0;
		max = (int) canvas.getHeight() - buffer;
		return max;
	}

	private int makeRandomNumInRange(int min, int max) {
		int num = 0;
		Random r = new Random();
		num = (r.nextInt(max - min));
		return num;
	}

	public void makeColors() {
		// create a color palette of 180 colors
		colors = new Paint[181];
		colors[0] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
				CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(0.2,
						Color.hsb(59, 0.38, 1)), new Stop(0.6, Color.hsb(59,
						0.38, 1, 0.1)), new Stop(1, Color.hsb(59, 0.38, 1, 0)));
		for (int h = 0; h < 360; h += 2) {
			colors[1 + (h / 2)] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
					CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(
							0.2, Color.hsb(h, 1, 1)), new Stop(0.6, Color.hsb(
							h, 1, 1, 0.1)), new Stop(1, Color.hsb(h, 1, 1, 0)));
		}
	}

	private void initialiseMyStage() {

		this.setResizable(false);
		this.setTitle(STAGE_TITLE);

		root = new FlowPane();
		root.setStyle("-fx-background-color: #336699;");
		root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

		canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				print(arg0.getX() + " " + arg0.getY());

			}
		});

		root.getChildren().add(canvas);

		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);

		this.setScene(scene);
	}

	private void print(Object value) {
		System.out.println(value);
	}

}
