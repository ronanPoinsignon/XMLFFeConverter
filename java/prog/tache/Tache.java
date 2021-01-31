package prog.tache;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 * Classe abstraite permettant d'exécuter une action par rapport à une classe donnée.
 * @author ronan
 *
 */
public abstract class Tache<T> {

	protected File folder;
	/**
	 * 
	 * @param folder
	 */
	public Tache(File folder) {
		this.folder = new File(folder.getPath());
	}
	
	/**
	 * Exécute ce que la tâche est censée faire.
	 * @param generic
	 * @throws IOException
	 * @throws WriteException
	 * @throws BiffException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract void execute(T generic) throws Exception;
}