package fr.eseo.i2.ld.edt.modele;

public class Eleve extends Personne {
	public int numEleve;
	
	public Eleve(String prenom, String nom, int numEleve) {
		super(prenom, nom);
		this.setNumEleve(numEleve);
	}

	public int getNumEleve() {
		return numEleve;
	}

	public void setNumEleve(int numEleve) {
		this.numEleve = numEleve;
	}
		
}
