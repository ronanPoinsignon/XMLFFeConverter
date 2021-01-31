package prog.tache.iText;

import java.io.File;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import prog.tache.Tache;
import utils.Utils;

/**
 * Classe permettant la création des listes d'arrivée.
 * @author ronan
 *
 */
public class TacheListeArrivee extends Tache<EpreuveExcel> {

	public TacheListeArrivee(File folder) {
		super(folder);
	}

	@Override
	public void execute(EpreuveExcel epreuve) throws Exception {
		File folder = new File(Utils.DOSSIER_RESULTATS + epreuve.getNomConcours());
    	if(!folder.exists()) folder.mkdirs();
		File folderDest = new File(folder.getPath() + "\\" + epreuve.getNumEpreuve() + ".pdf");
		PdfWriter pdfWriter = new PdfWriter(folderDest);
		PdfDocument pdf = new PdfDocument(pdfWriter);
		PdfDocumentDecorateur document = new PdfListeArrivee(new Document(pdf), folder, epreuve);
		document.addHeader().addBody().getResult(true, 0, 0);
	}

}
