package prog.xmlClasse;

/**
 * Classe représentant le membre dans le fichier XML.
 * @author ronan
 *
 */
public class Membre {
	
	private String id, dossard, role, equide_etat_eng, equide_type_eng;
	private Cavalier cavalier;
	private Equide equide;
	private Equipe equipe;
	
	/**
	 * 
	 */
	public Membre() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param dossard
	 * @param role
	 * @param equide_etat_eng
	 * @param equide_type_eng
	 * @param cavalier
	 * @param equide
	 */
	public Membre(String id, String dossard, String role, String equide_etat_eng, String equide_type_eng,
			Cavalier cavalier, Equide equide) {
		super();
		this.id = id;
		this.dossard = dossard;
		this.role = role;
		this.equide_etat_eng = equide_etat_eng;
		this.equide_type_eng = equide_type_eng;
		this.cavalier = cavalier;
		this.equide = equide;
	}
	
	/**
	 * Formate le dossard.
	 * @return
	 */
	public String getFormatDossard() {
		String doss = dossard;
		for(int i = doss.length(); i < 3; i++) {
			doss = "0" + doss;
		}
		return doss;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDossard() {
		return dossard;
	}

	public void setDossard(String dossard) {
		this.dossard = dossard;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEquide_etat_eng() {
		return equide_etat_eng;
	}

	public void setEquide_etat_eng(String equide_etat_eng) {
		this.equide_etat_eng = equide_etat_eng;
	}

	public String getEquide_type_eng() {
		return equide_type_eng;
	}

	public void setEquide_type_eng(String equide_type_eng) {
		this.equide_type_eng = equide_type_eng;
	}

	public Cavalier getCavalier() {
		return cavalier;
	}

	public void setCavalier(Cavalier cavalier) {
		this.cavalier = cavalier;
	}

	public Equide getEquide() {
		return equide;
	}

	public void setEquide(Equide equide) {
		this.equide = equide;
	}
	
	public Equipe getEquipe() {
		return this.equipe;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@Override
	public String toString() {
		return "Membre [id=" + id + ", dossard=" + dossard + ", role=" + role + ", equide_etat_eng=" + equide_etat_eng
				+ ", equide_type_eng=" + equide_type_eng + ", cavalier=" + cavalier + ", equide=" + equide + "]";
	}
	
	
}
