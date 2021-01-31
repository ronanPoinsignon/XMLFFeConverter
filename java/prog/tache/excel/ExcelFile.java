package prog.tache.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
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
import jxl.write.biff.RowsExceededException;

/**
 * Classe permettant l'écriture dans un fichier Excel.
 * @author ronan
 *
 */
public class ExcelFile {

	protected static int tailleCaractereXls = 256;
	protected static int nbCaracteresParColonne = 15;
	
	protected File file;
	protected Class<?> classe;
	WritableWorkbook workbook;
	protected static String separateurCsv = ";";
	
	/**
	 * 
	 * @param file
	 * @param classe
	 * @throws IOException
	 */
	public ExcelFile(File file, Class<?> classe) throws IOException {
		this.file = file;
		this.classe = classe;
		this.workbook = Workbook.createWorkbook(file);
	}
	
	/**
	 * 
	 * @param file
	 * @param classe
	 * @param wbSettings
	 * @throws IOException
	 */
	public ExcelFile(File file, Class<?> classe, WorkbookSettings wbSettings) throws IOException {
		this.file = file;
		this.classe = classe;
		this.workbook = Workbook.createWorkbook(file, wbSettings);
	}
	
	/**
	 * Ajoute le nom des colonnes correspondant aux informations de la variable classe.
	 * @param sheet
	 * @return
	 * @throws WriteException
	 */
	public ExcelFile ajouterNomColonnes(WritableSheet sheet) throws WriteException {
		ajouterNomColonnes(sheet, classe);
		return this;
	}
	
	/**
	 * Ajoute le nom des colonnes du paramètre classe au fichier excel.
	 * @param sheet
	 * @param classe
	 * @throws WriteException
	 */
	public static void ajouterNomColonnes(WritableSheet sheet, Class<?> classe) throws WriteException {
		Field[] tabField = classe.getDeclaredFields();
		for(int i = 0; i < tabField.length; i++) {
			sheet.addCell(new Label(i, 0, tabField[i].getName().toUpperCase()));
		}
	}
	
	/**
	 * Ajoute les informations de la liste dans le fichier.
	 * @param sheet
	 * @param listeGeneric
	 * @return
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public ExcelFile ajouterInformation(WritableSheet sheet, List<?> listeGeneric) throws WriteException, IllegalAccessException, InvocationTargetException {
		for(int i = 0; i < listeGeneric.size(); i++) {
			Object objet = listeGeneric.get(i);
			Field[] tabField = objet.getClass().getDeclaredFields();
			Method[] methods = objet.getClass().getMethods();
			int cpt = 0;
			for(int k = 0; k < tabField.length; k++) {
				for(int j = 0; j < methods.length; j++) {
					if(methods[j].getName().toLowerCase().trim().equals(("get" + tabField[k].getName().toLowerCase()).trim()) && tabField[k].getType() == String.class) {
						Label label = new Label(cpt, i + 1, methods[j].invoke(objet).toString());
						setFormatCellule(label);
						sheet.addCell(label);
						cpt++;
						break;
					}
				}
			}
		}
		return this;
	}
	
	/**
	 * Ajoute le bon format au titre des colonnes.
	 * @param sheet
	 * @return
	 * @throws WriteException
	 */
	public ExcelFile setFormatColonne(WritableSheet sheet) throws WriteException{
		Field[] tabField = classe.getDeclaredFields();
		for(int i = 0; i < tabField.length; i++) {
			WritableCell c = sheet.getWritableCell(i, 0);
			WritableCellFormat newFormat = new WritableCellFormat();

	        CellView cell = sheet.getColumnView(i);
	        cell.setSize(nbCaracteresParColonne * tailleCaractereXls);
	        sheet.setColumnView(i, cell);
			newFormat.setBackground(Colour.GRAY_25);
			newFormat.setAlignment(Alignment.CENTRE);
			newFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			c.setCellFormat(newFormat);
		}
		return this;
	}
	
	/**
	 * Ajoute le bon format à la cellule.
	 * @param cell
	 */
	public void setFormatCellule(WritableCell cell) {
		WritableCellFormat newFormat = new WritableCellFormat();
		cell.setCellFormat(newFormat);
	}
	
	/**
	 * Ferme le fichier et le retourne.
	 * @return
	 * @throws IOException
	 * @throws WriteException
	 */
	public ExcelFile getResult() throws IOException, WriteException{
		this.workbook.write();
		this.workbook.close();
		return this;
	}
	
	/**
	 * Convertit le fichier excel en fichier Csv en mettant ces fichiers dans le dossier donné.
	 * @param folderCsv
	 * @return
	 * @throws IOException
	 * @throws BiffException 
	 */
	public List<File> workBookToCsv(File folderCsv) throws IOException, BiffException {
		return workBookToCsv(file, folderCsv);
	}
	
	/**
	 * Convertit un fichier excel en fichiers Csv.
	 * @param fileWb
	 * @param folderCsv
	 * @return
	 * @throws IOException
	 * @throws BiffException 
	 */
	public List<File> workBookToCsv(File fileWb, File folderCsv) throws IOException, BiffException {
		List<File> listeCsv = new ArrayList<>();
		File file = new File(folderCsv.getPath());
		if(!file.exists())
			file.mkdirs();
	    Workbook wb = Workbook.getWorkbook(fileWb);
	    for(Sheet s : wb.getSheets()) {
	    	File f = new File(file.getPath() + "\\" + s.getName() + ".csv");
		    FileOutputStream output = null;
		    PrintStream str = null;
		    try {
		    	output = new FileOutputStream(f);
		    	str = new PrintStream(output, true, StandardCharsets.UTF_8.toString());
			    Cell[] row = null;
			      
			    for (int i = 0 ; i < s.getRows() ; i++) { //On parcours chaque ligne de la feuille excel
			    	row = s.getRow(i);
			    	if (row.length > 0) {
			    		str.write(row[0].getContents().getBytes());
			    		for (int j = 1; j < row.length; j++) { //On parcours chaque colonne de la ligne
			    			str.write(separateurCsv.getBytes());
			    			str.write(row[j].getContents().getBytes());
			    		}
			    	}
			    	str.write("\n".getBytes());
			    }
			    listeCsv.add(f);
		    }
		    catch(IOException e) {
		    	throw e;
		    }
		    finally {
			    if(output != null)
			    	output.close();
			    if(str != null)
			    	str.close();
		    }
	    }
	    return listeCsv;
	}
	
	/**
	 * Ecrit les informations dans le fichier puis le ferme.
	 * @throws IOException
	 * @throws WriteException
	 */
	public void closeWb() throws IOException, WriteException {
		workbook.write();
		workbook.close();
	}
	
	/**
	 * Crée une feuille dans le fichier excel.
	 * @param name
	 * @param index
	 * @return
	 */
	public WritableSheet createSheet(String name, int index) {
		return workbook.createSheet(name, index);
	}
	
	/**
	 * 
	 * @return
	 */
	public WritableWorkbook getWorkbook() {
		return workbook;
	}
	
	/**
	 * Convertit un fichier Csv en fichier excel.
	 * @param folderWorkbook
	 * @param csv
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static WritableWorkbook csvToWorkbook(File folderWorkbook, File csv) throws IOException, WriteException {
		WritableWorkbook workbook = Workbook.createWorkbook(folderWorkbook);
		WritableSheet sheet = workbook.createSheet(csv.getName(), 0);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
			String line = "";
			String[] informations;
			int ligne = 0;
			while ((line = br.readLine()) != null) {
				informations = line.split(separateurCsv);
				for(int i = 0; i < informations.length; i++) {
					sheet.addCell(new Label(i, ligne, informations[i]));
				}
			}
			workbook.write();
		}
		catch(IOException | WriteException e) {
			throw e;
		}
		finally {
			br.close();
		}
		return workbook;
	}

}