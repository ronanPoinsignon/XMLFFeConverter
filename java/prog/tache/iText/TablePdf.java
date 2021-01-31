package prog.tache.iText;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

/**
 * Classe permettant la création de {@link Table} prenant automatiquement la largeur maximale autorisée.
 * @author ronan
 *
 */
public class TablePdf extends Table {

	/**
	 * 
	 * @param pointColumnWidths
	 */
	public TablePdf(float[] pointColumnWidths) {
		super(UnitValue.createPercentArray(pointColumnWidths)); //On donne un tableau de pourceantage et non de float pour automatiquement créer les colonnes de la tables à la bonne largeur
		this.useAllAvailableWidth(); //On dit à la table d'utiliser toute la largeur disponible et non la largeur requise
	}

}
