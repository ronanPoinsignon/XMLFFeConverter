package prog.xmlClasse;

/**
 * Classe représentant le père dans le fichier XML.
 * @author ronan
 *
 */
public class Pere {
	
	private String nom, race_code, race;
	private Equide equide;
	
	/**
	 * 
	 */
	public Pere() {
		
	}

	/**
	 * 
	 * @param nom
	 * @param race_code
	 * @param race
	 */
	public Pere(String nom, String race_code, String race) {
		this.nom = nom;
		this.race_code = race_code;
		this.race = race;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRace_code() {
		return race_code;
	}

	public void setRace_code(String race_code) {
		this.race_code = race_code;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	public Equide getEquide() {
		return this.equide;
	}

	public void setEquide(Equide equide) {
		this.equide = equide;
	}
	
	@Override
	public String toString() {
		return "Pere [nom=" + nom + ", race_code=" + race_code + ", race=" + race + "]";
	}

}
