package fr.eseo.i2.ld.edt.controleur;

import fr.eseo.i2.ld.edt.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class EDTAreaController {

	// Déclaration des composants de la fenêtre
	@FXML
	private GridPane grille;

	// Référence à l'application principale
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
		System.out.println("La fenêtre principale a été initialisée");
	}
}