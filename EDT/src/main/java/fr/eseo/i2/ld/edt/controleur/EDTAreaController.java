package fr.eseo.i2.ld.edt.controleur;

import fr.eseo.i2.ld.edt.Main;
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
			System.out.printf("Click on cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
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
				span * this.grille.getHeight() / this.grille.getRowCount());
		infoAAfficher.setBackground(new Background(new BackgroundFill(couleur, null, null)));
		infoAAfficher.setAlignment(Pos.CENTER);
		GridPane.setMargin(infoAAfficher, new Insets(0.5));
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

	public void afficherTousLesCours(String label) {
		for (int i = 1; i < this.grille.getRowCount(); i++) {
			for (int j = 1; j < this.grille.getColumnCount(); j++) {
				afficherCoursDansCase(i, j, label, Color.PINK, 1);
			}
		}
	}
}