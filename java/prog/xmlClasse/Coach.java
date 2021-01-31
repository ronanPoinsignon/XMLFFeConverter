package prog.xmlClasse;

/**
 * Classe représentant le coach dans le fichier XML.
 * @author ronan
 *
 */
public class Coach {
	
	private String lic, nom, prenom;
	private Engagement engagement;
	private Equipe equipe;
	
	/**
	 * 
	 */
	public Coach() {
		
	}
	
	/**
	 * 
	 * @param lic
	 * @param nom
	 * @param prenom
	 */
	public Coach(String lic, String nom, String prenom) {
		this.lic = lic;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getLic() {
		return lic;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	
	public Engagement getEngagement() {
		return this.engagement;
	}
	
	public Equipe equipe() {
		return this.equipe;
	}

	public void setLic(String lic) {
		this.lic = lic;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void setEngagement(Engagement engagement) {
		this.engagement = engagement;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@Override
	public String toString() {
		return "Coach [lic=" + lic + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
