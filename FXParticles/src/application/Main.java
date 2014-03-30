// Rachael Colley 2014

package application;

import particle1.ParticleCanvas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	private BorderPane root;
	private Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			addComponentsToStage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addComponentsToStage() {
		addParticleCanvasLauncher();

	}

	private void addParticleCanvasLauncher() {
		Button button = new Button();
		button.setText("Click here");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final ParticleCanvas s = new ParticleCanvas();
				s.show();
			}
		});
		root.setCenter(button);
	}


}
