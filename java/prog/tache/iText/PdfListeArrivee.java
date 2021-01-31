package prog.tache.iText;

import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.VerticalAlignment;

import jxl.Sheet;


/**
 * 
 * @author Estelle
 *
 */
public class PdfListeArrivee extends PdfDocumentDecorateur { 
	
	private EpreuveExcel epreuve;
	
	/**
	 * Constructeur de Pdf.
	 * @param document
	 * @param folder
	 * @param epreuve
	 */
	public PdfListeArrivee(Document document, File folder, EpreuveExcel epreuve) {
		super(document, folder);
		this.epreuve = epreuve;
	}

	protected static String separateur = ";";
	protected static int tailleMax = 512; //taille de la feuille au format paysage
	protected static int tailleTitreEpreuve = 9;
	protected static int tailleTitre1 = 10;
	protected static int tailleTitre2 = 8;
	protected static int tailleLigne1 = 9;
	protected static int tailleLigne2 = 7;
	protected static float[] tabLigne1 = new float[]{1f, 1f, 7f, 6f, 2f, 2f, 2f}; //tableau correspondant à la proportion de la largeur de chaque colonne de la ligne principale
	protected static float[] tabLigne2 = new float[]{1f}; //tableau correspondant à la proportion de la largeur de chaque colonne de la ligne secondaire
	protected static float[] tTitre = new float[]{1f};
	
	@Override
	public PdfDocumentDecorateur addHeader() throws IOException {
		String[] info = epreuve.headerExcel();
		Paragraph paraEpreuve = new ParagraphPdf("Concours " + info[0] + " Epreuve : " + info[1])
				.setFontSize(tailleTitreEpreuve).setBold();
		Paragraph paraDescription = new ParagraphPdf("Dotation : " + info[2] + " - " + info[3] + " Engagés - " + info[4] + " Classés - " + info[5] + " Partants - " + info[6] + " Non Partants")
				.setFontSize(tailleTitreEpreuve);
		addHeadElement(paraEpreuve);
		addHeadElement(paraDescription);
		return this;
	}
	
	@Override
	public PdfDocumentDecorateur addBody() throws IOException {
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		Table table = new TablePdf(new float[]{1});
		int ligneR = 6;
		int ligneD;
	
		Table tableTitre = new TablePdf(tTitre);
		Table tableT1 = new TablePdf(tabLigne1);
		Table tableT2 = new TablePdf(tabLigne2);
		
		process1(tableT1, new String[] {"PLACE", "N°", "CHEVAL", "CAVALIER", "TEMPS", "PENALITE", "PRIX"}, bold, true, tailleTitre1);
		process2(tableT2, "sexe / robe / âge / gains / race / père / mère / père de mère - naisseur : propriétaire", font, tailleTitre2);
		
		tableTitre.addCell(tableT1);
		tableTitre.addCell(tableT2);
		table.addCell(tableTitre);
		addBottomBorder(table.getCell(0, 0));
		
		Sheet sheet = epreuve.getResultat().getSheet(0);
		try {
			//On lit toutes les données jusqu'au mot FIN.
			while (!sheet.getCell(0, ligneR).getContents().contentEquals("FIN")) { 
				String condition = sheet.getCell(1, ligneR).getContents();
				//On vérifie qu'il y a bien un cavalier dans la ligne en vérifiant que la deuxième et troisième colonnes ne sont pas vides 
				if(!(sheet.getCell(1, ligneR).getContents().isEmpty() || sheet.getCell(2, ligneR).getContents().isEmpty())) {
					String[] line1 = epreuve.lectureExcelResultat(ligneR);
					ligneD = epreuve.getNumDossard(ligneR);
					String line2 = epreuve.lectureExcelDepart(ligneD);
					addLine(line1, line2, bold, font, table);
				}
				//Si la deuxième colonne contient l'un de ces trois nom, il y a un formatage spécifique 
				if (condition.equals("BARRAGE:") || condition.equals("AVANT BARRAGE:") || condition.equals("NON PARTANTS:")) {
					String[] line1 = {"","","","----------"+condition.replace(":", "")+"---------", "","",""};
					String line2 = " ";
					addLine(line1, line2, bold, font, table);
				}	
			    ligneR++;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {}
		
		addLeftBorder(table);
		addBodyElement(table);
		return this;
	}
	
	/**
	 * Ajoute le contenu de la ligne à la table gérant les informations principales en mettant les éléments de la ligne aux bons endroits. 
	 * Ajoute de plus les bordures sur les bons éléments.
	 * @param table
	 * @param line
	 * @param font
	 * @param isHeader
	 * @param size
	 */
	protected void process1(Table table, String[] line, PdfFont font, boolean isHeader, float size) {
	    for(int i = 0; i < line.length; i++) {
	        Cell cell = new Cell();
	        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        	cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	        float taille = ((float)500)/18 * tabLigne1[i];
	        size = setSizeFont(line[i], font, size, taille);
	        Paragraph para = new ParagraphPdf(line[i]).setFont(font).setFontSize(size);
	        cell.add(para);
	        table.addCell(cell);
	    }
	}
	
	/**
	 * Ajoute le contenu de la ligne à la table secondaire en mettant les informations aux bons endroits.
	 * @param table
	 * @param line
	 * @param font
	 * @param size
	 */
	protected void process2(Table table, String line, PdfFont font, float size) {
		Cell cell = new Cell();
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    setTailleMax(line, font, size, tailleMax);
	    Paragraph para = new ParagraphPdf(setTailleMax(line, font, size, tailleMax)).setFont(font).setFontSize(size);
	    cell.add(para);
	    table.addCell(cell);
	}

	/**
	 * Ajoute line1 et line2 à la table passée en paramètre.
	 * @param line1
	 * @param line2
	 * @param bold
	 * @param font
	 * @param table
	 */
	public void addLine(String[] line1, String line2, PdfFont bold, PdfFont font, Table table) {
		Table tblBase = new TablePdf(tTitre);
		tblBase.setKeepTogether(true); //On précise à la table que les informations ne doivent pas être séparées lorsque la table chevauche 2 pages
		Table tbl = new TablePdf(tabLigne1);
		Table tbl2 = new TablePdf(tabLigne2);
		process1(tbl, line1, bold, false, tailleLigne1);
		process2(tbl2, line2, font, tailleLigne2);
		tblBase.addCell(tbl);
	    tblBase.addCell(tbl2);
		table.addCell(tblBase);
	}
	
	@Override
	public PdfDocumentDecorateur addFooter() {
		return this;
	}
}
