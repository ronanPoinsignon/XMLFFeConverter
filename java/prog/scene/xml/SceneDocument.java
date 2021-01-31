package prog.scene.xml;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import prog.XMLConverter;
import prog.scene.conversion.SceneConversion;
import utils.Utils;

/**
 * Scene permettant l'affichage des éléments correspondants à la fenêtre de réception des fichiers XML.
 * @author ronan
 *
 */
public class SceneDocument extends Scene {

	private Stage stage;
	private static BorderPane bp = new BorderPane();
	private XMLConverter converter = null;
	
	/**
	 * 
	 * @param stage
	 */
	public SceneDocument(Stage stage) {
		super(bp);
		this.stage = stage;
		this.converter = new XMLConverter();
		addEvent();
		creerScene();
	}
	
	/**
	 * Ajoute les événements de drag and drop pour déposer les fichiers dans l'application.
	 */
	public void addEvent() {
		bp.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != bp
                        && event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
		bp.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {

        		Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    converter.setFichiers(db.getFiles());
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
                stage.setScene(new SceneConversion(stage, converter));
            }
        });
	}
	
	/**
	 * Crée la scene.
	 */
	private void creerScene() {
		bp.setPadding(new Insets(10,50,50,50));
        
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));
    	Label text;

        text = new Label("Déplacer les fichiers à convertir");
        text.setFont(Font.font("Comic sans ms", FontWeight.NORMAL, 28));
        text.setStyle(Utils.getCssTitre());
        
        hb.getChildren().add(text);
        bp.setTop(hb);
	    String css = Utils.getStyleSheet();
	    if(!css.isEmpty())
	    	this.getStylesheets().add(css);
	}

}
