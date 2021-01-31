package prog.tache.listeInfo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import prog.tache.Tache;
import prog.tache.excel.ExcelFile;
import prog.xmlClasse.Cavalier;
import prog.xmlClasse.Concours;
import prog.xmlClasse.Equide;

/**
 * Classe permettant la création des différentes listes d'informations.
 * @author ronan
 *
 */
public class TacheListeInfo extends Tache<Concours> {

	/**
	 * 
	 * @param folder
	 */
	public TacheListeInfo(File folder) {
		super(folder);
	}

	@Override
	public void execute(Concours concours) throws BiffException, IOException, WriteException, IllegalAccessException, InvocationTargetException {
		folder = new File(folder.getPath() + "\\liste");
		if(!folder.exists())
			folder.mkdirs();
		List<Cavalier> listeCavaliers = concours.getListeCavaliers();
		List<Equide> listeEquides = concours.getListeEquides();
		List<String> listeProprietaires = concours.getListeProprietaires();
		createExcelProprietaire(listeProprietaires);
		createExcelCavalier(listeCavaliers);
		createExcelEquide(listeEquides);
	}
	
	/**
	 * Crée la liste des propriétaires.
	 * @param listeProprietaires
	 * @throws IOException
	 * @throws WriteException
	 */
	public void createExcelProprietaire(List<String> listeProprietaires) throws IOException, WriteException {
		Collections.sort(listeProprietaires);
		File file = new File(folder.getPath() + "\\listeProprietaires.xls");
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("fr", "FR"));
		if(!this.folder.exists())
			this.folder.mkdirs();
		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		WritableSheet sheet = workbook.createSheet("listeProprietaires", 0);
		sheet.addCell(new Label(0, 0, "PROPRIETAIRES"));
		sheet.addCell(new Label(1, 0, "NOMBRE"));
		sheet.addCell(new Label(1, 1, listeProprietaires.size() + ""));
		setFormatNomColonne(sheet);
		setFormatColonne(sheet, 256*45);
		for(int i = 0; i < listeProprietaires.size(); i++) {
			Label label = new Label(0, i + 1, listeProprietaires.get(i));
			sheet.addCell(label);
			setFormatCellule(label);
		}
		setFormatNomColonne(sheet);
		workbook.write();
		workbook.close();
	}
	
	/**
	 * Crée la liste des cavaliers.
	 * @param listeCavaliers
	 * @throws IOException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void createExcelCavalier(List<Cavalier> listeCavaliers) throws IOException, WriteException, IllegalAccessException, InvocationTargetException {
		List<InfoCavalier> listeInfoCavaliers = new ArrayList<>();
		for(Cavalier cavalier : listeCavaliers) {
			listeInfoCavaliers.add(new InfoCavalier(cavalier.getNomPrenom(), cavalier.getLic(), 
					cavalier.getDnaiss(), cavalier.getCategorie(), cavalier.getLibelle_age(),
					cavalier.getNom_departement_cavalier(), cavalier.getDepartement_cavalier(),
					cavalier.getNom_region(), cavalier.getRegion(), cavalier.getCre(), cavalier.getNom_club()));
		}
		Collections.sort(listeInfoCavaliers);
		File file = new File(folder.getPath() + "\\listeCavaliers.xls");
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("fr", "FR"));
		ExcelFile excel = new ExcelFile(file, InfoCavalier.class);
		WritableSheet sheet = excel.createSheet("listeCavaliers", 0);
		excel.ajouterNomColonnes(sheet).ajouterInformation(sheet, listeInfoCavaliers).setFormatColonne(sheet).closeWb();
	}
	
	/**
	 * Crée la liste des chevaux.
	 * @param listeEquides
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public void createExcelEquide(List<Equide> listeEquides) throws WriteException, IllegalAccessException, InvocationTargetException, IOException {
		List<InfoEquide> listeInfoEquides = new ArrayList<>();
		for(Equide equide : listeEquides) {
			String cmpt = "";
			if(equide.getEngagement() != null) {
				cmpt = equide.getEngagement().getEngageur().getNum();
			}
			else if(equide.getMembre().getEquipe().getEngageur().getNum() != null) {
				cmpt = equide.getMembre().getEquipe().getEngageur().getNum();
			}
			listeInfoEquides.add(new InfoEquide(equide.getNom(), equide.getSire(), cmpt, equide.getSexe(), equide.getCode_robe(),
					equide.getEquide_age(), equide.getEquide_gain(), equide.getCode_race(), equide.getRace(), equide.getPere().getNom(), equide.getMere().getNom(), equide.getMere().getPere().getNom(), equide.getEleveur(), equide.getProprietaire()));
		}
		Collections.sort(listeInfoEquides);
		File file = new File(folder.getPath() + "\\listeEquides.xls");
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("fr", "FR"));
		ExcelFile excel = new ExcelFile(file, InfoEquide.class);
		WritableSheet sheet = excel.createSheet("listeCavaliers", 0);
		excel.ajouterNomColonnes(sheet).ajouterInformation(sheet, listeInfoEquides).setFormatColonne(sheet).closeWb();
	}
	
	/**
	 * Applique le bon format au noms des colonnes.
	 * @param sheet
	 * @throws WriteException
	 */
	public void setFormatNomColonne(WritableSheet sheet) throws WriteException {
		for(int i = 0; i < 2; i++) {
			WritableCell c = sheet.getWritableCell(i, 0);
			WritableCellFormat newFormat = new WritableCellFormat();
			newFormat.setBackground(Colour.GRAY_25);
			newFormat.setAlignment(Alignment.CENTRE);
			newFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			c.setCellFormat(newFormat);
		}
	}
	
	/**
	 * Applique le bon format aux colonnes.
	 * @param sheet
	 * @param taille
	 */
	public void setFormatColonne(WritableSheet sheet, int taille) {
        CellView cell = sheet.getColumnView(0);
        cell.setSize(taille);
        sheet.setColumnView(0, cell);
	}
	
	/**
	 * Applique le bon format aux cellules.
	 * @param cell
	 */
	public void setFormatCellule(WritableCell cell) {
		WritableCellFormat newFormat = new WritableCellFormat();
		cell.setCellFormat(newFormat);
	}

}
