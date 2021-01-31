package prog.xmlClasse;

/**
 * Classe représentant l'engageur dans le fichier XML.
 * @author ronan
 *
 */
public class Engageur {
	
	private String type_engageur, nom, prenom, num;
	private Engagement engagement;
	private Equipe equipe;
	
	/**
	 * 
	 */
	public Engageur() {
		
	}
	
	/**
	 * 
	 * @param type_engageur
	 * @param nom
	 * @param prenom
	 * @param num
	 */
	public Engageur(String type_engageur, String nom, String prenom, String num) {
		this.type_engageur = type_engageur;
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
	}

	public String getType_engageur() {
		return type_engageur;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNum() {
		return num;
	}
	
	public Engagement getEngagement() {
		return engagement;
	}
	
	public Equipe getEquipe() {
		return this.equipe;
	}

	public void setType_engageur(String type_engageur) {
		this.type_engageur = type_engageur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public void setEngagement(Engagement engagement) {
		this.engagement = engagement;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@Override
	public String toString() {
		return "Engageur [type_engageur=" + type_engageur + ", nom=" + nom + ", prenom=" + prenom + ", num=" + num
				+ "]";
	}
	
}
