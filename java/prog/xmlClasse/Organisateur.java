package prog.xmlClasse;

/**
 * Classe représentant l'organisateur dans le fichier XML.
 * @author ronan
 *
 */
public class Organisateur {
	
	private String num, nom;
	private Concours concours;
	
	/**
	 * 
	 */
	public Organisateur() {
		
	}
	
	/**
	 * 
	 * @param num
	 * @param nom
	 */
	public Organisateur(String num, String nom) {
		this.num = num;
		this.nom = nom;
	}

	public String getNum() {
		return num;
	}

	public String getNom() {
		return nom;
	}
	
	public Concours getConcours() {
		return this.concours;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setConcours(Concours concours) {
		this.concours = concours;
	}

	@Override
	public String toString() {
		return "Organisateur [num=" + num + ", nom=" + nom + "]";
	}
	
}
