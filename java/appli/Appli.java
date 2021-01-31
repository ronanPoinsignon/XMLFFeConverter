package appli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import prog.scene.resultats.SceneFichier;
import prog.scene.xml.SceneDocument;
import utils.Utils;

/**
 * Classe main de l'application.
 * @author ronan
 *
 */
public class Appli extends Application {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		stage.setTitle("Chronojump");
		stage.getIcons().add(Utils.getChronoImage()); //ajoute l'image chronojump sur la fenêtre en haut à gauche
		Scene scene = choisirScene(stage);
		if(scene == null)
			return;
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Retourne la Scene correspondant à l'application de conversion de fichiers XML.
	 * @param stage
	 * @param folder
	 * @return Scene
	 */
	private Scene getSceneDocument(Stage stage) {
		return new SceneDocument(stage);
	}
	
	/**
	 * Retourne la Scene correspondant à l'application de création des listes d'arrivée.
	 * @return Scene
	 */
	private Scene getSceneFichier() {
		return new SceneFichier(new BorderPane());
	}

	/**
	 * Demande à l'utilisateur quelle application il souhaite lancer et retourne la Scene correspondante.
	 * @param stage
	 * @return
	 */
	private Scene choisirScene(Stage stage) {
		ArrayList<String> choices = new ArrayList<>();
		String xml = "Fichier XML";
		String resultat = "Listes Résultats";
		choices.add(xml);
		choices.add(resultat);
		HashMap<String, Scene> map = new HashMap<>();
		map.put(xml, getSceneDocument(stage));
		map.put(resultat, getSceneFichier());
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(xml, choices);
		dialog.setTitle("Chronojump");
		Stage stg = (Stage) dialog.getDialogPane().getScene().getWindow(); //récupère le stage de la fenêtre de dialogue
		stg.getIcons().add(Utils.getChronoImage());
		dialog.setHeaderText("Veuillez choisir une application à lancer");

		Optional<String> result = dialog.showAndWait(); //attend la réponse de l'utilisateur
		if(result.isPresent())
			return map.get(result.get()); //on récupère la valeur de la réponse donnée par l'utilisateur
		return null;
	}
	
}
