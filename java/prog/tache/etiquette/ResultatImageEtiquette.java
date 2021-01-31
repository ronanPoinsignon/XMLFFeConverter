package prog.tache.etiquette;

import prog.xmlClasse.Cavalier;
import prog.xmlClasse.Equide;

/**
 * Classe représentant les informations à ajouter sur l'étiquette.
 * @author ronan
 *
 */
public class ResultatImageEtiquette implements Comparable<ResultatImageEtiquette> {

	private String numEpreuve, numDepart, nbEng;
	private Cavalier cavalier;
	private Equide equide;
	
	/**
	 * 
	 */
	public ResultatImageEtiquette() {
		
	}
	
	/**
	 * 
	 * @param numEpreuve
	 * @param numDepart
	 * @param nbEng
	 * @param cavalier
	 * @param equide
	 */
	public ResultatImageEtiquette(String numEpreuve, String numDepart, String nbEng, Cavalier cavalier, Equide equide) {
		this.numEpreuve = numEpreuve;
		this.numDepart = numDepart;
		this.nbEng = nbEng;
		this.cavalier = cavalier;
		this.equide = equide;
	}


	/**
	 * 
	 * @return
	 */
	public String getNumEpreuve() {
		return numEpreuve;
	}
	
	/**
	 * 
	 * @param numEpreuve
	 */
	public void setNumEpreuve(String numEpreuve) {
		this.numEpreuve = numEpreuve;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNumDepart() {
		return numDepart;
	}
	
	/**
	 * 
	 * @param numDepart
	 */
	public void setNumDepart(String numDepart) {
		this.numDepart = numDepart;
	}
	
	/**
	 * 
	 * @return
	 */
	public Cavalier getCavalier() {
		return cavalier;
	}
	
	/**
	 * 
	 * @param cavalier
	 */
	public void setCavalier(Cavalier cavalier) {
		this.cavalier = cavalier;
	}
	
	/**
	 * 
	 * @return
	 */
	public Equide getEquide() {
		return equide;
	}
	
	/**
	 * 
	 * @param equide
	 */
	public void setEquide(Equide equide) {
		this.equide = equide;
	}

	/**
	 * 
	 * @return
	 */
	public String getNbEng() {
		return nbEng;
	}

	/**
	 * 
	 * @param nbEng
	 */
	public void setNbEng(String nbEng) {
		this.nbEng = nbEng;
	}
	
	@Override
	public String toString() {
		return "Etiquette [numEpreuve=" + numEpreuve + ", numDepart=" + numDepart + ", cavalier=" + cavalier
				+ ", equide=" + equide + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cavalier == null) ? 0 : cavalier.hashCode());
		result = prime * result + ((equide == null) ? 0 : equide.hashCode());
		result = prime * result + ((numDepart == null) ? 0 : numDepart.hashCode());
		result = prime * result + ((numEpreuve == null) ? 0 : numEpreuve.hashCode());
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
		ResultatImageEtiquette other = (ResultatImageEtiquette) obj;
		if (cavalier == null) {
			if (other.cavalier != null)
				return false;
		} 
		else if (!cavalier.equals(other.cavalier))
			return false;
		if (equide == null) {
			if (other.equide != null)
				return false;
		} 
		else if (!equide.equals(other.equide))
			return false;
		if (numDepart == null) {
			if (other.numDepart != null)
				return false;
		} 
		else if (!numDepart.equals(other.numDepart))
			return false;
		if (numEpreuve == null) {
			if (other.numEpreuve != null)
				return false;
		} 
		else if (!numEpreuve.equals(other.numEpreuve))
			return false;
		return true;
	}

	@Override
	public int compareTo(ResultatImageEtiquette et) { //Méthode permettant par la suite de trier une liste d'étiquettes
		Integer numDThis, numDEt;
		numDThis = Integer.parseInt(this.getNumDepart());
		numDEt = Integer.parseInt(et.getNumDepart());
		return numDThis.compareTo(numDEt);
	}
	
	
}
