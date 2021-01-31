package prog.alert;

/**
 * Classe permettant de vérifier si une erreur a déjà été trouvé ou non.
 * @author ronan
 *
 */
public class ObservateurBloquant {

	private boolean bloque = false;
	
	/**
	 * 
	 */
	public ObservateurBloquant() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isBLoque() {
		return this.bloque;
	}
	
	/**
	 * Met à faux l'observateur.
	 */
	public void notifier() {
		this.bloque = false;
	}
	
	public void notifierPb() {
		this.bloque = true;
	}
}
