package prog.observateurProbleme;

import utils.Utils;

/**
 * 
 * @author ronan
 *
 */
public class ObservateurProbleme implements ObservableProbleme {

	@Override
	public void notifyError(Exception e) {
		Utils.showErrorAlert(e);
	}

}
