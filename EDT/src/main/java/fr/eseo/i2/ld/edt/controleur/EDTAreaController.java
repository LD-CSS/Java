package fr.eseo.i2.ld.edt.controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import fr.eseo.i2.ld.edt.Main;
import fr.eseo.i2.ld.edt.modele.Classe;
import fr.eseo.i2.ld.edt.modele.Cours;
import fr.eseo.i2.ld.edt.modele.Matiere;
import fr.eseo.i2.ld.edt.modele.Professeur;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class EDTAreaController {

	// Déclaration des composants de la fenêtre
	@FXML
	private GridPane grille;

	// Référence à l'application principale
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	public Main getMain() {
		return this.main;
	}

	public void init() {
		try {
			Map<Integer, Professeur> mapProf = new HashMap<>();
			Map<Integer, Matiere> mapMatiere = new HashMap<>();
			// Connexion bdd
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.4.221:5432/EDT", "Louis",
					"network");
			// Requête SQL
			Statement stmt = con.createStatement();

			// Requête prof
			ResultSet rs = stmt.executeQuery("SELECT (prenom,nom,\"ProfID\") FROM \"PROF\"");
			while (rs.next()) {
				String texte[] = rs.getString(1).replace("(", "").replace(")", "").split(",");
				mapProf.put(Integer.valueOf(texte[2]), new Professeur(texte[0], texte[1], Integer.valueOf(texte[2])));
			}

			// Requête matiere
			rs = stmt.executeQuery("SELECT (nom, couleur, \"MatiereID\") FROM \"MATIERE\"");
			while (rs.next()) {
				String texte[] = rs.getString(1).replace("(", "").replace(")", "").split(",");
				mapMatiere.put(Integer.valueOf(texte[2]),
						new Matiere(texte[0], Color.valueOf(texte[1]), Integer.valueOf(texte[2])));
			}
			// Requête cours
			rs = stmt.executeQuery(
					"SELECT (date, horaire_debut, horaire_fin, classe, salle, \"Prof_profID\", \"Matiere_matiereID\") FROM \"COURS\"");
			while (rs.next()) {
				String texte[] = rs.getString(1).replace("(", "").replace(")", "").split(",");
				Cours c = new Cours(mapMatiere.get(Integer.valueOf(texte[6])).getNom(),
						mapProf.get(Integer.valueOf(texte[6])), mapMatiere.get(Integer.valueOf(texte[6])).getCouleur(),
						LocalDate.parse(texte[0]), texte[1], texte[2], new Classe(texte[3], texte[3]), texte[4]);
				this.getMain().getCours().add(c);
			}
			rs.close();
			stmt.close();
			con.close();
			System.out.println("La zone d'emploi du temps a été initialisée");
		} catch (Exception e) {
			System.out.println("Problème de connexion à la BDD");
			System.out.println(e.getCause());
		}
	}

	@FXML
	private void handleClick(MouseEvent e) {
		Node clickedNode = e.getPickResult().getIntersectedNode();
		if (clickedNode != this.grille) {
			Node parent = clickedNode.getParent();
			while (parent != this.grille) {
				clickedNode = parent;
				parent = clickedNode.getParent();
			}
			Integer colIndex = GridPane.getColumnIndex(clickedNode);
			Integer rowIndex = GridPane.getRowIndex(clickedNode);
			if (colIndex != null && rowIndex != null) {
				System.out.printf("Clic sur la cellule [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
				System.out.println(((Label) clickedNode).getText());
			}
		}
	}

	public void viderCours() {
		LocalDate today = LocalDate.now();
		int jour = today.getDayOfWeek().getValue();
		LocalDate dimancheAvant = today.minusDays(jour - 7 * this.getMain().decalageSemaine);
		int nbCoursASupprimer = 0;
		for (Cours c : this.getMain().getCours()) {
			if (c.getClasse().getIdClasse().equals(this.getMain().getClasseActuelle().getIdClasse())
					&& c.getDate().isAfter(dimancheAvant) && c.getDate().isBefore(dimancheAvant.plusDays(7))) {
				nbCoursASupprimer++;
			}
		}
		for (int k = 0; k < nbCoursASupprimer; k++) {
			this.grille.getChildren().remove(this.grille.getChildren().size() - 1);
		}
	}

	public void afficherCoursDansCase(int ligne, int colonne, String label, Color couleur, int span) {
		Label infoAAfficher = new Label(label);
		infoAAfficher.setMaxSize(this.grille.getWidth() / this.grille.getColumnCount(),
				span * this.grille.getHeight() / (this.grille.getRowCount() - 1) + 30);
		infoAAfficher.setBackground(new Background(new BackgroundFill(couleur, null, null)));
		infoAAfficher.setAlignment(Pos.CENTER);
		infoAAfficher.setTextAlignment(TextAlignment.CENTER);
		GridPane.setMargin(infoAAfficher, new Insets(0.9, 0.9, 0, 0.9));
		GridPane.setConstraints(infoAAfficher, colonne, ligne);
		GridPane.setRowSpan(infoAAfficher, span);
		this.grille.add(infoAAfficher, colonne, ligne);

		// Effet lorsqu'on passe dessus avec la souris
		infoAAfficher.setOnMouseEntered((MouseEvent t) -> {
			infoAAfficher.setStyle("-fx-background-color:#FFFF00;");
		});
		infoAAfficher.setOnMouseExited((MouseEvent t) -> {
			infoAAfficher
					.setStyle("-fx-background-color:" + String.format("#%02X%02X%02X", (int) (couleur.getRed() * 255),
							(int) (couleur.getGreen() * 255), (int) (couleur.getBlue() * 255)) + ";");
		});
	}

	public void afficherCours() {
		LocalDate today = LocalDate.now();
		int jour = today.getDayOfWeek().getValue();
		LocalDate dimancheAvant = today.minusDays(jour - 7 * this.getMain().decalageSemaine);
		this.getMain().rootLayoutController
				.setText("Semaine du " + String.format("%02d", dimancheAvant.plusDays(1).getDayOfMonth()) + "/"
						+ String.format("%02d", dimancheAvant.plusDays(1).getMonthValue()) + " au "
						+ String.format("%02d", dimancheAvant.plusDays(7).getDayOfMonth()) + "/"
						+ String.format("%02d", dimancheAvant.plusDays(7).getMonthValue()));
		for (Cours m : this.getMain().getCours()) {
			int i = Cours.getHeure(m.getHeureDebut()) - 7;
			int j = Cours.getJour(m.getDate());
			if (m.getClasse().getIdClasse().equals(this.getMain().getClasseActuelle().getIdClasse())
					&& m.getDate().isAfter(dimancheAvant) && m.getDate().isBefore(dimancheAvant.plusDays(7))) {
				afficherCoursDansCase(i, j, m.toString(), m.getCouleur(),
						Cours.getHeure(m.getHeureFin()) - Cours.getHeure(m.getHeureDebut()));
			}
		}
	}
}