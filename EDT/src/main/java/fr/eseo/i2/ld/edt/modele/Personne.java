package fr.eseo.i2.ld.edt.modele;

public abstract class Personne {
	String prenom;
	String nom;

	public Personne(String prenom, String nom) {
		this.setPrenom(prenom);
		this.setNom(nom);
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String toString() {
		return this.getPrenom() + " " + this.getNom();
	}

}
