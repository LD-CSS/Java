package fr.eseo.i2.ld.edt.controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.i2.ld.edt.Main;
import fr.eseo.i2.ld.edt.modele.Classe;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class RootLayoutController {

	// Déclaration des composants de la fenêtre
	@FXML
	private Button boutonAjoutCours;

	@FXML
	private Label info;

	@FXML
	private ChoiceBox<Classe> classes;

	// Référence à l'application principale
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setText(String texte) {
		this.info.setText(texte);
	}

	public Classe getClasse() {
		return this.classes.getValue();
	}

	@FXML
	private void initialize() {
		List<Classe> listeClasses = new ArrayList<>();
		try {
			// Connexion bdd
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.4.221:5432/EDT", "Louis",
					"network");
			// Requête SQL
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT classe FROM \"ELEVE\"");
			while (rs.next()) {
				String classe = rs.getString(1).replace("(", "").replace(")", "");
				listeClasses.add(new Classe(classe, classe));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Problème de connexion à la BDD");
			System.out.println(e.getMessage());
		}
		this.classes.setItems(FXCollections.observableArrayList(listeClasses));
		this.classes.setValue(listeClasses.get(0));
		System.out.println("La fenêtre principale a été initialisée");
	}

	@FXML
	private void handleAjoutCours() {
		System.out.println("Ajout d'un cours");
		this.main.initCreationCours();
	}

	@FXML
	private void handleSemaineAvant() {
		this.main.edtAreaController.viderCours();
		this.main.decalageSemaine--;
		this.main.edtAreaController.afficherCours();
	}

	@FXML
	private void handleToday() {
		this.main.edtAreaController.viderCours();
		this.main.decalageSemaine = 0;
		this.main.edtAreaController.afficherCours();
	}

	@FXML
	private void handleSemaineApres() {
		this.main.edtAreaController.viderCours();
		this.main.decalageSemaine++;
		this.main.edtAreaController.afficherCours();
	}

	@FXML
	private void handleChangeClasse() {
		this.main.edtAreaController.viderCours();
		this.main.setClasseActuelle(this.classes.getValue());
		this.main.edtAreaController.afficherCours();
	}
}