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
		//this.main.edtAreaController.afficherCoursDansCase(1, 1, "Maths", Color.PINK, 2);
		this.main.edtAreaController.afficherTousLesCours("Maths");
	}
	
	@FXML
	private void handleClear() {
		System.out.println("Appui sur le menu Supprimer détecté !");
		this.main.edtAreaController.viderCours();
	}
}
