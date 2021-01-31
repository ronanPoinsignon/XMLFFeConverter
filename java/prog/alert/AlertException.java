package prog.alert;

import javafx.scene.control.Alert;

/**
 * Classe permettant l'affichage d'informations diverses.
 * @author ronan
 *
 */
public class AlertException extends Alert {
	
	/**
	 * 
	 * @param alertType
	 * @param message
	 */
	public AlertException(AlertType alertType, String message) {
		super(alertType);
		
		this.setTitle("Attention");
        
		this.setHeaderText(null);
		this.setContentText(message);
	}
	
}
