package fr.eseo.i2.ld.edt.modele;

import javafx.scene.paint.Color;

public class Matiere {
	private String nomMatiere;
	private Color couleur;
	private Professeur prof;
	private Classe classe;
	private String heureDebut;
	private String heureFin;
	private int jour;

	public Matiere(String nomMatiere, Professeur prof, Color couleur, int jour, String heureDebut, String heureFin,
			Classe classe) {
		this.setNomMatiere(nomMatiere);
		this.setCouleur(couleur);
		this.setHeureDebut(heureDebut);
		this.setHeureFin(heureFin);
		this.setProf(prof);
		this.setClasse(classe);
		this.setJour(jour);
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	private void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	public Color getCouleur() {
		return couleur;
	}

	private void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Professeur getProf() {
		return prof;
	}

	private void setProf(Professeur prof) {
		this.prof = prof;
	}

	public Classe getClasse() {
		return classe;
	}

	private void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	private void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	private void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public int getJour() {
		return jour;
	}

	private void setJour(int jour) {
		this.jour = jour;
	}

	public String toString() {
		return this.getNomMatiere() + "\n" + this.getProf();
	}

	public static int getHeure(String heure) {
		return Integer.parseInt(heure.split("h")[0]);
	}

	public static int getMin(String heure) {
		return Integer.parseInt(heure.split("h")[1]);
	}
}
