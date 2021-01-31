package prog.tache.listeInfo;

import prog.xmlClasse.Cavalier;

/**
 * Classe représentant les informations utiles à la création da la liste des {@link Cavalier}.
 * @author ronan
 *
 */
public class InfoCavalier implements Comparable<InfoCavalier> {

	private String nom, licence, dNaiss, categorie, libelle_age, 
		nom_departement_cavalier, departement_cavalier, nom_region, region, cre, nom_club;

	/**
	 * 
	 */
	public InfoCavalier() {
		this.nom = "";
		this.licence = "";
		this.dNaiss = "";
		this.categorie = "";
		this.libelle_age = "";
		this.nom_departement_cavalier = "";
		this.departement_cavalier = "";
		this.nom_region = "";
		this.region = "";
		this.cre = "";
		this.nom_club = "";
	}
	
	/**
	 * 
	 * @param nom
	 * @param licence
	 * @param dNaiss
	 * @param categorie
	 * @param libelle_age
	 * @param nom_departement_cavalier
	 * @param departement_cavalier
	 * @param nom_region
	 * @param region
	 * @param cre
	 * @param nom_club
	 */
	public InfoCavalier(String nom, String licence, String dNaiss, String categorie, String libelle_age,
			String nom_departement_cavalier, String departement_cavalier, String nom_region, String region, String cre,
			String nom_club) {
		this.nom = nom;
		this.licence = licence;
		this.dNaiss = dNaiss;
		this.categorie = categorie;
		this.libelle_age = libelle_age;
		this.nom_departement_cavalier = nom_departement_cavalier;
		this.departement_cavalier = departement_cavalier;
		this.nom_region = nom_region;
		this.region = region;
		this.cre = cre;
		this.nom_club = nom_club;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getdNaiss() {
		return dNaiss;
	}

	public void setdNaiss(String dNaiss) {
		this.dNaiss = dNaiss;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getLibelle_age() {
		return libelle_age;
	}

	public void setLibelle_age(String libelle_age) {
		this.libelle_age = libelle_age;
	}

	public String getNom_departement_cavalier() {
		return nom_departement_cavalier;
	}

	public void setNom_departement_cavalier(String nom_departement_cavalier) {
		this.nom_departement_cavalier = nom_departement_cavalier;
	}

	public String getDepartement_cavalier() {
		return departement_cavalier;
	}

	public void setDepartement_cavalier(String departement_cavalier) {
		this.departement_cavalier = departement_cavalier;
	}

	public String getNom_region() {
		return nom_region;
	}

	public void setNom_region(String nom_region) {
		this.nom_region = nom_region;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCre() {
		return cre;
	}

	public void setCre(String cre) {
		this.cre = cre;
	}

	public String getNom_club() {
		return nom_club;
	}

	public void setNom_club(String nom_club) {
		this.nom_club = nom_club;
	}

	@Override
	public String toString() {
		return "InfoCavalier [nom=" + nom + ", licence=" + licence + ", dNaiss=" + dNaiss + ", categorie=" + categorie
				+ ", libelle_age=" + libelle_age + ", nom_departement_cavalier=" + nom_departement_cavalier
				+ ", departement_cavalier=" + departement_cavalier + ", nom_region=" + nom_region + ", region=" + region
				+ ", cre=" + cre + ", nom_club=" + nom_club + "]";
	}

	@Override
	public int compareTo(InfoCavalier o) {
		return this.getNom().compareTo(o.getNom());
	}
	
}
