package fr.eseo.i2.ld.edt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.i2.ld.edt.controleur.CreationCoursController;
import fr.eseo.i2.ld.edt.controleur.EDTAreaController;
import fr.eseo.i2.ld.edt.controleur.RootLayoutController;
import fr.eseo.i2.ld.edt.modele.Cours;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	// Déclaration des constantes
	private final static String cheminVersVues = "/fr/eseo/i2/ld/edt/vue/";

	// Déclaration des variables
	private Stage premierStage;
	private BorderPane rootLayout;
	private List<Cours> listeCours;

	// Controleurs
	private RootLayoutController rootLayoutController;
	public EDTAreaController edtAreaController;
	public CreationCoursController creationCoursController;

	// Accesseurs
	public List<Cours> getCours() {
		return this.listeCours;
	}

	public static void main(String[] args) {
		launch();
	}

	// Appellé quand le Main est lancé
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.premierStage = primaryStage;
		this.premierStage.setTitle("Emploi du temps");

		this.listeCours = new ArrayList<>();

		initRootLayout();
		initEDTArea();

		PauseTransition wait = new PauseTransition(Duration.seconds(2));
		wait.setOnFinished((e) -> {
			this.edtAreaController.afficherCours();
		});
		wait.play();
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
			System.out.println("Erreur sur la fenêtre principale");
			System.out.println(e.getCause());
		}
	}

	public void initEDTArea() {
		try {
			FXMLLoader loaderEDTArea = new FXMLLoader(getClass().getResource(cheminVersVues + "EDTArea.fxml"));
			AnchorPane drawArea = (AnchorPane) loaderEDTArea.load();

			// Give the controller access to the main class
			this.edtAreaController = loaderEDTArea.getController();
			this.edtAreaController.setMain(this);
			this.edtAreaController.init();

			// Set the draw area into the center of root layout.
			rootLayout.setCenter(drawArea);

		} catch (IOException e) {
			System.out.println("Erreur sur la zone d'emploi du temps");
			System.out.println(e.getCause());
		}
	}

	public void initCreationCours() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loaderInformation = new FXMLLoader(
					getClass().getResource(cheminVersVues + "CreationCours.fxml"));
			AnchorPane page = (AnchorPane) loaderInformation.load();

			// Create the dialog Stage.
			Stage dialogStageInfo = new Stage();
			dialogStageInfo.setTitle("Information");
			dialogStageInfo.initModality(Modality.WINDOW_MODAL);
			dialogStageInfo.initOwner(this.premierStage);

			Scene sceneInfo = new Scene(page);
			dialogStageInfo.setScene(sceneInfo);

			// Give the controller access to the main class
			this.creationCoursController = loaderInformation.getController();
			this.creationCoursController.setMain(this);
			this.creationCoursController.setStage(dialogStageInfo);
			this.creationCoursController.initFenetre();

			// Show the dialog and wait until the user closes it
			dialogStageInfo.showAndWait();
		} catch (IOException e) {
			System.out.println("Erreur sur la fenêtre d'ajout d'un cours");
			System.out.println(e.getCause());
		}
	}
}