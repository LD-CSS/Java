package fr.eseo.i2.ld.edt.controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.eseo.i2.ld.edt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IdentificationProfController {

	// Déclaration des composants de la fenêtre
	@FXML
	private TextField login;
	@FXML
	private PasswordField mdp;

	// Référence à l'application principale
	private Main main;
	private Stage stage;
	private boolean ok;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Main getMain() {
		return this.main;
	}

	public boolean isOk() {
		return this.ok;
	}

	@FXML
	private void handleValider() {
		String loginProf = this.login.getText();
		String mdpProf = this.mdp.getText();
		if (!loginProf.isEmpty() && !mdpProf.isEmpty()) {
			try {
				// Connexion bdd
				Class.forName("org.postgresql.Driver");
				Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.4.221:5432/EDT", "Louis",
						"network");
				// Requête SQL
				Statement stmt = con.createStatement();

				// Requête prof
				ResultSet rs = stmt.executeQuery(
						"SELECT * FROM \"PROF\" WHERE username='" + loginProf + "' AND password='" + mdpProf + "'");
				this.ok = rs.next();
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Problème de connexion à la BDD");
				System.out.println(e.getMessage());
			}
			this.stage.close();
			System.out.println("Authentification réussie");
		}
	}

	@FXML
	private void handleAnnuler() {
		this.ok = false;
		this.stage.close();
	}
}