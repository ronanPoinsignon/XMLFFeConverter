package prog.xmlClasse;

/**
 * Classe représentant l'officiel dans le fichier XML.
 * @author ronan
 *
 */
public class Officiel {
	
	private String nom_fonction, code_fonction, nom, prenom, licence, niv_min, nb_min, nb_max, obl_resus;
	private Officiels officiels;
	
	/**
	 * 
	 */
	public Officiel() {
		
	}
	
	/**
	 * 
	 * @param nom_fonction
	 * @param code_fonction
	 * @param nom
	 * @param prenom
	 * @param licence
	 * @param niv_min
	 * @param nb_min
	 * @param nb_max
	 * @param obl_resus
	 */
	public Officiel(String nom_fonction, String code_fonction, String nom, String prenom, String licence,
			String niv_min, String nb_min, String nb_max, String obl_resus) {
		this.nom_fonction = nom_fonction;
		this.code_fonction = code_fonction;
		this.nom = nom;
		this.prenom = prenom;
		this.licence = licence;
		this.niv_min = niv_min;
		this.nb_min = nb_min;
		this.nb_max = nb_max;
		this.obl_resus = obl_resus;
	}

	public String getNom_fonction() {
		return nom_fonction;
	}

	public String getCode_fonction() {
		return code_fonction;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLicence() {
		return licence;
	}

	public String getNiv_min() {
		return niv_min;
	}

	public String getNb_min() {
		return nb_min;
	}

	public String getNb_max() {
		return nb_max;
	}

	public String getObl_resus() {
		return obl_resus;
	}
	
	public Officiels getOfficiel() {
		return this.officiels;
	}

	public void setNom_fonction(String nom_fonction) {
		this.nom_fonction = nom_fonction;
	}

	public void setCode_fonction(String code_fonction) {
		this.code_fonction = code_fonction;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public void setNiv_min(String niv_min) {
		this.niv_min = niv_min;
	}

	public void setNb_min(String nb_min) {
		this.nb_min = nb_min;
	}

	public void setNb_max(String nb_max) {
		this.nb_max = nb_max;
	}

	public void setObl_resus(String obl_resus) {
		this.obl_resus = obl_resus;
	}
	
	public void setOfficiels(Officiels officiels) {
		this.officiels = officiels;
	}

	@Override
	public String toString() {
		return "Officiel [nom_fonction=" + nom_fonction + ", code_fonction=" + code_fonction + ", nom=" + nom
				+ ", prenom=" + prenom + ", licence=" + licence + ", niv_min=" + niv_min + ", nb_min=" + nb_min
				+ ", nb_max=" + nb_max + ", obl_resus=" + obl_resus + "]";
	}
	
}
