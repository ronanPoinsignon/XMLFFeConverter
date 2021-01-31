package prog.alert;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Classe permettant l'affichage d'erreurs ou de warning.
 * @author ronan
 *
 */
public class AlertErreur extends Alert {

	/**
	 * Affiche une erreur.
	 * @param alertType
	 * @param e
	 */
	public AlertErreur(AlertType alertType, Exception e) {
		super(alertType);
		this.setTitle("Exception Dialog");
		this.setHeaderText("Un problème est apparu");
		this.setContentText("Veuillez redémarrer l'application");

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("Erreur : ");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		this.getDialogPane().setExpandableContent(expContent);		
	}

}
