package prog.tache.excel;

import prog.xmlClasse.Epreuve;

/**
 * Classe correspondant aux informations utiles à la création des fichiers excel concernant les {@link Epreuve}.
 * @author ronan
 *
 */
public class ResultatExcel implements Comparable<ResultatExcel> {

	private String epreuve, dossard, nomche, sire, nom, licence, perche, dNaiss, numCmpt, equide_type_eng, hors_classement,
		sexe, robe, age, gains, race, pere, mere, pereMere, naisseur, proprietaire;
	
	/**
	 * 
	 */
	public ResultatExcel() {
		this.epreuve = "";
		this.dossard = "";
		this.nomche = "";
		this.sire = "";
		this.nom = "";
		this.licence = "";
		this.perche = "";
		this.dNaiss = "";
		this.numCmpt = "";
		this.equide_type_eng = "";
		this.hors_classement = "";
		this.sexe = "";
		this.robe = "";
		this.age = "";
		this.gains = "";
		this.race = "";
		this.pere = "";
		this.mere = "";
		this.pereMere = "";
		this.naisseur = "";
		this.proprietaire = "";
	}

	/**
	 * 
	 * @param epreuve
	 * @param dossard
	 * @param nomche
	 * @param sire
	 * @param nom
	 * @param licence
	 * @param perche
	 * @param dNaiss
	 * @param numCmpt
	 * @param equide_type_eng
	 * @param hors_classement
	 * @param sexe
	 * @param robe
	 * @param age
	 * @param gains
	 * @param race
	 * @param pere
	 * @param mere
	 * @param pereMere
	 * @param naisseur
	 * @param proprietaire
	 */
	public ResultatExcel(String epreuve, String dossard, String nomche, String sire, String nom, String licence,
			String perche, String dNaiss, String numCmpt, String equide_type_eng, String hors_classement,
			String sexe, String robe, String age, String gains, String race, String pere, String mere, String pereMere, String naisseur, String proprietaire) {
		this.epreuve = epreuve;
		this.dossard = dossard;
		this.nomche = nomche;
		this.sire = sire;
		this.nom = nom;
		this.licence = licence;
		this.perche = perche;
		this.dNaiss = dNaiss;
		this.numCmpt = numCmpt;
		this.equide_type_eng = equide_type_eng;
		this.hors_classement = hors_classement;
		this.sexe = sexe;
		this.robe = robe;
		this.age = age;
		this.gains = gains;
		this.race = race;
		this.pere = pere;
		this.mere = mere;
		this.pereMere = pereMere;
		this.naisseur = naisseur;
		this.proprietaire = proprietaire;
	}
	
	public String getGains() {
		return gains;
	}

	public void setGains(String gains) {
		this.gains = gains;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getRobe() {
		return robe;
	}

	public void setRobe(String robe) {
		this.robe = robe;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getRace() {
		return this.race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}

	public String getPere() {
		return pere;
	}

	public void setPere(String pere) {
		this.pere = pere;
	}

	public String getMere() {
		return mere;
	}

	public void setMere(String mere) {
		this.mere = mere;
	}

	public String getPereMere() {
		return pereMere;
	}

	public void setPereMere(String pereMere) {
		this.pereMere = pereMere;
	}

	public String getNaisseur() {
		return naisseur;
	}

	public void setNaisseur(String naisseur) {
		this.naisseur = naisseur;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	/**
	 * 
	 * @return
	 */
	public String getFormatDossard() {
		StringBuilder strB = new StringBuilder().append(dossard);
		for(int i = strB.length(); i < 3; i++) {
			strB.insert(0, "0");
		}
		return strB.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFormatLicence() {
		return licence.replaceAll("[^\\d]", "");
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFormatSire() {
		return sire.replaceAll("[^\\d]", "");
	}

	/**
	 * 
	 * @return
	 */
	public String getEpreuve() {
		return epreuve;
	}

	/**
	 * 
	 * @param epreuve
	 */
	public void setEpreuve(String epreuve) {
		this.epreuve = epreuve;
	}

	/**
	 * 
	 * @return
	 */
	public String getDossard() {
		return this.getFormatDossard();
	}

	/**
	 * 
	 * @param dossard
	 */
	public void setDossard(String dossard) {
		this.dossard = dossard;
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
		return this.getFormatSire();
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
		return this.getFormatLicence();
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

	public String getdNaiss() {
		return dNaiss;
	}

	/**
	 * 
	 * @param dNaiss
	 */
	public void setdNaiss(String dNaiss) {
		this.dNaiss = dNaiss;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumCmpt() {
		return numCmpt;
	}

	/**
	 * 
	 * @param numCmpt
	 */
	public void setNumCmpt(String numCmpt) {
		this.numCmpt = numCmpt;
	}

	/**
	 * 
	 * @return
	 */
	public String getEquide_type_eng() {
		return equide_type_eng;
	}

	/**
	 * 
	 * @param equide_type_eng
	 */
	public void setEquide_type_eng(String equide_type_eng) {
		this.equide_type_eng = equide_type_eng;
	}

	/**
	 * 
	 * @return
	 */
	public String getHors_classement() {
		return hors_classement;
	}

	/**
	 * 
	 * @param hors_classement
	 */
	public void setHors_classement(String hors_classement) {
		this.hors_classement = hors_classement;
	}

	@Override
	public String toString() {
		return "Resultat [epreuve=" + epreuve + ", dossard=" + dossard + ", nomche=" + nomche + ", sire=" + sire
				+ ", nom=" + nom + ", licence=" + licence + ", perche=" + perche + ", dNaiss="
				+ dNaiss + ", numCmpt=" + numCmpt + "]";
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((dNaiss == null) ? 0 : dNaiss.hashCode());
		result = prime * result + ((dossard == null) ? 0 : dossard.hashCode());
		result = prime * result + ((epreuve == null) ? 0 : epreuve.hashCode());
		result = prime * result + ((equide_type_eng == null) ? 0 : equide_type_eng.hashCode());
		result = prime * result + ((gains == null) ? 0 : gains.hashCode());
		result = prime * result + ((hors_classement == null) ? 0 : hors_classement.hashCode());
		result = prime * result + ((licence == null) ? 0 : licence.hashCode());
		result = prime * result + ((mere == null) ? 0 : mere.hashCode());
		result = prime * result + ((naisseur == null) ? 0 : naisseur.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((nomche == null) ? 0 : nomche.hashCode());
		result = prime * result + ((numCmpt == null) ? 0 : numCmpt.hashCode());
		result = prime * result + ((perche == null) ? 0 : perche.hashCode());
		result = prime * result + ((pere == null) ? 0 : pere.hashCode());
		result = prime * result + ((pereMere == null) ? 0 : pereMere.hashCode());
		result = prime * result + ((proprietaire == null) ? 0 : proprietaire.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((robe == null) ? 0 : robe.hashCode());
		result = prime * result + ((sexe == null) ? 0 : sexe.hashCode());
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
		ResultatExcel other = (ResultatExcel) obj;
		if (dNaiss == null) {
			if (other.dNaiss != null)
				return false;
		} 
		else if (!dNaiss.equals(other.dNaiss))
			return false;
		if (dossard == null) {
			if (other.dossard != null)
				return false;
		} 
		else if (!dossard.equals(other.dossard))
			return false;
		if (epreuve == null) {
			if (other.epreuve != null)
				return false;
		} 
		else if (!epreuve.equals(other.epreuve))
			return false;
		if (licence == null) {
			if (other.licence != null)
				return false;
		} 
		else if (!licence.equals(other.licence))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} 
		else if (!nom.equals(other.nom))
			return false;
		if (nomche == null) {
			if (other.nomche != null)
				return false;
		} 
		else if (!nomche.equals(other.nomche))
			return false;
		if (numCmpt == null) {
			if (other.numCmpt != null)
				return false;
		} 
		else if (!numCmpt.equals(other.numCmpt))
			return false;
		if (perche == null) {
			if (other.perche != null)
				return false;
		} 
		else if (!perche.equals(other.perche))
			return false;
		if (sire == null) {
			if (other.sire != null)
				return false;
		} 
		else if (!sire.equals(other.sire))
			return false;
		return true;
	}

	@Override
    public int compareTo(ResultatExcel res) {
		Integer numD1, numD2;
		numD1 = Integer.parseInt(this.getFormatDossard());
		numD2 = Integer.parseInt(res.getFormatDossard());
		return numD1.compareTo(numD2);
    }
	
}
