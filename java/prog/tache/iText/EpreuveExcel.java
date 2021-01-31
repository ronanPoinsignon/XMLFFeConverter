package prog.tache.iText;

import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author Estelle
 *
 */
public class EpreuveExcel {
	
	private Workbook depart;
	private Workbook resultat;
	
	/**
	 * Constructeur d'une épreuve.
	 * @param depart Liste de départ d'une épreuve.
	 * @param resultat Liste de résultat d'une épreuve.
	 */
	public EpreuveExcel(Workbook depart, Workbook resultat) {
		this.depart = depart;
		this.resultat = resultat;
	}
	
	/**
	 * Créé un tableau d'information pour remplir l'en-tête du pdf.
	 * @return Retourne les informations d'une épreuve.
	 * @throws IOException
	 */
	public String[] headerExcel() throws IOException {
		String[] tabString = new String[7];
		Sheet sheet = this.resultat.getSheet(0);
		
		//Nom du concours
		tabString[0] = sheet.getCell(0, 0).getContents();
		//
		tabString[1] = sheet.getCell(2, 3).getContents();
		//
		tabString[2] = sheet.getCell(2, 1).getContents();
		tabString[3] = sheet.getCell(5, 1).getContents();
		tabString[4] = sheet.getCell(2, 2).getContents();
		tabString[5] = sheet.getCell(5, 2).getContents();
		tabString[6] = sheet.getCell(5, 3).getContents();
		
		return tabString; 
	}
	
	/**
	 * Lit une ligne du fichier de liste de résultat et créé une chaîne de caractères.
	 * @param ligne Numéro de ligne du fichier Excel à lire.
	 * @return Retourne un tableau contenant les données de la ligne lue.
	 * @throws IOException
	 */
	public String[] lectureExcelResultat(int ligne) throws IOException {
		
		String[] tabString = new String[7];
		Sheet sheet = this.resultat.getSheet(0);
		tabString[0] = sheet.getCell(0, ligne).getContents();
		tabString[1] = sheet.getCell(1, ligne).getContents();
		tabString[2] = sheet.getCell(2, ligne).getContents();
		tabString[3] = sheet.getCell(4, ligne).getContents();
		tabString[4] = sheet.getCell(5, ligne).getContents();
		tabString[5] = sheet.getCell(6, ligne).getContents();
		tabString[6] = sheet.getCell(7, ligne).getContents();
		
		return tabString;
	}
	
	/**
	 * Lit une ligne du fichier de liste de départ et crée une chaîne de caractères formatée.
	 * @param ligne Numéro de ligne du fichier Excel à lire. 
	 * @return Retourne un tableau contenant les données de la ligne lue.
	 * @throws IOException
	 */
	public String lectureExcelDepart(int ligne) throws IOException {
		Sheet sheet = this.depart.getSheet(0);
		return sheet.getCell(11, ligne).getContents() + " / " + sheet.getCell(12, ligne).getContents() + " / "
				+ sheet.getCell(13, ligne).getContents() + " / " + sheet.getCell(14, ligne).getContents() + " / "
				+ sheet.getCell(15, ligne).getContents() + " / " + sheet.getCell(16, ligne).getContents() + " / "
				+ sheet.getCell(17, ligne).getContents() + " / " + sheet.getCell(18, ligne).getContents() + " - "
				+ sheet.getCell(19, ligne).getContents() + " : " + sheet.getCell(20, ligne).getContents();		
	}
	
	/**
	 * Retourne le numéro de dossard correspondant à la ligne.
	 * @param ligne Numéro de ligne du fichier Excel à lire. 
	 * @return Numéro de dossard.
	 */
	public int getNumDossard(int ligne) {
		return Integer.parseInt(this.resultat.getSheet(0).getCell(1, ligne).getContents());
	}
	
	/**
	 * Retourne le numéro d'épreuve.
	 * @return Numéro d'épreuve.
	 */
	public int getNumEpreuve() {
		return Integer.parseInt(this.resultat.getSheet(0).getCell(2, 3).getContents().substring(0, 2));
	}
	
	/**
	 * Retourne le nom du concours.
	 * @return Nom du concours.
	 */
	public String getNomConcours() {
		return this.resultat.getSheet(0).getCell(0, 0).getContents().replaceAll("\\s+", "");
	}

	/**
	 * Retourne le Workbook de la liste de résultat.
	 * @return Liste de résultat.
	 */
	public Workbook getResultat() {
		return resultat;
	}

	/**
	 * Retourne le Workbook de la liste de résultat.
	 * @return Liste de départ.
	 */
	public Workbook getDepart() {
		return depart;
	}
	
	/**
	 * Retourne le nombre d'engagés écrit dans la liste de départ.
	 * @return Nombre d'engagés.
	 */
	public int getNbEngagesDepart() {
		int cpt = 0;
		try {
			while(!depart.getSheet(0).getCell(0, cpt).getContents().isEmpty()) {
				cpt++;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			
		}
		return cpt-1; //-1 pour enlever le nom de la colonne
	}
	/**
	 * Retourne le nombre d'engagés écrit dans la liste de résultat.
	 * @return Nombre d'engagés.
	 */
	public int getNbEngagesResultat() {
		int res = Integer.parseInt(resultat.getSheet(0).getCell(5, 1).getContents());
		System.out.println(res);
		return res;
	}
}
