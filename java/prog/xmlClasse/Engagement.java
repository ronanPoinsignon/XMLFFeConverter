package prog.xmlClasse;

/**
 * Classe représentant l'engagement dans le fichier XML.
 * @author ronan
 *
 */
public class Engagement {
	
	private String id, dossard, hors_classement, role, equide_type_eng, equide_etat_eng;
	private Cavalier cavalier;
	private Equide equide;
	private Engageur engageur;
	private Coach coach;
	private Epreuve epreuve;
	
	/**
	 * 
	 */
	public Engagement() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param dossard
	 * @param hors_classement
	 * @param role
	 * @param equide_type_eng
	 * @param equide_etat_eng
	 * @param cavalier
	 * @param equide
	 * @param engageur
	 * @param coach
	 */
	public Engagement(String id, String dossard, String hors_classement, String role, String equide_type_eng,
			String equide_etat_eng, Cavalier cavalier, Equide equide, Engageur engageur, Coach coach) {
		this.id = id;
		this.dossard = dossard;
		this.hors_classement = hors_classement;
		this.role = role;
		this.equide_type_eng = equide_type_eng;
		this.equide_etat_eng = equide_etat_eng;
		this.cavalier = cavalier;
		this.equide = equide;
		this.engageur = engageur;
		this.coach = coach;
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

	public String getId() {
		return id;
	}

	public String getDossard() {
		return dossard;
	}

	public String getHors_classement() {
		return hors_classement;
	}

	public String getRole() {
		return role;
	}

	public String getEquide_type_eng() {
		return equide_type_eng;
	}

	public String getEquide_etat_eng() {
		return equide_etat_eng;
	}

	public Cavalier getCavalier() {
		return cavalier;
	}

	public Equide getEquide() {
		return equide;
	}

	public Engageur getEngageur() {
		return engageur;
	}

	public Coach getCoach() {
		return coach;
	}
	
	public Epreuve getEpreuve() {
		return this.epreuve;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDossard(String dossard) {
		this.dossard = dossard;
	}

	public void setHors_classement(String hors_classement) {
		this.hors_classement = hors_classement;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEquide_type_eng(String equide_type_eng) {
		this.equide_type_eng = equide_type_eng;
	}

	public void setEquide_etat_eng(String equide_etat_eng) {
		this.equide_etat_eng = equide_etat_eng;
	}

	public void setCavalier(Cavalier cavalier) {
		this.cavalier = cavalier;
	}

	public void setEquide(Equide equide) {
		this.equide = equide;
	}

	public void setEngageur(Engageur engageur) {
		this.engageur = engageur;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	@Override
	public String toString() {
		return "Engagement [id=" + id + ", dossard=" + dossard + ", hors_classement=" + hors_classement + ", role="
				+ role + ", equide_type_eng=" + equide_type_eng + ", equide_etat_eng=" + equide_etat_eng + ", cavalier="
				+ cavalier + ", equide=" + equide + ", engageur=" + engageur + ", coach=" + coach + "]";
	}
	
	
}
