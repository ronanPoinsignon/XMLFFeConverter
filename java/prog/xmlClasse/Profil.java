package prog.xmlClasse;

/**
 * Classe représentant le profil dans le fichier XML.
 * @author ronan
 *
 */
public class Profil {
	
	private Officiels officiels;
	private Epreuve epreuve;
	
	/**
	 * 
	 */
	public Profil() {
		
	}
	
	/**
	 * 
	 * @param officiels
	 */
	public Profil(Officiels officiels) {
		this.officiels = officiels;
	}

	public Officiels getOfficiels() {
		return officiels;
	}
	
	public Epreuve getEpreuve() {
		return this.epreuve;
	}

	public void setOfficiels(Officiels officiels) {
		this.officiels = officiels;
	}
	
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	@Override
	public String toString() {
		return "Profil [officiels=" + officiels + "]";
	}
	
}
