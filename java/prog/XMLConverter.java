package prog;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.alert.AlertErreur;
import prog.observateurProbleme.ObservateurProbleme;
import prog.tache.Tache;
import prog.tache.TacheConverter;
import prog.tache.fabrique.FabriqueTache;
import prog.tache.fabrique.FabriqueTacheDepart;
import prog.tache.fabrique.FabriqueTacheEtiquette;
import prog.tache.fabrique.FabriqueTacheExcel;
import prog.tache.fabrique.FabriqueTacheInfo;
import prog.xmlClasse.Cavalier;
import prog.xmlClasse.Coach;
import prog.xmlClasse.Concours;
import prog.xmlClasse.Engagement;
import prog.xmlClasse.Engageur;
import prog.xmlClasse.Epreuve;
import prog.xmlClasse.Equide;
import prog.xmlClasse.Equipe;
import prog.xmlClasse.Membre;
import prog.xmlClasse.Mere;
import prog.xmlClasse.Officiel;
import prog.xmlClasse.Officiels;
import prog.xmlClasse.Organisateur;
import prog.xmlClasse.Pere;
import prog.xmlClasse.Profil;
import utils.Utils;

/**
 * Classe convertissant le fichier XML et effectuant les différentes {@link Tache}.
 * @author ronan
 *
 */
public class XMLConverter {

	
	protected DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	protected DocumentBuilder builder;
	
	protected ProgressBar pExcel, pEtiquette, pDepart, pInfo;
	protected List<File> listeFichiers = new ArrayList<>();
	
	/**
	 * 
	 */
	public XMLConverter() {
		this.pExcel = new ProgressBar(0);
		this.pEtiquette =  new ProgressBar(0);
		this.pDepart =  new ProgressBar(0);
		this.pInfo =  new ProgressBar(0);
		try {
		 	this.builder = factory.newDocumentBuilder();
		}
		catch (final ParserConfigurationException e) {
			Alert alert = new AlertErreur(Alert.AlertType.ERROR, e);
			alert.showAndWait();
		}
	}
	
	/**
	 * Convertit la liste de fichiers. Chaque fichier sera traité à la suite. 
	 * Lorsque que l'appication a detecté un fichier xml possédant un concours, ce fichier sera converti en un Objet concours. 
	 * Cet objet possèdera toutes les sous classes trouvées dans le fichier que ce soit les épreuves, les cavaliers etc.
	 * @param stage
	 * @param folder
	 * @param label
	 */
	public void conversion(Stage stage, File folder, Label label) {
		boolean isError = false;
		for(File fichier : listeFichiers) {
			pExcel.setStyle("-fx-accent: blue;");
			pEtiquette.setStyle("-fx-accent: blue;");
			pDepart.setStyle("-fx-accent: blue;");
			pInfo.setStyle("-fx-accent: blue;");
			pExcel.setProgress(0);
			pEtiquette.setProgress(0);
			pDepart.setProgress(0);
			pInfo.setProgress(0);
			Document document = null;
			try {
				document = builder.parse(fichier);
				Concours concours = (Concours) convertNode(document.getDocumentElement(), null);
				if(concours == null)
					return;
				List<Thread> listeThread = getListeThread(folder, concours);
				executeThreadList(listeThread);
			} catch (SAXException | MalformedByteSequenceException e) {
				if(!isError)
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Utils.showTooltip(stage, label, "Un ou plusieurs fichiers ne peuvent être lus", null);
						}
					});
				isError = true;
			}
			catch (IOException e) {
				Utils.showErrorAlert(e);
			} 
		}
		Platform.exit(); //l'application se ferme à la fin de ses tâches
		System.exit(0);
	}
	
	/**
	 * Convertit le noeud en Concours. Retourne null si aucun concours n'a été trouvé.
	 * @param node
	 * @param object
	 * @return
	 */
	public Concours convertNode(Node node, Object object) {
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node noeud = node.getChildNodes().item(i);
			if(noeud.getNodeType() == Node.ELEMENT_NODE) {
				switch(noeud.getNodeName()) {
				case "concours":
					Concours concours = new Concours();
					ajouter(noeud, concours);
					convertNode(noeud, concours);
					return concours;
				case "organisateur":
					Organisateur organisateur = new Organisateur();
					ajouter(noeud, organisateur);
					((Concours) object).setOrganisateur(organisateur);
					organisateur.setConcours((Concours) object);
					convertNode(noeud, organisateur);
					break;
				case "epreuve":
					Epreuve epreuve = new Epreuve();
					ajouter(noeud, epreuve);
					((Concours) object).getListeEpreuves().add(epreuve);
					epreuve.setConcours((Concours) object);
					convertNode(noeud, epreuve);
					break;
				case "engagement":
					Engagement engagement = new Engagement();
					ajouter(noeud, engagement);
					((Epreuve) object).getListeEngagements().add(engagement);
					engagement.setEpreuve((Epreuve) object);
					convertNode(noeud, engagement);
					break;
				case "cavalier":
					Cavalier cavalier = new Cavalier();
					ajouter(noeud, cavalier);
					if(object instanceof Engagement) {
						((Engagement) object).setCavalier(cavalier);
						cavalier.setEngagement((Engagement) object);
					}
					else {
						((Membre) object).setCavalier(cavalier);
						cavalier.setMembre((Membre) object);
					}
					convertNode(noeud, cavalier);
					break;
				case "equide":
					Equide equide = new Equide();
					ajouter(noeud, equide);
					if(object instanceof Engagement) {
						((Engagement) object).setEquide(equide);
						equide.setEngagement((Engagement) object);
					}
					else {
						((Membre) object).setEquide(equide);
						equide.setMembre((Membre) object);
					}
					convertNode(noeud, equide);
					break;
				case "pere":
					Pere pere = new Pere();
					ajouter(noeud, pere);
					if(object instanceof Mere) {
						((Mere) object).setPere(pere);
					}
					else {
						((Equide) object).setPere(pere);
						pere.setEquide((Equide) object);
					}
					convertNode(noeud, pere);
					break;
				case "mere":
					Mere mere = new Mere();
					ajouter(noeud, mere);
					((Equide) object).setMere(mere);
					mere.setEquide((Equide) object);
					convertNode(noeud, mere);
					break;
				case "engageur":
					Engageur engageur = new Engageur();
					ajouter(noeud, engageur);
					if(object instanceof Engagement) {
						((Engagement) object).setEngageur(engageur);
						engageur.setEngagement((Engagement) object);
					}
					else {
						((Equipe) object).setEngageur(engageur);
						engageur.setEquipe((Equipe) object);
					}
					convertNode(noeud, engageur);
					break;
				case "coach":
					Coach coach = new Coach();
					ajouter(noeud, coach);
					if(object instanceof Engagement) {
						((Engagement) object).setCoach(coach);
						coach.setEngagement((Engagement) object);
					}
					else {
						((Equipe) object).setCoach(coach);
						coach.setEquipe((Equipe) object);
					}
					convertNode(noeud, coach);
					break;
				case "profil":
					Profil profil = new Profil();
					ajouter(noeud, profil);
					((Epreuve) object).setProfil(profil);
					profil.setEpreuve((Epreuve) object);
					convertNode(noeud, profil);
					break;
				case "officiels":
					Officiels officiels = new Officiels();
					ajouter(noeud, officiels);
					((Profil) object).setOfficiels(officiels);
					officiels.setProfil((Profil) object);
					convertNode(noeud, officiels);
					break;
				case "officiel":
					Officiel officiel = new Officiel();
					ajouter(noeud, officiel);
					((Officiels) object).getListeOfficiels().add(officiel);
					officiel.setOfficiels((Officiels) object);
					convertNode(noeud, officiel);
					break;
				case "equipe":
					Equipe equipe = new Equipe();
					ajouter(noeud, equipe);
					((Epreuve) object).getListeEquipes().add(equipe);
					equipe.setEpreuve((Epreuve) object);
					convertNode(noeud, equipe);
					break;
				case "membre":
					Membre membre = new Membre();
					ajouter(noeud, membre);
					((Equipe) object).getListeMembres().add(membre);
					membre.setEquipe((Equipe) object);
					convertNode(noeud, membre);
					break;
				}
			}
		}
		return null;
	}
	
	/**
	 * Ajoute à l'objet toutes les informations du noeud correspondant.
	 * @param noeud
	 * @param objet
	 */
	public static void ajouter(Node noeud, Object objet) {
		Field[] tabField = objet.getClass().getDeclaredFields(); //on récupère les variables de la classe de l'objet
		Method[] methods = objet.getClass().getMethods(); //on récupère les méthodes de la classe de l'objet
		for(int y = 0; y < methods.length; y++) {
			for(int z = 0; z < tabField.length; z++) {
				if(methods[y].getName().toLowerCase().trim().equals(("set" + tabField[z].getName().toLowerCase()).trim()) && tabField[z].getType() == String.class) { //on vérifie que la méthode appelé est bien un setter et que le type de setter correspond bien à un String
					try {
						methods[y].invoke(objet, noeud.getAttributes().getNamedItem(tabField[z].getName()).getNodeValue()); //on appelle la méthode en lui donnant l'objet qui sera utilisé et ses paramètres
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | DOMException e) {
						Utils.showErrorAlert(e);
					}
					break;
				}
			}
		}
	}
	
	/**
	 * Retourne la liste de threads concernant les différentes {@link Tache} à effectuer.
	 * @param folder
	 * @param concours
	 * @return
	 */
	public List<Thread> getListeThread(File folder, Concours concours) {
		ArrayList<Thread> listeThread = new ArrayList<>();
		File dossierConcours = new File(folder.getPath() + "\\" + concours.getNum());
		List<Epreuve> listeEpreuves = concours.getListeEpreuves();
		List<FabriqueTache<Epreuve>> listeTachesEpreuve = new ArrayList<>();
		List<FabriqueTache<Concours>> listeTachesConcours = new ArrayList<>();
		ProgressBar[] progressEpreuve = new ProgressBar[3];
		ProgressBar[] progressConcours = new ProgressBar[1];
		
		listeTachesEpreuve.add(FabriqueTacheExcel.getInstance());
		listeTachesEpreuve.add(FabriqueTacheEtiquette.getInstance());
		listeTachesEpreuve.add(FabriqueTacheDepart.getInstance());
		progressEpreuve[0] = pExcel;
		progressEpreuve[1] = pEtiquette;
		progressEpreuve[2] = pDepart;

		listeTachesConcours.add(FabriqueTacheInfo.getInstance());
		progressConcours[0] = pInfo;
		
		ObservateurProbleme observateur = new ObservateurProbleme();
		for(int i = 0; i < listeTachesEpreuve.size(); i++) {
			FabriqueTache<Epreuve> fabrique = listeTachesEpreuve.get(i);
			try {
				TacheConverter<Epreuve> tache = new TacheConverter<Epreuve>(progressEpreuve[i], fabrique.fabrique(dossierConcours), observateur);
				for(Epreuve epreuve : listeEpreuves) {
					tache.feed(epreuve);
				}
				listeThread.add(tache);
			} 
			catch (BiffException | WriteException | IOException e) {
				Utils.showErrorAlert(e);
			}
		}
		for(int i = 0; i < listeTachesConcours.size(); i++) {
			FabriqueTache<Concours> fabrique = listeTachesConcours.get(i);
			try {
				TacheConverter<Concours> tache = new TacheConverter<Concours>(progressConcours[i], fabrique.fabrique(dossierConcours), observateur);
				tache.feed(concours);
				listeThread.add(tache);
			} 
			catch (BiffException | WriteException | IOException e) {
				Utils.showErrorAlert(e);
			}
		}
		return listeThread;
	}
	
	/**
	 * Ececute la liste de thread et les join au thread principal pour attendre
	 *  que tous les threads aient fini leur travail.
	 * @param listeThread
	 */
	public void executeThreadList(List<Thread> listeThread) {
		for(Thread thread : listeThread) {
			thread.start();
		}
		for(Thread thread : listeThread) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ProgressBar getpExcel() {
		return pExcel;
	}

	/**
	 * 
	 * @return
	 */
	public ProgressBar getpEtiquette() {
		return pEtiquette;
	}
	
	/**
	 * 
	 * @return
	 */
	public ProgressBar getpDepart() {
		return pDepart;
	}
	
	/**
	 * 
	 * @return
	 */
	public ProgressBar getpInfo() {
		return pInfo;
	}

	/**
	 * 
	 * @param tabFichier
	 */
	public void setFichiers(List<File> tabFichier) {
		this.listeFichiers = tabFichier;
	}
}
