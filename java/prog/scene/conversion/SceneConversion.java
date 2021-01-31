package prog.scene.conversion;

import java.io.File;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import prog.XMLConverter;
import utils.Utils;

/**
 * Scene gérant l'affichage des éléments de la fenêtre de conversion.
 * @author ronan
 *
 */
public class SceneConversion extends Scene {
		
	
	private Stage stage;
	private XMLConverter converter;
	private Label text;
	
	/**
	 * 
	 * @param stage
	 * @param converter
	 */
	public SceneConversion(Stage stage, XMLConverter converter) {
		super(new PaneConversion(converter));
		BorderPane pane = (BorderPane) getRoot().getChildrenUnmodifiable().get(0);
		HBox hb = (HBox)pane.getChildren().get(0);
		this.text = (Label)hb.getChildren().get(0);
		this.stage = stage;
		this.converter = converter;
		addEvent();
		convertir();
	}
	
	/**
	 * Ajoute une boîte de dialogue lorsque l'utilisateur quitte l'application.
	 */
	private void addEvent() {
		stage.setOnCloseRequest(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Vous êtes sur le point de quitter l'application");
			alert.setContentText("Êtes vous sûr? Cela arrêtera peut-être les processus de fond");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    System.exit(0);
			} 
			else {
				event.consume();
			}
		});
	}
	
	/**
	 * Convertit les fichiers dans le dossier correspondant à la valeur
	 * de la variable DOSSIER_CONCOURS de la classe {@link Utils}
	 */
	private void convertir() {
		Thread thread = new Thread(new Runnable() { //Ici un thread est utilisé pour que le thread graphique garde la main sur l'interface et que tout se déroule en tâche de fond
			@Override
			public void run() {
				converter.conversion(stage, new File(Utils.DOSSIER_CONCOURS), text);
			}
		});
		thread.start();
	}

}
