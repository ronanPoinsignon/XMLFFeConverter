package prog.xmlClasse;

/**
 * Classe représentant le cavalier dans le fichier XML.
 * @author ronan
 *
 */
public class Cavalier {
	
	private String lic, numero_fei, titre_cavalier, nom, prenom, dnaiss, club, nom_club, cre,
		region, nom_region, departement_cavalier, nom_departement_cavalier, categorie, code_age, libelle_age;
	private Engagement engagement;
	private Membre membre;
	
	/**
	 * 
	 */
	public Cavalier() {
		
	}
	
	/**
	 * 
	 * @param lic
	 * @param numero_fei
	 * @param titre_cavalier
	 * @param nom
	 * @param prenom
	 * @param dnaiss
	 * @param club
	 * @param nom_club
	 * @param cre
	 * @param region
	 * @param nom_region
	 * @param departement_cavalier
	 * @param nom_departement_cavalier
	 * @param categorie
	 * @param code_age
	 * @param libelle_age
	 */
	public Cavalier(String lic, String numero_fei, String titre_cavalier, String nom, String prenom, String dnaiss,
			String club, String nom_club, String cre, String region, String nom_region, String departement_cavalier,
			String nom_departement_cavalier, String categorie, String code_age, String libelle_age) {
		this.lic = lic;
		this.numero_fei = numero_fei;
		this.titre_cavalier = titre_cavalier;
		this.nom = nom;
		this.prenom = prenom;
		this.dnaiss = dnaiss;
		this.club = club;
		this.nom_club = nom_club;
		this.cre = cre;
		this.region = region;
		this.nom_region = nom_region;
		this.departement_cavalier = departement_cavalier;
		this.nom_departement_cavalier = nom_departement_cavalier;
		this.categorie = categorie;
		this.code_age = code_age;
		this.libelle_age = libelle_age;
	}
	
	/**
	 * Retourne le formatage correcte du nom (titre + nom + prénom).
	 * @return
	 */
	public String getNomPrenom() {
		return (this.titre_cavalier + " " +  this.nom + " " + this.prenom).toUpperCase();
	}
	
	
	/**
	 * Retourne un format de nom complet (titre + prénom + nom);
	 * @return
	 */
	public String getPrenomNom() {
		return (this.titre_cavalier + " " +  this.prenom + " " + this.nom).toUpperCase();
	}

	public String getLic() {
		return lic;
	}

	public String getNumero_fei() {
		return numero_fei;
	}

	public String getTitre_cavalier() {
		return titre_cavalier;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getDnaiss() {
		return dnaiss;
	}

	public String getClub() {
		return club;
	}

	public String getNom_club() {
		return nom_club;
	}

	public String getCre() {
		return cre;
	}

	public String getRegion() {
		return region;
	}

	public String getNom_region() {
		return nom_region;
	}

	public String getDepartement_cavalier() {
		return departement_cavalier;
	}

	public String getNom_departement_cavalier() {
		return nom_departement_cavalier;
	}

	public String getCategorie() {
		return categorie;
	}

	public String getCode_age() {
		return code_age;
	}

	public String getLibelle_age() {
		return libelle_age;
	}
	
	public Engagement getEngagement() {
		return this.engagement;
	}
	
	public Membre getMembre() {
		return this.membre;
	}

	public void setLic(String lic) {
		this.lic = lic;
	}

	public void setNumero_fei(String numero_fei) {
		this.numero_fei = numero_fei;
	}

	public void setTitre_cavalier(String titre_cavalier) {
		this.titre_cavalier = titre_cavalier;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setDnaiss(String dnaiss) {
		this.dnaiss = dnaiss;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public void setNom_club(String nom_club) {
		this.nom_club = nom_club;
	}

	public void setCre(String cre) {
		this.cre = cre;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setNom_region(String nom_region) {
		this.nom_region = nom_region;
	}

	public void setDepartement_cavalier(String departement_cavalier) {
		this.departement_cavalier = departement_cavalier;
	}

	public void setNom_departement_cavalier(String nom_departement_cavalier) {
		this.nom_departement_cavalier = nom_departement_cavalier;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setCode_age(String code_age) {
		this.code_age = code_age;
	}

	public void setLibelle_age(String libelle_age) {
		this.libelle_age = libelle_age;
	}
	
	public void setEngagement(Engagement engagement) {
		this.engagement = engagement;
	}
	
	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	@Override
	public String toString() {
		return "Cavalier [lic=" + lic + ", numero_fei=" + numero_fei + ", titre_cavalier=" + titre_cavalier + ", nom="
				+ nom + ", prenom=" + prenom + ", dnaiss=" + dnaiss + ", club=" + club + ", nom_club=" + nom_club
				+ ", cre=" + cre + ", region=" + region + ", nom_region=" + nom_region + ", departement_cavalier="
				+ departement_cavalier + ", nom_departement_cavalier=" + nom_departement_cavalier + ", categorie="
				+ categorie + ", code_age=" + code_age + ", libelle_age=" + libelle_age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((club == null) ? 0 : club.hashCode());
		result = prime * result + ((code_age == null) ? 0 : code_age.hashCode());
		result = prime * result + ((cre == null) ? 0 : cre.hashCode());
		result = prime * result + ((departement_cavalier == null) ? 0 : departement_cavalier.hashCode());
		result = prime * result + ((dnaiss == null) ? 0 : dnaiss.hashCode());
		result = prime * result + ((engagement == null) ? 0 : engagement.hashCode());
		result = prime * result + ((libelle_age == null) ? 0 : libelle_age.hashCode());
		result = prime * result + ((lic == null) ? 0 : lic.hashCode());
		result = prime * result + ((membre == null) ? 0 : membre.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((nom_club == null) ? 0 : nom_club.hashCode());
		result = prime * result + ((nom_departement_cavalier == null) ? 0 : nom_departement_cavalier.hashCode());
		result = prime * result + ((nom_region == null) ? 0 : nom_region.hashCode());
		result = prime * result + ((numero_fei == null) ? 0 : numero_fei.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((titre_cavalier == null) ? 0 : titre_cavalier.hashCode());
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
		Cavalier cavalier = (Cavalier) obj;
		return this.getLic().equals(cavalier.getLic()) && this.getNumero_fei().equals(cavalier.getNumero_fei())
				&& this.getTitre_cavalier().equals(cavalier.getTitre_cavalier()) && this.getNom().equals(cavalier.getNom())
				&& this.getPrenom().equals(cavalier.getPrenom()) && this.getDnaiss().equals(cavalier.getDnaiss())
				&& this.getClub().equals(cavalier.getClub()) && this.getNom_club().equals(cavalier.getNom_club())
				&& this.getCre().equals(cavalier.getCre()) && this.getRegion().equals(cavalier.getRegion())
				&& this.getNom_region().equals(cavalier.getNom_region()) && this.getDepartement_cavalier().equals(cavalier.getDepartement_cavalier())
				&& this.getNom_departement_cavalier().equals(cavalier.getNom_departement_cavalier()) && this.getCategorie().equals(cavalier.getCategorie())
				&& this.getCode_age().equals(cavalier.getCode_age()) && this.getLibelle_age().equals(cavalier.getLibelle_age());
	}
	
}
