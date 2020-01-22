package fr.eseo.i2.ld.edt.modele;

import java.time.LocalDate;

import javafx.scene.paint.Color;

public class Cours {
	
	private static int nbCours = 0;
	
	private String nomCours;
	private Color couleur;
	private Professeur prof;
	private Classe classe;
	private String heureDebut;
	private String heureFin;
	private LocalDate date;
	private String salle;
	private int idCours;

	public Cours(String nomCours, Professeur prof, Color couleur, LocalDate date, String heureDebut, String heureFin,
			Classe classe, String salle) {
		this.setNomMatiere(nomCours);
		this.setCouleur(couleur);
		this.setHeureDebut(heureDebut);
		this.setHeureFin(heureFin);
		this.setProf(prof);
		this.setClasse(classe);
		this.setDate(date);
		this.setSalle(salle);
		this.setIdCours(nbCours++);
	}

	public String getNomCours() {
		return nomCours;
	}

	private void setNomMatiere(String nomCours) {
		this.nomCours = nomCours;
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

	public LocalDate getDate() {
		return this.date;
	}

	private void setDate(LocalDate date) {
		this.date = date;
	}

	private void setSalle(String salle) {
		this.salle = salle;
	}

	public String getSalle() {
		return this.salle;
	}
	
	private void setIdCours(int idCours) {
		this.idCours = idCours;
	}
	
	public int getIdCours() {
		return this.idCours;
	}

	public String toString() {
		return this.getNomCours() + "\n" + this.getProf() + "\n" + this.getSalle();
	}

	public static int getHeure(String heure) {
		return Integer.parseInt(heure.split("h")[0]);
	}

	public static int getMin(String heure) {
		return Integer.parseInt(heure.split("h")[1]);
	}

	public static int getJour(LocalDate date) {
		return date.getDayOfWeek().getValue();
	}
}
