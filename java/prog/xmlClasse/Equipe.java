package prog.xmlClasse;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'équipe dans le fichier XML.
 * @author ronan
 *
 */
public class Equipe {
	
	private String nom_equipe;
	private Engageur engageur;
	private Coach coach;
	private Epreuve epreuve;
	
	private List<Membre> listeMembres = new ArrayList<>();

	/**
	 * 
	 */
	public Equipe() {
		
	}
	
	/**
	 * 
	 * @param nom_equipe
	 * @param engageur
	 * @param coach
	 */
	public Equipe(String nom_equipe, Engageur engageur, Coach coach) {
		super();
		this.nom_equipe = nom_equipe;
		this.engageur = engageur;
		this.coach = coach;
	}

	public String getNom_equipe() {
		return nom_equipe;
	}

	public void setNom_equipe(String nom_equipe) {
		this.nom_equipe = nom_equipe;
	}

	public Engageur getEngageur() {
		return engageur;
	}

	public void setEngageur(Engageur engageur) {
		this.engageur = engageur;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	public Epreuve getEpreuve() {
		return this.epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	public List<Membre> getListeMembres() {
		return listeMembres;
	}

	@Override
	public String toString() {
		String res = "Equipe [nom_equipe=" + nom_equipe + ", engageur=" + engageur + ", coach=" + coach + ", listeMembres={";
		for(Membre mbr : listeMembres)
			res += mbr;
		res += "}]";
		return res;
	}
	
	
	
}
