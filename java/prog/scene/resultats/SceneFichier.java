package prog.scene.resultats;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import prog.alert.ObservateurBloquant;
import prog.observateurProbleme.ObservateurProbleme;
import prog.tache.TacheConverter;
import prog.tache.fabrique.FabriqueTache;
import prog.tache.fabrique.FabriqueTacheArrivee;
import prog.tache.iText.EpreuveExcel;
import utils.Utils;

/**
 * Scene correspondant à l'affichage des éléments correspondant à la fenêtre de création des listes de résultat.
 * @author Estelle
 *
 */
public class SceneFichier extends Scene {

	DirectoryChooser dossierResultat;
	DirectoryChooser dossierDepart;
	private BorderPane panneau;
	private File cheminDepart;
	private File cheminResultat;
	
	/**
	 * 
	 * @param panneau
	 */
	public SceneFichier(BorderPane panneau) {
		super(panneau);
		this.panneau = panneau;
		this.dossierDepart = new DirectoryChooser();
		this.dossierResultat = new DirectoryChooser();
		creerScene();
	}
	
	/**
	 * Créer les éléments qui seront affichés dans la fenêtre.
	 */
	public void creerScene() {
		panneau.setPadding(new Insets(10,50,50,50));
		
		HBox hb = new HBox();
        hb.setPadding(new Insets(10,10,10,10));
         
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
        
        dossierDepart.setTitle("Dossier liste de départ");       
        dossierResultat.setTitle("Dossier liste de résultat");
        
        Label dossierD = new Label("Dossier des listes de départ");
        TextField nomDossierDepart = new TextField();
        Button btnP1 = new Button("Parcourir");
        
        Label dossierR = new Label("Dossier des listes de résultat");
        TextField nomDossierResultat = new TextField();
        Button btnP2 = new Button("Parcourir");
        
        Button btnValide = new Button("Créer PDF");
        
        gridPane.add(dossierD, 0, 0);
        gridPane.add(nomDossierDepart, 1, 0);
        gridPane.add(btnP1, 2, 0);

        gridPane.add(dossierR, 0, 1);
        gridPane.add(nomDossierResultat, 1, 1);
        gridPane.add(btnP2, 2, 1);
        
        gridPane.add(btnValide, 1, 2);
        
      //Evénement pour chercher le dossier des listes de départ
        EventHandler<ActionEvent> eventDepart = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent event) {
            	cheminDepart = dossierDepart.showDialog(getWindow());
            	if (cheminDepart  != null) nomDossierDepart.setText(cheminDepart.getAbsolutePath());
            }
        };
      //Evénement pour chercher le dossier des listes de résultat
        EventHandler<ActionEvent> eventResultat = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent event) {
            	cheminResultat = dossierResultat.showDialog(getWindow());
            	if (cheminResultat != null) nomDossierResultat.setText(cheminResultat.getAbsolutePath());
            }
        };
      //Evénement pour commencer la création des pdf
        EventHandler<ActionEvent> eventValide = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent event) {
            	
            	File nD = new File (nomDossierDepart.getText());
            	File nR = new File (nomDossierResultat.getText());
            	if (!nD.exists() || !nR.exists()) {
					Utils.showExceptionAlert("Il faut renseigner les dossiers pour créer les PDF.", new ObservateurBloquant());
				} else {
					try {
						creationPDF(nD, nR);
					} catch (BiffException | IOException e) {
						Utils.showExceptionAlert("Les dossiers ne sont pas corrects.", new ObservateurBloquant());
						e.printStackTrace();
					} catch(Exception e) {
						Utils.showExceptionAlert("Un problème est survenu", new ObservateurBloquant());
						e.printStackTrace();
					}
				}
            }
        };
        
        btnP1.setOnAction(eventDepart);
        btnP2.setOnAction(eventResultat);
        btnValide.setOnAction(eventValide);
        
        panneau.setTop(hb);
        panneau.setCenter(gridPane);
        panneau.setMinSize(500, 200);
        
	}
	
	/**
	 * Crée les fichiers PDF correspondant aux listes de résultats.
	 * @param cheminD
	 * @param cheminR
	 * @throws Exception
	 */
	public void creationPDF(File cheminD, File cheminR) throws Exception {
		File[] listeDepart = cheminD.listFiles(); //on récupère les fichiers du dossier
    	File[] listeResultat = cheminR.listFiles();
    	FabriqueTache<EpreuveExcel> fabrique = FabriqueTacheArrivee.getInstance();
    	ObservateurProbleme obsP = new ObservateurProbleme();
    	ObservateurBloquant obsB = new ObservateurBloquant();
    	
    	TacheConverter<EpreuveExcel> tache = new TacheConverter<>(new ProgressBar(), 
    			fabrique.fabrique(new File(Utils.DOSSIER_RESULTATS)), 
    			obsP);
	    if (listeResultat.length == listeDepart.length) {
	    	for (int i = 0; i < listeResultat.length; i++) {
	    		Workbook excelDepart = Workbook.getWorkbook(listeDepart[i]); //on récupère le fichier xls de la liste de départ
	    		Workbook excelResultat = Workbook.getWorkbook(listeResultat[i]); //on récupère le fichier xls de la liste d'arrivée
	    		EpreuveExcel epreuve = new EpreuveExcel(excelDepart, excelResultat);
	    		if (epreuve.getNbEngagesDepart() == epreuve.getNbEngagesResultat()) { //on vérifie qu'il y ai bien le même nombre d'engagés dans les deux fichiers
		    		tache.feed(epreuve); //on ajoute cette épreuve à la tâche
				} else {
					if(!obsB.isBLoque())
						Utils.showExceptionAlert("Les listes ne correspondent pas", obsB);
				}
			}
	    } else {
	    	if(!obsB.isBLoque())
	    		Utils.showExceptionAlert("Il n'y a pas le même nombre de fichiers", obsB);
	    }
	    tache.start();
	}
}
