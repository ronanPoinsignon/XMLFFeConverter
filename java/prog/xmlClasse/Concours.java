package prog.xmlClasse;

import java.util.ArrayList;
import java.util.List;

import prog.tache.etiquette.ResultatImageEtiquette;
import prog.tache.etiquette.ResultatTxtEtiquette;
import prog.tache.excel.ResultatExcel;

/**
 * Classe représentant le concours dans le fichier XML.
 * @author ronan
 *
 */
public class Concours {
	
	private String num, nom, departement, nom_departement, date_debut, date_fin, date_cloture;
	private Organisateur organisateur;
	private List<Epreuve> listeEpreuves = new ArrayList<>();
	
	/**
	 * 
	 */
	public Concours() {
		
	}
	
	/**
	 * 
	 * @param num
	 * @param nom
	 * @param departement
	 * @param nom_departement
	 * @param date_debut
	 * @param date_fin
	 * @param date_cloture
	 */
	public Concours(String num, String nom, String departement, String nom_departement, String date_debut,
			String date_fin, String date_cloture) {
		this.num = num;
		this.nom = nom;
		this.departement = departement;
		this.nom_departement = nom_departement;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.date_cloture = date_cloture;
	}
	
	/**
	 * Retourne la liste de cavaliers du concours.
	 * @return
	 */
	public List<Cavalier> getListeCavaliers() {
		ArrayList<Cavalier> listeCavaliers = new ArrayList<>();
		boolean estDansListe = false;
		for(Epreuve epreuve : getListeEpreuves()) {
			for(Cavalier cavalier : epreuve.getListeCavaliers()) {
				estDansListe = false;
				for(Cavalier caval : listeCavaliers) {
					if(caval.equals(cavalier)) {
						estDansListe = true;
						break;
					}
				}
				if(!estDansListe)
					listeCavaliers.add(cavalier);
			}
		}
		return listeCavaliers;
	}
	
	/**
	 * Retourne la liste de chevaux du concours.
	 * @return
	 */
	public List<Equide> getListeEquides(){
		ArrayList<Equide> listeEquides = new ArrayList<>();
		boolean estDansListe = false;
		for(Epreuve epreuve : getListeEpreuves()) {
			for(Equide equide: epreuve.getListeEquides()) {
				estDansListe = false;
				for(Equide eqd : listeEquides) {
					if(equide.equals(eqd)) {
						estDansListe = true;
						break;
					}
				}
				if(!estDansListe)
					listeEquides.add(equide);
			}
		}
		return listeEquides;
	}

	/**
	 * Retourne la liste de propriétaires du concours.
	 * @return
	 */
	public List<String> getListeProprietaires() {
		List<String> listeProprietaires = new ArrayList<>();
		for(Epreuve epreuve : getListeEpreuves()) {
			for(String proprietaire : epreuve.getListeProprietaires()) {
				proprietaire = proprietaire.trim();
				if(!listeProprietaires.contains(proprietaire))
					listeProprietaires.add(proprietaire);
			}
		}
		return listeProprietaires;
	}
	
	/**
	 * Retourne la liste des informations sur les images correspondantes aux étiquettes.
	 * @return
	 */
	public List<ResultatImageEtiquette> getListeEtiquettes(){
		ArrayList<ResultatImageEtiquette> listeEtiquettes = new ArrayList<>();
		for(Epreuve epreuve : listeEpreuves) {
			for(ResultatImageEtiquette etiquette : epreuve.getListeEtiquettes())
				listeEtiquettes.add(etiquette);
		}
		return listeEtiquettes;
	}
	
	/**
	 * Retourne la liste des informations correspondantes aux fichiers excel.
	 * @return
	 */
	public List<ResultatExcel> getListeResultats(){
		List<ResultatExcel> listeResultats = new ArrayList<>();
		boolean isDedans = false;
		for(Epreuve epreuve : this.getListeEpreuves()) {
			isDedans = false;
			for(ResultatExcel resultat : epreuve.getListeResultats()) {
				for(ResultatExcel res : listeResultats) {
					if(resultat.equals(res)) {
						isDedans = true;
						break;
					}
				}
				if(!isDedans)
					listeResultats.add(resultat);
			}
		}
		return listeResultats;
	}
	
	/**
	 * Retourne la liste des informations correspondantes au fichier txt sur les étiquettes.
	 * @return
	 */
	public List<ResultatTxtEtiquette> getListeResultatsTxt(){
		List<ResultatTxtEtiquette> listeResultats = new ArrayList<>();
		for(Epreuve epreuve : listeEpreuves) {
			for(ResultatTxtEtiquette res : epreuve.getListeResultatTxt()) {
				listeResultats.add(res);
			}
		}
		return listeResultats;
	}
	
	public String getNumFormat() {
		return this.num;
	}
	
	public String getNum() {
		return num;
	}

	public String getNom() {
		return nom;
	}

	public String getDepartement() {
		return departement;
	}

	public String getNom_departement() {
		return nom_departement;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public String getDate_cloture() {
		return date_cloture;
	}

	public Organisateur getOrganisateur() {
		return organisateur;
	}
	
	public void setOrganisateur(Organisateur organisateur) {
		this.organisateur = organisateur;
	}

	public List<Epreuve> getListeEpreuves() {
		return listeEpreuves;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public void setNom_departement(String nom_departement) {
		this.nom_departement = nom_departement;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public void setDate_cloture(String date_cloture) {
		this.date_cloture = date_cloture;
	}

	public void setListeEpreuves(List<Epreuve> listeEpreuves) {
		this.listeEpreuves = listeEpreuves;
	}

	@Override
	public String toString() {
		String res = "Concours [num=" + num + ", nom=" + nom + ", departement=" + departement + ", nom_departement="
				+ nom_departement + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", date_cloture="
				+ date_cloture + ", organisateur=" + organisateur + ", listeEpreuves={";
		StringBuilder strB = new StringBuilder().append(res);
		for(Epreuve epr : listeEpreuves)
			strB.append(epr.toString());
		strB.append("}]");
		return strB.toString();
	}
	
}
