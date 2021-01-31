package prog.tache.fabrique;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.tache.Tache;
import prog.tache.listeInfo.TacheListeInfo;
import prog.xmlClasse.Concours;

/**
 * Classe permettant de créer une {@link TacheListeInfo}.
 * @author ronan
 *
 */
public class FabriqueTacheInfo extends FabriqueTache<Concours> {

	private static FabriqueTache<Concours> fabrique = null;
	
	/**
	 * Patron singleton.
	 * @return
	 */
	public static FabriqueTache<Concours> getInstance() {
		if(fabrique == null)
			fabrique = new FabriqueTacheInfo();
		return fabrique;
	}
	
	@Override
	public Tache<Concours> fabrique(File folder) throws BiffException, WriteException, IOException {
		return new TacheListeInfo(folder);
	}

}
