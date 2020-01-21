package fr.eseo.i2.ld.edt.modele;

public class Professeur extends Personne {
	public int numProf;
	
	public Professeur(String prenom, String nom, int numProf) {
		super(prenom, nom);
		this.setNumProf(numProf);
	}

	public int getNumProf() {
		return numProf;
	}

	public void setNumProf(int numProf) {
		this.numProf = numProf;
	}
	
}
