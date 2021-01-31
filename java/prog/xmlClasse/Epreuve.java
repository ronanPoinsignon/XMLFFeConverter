package prog.xmlClasse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import prog.tache.etiquette.ResultatImageEtiquette;
import prog.tache.etiquette.ResultatTxtEtiquette;
import prog.tache.excel.ResultatExcel;
import prog.tache.iText.ResultatItext;

/**
 * Classe représentant l'épreuve dans le fichier XML.
 * @author ronan
 *
 */
public class Epreuve {
	
	private String num, num_sequence, date, discipline, discipline_libelle,
		nom_epreuve, categorie, nom_categorie, code_bareme, nom_bareme, nbr_engages,
		montant_eng, dotation_epreuve, montant_eng_terrain, engagement_terrain, 
		invitation_organisateur, epreuve_elevage, epreuve_equipe, epreuve_indice,
		type_piste, nom_piste, longueur_piste, largeur_piste, description_piste,
		heure_debut, id_protocole_version;
	private List<Engagement> listeEngagements = new ArrayList<>();
	private List<Equipe> listeEquipes = new ArrayList<>();
	private Profil profil;
	private Concours concours;
	
	/**
	 * 
	 */
	public Epreuve() {
		
	}
	
	/**
	 * 
	 * @param num
	 * @param num_sequence
	 * @param date
	 * @param discipline
	 * @param discipline_libelle
	 * @param nom_epreuve
	 * @param categorie
	 * @param nom_categorie
	 * @param code_bareme
	 * @param nom_bareme
	 * @param nbr_engages
	 * @param montant_eng
	 * @param dotation_epreuve
	 * @param montant_eng_terrain
	 * @param engagement_terrain
	 * @param invitation_organisateur
	 * @param epreuve_elevage
	 * @param epreuve_equipe
	 * @param epreuve_indice
	 * @param type_piste
	 * @param nom_piste
	 * @param longueur_piste
	 * @param largeur_piste
	 * @param description_piste
	 * @param heure_debut
	 * @param id_protocole_version
	 * @param profil
	 */
	public Epreuve(String num, String num_sequence, String date, String discipline, String discipline_libelle,
			String nom_epreuve, String categorie, String nom_categorie, String code_bareme, String nom_bareme,
			String nbr_engages, String montant_eng, String dotation_epreuve, String montant_eng_terrain,
			String engagement_terrain, String invitation_organisateur, String epreuve_elevage, String epreuve_equipe,
			String epreuve_indice, String type_piste, String nom_piste, String longueur_piste, String largeur_piste,
			String description_piste, String heure_debut, String id_protocole_version, Profil profil) {
		this.num = num;
		this.num_sequence = num_sequence;
		this.date = date;
		this.discipline = discipline;
		this.discipline_libelle = discipline_libelle;
		this.nom_epreuve = nom_epreuve;
		this.categorie = categorie;
		this.nom_categorie = nom_categorie;
		this.code_bareme = code_bareme;
		this.nom_bareme = nom_bareme;
		this.nbr_engages = nbr_engages;
		this.montant_eng = montant_eng;
		this.dotation_epreuve = dotation_epreuve;
		this.montant_eng_terrain = montant_eng_terrain;
		this.engagement_terrain = engagement_terrain;
		this.invitation_organisateur = invitation_organisateur;
		this.epreuve_elevage = epreuve_elevage;
		this.epreuve_equipe = epreuve_equipe;
		this.epreuve_indice = epreuve_indice;
		this.type_piste = type_piste;
		this.nom_piste = nom_piste;
		this.longueur_piste = longueur_piste;
		this.largeur_piste = largeur_piste;
		this.description_piste = description_piste;
		this.heure_debut = heure_debut;
		this.id_protocole_version = id_protocole_version;
		this.profil = profil;
	}
	
	/**
	 * Retourne la liste de cavaliers de l'épreuve.
	 * @return
	 */
	public List<Cavalier> getListeCavaliers(){
		ArrayList<Cavalier> listeCavaliers = new ArrayList<>();
		boolean estDansListe = false;
		for(Engagement engagement : this.getListeEngagements()) {
			estDansListe = false;
			for(Cavalier cavalier : listeCavaliers) {
				if(cavalier.equals(engagement.getCavalier())) {
					estDansListe = true;
					break;
				}
			}
			if(!estDansListe)
				listeCavaliers.add(engagement.getCavalier());
		}
		for(Equipe equipe : this.getListeEquipes()) {
			for(Membre membre : equipe.getListeMembres()) {
				for(Cavalier cavalier : listeCavaliers) {
					estDansListe = false;
					if(cavalier.equals(membre.getCavalier())) {
						estDansListe = true;
						break;
					}
				}
				if(!estDansListe)
					listeCavaliers.add(membre.getCavalier());
			}
		}
		return listeCavaliers;
	}
	
	/**
	 * Retourne la liste de chevaux de l'épreuve.
	 * @return
	 */
	public List<Equide> getListeEquides(){
		List<Equide> listeEquides = new ArrayList<>();
		boolean estDansListe = false;
		for(Engagement engagement : this.getListeEngagements()) {
			estDansListe = false;
			for(Equide equide : listeEquides) {
				if(equide.equals(engagement.getEquide())) {
					estDansListe = true;
					break;
				}
			}
			if(!estDansListe)
				listeEquides.add(engagement.getEquide());
		}
		for(Equipe equipe : this.getListeEquipes()) {
			for(Membre membre : equipe.getListeMembres()) {
				for(Equide equide : listeEquides) {
					estDansListe = false;
					if(equide.equals(membre.getEquide())) {
						estDansListe = true;
						break;
					}
				}
				if(!estDansListe)
					listeEquides.add(membre.getEquide());
			}
		}
		return listeEquides;
	}

	/**
	 * Retourne la liste de propriétaires de l'apreuve.
	 * @return
	 */
	public List<String> getListeProprietaires() {
		List<Equide> listeEquides = this.getListeEquides();
		List<String> listeProprietaires = new ArrayList<>();
		for(Equide equide : listeEquides) {
			String prop = equide.getProprietaire();
			String[] tabProp = prop.split("[\\(]?[ ]*[0-9]*[ ]*(%)[ ]*[\\)]?[ ]*[,]?"); //on sépare les propriétaires en enlevant le pourcentage
			for(String str : tabProp) {
				str = str.trim();
				if(!str.equals("")) {
					if(!listeProprietaires.contains(str))
						listeProprietaires.add(str);
				}
			}
		}
		return listeProprietaires;
	}
	
	/**
	 * Retourne la liste des informations concernant les fichiers excel de l'épreuve.
	 * @return
	 */
	public List<ResultatExcel> getListeResultats(){
		List<ResultatExcel> listeResultats = new ArrayList<>();
		for(Engagement eng : listeEngagements) {
			ResultatExcel res = new ResultatExcel();
			listeResultats.add(res);
			res.setEpreuve(this.num);
			res.setLicence(eng.getCavalier().getLic());
			res.setNom(eng.getCavalier().getNomPrenom());
			res.setNomche(eng.getEquide().getNom());
			res.setDossard(eng.getFormatDossard());
			res.setSire(eng.getEquide().getSire());
			res.setdNaiss(eng.getCavalier().getDnaiss());
			res.setNumCmpt(eng.getEngageur().getNum());
			res.setPerche(eng.getEquide().getPere().getNom());
			res.setEquide_type_eng(eng.getEquide_type_eng());
			res.setHors_classement(eng.getHors_classement());
			String sexe = "H";
			if(eng.getEquide().getSexe().trim().toLowerCase().equals("jument"))
				sexe = "F";
			res.setSexe(sexe);
			res.setRobe(eng.getEquide().getRobe());
			res.setAge(eng.getEquide().getEquide_age());
			res.setGains(eng.getEquide().getEquide_gain());
			res.setRace(eng.getEquide().getCode_race());
			res.setPere(eng.getEquide().getPere().getNom());
			res.setMere(eng.getEquide().getMere().getNom());
			res.setPereMere(eng.getEquide().getMere().getPere().getNom());
			res.setNaisseur(eng.getEquide().getEleveur());
			res.setProprietaire(eng.getEquide().getProprietaire());
		}
		for(Equipe equipe : listeEquipes) {
			for(Membre membre : equipe.getListeMembres()) {
				ResultatExcel res = new ResultatExcel();
				listeResultats.add(res);
				res.setEpreuve(this.num);
				res.setLicence(membre.getCavalier().getLic());
				res.setNom(membre.getCavalier().getNomPrenom());
				res.setNomche(membre.getEquide().getNom());
				res.setDossard(membre.getDossard());
				res.setSire(membre.getEquide().getSire());
				res.setdNaiss(membre.getCavalier().getDnaiss());
				res.setNumCmpt(equipe.getEngageur().getNum());
				res.setPerche(membre.getEquide().getPere().getNom());
				res.setEquide_type_eng(membre.getEquide_type_eng());
				String sexe = "H";
				if(membre.getEquide().getSexe().trim().toLowerCase().equals("jument"))
					sexe = "F";
				res.setSexe(sexe);
				res.setRobe(membre.getEquide().getRobe());
				res.setAge(membre.getEquide().getEquide_age());
				res.setGains(membre.getEquide().getEquide_gain());
				res.setRace(membre.getEquide().getCode_race());
				res.setPere(membre.getEquide().getPere().getNom());
				res.setMere(membre.getEquide().getMere().getNom());
				res.setPereMere(membre.getEquide().getMere().getPere().getNom());
				res.setNaisseur(membre.getEquide().getEleveur());
				res.setProprietaire(membre.getEquide().getProprietaire());
			}
		}
		return listeResultats;
	}
	
	/**
	 * Retourne la liste d'informations utiles à la création du fichier txt concernant les étiquettes.
	 * @return
	 */
	public List<ResultatTxtEtiquette> getListeResultatTxt(){
		List<ResultatTxtEtiquette> listeEtiquettes = new ArrayList<>();
		for(Engagement eng : listeEngagements) {
			ResultatTxtEtiquette res = new ResultatTxtEtiquette();
			listeEtiquettes.add(res);
			res.setLieu(this.getConcours().getNom());
			res.setEpreuve(this.getNum());
			res.setClasse(this.getNom_categorie().replaceAll("\\)", ""));
			res.setEng(listeEngagements.size() + "");
			res.setNumdep(eng.getFormatDossard());
			res.setNomche(eng.getEquide().getNom());
			res.setSire(eng.getEquide().getSire().replaceAll("[^\\d]", ""));
			res.setGains(eng.getEquide().getEquide_gain());
			res.setPerche(eng.getEquide().getPere().getNom());
			res.setNom(eng.getCavalier().getNomPrenom());
			res.setLicence(eng.getCavalier().getLic().replaceAll("[^\\d]", ""));
			res.setLigue(eng.getCavalier().getCre());
			res.setCategorie(eng.getCavalier().getCategorie());
			res.setJunsen(eng.getCavalier().getCode_age());
			res.setClucav(eng.getCavalier().getNom_club());
		}
		int nbMembres = 0;
		for(Equipe equipe : listeEquipes) {
			nbMembres += equipe.getListeMembres().size();
		}
		for(Equipe equipe : listeEquipes) {
			for(Membre membre : equipe.getListeMembres()) {
				ResultatTxtEtiquette res = new ResultatTxtEtiquette();
				listeEtiquettes.add(res);
				res.setLieu(this.getConcours().getNom());
				res.setEpreuve(this.getNum());
				res.setClasse(this.getNom_categorie().replaceAll("\\)", ""));
				res.setEng(nbMembres + "");
				res.setNumdep(membre.getFormatDossard());
				res.setNomche(membre.getEquide().getNom());
				res.setSire(membre.getEquide().getSire().replaceAll("[^\\d]", ""));
				res.setGains(membre.getEquide().getEquide_gain());
				res.setPerche(membre.getEquide().getPere().getNom());
				res.setNom(membre.getCavalier().getNomPrenom());
				res.setLicence(membre.getCavalier().getLic().replaceAll("[^\\d]", ""));
				res.setLigue(membre.getCavalier().getCre());
				res.setCategorie(membre.getCavalier().getCategorie());
				res.setJunsen(membre.getCavalier().getCode_age());
				res.setClucav(membre.getCavalier().getNom_club());
			}
		}
		Collections.sort(listeEtiquettes);
		return listeEtiquettes;
	}
	
	/**
	 * Retourne la liste d'informations concernant la création des liste de départ.
	 * @return
	 */
	public List<ResultatItext> getListeResultatsItext(){
		List<ResultatItext> listeResultats = new ArrayList<>();
		for(Engagement eng : listeEngagements) {
			ResultatItext res = new ResultatItext();
			listeResultats.add(res);
			res.setEpreuve(this);
			res.setCavalier(eng.getCavalier());
			res.setEquide(eng.getEquide());
			res.setNumDossard(eng.getDossard());
		}
		for(Equipe equipe : listeEquipes) {
			for(Membre membre : equipe.getListeMembres()) {
				ResultatItext res = new ResultatItext();
				listeResultats.add(res);
				res.setEpreuve(this);
				res.setCavalier(membre.getCavalier());
				res.setEquide(membre.getEquide());
				res.setNumDossard(membre.getDossard());
			}
		}
		return listeResultats;
	}
	
	/**
	 * Retourne la liste d'informations correspondant à la création des étiquettes.
	 * @return
	 */
	public List<ResultatImageEtiquette> getListeEtiquettes(){
		List<ResultatImageEtiquette> listeEtiquettes = new ArrayList<>();
		for(Engagement engagement : this.getListeEngagements()) {
			ResultatImageEtiquette etiquette = new ResultatImageEtiquette(this.getNum(), 
					engagement.getDossard(), this.getNbr_engages(), engagement.getCavalier(), engagement.getEquide());
			listeEtiquettes.add(etiquette);
		}
		for(Equipe equipe : this.getListeEquipes()) {
			for(Membre membre : equipe.getListeMembres()) {
				ResultatImageEtiquette etiquette = new ResultatImageEtiquette(this.getNum(), 
						membre.getDossard(), this.getNbr_engages(), membre.getCavalier(), membre.getEquide());
				listeEtiquettes.add(etiquette);
			}
		}
		return listeEtiquettes;
	}
	
	/**
	 * Formate le numéro de dossard.
	 * @return
	 */
	public String getNumFormat() {
		if(num.length() < 2)
			return "0" + num;
		else
			return num;
	}

	public String getNum() {
		return num;
	}

	public String getNum_sequence() {
		return num_sequence;
	}

	public String getDate() {
		return date;
	}

	public String getDiscipline() {
		return discipline;
	}

	public String getDiscipline_libelle() {
		return discipline_libelle;
	}

	public String getNom_epreuve() {
		return nom_epreuve;
	}

	public String getCategorie() {
		return categorie;
	}

	public String getNom_categorie() {
		return nom_categorie;
	}

	public String getCode_bareme() {
		return code_bareme;
	}

	public String getNom_bareme() {
		return nom_bareme;
	}

	public String getNbr_engages() {
		return nbr_engages;
	}

	public String getMontant_eng() {
		return montant_eng;
	}

	public String getDotation_epreuve() {
		return dotation_epreuve;
	}

	public String getMontant_eng_terrain() {
		return montant_eng_terrain;
	}

	public String getEngagement_terrain() {
		return engagement_terrain;
	}

	public String getInvitation_organisateur() {
		return invitation_organisateur;
	}

	public String getEpreuve_elevage() {
		return epreuve_elevage;
	}

	public String getEpreuve_equipe() {
		return epreuve_equipe;
	}

	public String getEpreuve_indice() {
		return epreuve_indice;
	}

	public String getType_piste() {
		return type_piste;
	}

	public String getNom_piste() {
		return nom_piste;
	}

	public String getLongueur_piste() {
		return longueur_piste;
	}

	public String getLargeur_piste() {
		return largeur_piste;
	}

	public String getDescription_piste() {
		return description_piste;
	}

	public String getHeure_debut() {
		return heure_debut;
	}

	public String getId_protocole_version() {
		return id_protocole_version;
	}
	
	public Concours getConcours() {
		return this.concours;
	}

	public List<Engagement> getListeEngagements() {
		return listeEngagements;
	}
	
	public List<Equipe> getListeEquipes(){
		return listeEquipes;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setNum_sequence(String num_sequence) {
		this.num_sequence = num_sequence;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public void setDiscipline_libelle(String discipline_libelle) {
		this.discipline_libelle = discipline_libelle;
	}

	public void setNom_epreuve(String nom_epreuve) {
		this.nom_epreuve = nom_epreuve;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}

	public void setCode_bareme(String code_bareme) {
		this.code_bareme = code_bareme;
	}

	public void setNom_bareme(String nom_bareme) {
		this.nom_bareme = nom_bareme;
	}

	public void setNbr_engages(String nbr_engages) {
		this.nbr_engages = nbr_engages;
	}

	public void setMontant_eng(String montant_eng) {
		this.montant_eng = montant_eng;
	}

	public void setDotation_epreuve(String dotation_epreuve) {
		this.dotation_epreuve = dotation_epreuve;
	}

	public void setMontant_eng_terrain(String montant_eng_terrain) {
		this.montant_eng_terrain = montant_eng_terrain;
	}

	public void setEngagement_terrain(String engagement_terrain) {
		this.engagement_terrain = engagement_terrain;
	}

	public void setInvitation_organisateur(String invitation_organisateur) {
		this.invitation_organisateur = invitation_organisateur;
	}

	public void setEpreuve_elevage(String epreuve_elevage) {
		this.epreuve_elevage = epreuve_elevage;
	}

	public void setEpreuve_equipe(String epreuve_equipe) {
		this.epreuve_equipe = epreuve_equipe;
	}

	public void setEpreuve_indice(String epreuve_indice) {
		this.epreuve_indice = epreuve_indice;
	}

	public void setType_piste(String type_piste) {
		this.type_piste = type_piste;
	}

	public void setNom_piste(String nom_piste) {
		this.nom_piste = nom_piste;
	}

	public void setLongueur_piste(String longueur_piste) {
		this.longueur_piste = longueur_piste;
	}

	public void setLargeur_piste(String largeur_piste) {
		this.largeur_piste = largeur_piste;
	}

	public void setDescription_piste(String description_piste) {
		this.description_piste = description_piste;
	}

	public void setHeure_debut(String heure_debut) {
		this.heure_debut = heure_debut;
	}

	public void setId_protocole_version(String id_protocole_version) {
		this.id_protocole_version = id_protocole_version;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	public void setConcours(Concours concours) {
		this.concours = concours;
	}

	@Override
	public String toString() {
		String res =  "Epreuve [num=" + num + ", num_sequence=" + num_sequence + ", date=" + date + ", discipline="
				+ discipline + ", discipline_libelle=" + discipline_libelle + ", nom_epreuve=" + nom_epreuve
				+ ", categorie=" + categorie + ", nom_categorie=" + nom_categorie + ", code_bareme=" + code_bareme
				+ ", nom_bareme=" + nom_bareme + ", nbr_engages=" + nbr_engages + ", montant_eng=" + montant_eng
				+ ", dotation_epreuve=" + dotation_epreuve + ", montant_eng_terrain=" + montant_eng_terrain
				+ ", engagement_terrain=" + engagement_terrain + ", invitation_organisateur=" + invitation_organisateur
				+ ", epreuve_elevage=" + epreuve_elevage + ", epreuve_equipe=" + epreuve_equipe + ", epreuve_indice="
				+ epreuve_indice + ", type_piste=" + type_piste + ", nom_piste=" + nom_piste + ", longueur_piste="
				+ longueur_piste + ", largeur_piste=" + largeur_piste + ", description_piste=" + description_piste
				+ ", heure_debut=" + heure_debut + ", id_protocole_version=" + id_protocole_version + ", profil="
				+ profil + ", engagement={";
		for(Engagement eng : listeEngagements)
			res += eng;
		res += "}]";
		return res;
	}
	
	
}
