package prog.tache.iText;

import com.itextpdf.layout.element.Paragraph;

/**
 * Crée un {@link Paragraph} en définissant sa marge et son padding.
 * @author ronan
 *
 */
public class ParagraphPdf extends Paragraph {

	public ParagraphPdf() {
		this.setMargin();
	}
	
	public ParagraphPdf(String texte) {
		super(texte);
		this.setMargin();
	}
	
	protected void setMargin() {
		this.setMargin(0);
		this.setMarginLeft(5);
		this.setPadding(0);
	}
	
}
