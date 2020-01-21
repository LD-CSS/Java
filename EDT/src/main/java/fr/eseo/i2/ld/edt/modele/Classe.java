package fr.eseo.i2.ld.edt.modele;

public class Classe {
	public String nomClasse;
	public String idClasse;

	public Classe(String nomClasse, String idClasse) {
		this.setNomClasse(nomClasse);
		this.setIdClasse(idClasse);
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public String getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(String idClasse) {
		this.idClasse = idClasse;
	}
	
}
