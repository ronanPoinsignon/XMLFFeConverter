package utils;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import prog.alert.AlertErreur;
import prog.alert.AlertException;
import prog.alert.ObservateurBloquant;

/**
 * Classe regroupant les informations plus ou moins utiles à l'exécution du programme comme les chemins d'accès.
 * @author ronan
 *
 */
public class Utils {

	public static final String DOSSIER_CONCOURS = "C:\\fichierXmlAppliChronojump";
	public static final String DOSSIER_RESULTATS = "C:\\fichierXmlAppliChronojump\\";

	/**
	 * Retourne l'image de Chronojump.
	 * @return
	 */
	public static Image getChronoImage() {
		try {
			return new Image(Utils.class.getResource("chronojumpImage.jpg").toString());
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * retourne le css correspondant au titre de l'application.
	 * @return
	 */
	public static String getCssTitre() {
		return "-fx-font: 30 arial;";
	}
	
	/**
	 * Retourne le css correspondant au label des champs de connexion.
	 * @return
	 */
	public static String getCssTexte() {
		return "-fx-font: 20 arial;";
	}
	
	/**
	 * Retourne le fichier css de l'application.
	 * @return
	 */
	public static String getStyleSheet() {
		try {
			return Utils.class.getResource("stylesheet.css").toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Affiche une bulle de texte par rapport à un élément.
	 * @param owner
	 * @param control
	 * @param tooltipText
	 * @param tooltipGraphic
	 */
	public static void showTooltip(Stage owner, Control control, String tooltipText, ImageView tooltipGraphic){
		Point2D p = control.localToScene(250.0, 30.0);
		Tooltip tooltip = new Tooltip();
		tooltip.setText(tooltipText);
		control.setTooltip(tooltip);
		tooltip.setAutoHide(true);
		tooltip.show(owner, p.getX()
		    + control.getScene().getX() + control.getScene().getWindow().getX(), p.getY()
		    + control.getScene().getY() + control.getScene().getWindow().getY());
		Timer t2 = new java.util.Timer();
        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(tooltip::hide);
            }
        }, 3000);
	}
	
	/**
	 * Affiche une alerte correspondant à une erreur critique dans l'application.
	 * @param e
	 */
	public static void showErrorAlert(Exception e) {
		e.printStackTrace();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new AlertErreur(Alert.AlertType.ERROR, e);
				alert.show();
			}
		});
	}
	
	/**
	 * Affiche une alerte.
	 * @param message
	 * @param observateur
	 */
	public static void showExceptionAlert(String message, ObservateurBloquant observateur) {
		observateur.notifierPb();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new AlertException(Alert.AlertType.WARNING, message);
				 alert.showAndWait();
				 observateur.notifier();
			}
		});
	}
}
