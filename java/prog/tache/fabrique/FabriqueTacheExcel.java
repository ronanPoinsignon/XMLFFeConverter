package prog.tache.fabrique;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.tache.Tache;
import prog.tache.excel.TacheExcel;
import prog.xmlClasse.Epreuve;

/**
 * Classe permettant de créer une {@link TacheExcel}.
 * @author ronan
 *
 */
public class FabriqueTacheExcel extends FabriqueTache<Epreuve> {

	private static FabriqueTache<Epreuve> fabrique = null;
	
	/**
	 * Patron singleton.
	 * @return
	 */
	public static FabriqueTache<Epreuve> getInstance() {
		if(fabrique == null)
			fabrique = new FabriqueTacheExcel();
		return fabrique;
	}
	
	/**
	 * 
	 */
	protected FabriqueTacheExcel() {
		
	}
	
	@Override
	public Tache<Epreuve> fabrique(File folder) throws BiffException, WriteException, IOException {
		return new TacheExcel(folder);
	}

}
