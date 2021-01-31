package prog.observateurProbleme;

/**
 * 
 * @author ronan
 *
 */
public interface ObservableProbleme {

	/**
	 * Notifie l'observateur lorsqu'une erreur a été captée.
	 * @param e
	 */
	public abstract void notifyError(Exception e);
}
