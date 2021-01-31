package prog.tache.iText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import prog.xmlClasse.Epreuve;
import prog.xmlClasse.Equide;

/**
 * Classe permettant la création de fichier pdf correspondant aux listes de départ.
 * @author ronan
 *
 */
public class PdfListeDepart extends PdfDocumentDecorateur { 
	
	public static final String SEPARATEUR = ";";
	protected static final int TAILLE_MAX = 512; //taille de la feuille au format paysage
	protected static final int TAILLE_TITRE_EPREUVE = 9;
	protected static final int TAILLE_TITRE_1 = 10;
	protected static final int TAILLE_TITRE_2 = 8;
	protected static final int TAILLE_LIGNE_1 = 9;
	protected static final int TAILLE_LIGNE_2 = 7;
	protected static final float[] TAB_LIGNE_1 = new float[]{1f, 7f, 6f, 2f, 2f}; //tableau correspondant à la proportion de la largeur de chaque colonne de la ligne principale
	protected static final float[] TAB_LIGNE_2 = new float[]{1f}; //tableau correspondant à la proportion de la largeur de chaque colonne de la ligne secondaire
	protected static final float[] T_TITRE = new float[]{1f};
	
	private Epreuve epreuve;
	
	/**
	 * 
	 * @param document
	 * @param folder
	 * @param epreuve
	 */
	public PdfListeDepart(Document document, File folder, Epreuve epreuve) {
		super(document, folder);
		this.epreuve = epreuve;
	}
	
	@Override
	public PdfDocumentDecorateur addHeader() {
		Paragraph paraEpreuve = new ParagraphPdf("Epreuve " + epreuve.getNum() + "              " + epreuve.getNom_epreuve())
                .setFontSize(TAILLE_TITRE_EPREUVE).setBold();
		Paragraph paraDescription = new ParagraphPdf("CLASSE : " + epreuve.getNom_categorie() + "        " + epreuve.getNbr_engages() + " Engagés" + "   -   " + epreuve.getNom_bareme())
				.setFontSize(TAILLE_TITRE_EPREUVE);
		Table table = new Table(new float[] {1f});
		table.addCell(paraEpreuve);
		table.addCell(paraDescription);
		addHeadElement(table);
		return this;
	}
	
	@Override
	public PdfDocumentDecorateur addBody() throws IOException {
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		Table table = new TablePdf(new float[]{1});
		List<ResultatItext> listeResultats = epreuve.getListeResultatsItext();
		Collections.sort(listeResultats); //On trie les Résultats pour qu'ils soient dans un ordre précis
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		try {
			br1 = new BufferedReader(new FileReader(createCsv1(listeResultats))); //on met le contenu du fichier dans un buffer
			br2 = new BufferedReader(new FileReader(createCsv2(listeResultats)));
			String line1 = br1.readLine();
			String line2 = br2.readLine();
			Table tableTitre = new TablePdf(T_TITRE);
			Table tableT1 = new TablePdf(TAB_LIGNE_1);
			Table tableT2 = new TablePdf(TAB_LIGNE_2);
			process1(tableT1, line1, bold, true, TAILLE_TITRE_1);
			process2(tableT2, line2, font, TAILLE_TITRE_2);
			tableTitre.addCell(tableT1);
			tableTitre.addCell(tableT2);
			table.addCell(tableTitre);
			addBottomBorder(table.getCell(0, 0));
			while ((line1 = br1.readLine()) != null) { //On lit toutes les données
				line2 = br2.readLine();
				Table tblBase = new TablePdf(T_TITRE);
				tblBase.setKeepTogether(true); //On précise à la table que les informations ne doivent pas être séparées lorsque la table chevauche 2 pages
				Table tbl = new TablePdf(TAB_LIGNE_1);
				Table tbl2 = new TablePdf(TAB_LIGNE_2);
				process1(tbl, line1, bold, false, TAILLE_LIGNE_1);
				process2(tbl2, line2, font, TAILLE_LIGNE_2);
			    tblBase.addCell(tbl);
			    tblBase.addCell(tbl2);
			    table.addCell(tblBase);
			}
		}
		catch(IOException e) {
			throw e;
		}
		finally {
			if(br1 != null)
				br1.close();
			if(br2 != null)
				br2.close();
		}
		removeBorder(table);
		addLeftBorder(table);
		addBodyElement(table);
		return this;
	}
	
	@Override
	public PdfListeDepart addFooter() {
		Table table = new Table(new float[] {1});
		addFooterElement(table);
		return this;
	}
	
	/**
	 * Ajoute le contenu de la ligne à la table gérant les informations principales en mettant les éléments de la ligne aux bons endroits. Ajoute de plus les bordures sur les bons éléments.
	 * @param table
	 * @param line
	 * @param font
	 * @param isHeader
	 * @param size
	 */
	protected void process1(Table table, String line, PdfFont font, boolean isHeader, float size) {
		String[] info = line.split(SEPARATEUR, -1); //sépare les informations selon un pattern donné (le -1 signifiant que si la donnée est null, elle est tout de même prise en compte dans le tableau)
	    for(int i = 0; i < info.length; i++) {
	        Cell cell = new Cell();
	        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	        switch(i) {
	        case 0:
	        	cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	        	break;
	        case 3:
	        	if(!isHeader) //ne pas prendre la premiere cellule correspondant à la cellule de présentation des colonnes
	        		cell.setNextRenderer(new LeftCellBorder(cell));
	        	break;
	        case 4:
	        	if(!isHeader)
	        		cell.setNextRenderer(new LeftCellBorder(cell));
	        	break;
	        default:
	        	break;
	        }
	        float taille = ((float)500)/18 * TAB_LIGNE_1[i];
	        size = setSizeFont(info[i], font, size, taille);
	        Paragraph para = new ParagraphPdf(info[i]).setFont(font).setFontSize(size);
	        cell.add(para);
	        cell.setMinHeight(0);
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
		String[] info = line.split(";", -1);
	    for(int i = 0; i < info.length; i++) {
	        Cell cell = new Cell();
	        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	        setTailleMax(info[i], font, size, TAILLE_MAX);
	        Paragraph para = new ParagraphPdf(setTailleMax(info[i], font, size, TAILLE_MAX)).setFont(font).setFontSize(size);
	        cell.add(para);
	        table.addCell(cell);
	    }
	}
	
	/**
	 * Crée un fichier contenant les informations utiles pour la table principale.
	 * @param listeResultats
	 * @return
	 * @throws IOException
	 */
	public File createCsv1(List<ResultatItext> listeResultats) throws IOException {
		if(listeResultats.isEmpty())
			return null;
		File folderCsv = new File(folder.getPath() + "\\csv");
		if(!folderCsv.exists())
			folderCsv.mkdir();
		File fileCsv = new File(folderCsv.getPath() + "\\epr" + listeResultats.get(0).getEpreuve().getNum() + "_1.csv");
		FileOutputStream output = null;
		PrintStream str = null;
		try {
			output = new FileOutputStream(fileCsv);
			str = new PrintStream(output, true, StandardCharsets.UTF_8.toString());
			String nomColonnes = "N°" + SEPARATEUR + "CHEVAL" + SEPARATEUR + "CAVALIER" + SEPARATEUR + "POINTS" + SEPARATEUR + "TEMPS";
			str.write(nomColonnes.getBytes());
			for(int i = 0; i < listeResultats.size(); i++) {
				String res = "\n" + listeResultats.get(i).getFormatDossard() + SEPARATEUR + listeResultats.get(i).getEquide().getNom() + SEPARATEUR + listeResultats.get(i).getCavalier().getNomPrenom() + SEPARATEUR + "" + SEPARATEUR + "";
				str.write(res.getBytes());
			}
		}
		catch(IOException e) {
			throw e;
		}
		finally {
			if(str != null)
				str.close();
			if(output != null)
				output.close();
		}
		return fileCsv;
	}
		
	/**
	 * Crée un fichier contenant les informations utiles pour la table secondaire.
	 * @param listeResultats
	 * @return
	 * @throws IOException
	 */
	public File createCsv2(List<ResultatItext> listeResultats) throws IOException {
		if(listeResultats.isEmpty())
			return null;
		File folderCsv = new File(folder.getPath() + "\\csv");
		if(!folderCsv.exists())
			folderCsv.mkdir();
		File fileTxt = new File(folderCsv.getPath() + "\\epr" + listeResultats.get(0).getEpreuve().getNum() + "_2.csv");
		FileOutputStream output = null;
		PrintStream str = null;
		try {
			output = new FileOutputStream(fileTxt);
			str = new PrintStream(output, true, StandardCharsets.UTF_8.toString());
			String nomColonnes = "sexe/robe/âge/gains/race/père/mère/père de mère - naisseur : propriétaire";
			str.write(nomColonnes.getBytes());
			for(int i = 0; i < listeResultats.size(); i++) {
				Equide equide = listeResultats.get(i).getEquide();
				String sexe = "H";
				if(equide.getSexe().trim().equalsIgnoreCase("jument"))
					sexe = "F";
				String proprietaire = equide.getProprietaire().replaceAll("[\\(]?[0-9]*(%)?[\\)]?", "");
				proprietaire = proprietaire.replaceAll(",[ ]*,", ",").trim();
				String eleveur = equide.getEleveur().replaceAll("[\\(]?[0-9]*(%)?[\\)]?", "");
				eleveur = eleveur.replace(",[ ]*,", ",").trim();
				String robe = "", age = "",race = "";
				int gains = 0;
				if(equide != null) {
					robe = equide.getRobe();
					age = equide.getEquide_age();
					race = equide.getCode_race();
					try {
						gains = Integer.parseInt(equide.getEquide_gain().replaceAll("\\.", ""));
					} catch(NumberFormatException e) {

					}
				}
				String pere = "";
				if(equide.getPere() != null)
					pere = equide.getPere().getNom();
				String mere = "";
				if(equide.getMere() != null)
					mere = equide.getMere().getNom();
				String PereMere = "";
				if(equide.getMere() != null && equide.getMere().getPere() != null)
					PereMere = equide.getMere().getPere().getNom();
				String res = "\n" + sexe + " / " + robe + " / " + age + " / " + gains + " / " + race + " / " + pere + " / " + mere + " / " + PereMere + " - " + eleveur + " : " + proprietaire;
				str.write(res.getBytes());
			}
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
		return fileTxt;
	}
}
