package prog.xmlClasse;

/**
 * Classe représentant la mère dans le fichier XML.
 * @author ronan
 *
 */
public class Mere {
	
	private String nom, race_code, race;
	private Pere pere;
	private Equide equide;
	
	/**
	 * 
	 */
	public Mere() {
		
	}

	/**
	 * 
	 * @param nom
	 * @param race_code
	 * @param race
	 * @param pere
	 */
	public Mere(String nom, String race_code, String race, Pere pere) {
		this.nom = nom;
		this.race_code = race_code;
		this.race = race;
		this.pere = pere;
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

	public Pere getPere() {
		return pere;
	}

	public void setPere(Pere pere) {
		this.pere = pere;
	}
	
	public Equide getEquide() {
		return this.equide;
	}

	public void setEquide(Equide equide) {
		this.equide = equide;
	}

	@Override
	public String toString() {
		return "Mere [nom=" + nom + ", race_code=" + race_code + ", race=" + race + ", pere=" + pere + "]";
	}
	
}
