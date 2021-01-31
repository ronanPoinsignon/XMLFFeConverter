package prog.tache.listeInfo;

import prog.xmlClasse.Equide;

/**
 * Classe représentant les informations utiles à la création du fichier correspondant à la liste des {@link Equide}.
 * @author ronan
 *
 */
public class InfoEquide implements Comparable<InfoEquide> {

	private String nomChe, sire, numCmpt, sexe, code_robe, equide_age, equide_gain, code_race, race,
		nomPere, nomMere, nomPereMere, eleveur, proprietaire;
	
	/**
	 * 
	 */
	public InfoEquide() {
		this.nomChe = "";
		this.sire = "";
		this.numCmpt = "";
		this.sexe = "";
		this.code_robe = "";
		this.equide_age = "";
		this.equide_gain = "";
		this.code_race = "";
		this.race = "";
		this.nomPere = "";
		this.nomMere = "";
		this.nomPereMere = "";
		this.eleveur = "";
		this.proprietaire = "";
	}
	
	/**
	 * 
	 * @param nomChe
	 * @param sire
	 * @param numCmpt
	 * @param sexe
	 * @param code_robe
	 * @param equide_age
	 * @param equide_gain
	 * @param code_race
	 * @param race
	 * @param nomPere
	 * @param nomMere
	 * @param nomPereMere
	 * @param eleveur
	 * @param proprietaire
	 */
	public InfoEquide(String nomChe, String sire, String numCmpt, String sexe, String code_robe, String equide_age,
			String equide_gain, String code_race, String race, String nomPere, String nomMere, String nomPereMere,
			String eleveur, String proprietaire) {
		this.nomChe = nomChe;
		this.sire = sire;
		this.numCmpt = numCmpt;
		this.sexe = sexe;
		this.code_robe = code_robe;
		this.equide_age = equide_age;
		this.equide_gain = equide_gain;
		this.code_race = code_race;
		this.race = race;
		this.nomPere = nomPere;
		this.nomMere = nomMere;
		this.nomPereMere = nomPereMere;
		this.eleveur = eleveur;
		this.proprietaire = proprietaire;
	}

	public String getNomChe() {
		return nomChe;
	}

	public void setNomChe(String nomChe) {
		this.nomChe = nomChe;
	}

	public String getSire() {
		return sire;
	}

	public void setSire(String sire) {
		this.sire = sire;
	}

	public String getNumCmpt() {
		return numCmpt;
	}

	public void setNumCmpt(String numCmpt) {
		this.numCmpt = numCmpt;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getCode_robe() {
		return code_robe;
	}

	public void setCode_robe(String code_robe) {
		this.code_robe = code_robe;
	}

	public String getEquide_age() {
		return equide_age;
	}

	public void setEquide_age(String equide_age) {
		this.equide_age = equide_age;
	}

	public String getEquide_gain() {
		return equide_gain;
	}

	public void setEquide_gain(String equide_gain) {
		this.equide_gain = equide_gain;
	}

	public String getCode_race() {
		return code_race;
	}

	public void setCode_race(String code_race) {
		this.code_race = code_race;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getNomPere() {
		return nomPere;
	}

	public void setNomPere(String nomPere) {
		this.nomPere = nomPere;
	}

	public String getNomMere() {
		return nomMere;
	}

	public void setNomMere(String nomMere) {
		this.nomMere = nomMere;
	}

	public String getEleveur() {
		return eleveur;
	}

	public void setEleveur(String eleveur) {
		this.eleveur = eleveur;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getNomPereMere() {
		return nomPereMere;
	}

	public void setNomPereMere(String nomPereMere) {
		this.nomPereMere = nomPereMere;
	}

	@Override
	public String toString() {
		return "InfoEquide [nomChe=" + nomChe + ", sire=" + sire + ", numCmpt=" + numCmpt + ", sexe=" + sexe
				+ ", code_robe=" + code_robe + ", equide_age=" + equide_age + ", equide_gain=" + equide_gain
				+ ", code_race=" + code_race + ", race=" + race + ", nomPere=" + nomPere + ", nomMere=" + nomMere
				+ ", eleveur=" + eleveur + ", proprietaire=" + proprietaire + "]";
	}

	@Override
	public int compareTo(InfoEquide o) {
		return this.nomChe.compareTo(o.getNomChe());
	}
	
}
