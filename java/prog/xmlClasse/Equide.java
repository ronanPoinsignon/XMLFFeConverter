package prog.xmlClasse;

/**
 * Classe représentant l'équidé dans le fichier XML.
 * @author ronan
 *
 */
public class Equide {
	
	private String sire, nom, taille, equide_fei, passeport_fei, enregistrement_fei, equide_gain,
	equide_ctrl_veto, equide_code_pays, equide_libelle_pays, dnaiss, equide_age, race,
	code_race, robe, code_robe, transpondeur, sexe, eleveur, proprietaire;
	private Pere pere;
	private Mere mere;
	private Engagement engagement;
	private Membre membre;
	
	/**
	 * 
	 */
	public Equide() {

	}
	
	/**
	 * 
	 * @param sire
	 * @param nom
	 * @param taille
	 * @param equide_fei
	 * @param passeport_fei
	 * @param enregistrement_fei
	 * @param equide_gain
	 * @param equide_ctrl_veto
	 * @param equide_code_pays
	 * @param equide_libelle_pays
	 * @param dnaiss
	 * @param equide_age
	 * @param race
	 * @param code_race
	 * @param robe
	 * @param code_robe
	 * @param transpondeur
	 * @param sexe
	 * @param eleveur
	 * @param proprietaire
	 * @param pere
	 * @param mere
	 */
	public Equide(String sire, String nom, String taille, String equide_fei, String passeport_fei,
			String enregistrement_fei, String equide_gain, String equide_ctrl_veto, String equide_code_pays,
			String equide_libelle_pays, String dnaiss, String equide_age, String race, String code_race, String robe,
			String code_robe, String transpondeur, String sexe, String eleveur, String proprietaire, Pere pere,
			Mere mere) {
		this.sire = sire;
		this.nom = nom;
		this.taille = taille;
		this.equide_fei = equide_fei;
		this.passeport_fei = passeport_fei;
		this.enregistrement_fei = enregistrement_fei;
		this.equide_gain = equide_gain;
		this.equide_ctrl_veto = equide_ctrl_veto;
		this.equide_code_pays = equide_code_pays;
		this.equide_libelle_pays = equide_libelle_pays;
		this.dnaiss = dnaiss;
		this.equide_age = equide_age;
		this.race = race;
		this.code_race = code_race;
		this.robe = robe;
		this.code_robe = code_robe;
		this.transpondeur = transpondeur;
		this.sexe = sexe;
		this.eleveur = eleveur;
		this.proprietaire = proprietaire;
		this.pere = pere;
		this.mere = mere;
	}

	public String getSire() {
		return sire;
	}

	public String getNom() {
		return nom;
	}

	public String getTaille() {
		return taille;
	}

	public String getEquide_fei() {
		return equide_fei;
	}

	public String getPasseport_fei() {
		return passeport_fei;
	}

	public String getEnregistrement_fei() {
		return enregistrement_fei;
	}

	public String getEquide_gain() {
		return equide_gain;
	}

	public String getEquide_ctrl_veto() {
		return equide_ctrl_veto;
	}

	public String getEquide_code_pays() {
		return equide_code_pays;
	}

	public String getEquide_libelle_pays() {
		return equide_libelle_pays;
	}

	public String getDnaiss() {
		return dnaiss;
	}

	public String getEquide_age() {
		return equide_age;
	}

	public String getRace() {
		return race;
	}

	public String getCode_race() {
		return code_race;
	}

	public String getRobe() {
		return robe;
	}

	public String getCode_robe() {
		return code_robe;
	}

	public String getTranspondeur() {
		return transpondeur;
	}

	public String getSexe() {
		return sexe;
	}

	public String getEleveur() {
		return eleveur;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public Pere getPere() {
		return pere;
	}

	public Mere getMere() {
		return mere;
	}

	public Engagement getEngagement() {
		return this.engagement;
	}
	
	public Membre getMembre() {
		return this.membre;
	}
	
	public void setSire(String sire) {
		this.sire = sire;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public void setEquide_fei(String equide_fei) {
		this.equide_fei = equide_fei;
	}

	public void setPasseport_fei(String passeport_fei) {
		this.passeport_fei = passeport_fei;
	}

	public void setEnregistrement_fei(String enregistrement_fei) {
		this.enregistrement_fei = enregistrement_fei;
	}

	public void setEquide_gain(String equide_gain) {
		this.equide_gain = equide_gain;
	}

	public void setEquide_ctrl_veto(String equide_ctrl_veto) {
		this.equide_ctrl_veto = equide_ctrl_veto;
	}

	public void setEquide_code_pays(String equide_code_pays) {
		this.equide_code_pays = equide_code_pays;
	}

	public void setEquide_libelle_pays(String equide_libelle_pays) {
		this.equide_libelle_pays = equide_libelle_pays;
	}

	public void setDnaiss(String dnaiss) {
		this.dnaiss = dnaiss;
	}

	public void setEquide_age(String equide_age) {
		this.equide_age = equide_age;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public void setCode_race(String code_race) {
		this.code_race = code_race;
	}

	public void setRobe(String robe) {
		this.robe = robe;
	}

	public void setCode_robe(String code_robe) {
		this.code_robe = code_robe;
	}

	public void setTranspondeur(String transpondeur) {
		this.transpondeur = transpondeur;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setEleveur(String eleveur) {
		this.eleveur = eleveur;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public void setPere(Pere pere) {
		this.pere = pere;
	}

	public void setMere(Mere mere) {
		this.mere = mere;
	}
	
	public void setEngagement(Engagement engagement) {
		this.engagement = engagement;
	}
	
	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	@Override
	public String toString() {
		return "Equide [sire=" + sire + ", nom=" + nom + ", taille=" + taille + ", equide_fei=" + equide_fei
				+ ", passeport_fei=" + passeport_fei + ", enregistrement_fei=" + enregistrement_fei + ", equide_gain="
				+ equide_gain + ", equide_ctrl_veto=" + equide_ctrl_veto + ", equide_code_pays=" + equide_code_pays
				+ ", equide_libelle_pays=" + equide_libelle_pays + ", dnaiss=" + dnaiss + ", equide_age=" + equide_age
				+ ", race=" + race + ", code_race=" + code_race + ", robe=" + robe + ", code_robe=" + code_robe
				+ ", transpondeur=" + transpondeur + ", sexe=" + sexe + ", eleveur=" + eleveur + ", proprietaire="
				+ proprietaire + ", pere=" + pere + ", mere=" + mere + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code_race == null) ? 0 : code_race.hashCode());
		result = prime * result + ((code_robe == null) ? 0 : code_robe.hashCode());
		result = prime * result + ((dnaiss == null) ? 0 : dnaiss.hashCode());
		result = prime * result + ((eleveur == null) ? 0 : eleveur.hashCode());
		result = prime * result + ((engagement == null) ? 0 : engagement.hashCode());
		result = prime * result + ((enregistrement_fei == null) ? 0 : enregistrement_fei.hashCode());
		result = prime * result + ((equide_age == null) ? 0 : equide_age.hashCode());
		result = prime * result + ((equide_code_pays == null) ? 0 : equide_code_pays.hashCode());
		result = prime * result + ((equide_ctrl_veto == null) ? 0 : equide_ctrl_veto.hashCode());
		result = prime * result + ((equide_fei == null) ? 0 : equide_fei.hashCode());
		result = prime * result + ((equide_gain == null) ? 0 : equide_gain.hashCode());
		result = prime * result + ((equide_libelle_pays == null) ? 0 : equide_libelle_pays.hashCode());
		result = prime * result + ((membre == null) ? 0 : membre.hashCode());
		result = prime * result + ((mere == null) ? 0 : mere.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((passeport_fei == null) ? 0 : passeport_fei.hashCode());
		result = prime * result + ((pere == null) ? 0 : pere.hashCode());
		result = prime * result + ((proprietaire == null) ? 0 : proprietaire.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((robe == null) ? 0 : robe.hashCode());
		result = prime * result + ((sexe == null) ? 0 : sexe.hashCode());
		result = prime * result + ((sire == null) ? 0 : sire.hashCode());
		result = prime * result + ((taille == null) ? 0 : taille.hashCode());
		result = prime * result + ((transpondeur == null) ? 0 : transpondeur.hashCode());
		return result;
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equide equide = (Equide) obj;
		return this.sire.equals(equide.getSire()) && this.nom.equals(equide.getNom()) && this.taille.equals(equide.getTaille())
				&& this.equide_fei.equals(equide.getEquide_fei()) && this.passeport_fei.equals(equide.getPasseport_fei())
				&& this.enregistrement_fei.equals(equide.getEnregistrement_fei()) && this.equide_gain.equals(equide.getEquide_gain())
				&& this.equide_code_pays.equals(equide.getEquide_code_pays())
				&& this.equide_libelle_pays.equals(equide.getEquide_libelle_pays()) && this.dnaiss.equals(equide.getDnaiss())
				&& this.equide_age.equals(equide.getEquide_age()) && this.race.equals(equide.getRace())
				&& this.code_race.equals(equide.getCode_race()) && this.robe.equals(equide.getRobe())
				&& this.code_robe.equals(equide.getCode_robe()) && this.transpondeur.equals(equide.getTranspondeur())
				&& this.sexe.equals(equide.getSexe()) && this.eleveur.equals(equide.getEleveur())
				&& this.proprietaire.equals(equide.getProprietaire());
	}
	
}
