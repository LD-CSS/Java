package fr.eseo.i2.ld.edt.controleur;

import fr.eseo.i2.ld.edt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RootLayoutController {

	// Déclaration des composants de la fenêtre
	@FXML
	private Button boutonAjoutCours;

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
	private void handleAjoutCours() {
		System.out.println("Ajout d'un cours");
		//this.main.edtAreaController.afficherCoursDansCase(1, 1, "Maths\nM. Glière\nA402 - Truc", Color.PINK, 1);
		//this.main.edtAreaController.afficherTousLesCours("Maths\nM. Glière\nA402 - Truc");
		//this.main.edtAreaController.afficherCours();
		this.main.initCreationCours();
	}
}
