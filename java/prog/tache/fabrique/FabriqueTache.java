package prog.tache.fabrique;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.tache.Tache;

/**
 * Classe abstraite permettant de de créer une {@link Tache}.
 * @author ronan
 *
 * @param <T>
 */
public abstract class FabriqueTache<T> {
	
	/**
	 * 
	 */
	protected FabriqueTache() {
		
	}

	/**
	 * Fabrique une Tache.
	 * @param folder
	 * @return
	 * @throws BiffException
	 * @throws WriteException
	 * @throws IOException
	 */
	public abstract Tache<T> fabrique(File folder) throws BiffException, WriteException, IOException;
	
}
