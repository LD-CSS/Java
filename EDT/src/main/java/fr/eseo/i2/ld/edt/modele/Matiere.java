package fr.eseo.i2.ld.edt.modele;

import java.awt.Color;

public class Matiere {
	private String nomMatiere;
	private Color couleur;
	private Professeur prof;
	private Classe classe;
	private String heureDebut;
	private String heureFin;
	

	public Matiere(String nomMatiere, Color couleur) {
		super();
		this.setNomMatiere(nomMatiere);
		this.setCouleur(couleur);
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
}
