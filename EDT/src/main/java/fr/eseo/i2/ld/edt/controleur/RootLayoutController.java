package fr.eseo.i2.ld.edt.controleur;

import fr.eseo.i2.ld.edt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RootLayoutController {

	// Déclaration des composants de la fenêtre
	@FXML
	private Button boutonExemple;

	// Référence à l'application principale
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
		System.out.println("La fenêtre principale a été initialisée");
	}

	@FXML
	private void handleExemple() {
		System.out.println("Appui sur le bouton exemple détecté !");
	}
}
