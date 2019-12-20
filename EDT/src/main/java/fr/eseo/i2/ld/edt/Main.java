package fr.eseo.i2.ld.edt;

import java.io.IOException;

import fr.eseo.i2.ld.edt.controleur.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	// Déclaration des constantes
	private final static String cheminVersVues = "/fr/eseo/i2/ld/edt/vue/";

	// Déclaration des variables
	private Stage premierStage;
	private BorderPane rootLayout;

	// Controleurs
	private RootLayoutController rootLayoutController;

	public static void main(String[] args) {
		launch();
	}

	// Appellé quand le Main est lancé
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.premierStage = primaryStage;
		this.premierStage.setTitle("Emploi du temps");
		
		initRootLayout();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loaderRoot = new FXMLLoader(getClass().getResource(cheminVersVues + "RootLayout.fxml"));
			rootLayout = (BorderPane) loaderRoot.load();

			// Give the controller access to the main class
			this.rootLayoutController = loaderRoot.getController();
			this.rootLayoutController.setMain(this);

			// Show the scene containing the root layout.
			Scene sceneRoot = new Scene(rootLayout);
			this.premierStage.setScene(sceneRoot);
			this.premierStage.show();

		} catch (IOException e) {
			System.out.println("Zut");
		}
	}

}
