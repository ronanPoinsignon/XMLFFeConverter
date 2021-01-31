package prog.tache.etiquette;

/**
 * Classe représentant les informations utiles à la création du fichier texte correspondant aux étiquettes.
 * @author ronan
 *
 */
public class ResultatTxtEtiquette implements Comparable<ResultatTxtEtiquette> {
	
	private String lieu, numEpreuve, classe, eng, numdep, nomche, sire, gains, perche, nom, licence, ligue, categorie, junsen, clucav;
	
	/**
	 * 
	 */
	public ResultatTxtEtiquette() {
		this.lieu = "";
		this.numEpreuve = "";
		this.classe = "";
		this.eng = "";
		this.numdep = "";
		this.sire = "";
		this.gains = "";
		this.perche = "";
		this.nom = "";
		this.licence = "";
		this.ligue = "";
		this.categorie = "";
		this.junsen = "";
		this.clucav = "";
	}

	/**
	 * 
	 * @param lieu
	 * @param epreuve
	 * @param classe
	 * @param eng
	 * @param numdep
	 * @param nomche
	 * @param sire
	 * @param gains
	 * @param perche
	 * @param nom
	 * @param licence
	 * @param ligue
	 * @param categorie
	 * @param junsen
	 * @param clucav
	 */
	public ResultatTxtEtiquette(String lieu, String epreuve, String classe, String eng, String numdep, String nomche,
			String sire, String gains, String perche, String nom, String licence, String ligue, String categorie,
			String junsen, String clucav) {
		this.lieu = lieu;
		this.numEpreuve = epreuve;
		this.classe = classe;
		this.eng = eng;
		this.numdep = numdep;
		this.nomche = nomche;
		this.sire = sire;
		this.gains = gains;
		this.perche = perche;
		this.nom = nom;
		this.licence = licence;
		this.ligue = ligue;
		this.categorie = categorie;
		this.junsen = junsen;
		this.clucav = clucav;
	}
	
	/**
	 * Retourne le numéro de l'épreuve avec un format correct.
	 * @return
	 */
	public String getFormatEpreuve() {
		StringBuilder strB = new StringBuilder().append(numEpreuve);
		while(strB.toString().length() < 3)
			strB.insert(0, "0");
		return strB.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getLieu() {
		return lieu;
	}

	/**
	 * 
	 * @param lieu
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumEpreuve() {
		return numEpreuve;
	}

	/**
	 * 
	 * @param numEpreuve
	 */
	public void setEpreuve(String numEpreuve) {
		this.numEpreuve = numEpreuve;
	}

	/**
	 * 
	 * @return
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * 
	 * @param classe
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * 
	 * @return
	 */
	public String getEng() {
		return eng;
	}

	/**
	 * 
	 * @param eng
	 */
	public void setEng(String eng) {
		this.eng = eng;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumdep() {
		return numdep;
	}

	/**
	 * 
	 * @param numdep
	 */
	public void setNumdep(String numdep) {
		this.numdep = numdep;
	}

	/**
	 * 
	 * @return
	 */
	public String getNomche() {
		return nomche;
	}

	/**
	 * 
	 * @param nomche
	 */
	public void setNomche(String nomche) {
		this.nomche = nomche;
	}

	/**
	 * 
	 * @return
	 */
	public String getSire() {
		return sire;
	}

	/**
	 * 
	 * @param sire
	 */
	public void setSire(String sire) {
		this.sire = sire;
	}

	/**
	 * 
	 * @return
	 */
	public String getGains() {
		return gains;
	}

	/**
	 * 
	 * @param gains
	 */
	public void setGains(String gains) {
		this.gains = gains;
	}

	/**
	 * 
	 * @return
	 */
	public String getPerche() {
		return perche;
	}

	/**
	 * 
	 * @param perche
	 */
	public void setPerche(String perche) {
		this.perche = perche;
	}

	/**
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @return
	 */
	public String getLicence() {
		return licence;
	}

	/**
	 * 
	 * @param licence
	 */
	public void setLicence(String licence) {
		this.licence = licence;
	}

	/**
	 * 
	 * @return
	 */
	public String getLigue() {
		return ligue;
	}

	/**
	 * 
	 * @param ligue
	 */
	public void setLigue(String ligue) {
		this.ligue = ligue;
	}

	/**
	 * 
	 * @return
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * 
	 * @param categorie
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * 
	 * @return
	 */
	public String getJunsen() {
		return junsen;
	}

	/**
	 * 
	 * @param junsen
	 */
	public void setJunsen(String junsen) {
		this.junsen = junsen;
	}

	/**
	 * 
	 * @return
	 */
	public String getClucav() {
		return clucav;
	}

	/**
	 * 
	 * @param clucav
	 */
	public void setClucav(String clucav) {
		this.clucav = clucav;
	}

	@Override
	public int compareTo(ResultatTxtEtiquette o) {
		return (this.numEpreuve + this.numdep).compareTo(o.numEpreuve + o.numdep);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + ((clucav == null) ? 0 : clucav.hashCode());
		result = prime * result + ((eng == null) ? 0 : eng.hashCode());
		result = prime * result + ((gains == null) ? 0 : gains.hashCode());
		result = prime * result + ((junsen == null) ? 0 : junsen.hashCode());
		result = prime * result + ((licence == null) ? 0 : licence.hashCode());
		result = prime * result + ((lieu == null) ? 0 : lieu.hashCode());
		result = prime * result + ((ligue == null) ? 0 : ligue.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((nomche == null) ? 0 : nomche.hashCode());
		result = prime * result + ((numEpreuve == null) ? 0 : numEpreuve.hashCode());
		result = prime * result + ((numdep == null) ? 0 : numdep.hashCode());
		result = prime * result + ((perche == null) ? 0 : perche.hashCode());
		result = prime * result + ((sire == null) ? 0 : sire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultatTxtEtiquette other = (ResultatTxtEtiquette) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (clucav == null) {
			if (other.clucav != null)
				return false;
		} else if (!clucav.equals(other.clucav))
			return false;
		if (eng == null) {
			if (other.eng != null)
				return false;
		} else if (!eng.equals(other.eng))
			return false;
		if (gains == null) {
			if (other.gains != null)
				return false;
		} else if (!gains.equals(other.gains))
			return false;
		if (junsen == null) {
			if (other.junsen != null)
				return false;
		} else if (!junsen.equals(other.junsen))
			return false;
		if (licence == null) {
			if (other.licence != null)
				return false;
		} else if (!licence.equals(other.licence))
			return false;
		if (lieu == null) {
			if (other.lieu != null)
				return false;
		} else if (!lieu.equals(other.lieu))
			return false;
		if (ligue == null) {
			if (other.ligue != null)
				return false;
		} else if (!ligue.equals(other.ligue))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (nomche == null) {
			if (other.nomche != null)
				return false;
		} else if (!nomche.equals(other.nomche))
			return false;
		if (numEpreuve == null) {
			if (other.numEpreuve != null)
				return false;
		} else if (!numEpreuve.equals(other.numEpreuve))
			return false;
		if (numdep == null) {
			if (other.numdep != null)
				return false;
		} else if (!numdep.equals(other.numdep))
			return false;
		if (perche == null) {
			if (other.perche != null)
				return false;
		} else if (!perche.equals(other.perche))
			return false;
		if (sire == null) {
			if (other.sire != null)
				return false;
		} else if (!sire.equals(other.sire))
			return false;
		return true;
	}

}
