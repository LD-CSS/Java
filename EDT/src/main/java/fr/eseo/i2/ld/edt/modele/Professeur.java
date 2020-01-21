package fr.eseo.i2.ld.edt.modele;

public class Professeur extends Personne {
	public int numProf;

	private static int nbProf = 0;

	public Professeur(String prenom, String nom) {
		super(prenom, nom);
		this.setNumProf(nbProf);
		nbProf++;
	}

	public int getNumProf() {
		return numProf;
	}

	private void setNumProf(int numProf) {
		this.numProf = numProf;
	}

}
