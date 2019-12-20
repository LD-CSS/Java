package fr.eseo.i2.ld.edt;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	// Déclaration des variables
	private Stage premierStage;
	private BorderPane rootLayout;
	
	// Appellé quand le Main est lancé
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.premierStage = primaryStage;
		this.premierStage.setTitle("EMR Editor");
		initRootLayout();
	}
	
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderRoot = new FXMLLoader(getClass().getResource("/vue/RootLayout.fxml"));
			rootLayout = (BorderPane) loaderRoot.load();

			// Give the controller access to the main class
			//this.rootLayoutController = loaderRoot.getController();
			//this.rootLayoutController.setMainClass(this);

			// Show the scene containing the root layout.
			Scene sceneRoot = new Scene(rootLayout);
			premierStage.setScene(sceneRoot);
			premierStage.show();

		} catch (IOException e) {
			System.out.println("Zut");
		}
	}

}
