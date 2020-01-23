package fr.eseo.i2.ld.edt.controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.i2.ld.edt.Main;
import fr.eseo.i2.ld.edt.modele.Classe;
import fr.eseo.i2.ld.edt.modele.Cours;
import fr.eseo.i2.ld.edt.modele.Matiere;
import fr.eseo.i2.ld.edt.modele.Professeur;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreationCoursController {

	// Déclaration des composants de la fenêtre
	@FXML
	private ChoiceBox<Professeur> choixProf;

	@FXML
	private ChoiceBox<Classe> choixClasse;

	@FXML
	private DatePicker choixDate;

	@FXML
	private Slider choixHeureDebut;

	@FXML
	private Slider choixHeureFin;

	@FXML
	private ChoiceBox<Matiere> choixMatiere;

	@FXML
	private TextField choixSalle;

	private List<Professeur> listeProf;
	private List<Classe> listeClasses;
	private List<Matiere> listeMatieres;

	// Référence à l'application principale
	private Main main;
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Main getMain() {
		return this.main;
	}

	public void initFenetre() {
		this.listeProf = new ArrayList<>();
		this.listeClasses = new ArrayList<>();
		this.listeMatieres = new ArrayList<>();
		try {
			// Connexion bdd
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.4.221:5432/EDT", "Louis",
					"network");
			// Requête SQL
			Statement stmt = con.createStatement();

			// Requête prof
			ResultSet rs = stmt.executeQuery("SELECT (prenom,nom,\"ProfID\") FROM \"PROF\"");
			while (rs.next()) {
				String prof = rs.getString(1).replace("(", "").replace(")", "");
				listeProf.add(
						new Professeur(prof.split(",")[0], prof.split(",")[1], Integer.valueOf(prof.split(",")[2])));
			}

			// Requête matières
			rs = stmt.executeQuery("SELECT (nom,couleur,\"MatiereID\") FROM \"MATIERE\"");
			while (rs.next()) {
				String texte = rs.getString(1).replace("(", "").replace(")", "");
				String nom = texte.split(",")[0];
				Color couleur = Color.valueOf(texte.split(",")[1]);
				int id = Integer.valueOf(texte.split(",")[2]);
				this.listeMatieres.add(new Matiere(nom, couleur, id));
			}

			// Requête classes
			rs = stmt.executeQuery("SELECT DISTINCT classe FROM \"ELEVE\"");
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
		this.choixProf.setItems(FXCollections.observableArrayList(this.listeProf));
		this.choixMatiere.setItems(FXCollections.observableArrayList(this.listeMatieres));
		this.choixClasse.setItems(FXCollections.observableArrayList(this.listeClasses));
	}

	@FXML
	private void handleOK() {
		Professeur prof = this.choixProf.getValue();
		Classe classe = this.choixClasse.getValue();
		Matiere matiere = this.choixMatiere.getValue();
		LocalDate date = this.choixDate.getValue();
		int heureDebut = (int) this.choixHeureDebut.getValue();
		int heureFin = (int) this.choixHeureFin.getValue();
		String salle = this.choixSalle.getText();
		if (prof != null && classe != null && matiere != null && date != null && heureDebut != 0 && heureFin != 0
				&& !salle.isEmpty()) {
			if (this.getMain().initIdentification()) {
				Cours cours = new Cours(matiere.getNom(), prof, matiere.getCouleur(), date, heureDebut + "h00",
						heureFin + "h00", classe, salle);
				if (!this.getMain().getCours().isEmpty()) {
					this.getMain().edtAreaController.viderCours();
				}
				this.getMain().getCours().add(cours);
				this.getMain().edtAreaController.afficherCours();

				try {
					// Connexion bdd
					Class.forName("org.postgresql.Driver");
					Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.4.221:5432/EDT", "Louis",
							"network");
					// Requête SQL
					Statement stmt = con.createStatement();

					// Requête prof

					// Requête cours
					stmt.execute(
							"INSERT INTO \"COURS\" (salle, horaire_debut, horaire_fin, date, classe, \"Matiere_matiereID\", \"Prof_profID\") VALUES ('"
									+ cours.getSalle() + "', '" + cours.getHeureDebut() + "', '" + cours.getHeureFin()
									+ "', '" + cours.getDate() + "', '" + cours.getClasse() + "', " + matiere.getId()
									+ ", " + prof.getId() + ")");
					stmt.close();
					con.close();
				} catch (Exception e) {
					System.out.println("Problème de connexion à la BDD");
					System.out.println(e.getMessage());
				}
				this.stage.close();
				System.out.println("Cours ajouté");
			}
		}
	}

	@FXML
	private void handleAnnuler() {
		this.stage.close();
	}
}