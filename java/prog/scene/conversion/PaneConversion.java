package prog.scene.conversion;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prog.XMLConverter;
import prog.scene.PaneScene;
import utils.Utils;

/**
 * Ajoute les éléments utiles pour la fenêtre de conversion.
 * @author ronan
 *
 */
public class PaneConversion extends PaneScene {

	private static final double tailleBarreProgression = 500;
	
	private XMLConverter converter;
	
	/**
	 * 
	 * @param converter
	 */
	public PaneConversion(XMLConverter converter) {
		this.converter = converter;
		creerScene();
	}
	
	/**
	 * Crée la scene.
	 */
	private void creerScene() {
		Label text;
		BorderPane pane = new BorderPane();
		text = new Label("Conversion en cours");
	    text.setFont(Font.font("Comic sans ms", FontWeight.NORMAL, 28));
	    text.setStyle(Utils.getCssTitre());
        
        HBox hb = new HBox();
        hb.getChildren().add(text);
        hb.setPadding(new Insets(20,20,20,30));

        pane.setTop(hb);
        pane.setPadding(new Insets(10,50,50,50));
		
		String css = Utils.getStyleSheet();
	    if(!css.isEmpty())
	    	this.getStylesheets().add(css);
	    pane.setBottom(getProgressBar());
	    this.getChildren().add(pane);
	}
	
	/**
	 * Ajoute les barres de progression à la fenêtre.
	 * @return GridPane
	 */
	public GridPane getProgressBar() {
		Label descriptionExcel = new Label("Création des fichiers xls");
        Label descriptionImage = new Label("Création des etiquettes");
        Label descriptionDepart = new Label("Création des listes de départ");
        Label descriptionInfo = new Label("Création des listes d'informations");
        
        ProgressBar progressBarExcel = converter.getpExcel();
        progressBarExcel.setMinWidth(tailleBarreProgression);
        
        ProgressBar progressBarImage = converter.getpEtiquette();
        progressBarImage.setMinWidth(tailleBarreProgression);
        
        ProgressBar progressBarDepart = converter.getpDepart();
        progressBarDepart.setMinWidth(tailleBarreProgression);
        
        ProgressBar progressBarInfo = converter.getpInfo();
        progressBarInfo.setMinWidth(tailleBarreProgression);
        
        GridPane progress = new GridPane();
        progress.add(descriptionExcel, 0, 0);
        progress.add(progressBarExcel, 0, 1);
        progress.add(descriptionImage, 0, 2);
        progress.add(progressBarImage, 0, 3);
        progress.add(descriptionDepart, 0, 4);
        progress.add(progressBarDepart, 0, 5);
        progress.add(descriptionInfo, 0, 6);
        progress.add(progressBarInfo, 0, 7);
        return progress;
	}
}
