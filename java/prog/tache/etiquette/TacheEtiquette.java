package prog.tache.etiquette;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import prog.tache.Tache;
import prog.xmlClasse.Cavalier;
import prog.xmlClasse.Epreuve;
import prog.xmlClasse.Equide;
import utils.Utils;

/**
 * Classe permettant la création des étiquettes et du fichier texte correspondant.
 * @author ronan
 *
 */
public class TacheEtiquette extends Tache<Epreuve> {

	private static final int NB_ETIQUETTES_PAR_FICHE = 4;
	public static final String SEPARATEUR = ";";
	private int cpt = 1;

	/**
	 * 
	 * @param folder
	 */
	public TacheEtiquette(File folder) {
		super(new File(folder.getPath() + "\\etiquettes"));
	}

	@Override
	public void execute(Epreuve epreuve) {
		try {
			this.addLineToCsv(epreuve.getConcours().getListeResultatsTxt());
		} catch (IOException e) {
			Utils.showErrorAlert(e);
		}
		File folderEtiquettes = new File(folder.getPath() + "\\images\\" + epreuve.getNum());
		List<ResultatImageEtiquette> listeEtiquettes = epreuve.getListeEtiquettes();
		Collections.sort(listeEtiquettes);
		int nbEtiquettes = listeEtiquettes.size();
		int nbFichesMin = (int) Math.floor(((double)nbEtiquettes)/NB_ETIQUETTES_PAR_FICHE); //floor arrondit le résultat à l'entier inférieur
		int[] tabInt = new int[NB_ETIQUETTES_PAR_FICHE];
		int reste = nbEtiquettes - nbFichesMin * tabInt.length;
		for(int i = 0; i < tabInt.length; i++) {
			tabInt[i] = (int) Math.floor(((double)nbEtiquettes)/tabInt.length);
		}
		for(int i = 0; i < reste; i++) { //boucle pour ajouter par la suite les dernières images si le reste n'est pas nul
			tabInt[i] = tabInt[i] + 1;
		}
		ArrayList<ArrayList<ResultatImageEtiquette>> listeArray = new ArrayList<>();
		for(int i = 0; i < tabInt.length; i++)
			listeArray.add(new ArrayList<ResultatImageEtiquette>()); //regroupe les listes d'étiquettes (il y a autant de liste que d'étiquettes par image)
		for(int i = 0; i < tabInt.length; i++) { //ce bloc sert à séparer les étiquettes pour les séparer également entre les différentes listes. 
			for(int j = 0; j < tabInt[i]; j++) {
				int position = 0;
				for(int k = 0; k < i; k++) {
					position += listeArray.get(k).size();
				}
				listeArray.get(i).add(listeEtiquettes.get(j + position));
			}
		}
		gereImagelisteArray(listeArray, folderEtiquettes);
	}
	
	/**
	 * Modifie la {@link BufferedImage} en ajoutant les informations des {@link ResultatImageEtiquette}.
	 * @param image
	 * @param etiquette
	 */
	public void updateImage(BufferedImage image, ResultatImageEtiquette etiquette) {
		Cavalier cavalier = etiquette.getCavalier();
		Equide equide = etiquette.getEquide();
		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(18f));
		g.setColor(Color.BLACK);
		g.drawString("Epr : " + etiquette.getNumEpreuve(), 450, 30);
		g.drawString("N° : " + etiquette.getNumDepart(), 550, 30);
		g.drawString("Engagés : " + etiquette.getNbEng(), 450, 75);
		g.setFont(g.getFont().deriveFont(20f));
		g.drawString(etiquette.getEquide().getNom(), 50, 50);
		g.drawString(cavalier.getNomPrenom(), 50, 110);
		g.setFont(g.getFont().deriveFont(16f));
		g.drawString("sire : " + equide.getSire() + "          GAINS : " + equide.getEquide_gain(), 50, 75);
		g.drawString("cat : " + cavalier.getCategorie() + " ; cAge : " + cavalier.getCode_age() + " ; dep : " + cavalier.getDepartement_cavalier() + " ; cre : " + cavalier.getCre() +" ; naiss : " + cavalier.getDnaiss() + " ; lic : " + cavalier.getLic(), 50, 135);
		g.drawString("club : " + cavalier.getNom_club(), 50, 160);
		g.dispose();
	}
	
	/**
	 * Regroupe les images ensemble. La taille dépend de la valeur de NB_ETIQUETTES_PAR_FICHE.
	 */
	protected void gereImagelisteArray(ArrayList<ArrayList<ResultatImageEtiquette>> listeArray, File folderEtiquettes) {
		BufferedImage image = null;
		BufferedImage[] tabImagesGroupe = new BufferedImage[NB_ETIQUETTES_PAR_FICHE];
		for(int i = 0; i < listeArray.get(0).size(); i++) {
			for(int j = 0; j < tabImagesGroupe.length; j++) {
				try {
					ResultatImageEtiquette etiquette = listeArray.get(j).get(i);
					image = ImageIO.read(Utils.class.getResource("etiquette_template.png"));
					updateImage(image, etiquette);
					tabImagesGroupe[j] = image;
				}
				catch(IndexOutOfBoundsException e) {
					break;
				}
				catch (IOException e) {
					Utils.showErrorAlert(e);
				}
			}
			try {
				saveImage(tabImagesGroupe, folderEtiquettes);
				tabImagesGroupe = new BufferedImage[NB_ETIQUETTES_PAR_FICHE];
			} catch (IOException e1) {
				Utils.showErrorAlert(e1);
			}		}
	}
	
	/**
	 * Fusionne le tableau d'images pour en obtenir une seule et ajoute cette image dans le dossier correspondant.
	 * @param tabImages
	 * @param folderEtiquettes
	 * @throws IOException
	 */
	public void saveImage(BufferedImage[] tabImages, File folderEtiquettes) throws IOException {
		int width = 0;
		if(tabImages.length > 1)
			width = tabImages[0].getWidth() * 2;
		else
			width = tabImages[0].getHeight();
		int height = (int) Math.ceil(((double)tabImages.length)/2) * tabImages[0].getHeight();
		BufferedImage newImage = new BufferedImage(width, height,
		    BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		g2.setColor(oldColor);
		for(int i = 0; i < tabImages.length; i++) {
			g2.drawImage(tabImages[i], null, (i%2) * tabImages[0].getWidth(), (i/2) * tabImages[0].getHeight());
		}
		g2.dispose();
		if(!folderEtiquettes.exists())
			folderEtiquettes.mkdirs();
		ImageIO.write(newImage, "png", new File(folderEtiquettes.getPath() + "\\" + cpt + ".png"));
		cpt++;
	}
	
	/**
	 * Ajoute la liste de {@link ResultatTxtEtiquette} dans le fichier txt.
	 * @param listeResultat
	 * @throws IOException
	 */
	public void addLineToCsv(List<ResultatTxtEtiquette> listeResultat) throws IOException {
		File folderTxt = new File(folder.getPath() + "\\txt");
		File fichierTxt = new File(folderTxt.getPath() + "\\etiquettes.txt");
		if(!folderTxt.exists())
			folderTxt.mkdirs();
		String titre = "LIEU;EPREUVE;CLASSE;ENG;NUMDEP;NOMCHE;SIRE;GAINS;PERCHE;NOM;LICENCE;LIGUE;CATEGORIE;JUNSEN;CLUCAV";
		StringBuilder strB = new StringBuilder().append(titre);
		for(ResultatTxtEtiquette res : listeResultat) {
			String line = "\n" + res.getLieu() + SEPARATEUR + res.getNumEpreuve() + SEPARATEUR + res.getClasse() +
					SEPARATEUR + res.getEng() + SEPARATEUR + res.getNumdep() + SEPARATEUR + res.getNomche() +
					SEPARATEUR + res.getSire() + SEPARATEUR + res.getGains() + SEPARATEUR + res.getPerche() +
					SEPARATEUR + res.getNom() + SEPARATEUR + res.getLicence() + SEPARATEUR + res.getLigue() +
					SEPARATEUR + res.getCategorie() + SEPARATEUR + res.getJunsen() + SEPARATEUR + res.getClucav();
			strB.append(line);
		}
		FileOutputStream output = null;
		PrintStream str = null;
		try {
			output = new FileOutputStream(fichierTxt);
			str = new PrintStream(output, true, StandardCharsets.UTF_8.toString());
			str.write(strB.toString().getBytes());
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
}
