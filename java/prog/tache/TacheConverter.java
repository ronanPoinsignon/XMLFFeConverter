package prog.tache;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.PdfException;

import javafx.scene.control.ProgressBar;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import prog.alert.ObservateurBloquant;
import prog.observateurProbleme.ObservateurProbleme;
import utils.Utils;

/**
 * Classe permettant l'exécution des différentes tâches.
 * @author ronan
 * @param <T>
 *
 */
public class TacheConverter<T> extends Thread {

	protected List<T> listeGenerics = new ArrayList<>();

	protected ProgressBar progres;
	protected Tache<T> tache;
	protected ObservateurProbleme observateur;
	
	private int compteur = 0;
	
	private boolean isError = false;

	/**
	 * 
	 * @param progres
	 * @param tache
	 * @param observeur
	 */
	public TacheConverter(ProgressBar progres, Tache<T> tache, ObservateurProbleme observeur) {
		this.progres = progres;
		this.tache = tache;
		this.observateur = observeur;
	}
	
	/**
	 * Ajoute l'élément à la tâche.
	 * @param generic
	 */
	public void feed(T generic) {
		if(!listeGenerics.contains(generic))
			listeGenerics.add(generic);
	}
	
	@Override
	public void run() {
		execute(listeGenerics);
		if(!isError)
			progres.setStyle("-fx-accent: green;");
		else
			progres.setStyle("-fx-accent: red;");
	}
	
	/**
	 * Execute la tâche prévue pour chaque composant de la liste.
	 * @param listeGenerics
	 */
	private void execute(List<T> listeGenerics) {
		if(listeGenerics.isEmpty())
			return;
		List<T> listeListablesRatees = new ArrayList<>();
		for(T generic : listeGenerics) {
			try {
				tache.execute(generic);
				progres.setProgress(((double)++compteur)/listeGenerics.size());
			} 
			catch (IOException | WriteException e) {
				listeListablesRatees.add(generic);
			}
			catch(PdfException | BiffException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				if(!isError) {
					observateur.notifyError(e);
					isError = true;
				}
			}
			catch(Exception e) {
				Utils.showErrorAlert(e);
			}
		}
		checkError(listeListablesRatees);
	}
	
	/**
	 * Lorsque qu'une ou plusieurs épreuves ont déjà leur fichier correspondant ouvert, le programme attendra que l'utilisateur ferme les fichiers ouverts pour que ceux-ci puissent être modifiés. 
	 * @param listeListablesRatees
	 */
	public void checkError(List<T> listeListablesRatees) {
		if(!listeListablesRatees.isEmpty()) {
			ObservateurBloquant observateurBloquant = new ObservateurBloquant();
			Utils.showExceptionAlert("Un ou plusieurs fichiers ne peuvent être modifiés car ils sont déjà ouverts. Veuillez les fermer.", observateurBloquant);
			while(observateurBloquant.isBLoque()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			execute(listeListablesRatees);
		}
	}
	
}
