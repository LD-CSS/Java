package fr.eseo.i2.ld.edt.controleur;

import fr.eseo.i2.ld.edt.Main;
import fr.eseo.i2.ld.edt.modele.Matiere;
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

	@FXML
	private void initialize() {
		System.out.println("La zone d'emploi du temps a été initialisée");
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
				System.out.println(((Label)clickedNode).getText());
			}
		}
	}

	public void viderCours() {
		for (int k = (this.grille.getColumnCount() - 1) * (this.grille.getRowCount() - 1); k > 0; k--) {
			this.grille.getChildren().remove(this.grille.getChildren().size() - 1);
		}
	}

	public void afficherCoursDansCase(int ligne, int colonne, String label, Color couleur, int span) {
		Label infoAAfficher = new Label(label);
		infoAAfficher.setMaxSize(this.grille.getWidth() / this.grille.getColumnCount(),
				span * this.grille.getHeight() / (this.grille.getRowCount()-1) + 30);
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
		for(Matiere m : this.getMain().getMatieres()) {
			int i = Matiere.getHeure(m.getHeureDebut())-7;
			int j = m.getJour();
			afficherCoursDansCase(i, j, m.toString(), m.getCouleur(), Matiere.getHeure(m.getHeureFin())-Matiere.getHeure(m.getHeureDebut()));
		}
	}
}