package prog.tache.excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import prog.tache.Tache;
import prog.xmlClasse.Epreuve;

/**
 * Tâche permettant de créer les fichier excel des {@link Epreuve}.
 * @author ronan
 *
 */
public class TacheExcel extends Tache<Epreuve> {
	
	/**
	 * 
	 * @param folder
	 * @throws BiffException
	 * @throws IOException
	 * @throws WriteException
	 */
	public TacheExcel(File folder) throws BiffException, IOException, WriteException {
		super(new File(folder.getPath() + "\\excel"));
	}

	@Override
	public void execute(Epreuve epreuve) throws IOException, WriteException, IllegalAccessException, InvocationTargetException {
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("fr", "FR"));
		if(!this.folder.exists())
			this.folder.mkdirs();
		File fichierWorkbook = new File(this.folder.getPath() + "\\epr" + epreuve.getNumFormat() + ".xls");
		ExcelFile workbook = new ExcelFile(fichierWorkbook, ResultatExcel.class, wbSettings);
		WritableSheet sheet = workbook.createSheet("epr" + epreuve.getNumFormat(), 0);
		List<ResultatExcel> listeResultats = epreuve.getListeResultats();
		Collections.sort(listeResultats); //On trie les résultats
		workbook.ajouterNomColonnes(sheet).ajouterInformation(sheet, listeResultats).setFormatColonne(sheet).closeWb();
	}
}
