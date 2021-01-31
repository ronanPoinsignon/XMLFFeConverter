package prog.tache.fabrique;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.tache.Tache;
import prog.tache.iText.EpreuveExcel;
import prog.tache.iText.TacheListeArrivee;

/**
 * Classe permettant de créer une {@link TacheListeArrivee}.
 * @author ronan
 *
 */
public class FabriqueTacheArrivee extends FabriqueTache<EpreuveExcel> {

	private static FabriqueTache<EpreuveExcel> fabrique = null;
	
	/**
	 * 
	 * @return
	 */
	public static FabriqueTache<EpreuveExcel> getInstance(){
		if(fabrique == null)
			fabrique = new FabriqueTacheArrivee();
		return fabrique;
	}
	
	/**
	 * 
	 */
	protected FabriqueTacheArrivee() {

	}
	
	@Override
	public Tache<EpreuveExcel> fabrique(File folder) throws BiffException, WriteException, IOException {
		return new TacheListeArrivee(folder);
	}

}
