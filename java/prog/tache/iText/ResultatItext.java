package prog.tache.iText;

import prog.xmlClasse.Cavalier;
import prog.xmlClasse.Epreuve;
import prog.xmlClasse.Equide;

/**
 * Classe représentant les informations utiles pour créer les listes de départ.
 * @author ronan
 *
 */
public class ResultatItext implements Comparable<ResultatItext>{

	private Epreuve epreuve;
	private Equide equide;
	private Cavalier cavalier;
	private String numDossard;
	
	/**
	 * 
	 */
	public ResultatItext() {
		
	}

	/**
	 * 
	 * @param epreuve
	 * @param equide
	 * @param cavalier
	 * @param numDossard
	 */
	public ResultatItext(Epreuve epreuve, Equide equide, Cavalier cavalier, String numDossard) {
		this.epreuve = epreuve;
		this.equide = equide;
		this.cavalier = cavalier;
		this.numDossard = numDossard;
	}

	/**
	 * 
	 * @return
	 */
	public String getFormatDossard() {
		StringBuilder strB = new StringBuilder().append(numDossard);
		for(int i = strB.length(); i < 3; i++) {
			strB.insert(0, "0");
		}
		return strB.toString();
	}

	/**
	 * 
	 * @return
	 */
	public Epreuve getEpreuve() {
		return epreuve;
	}

	/**
	 * 
	 * @param epreuve
	 */
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
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
	public String getNumDossard() {
		return numDossard;
	}

	/**
	 * 
	 * @param numDossard
	 */
	public void setNumDossard(String numDossard) {
		this.numDossard = numDossard;
	}
	
	@Override
    public int compareTo(ResultatItext res) {
		Integer numD1, numD2;
		numD1 = Integer.parseInt(this.getFormatDossard());
		numD2 = Integer.parseInt(res.getFormatDossard());
		return numD1.compareTo(numD2);
    }
	
}
