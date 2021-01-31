package prog.xmlClasse;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant les officiels dans le fichier XML.
 * @author ronan
 *
 */
public class Officiels {
	
	private List<Officiel> listeOfficiels = new ArrayList<>();
	private Profil profil;
	
	/**
	 * 
	 */
	public Officiels() {

	}

	public List<Officiel> getListeOfficiels() {
		return listeOfficiels;
	}
	
	public Profil getProfil() {
		return this.profil;
	}
	
	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	@Override
	public String toString() {
		String res = "Officiels [listeOfficiels= {";
		for(Officiel off : listeOfficiels)
			res += off.toString();
		res += "}]";
		return res;
	}
	
	
}
