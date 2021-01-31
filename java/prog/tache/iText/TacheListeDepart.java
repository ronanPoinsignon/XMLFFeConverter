package prog.tache.iText;

import java.io.File;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import prog.tache.Tache;
import prog.xmlClasse.Epreuve;

/**
 * Classe permettant la création des listes de départ.
 * @author ronan
 *
 */
public class TacheListeDepart extends Tache<Epreuve> {
	
	/**
	 * 
	 * @param folder
	 */
	public TacheListeDepart(File folder) {
		super(new File(folder.getPath() + "\\listeDepart"));
	}

	@Override
	public void execute(Epreuve epreuve) throws Exception {
		if(!this.folder.exists())
			this.folder.mkdirs();
		File folderDest = new File(folder.getPath() + "\\epr" + epreuve.getNumFormat() + ".pdf");
		PdfWriter pdfWriter = new PdfWriter(folderDest);
		PdfDocument pdf = new PdfDocument(pdfWriter);
		PdfDocumentDecorateur document = new PdfListeDepart(new Document(pdf), folder, epreuve);
		document.addHeader().addBody().addFooter().getResult(true, 0, 0);
	}
}