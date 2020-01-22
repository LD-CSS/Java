package fr.eseo.i2.ld.edt.modele;

import javafx.scene.paint.Color;

public class Matiere {
	private String nom;
	private Color couleur;
	private int id;
	
	public Matiere(String nom, Color couleur, int id) {
		this.setNom(nom);
		this.setCouleur(couleur);
		this.setId(id);
	}

	public String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}

	public Color getCouleur() {
		return couleur;
	}

	private void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.nom;
	}
}
