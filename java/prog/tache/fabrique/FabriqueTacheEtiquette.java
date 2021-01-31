package prog.tache.fabrique;

import java.io.File;

import prog.tache.Tache;
import prog.tache.etiquette.TacheEtiquette;
import prog.xmlClasse.Epreuve;

/**
 * Classe permettant de créer une {@link TacheEtiquette}.
 * @author ronan
 *
 */
public class FabriqueTacheEtiquette extends FabriqueTache<Epreuve> {

	private static FabriqueTache<Epreuve> fabrique = null;
	
	/**
	 * Patron singleton.
	 * @return
	 */
	public static FabriqueTache<Epreuve> getInstance() {
		if(fabrique == null)
			fabrique = new FabriqueTacheEtiquette();
		return fabrique;
	}
	
	/**
	 * 
	 */
	protected FabriqueTacheEtiquette() {
		
	}
	
	@Override
	public Tache<Epreuve> fabrique(File folder) {
		return new TacheEtiquette(folder);
	}

}
