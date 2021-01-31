package prog.tache.fabrique;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.tache.Tache;
import prog.tache.iText.TacheListeDepart;
import prog.xmlClasse.Epreuve;

/**
 * Classe permettant de créer une {@link TacheListeDepart}.
 * @author ronan
 *
 */
public class FabriqueTacheDepart extends FabriqueTache<Epreuve> {
	
	private static FabriqueTache<Epreuve> fabrique = null;
	
	/**
	 * Patron singleton.
	 * @return
	 */
	public static FabriqueTache<Epreuve> getInstance() {
		if(fabrique == null) {
			fabrique = new FabriqueTacheDepart();
		}
		return fabrique;
	}
	
	/**
	 * 
	 */
	protected FabriqueTacheDepart() {
		
	}
	
	@Override
	public Tache<Epreuve> fabrique(File folder) throws BiffException, WriteException, IOException {
		return new TacheListeDepart(folder);
	}

}
