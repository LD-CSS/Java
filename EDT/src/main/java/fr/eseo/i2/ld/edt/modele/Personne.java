package fr.eseo.i2.ld.edt.modele;

public abstract class Personne {

	private String prenom;
	private String nom;
	private int id;

	public Personne(String prenom, String nom, int id) {
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setId(id);
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

	public int getId() {
		return this.id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return this.getPrenom() + " " + this.getNom();
	}

}
