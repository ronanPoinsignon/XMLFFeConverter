package prog.tache.iText;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

/**
 * Classe abstraite permettant la création de fichier pdf.
 * @author ronan
 *
 */
public abstract class PdfDocumentDecorateur {

	protected Document document;
	protected File folder;
	
	protected Table header = new Table(UnitValue.createPercentArray(new float[] {1})).useAllAvailableWidth();
	protected Table body = new Table(UnitValue.createPercentArray(new float[] {1})).useAllAvailableWidth();
	protected Table footer = new Table(UnitValue.createPercentArray(new float[] {1})).useAllAvailableWidth();
	private Table doc = new Table(UnitValue.createPercentArray(new float[] {1})).useAllAvailableWidth();
	
	public PdfDocumentDecorateur(Document document, File folder) {
		this.document = document;
		doc.addCell(header);
		doc.addCell(body);
		doc.addCell(footer);
		this.folder = folder;
	}
	
	/**
	 * Ajoute un élément à l'en-tête du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addHeadElement(BlockElement<?> element) {
		header.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément à l'en-tête du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addHeadElement(Cell element) {
		header.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément à l'en-tête du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addHeadElement(Image element) {
		header.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément à l'en-tête du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addHeadElement(String element) {
		header.addCell(element);
		return this;
	}
	
	/**
	 * Ajoute un élément au corps du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addBodyElement(BlockElement<?> element) {
		body.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément au corps du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addBodyElement(Cell element) {
		body.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément au corps du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addBodyElement(Image element) {
		body.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément au corps du document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addBodyElement(String element) {
		body.addCell(element);
		return this;
	}
	
	/**
	 * Ajoute un élément en fin de document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addFooterElement(BlockElement<?> element) {
		footer.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément en fin de document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addFooterElement(Cell element) {
		footer.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément en fin de document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addFooterElement(Image element) {
		footer.addCell(element);
		return this;
	}
	/**
	 * Ajoute un élément en fin de document.
	 * @param element
	 * @return
	 */
	public PdfDocumentDecorateur addFooterElement(String element) {
		footer.addCell(element);
		return this;
	}
	
	/**
	 * Enlève les bordures de la table et de ses cellules.
	 * @param table
	 */
	public void removeBorder(Table table) {
		table.setBorder(Border.NO_BORDER);
		for(IElement element : table.getChildren()) {
			if(element instanceof Table) {
				((Table)element).setBorder(Border.NO_BORDER);
				removeBorder(((Table)element));
			}
			else if(element instanceof Cell) {
				((Cell)element).setBorder(Border.NO_BORDER);
				for(IElement elementCell : ((Cell)element).getChildren()) {
					if(elementCell instanceof Table) {
						((Table)elementCell).setBorder(Border.NO_BORDER);
						removeBorder(((Table)elementCell));
					}
					else if(element instanceof Cell) {
						((Cell)element).setBorder(Border.NO_BORDER);
					}
				}
			}
		}
	}
	
	/**
	 * Ajoute un trait à gauche de la table.
	 * @param table
	 */
	public void addLeftBorder(Table table) {
		boolean isHead = true;
		for(IElement element : table.getChildren()) {
			if(element instanceof Cell) {
				if(isHead)
					isHead = false;
				else {
					((Cell)element).setNextRenderer(new LeftCellBorder(((Cell)element)));
				}
			}
		}
	}
	
	/**
	 * Ajoute une ligne en dessous d'une cellule.
	 * @param cell
	 */
	public void addBottomBorder(Cell cell) {
		cell.setNextRenderer(new BottomCellBorder(cell));
	}
	
	/**
	 * Enlève la marge et le padding entre les paragraphes.
	 * @param table
	 */
	public void removePadding(Table table, float marge, float padding) {
		table.setPadding(padding);
		table.setMargin(marge);
		for(IElement element : table.getChildren()) {
			if(element instanceof Table) {
				((Table)element).setPadding(padding);
				((Table)element).setMargin(marge);
				removePadding(((Table)element), marge, padding);
			}
			else if(element instanceof Cell) {
				((Cell)element).setPadding(padding);
				((Cell)element).setMargin(marge);
				for(IElement elementCell : ((Cell)element).getChildren()) {
					if(elementCell instanceof Table) {
						((Table)elementCell).setPadding(padding);
						((Table)elementCell).setMargin(marge);
						removePadding(((Table)elementCell), marge, padding);
					}
					else if(elementCell instanceof Cell) {
						((Cell)elementCell).setPadding(padding);
						((Cell)elementCell).setMargin(marge);
					}
				}
			}
		}
	}
	
	/**
	 * Coupe la ligne pour être sûr qu'elle ne dépasse pas la taille maximale.
	 * @param line
	 * @param font
	 * @param tailleFont
	 * @param tailleMax
	 * @return
	 */
	public String setTailleMax(String line, PdfFont font, float tailleFont, float tailleMax) {
		if(font.getWidth(line, tailleFont) > tailleMax)
			line = setTailleMax(line.substring(0, line.length() - 1), font, tailleFont, tailleMax);
		return line;
	}
	
	/**
	 * Réduit la police pour que le contenu de la ligne tienne dans la taille maximale.
	 * @param line
	 * @param font
	 * @param tailleFont
	 * @param tailleMax
	 * @return
	 */
	public float setSizeFont(String line, PdfFont font, float tailleFont, float tailleMax) {
		if(font.getWidth(line, tailleFont) > tailleMax)
			tailleFont = setSizeFont(line, font, tailleFont - 1, tailleMax);
		return tailleFont;
	}
	
	/**
	 * Ferme le document et ajoute les dernières fonctinnalités avant de le renvoyer.
	 * @return
	 */
	public Document getResult() {
		document.add(doc);
		this.document.close();
		return this.document;
	}
	
	/**
	 * Ferme le document et ajoute les dernières fonctinnalités avant de le renvoyer.
	 * @return
	 */
	public Document getResult(float marge, float padding) {
		removePadding(doc, marge, padding);
		document.add(doc);
		this.document.close();
		return this.document;
	}
	
	/**
	 * Ferme le document et ajoute les dernières fonctinnalités avant de le renvoyer.
	 * @return
	 */
	public Document getResult(boolean isSansBordure) {
		if(isSansBordure)
			removeBorder(doc);
		document.add(doc);
		this.document.close();
		return this.document;
	}
	
	/**
	 * Ferme le document et ajoute les dernières fonctinnalités avant de le renvoyer.
	 * @return
	 */
	public Document getResult(boolean isSansBordure, float marge, float padding) {
		if(isSansBordure)
			removeBorder(doc);
		removePadding(doc, marge, padding);
		document.add(doc);
		this.document.close();
		return this.document;
	}
	
	/**
	 * Ajoute l'entête du document.
	 * @return
	 * @throws IOException 
	 */
	public abstract PdfDocumentDecorateur addHeader() throws Exception;
	
	/**
	 * Ajoute de corps du document. C'est ici que sera créée et placée la liste de départ.
	 * @return
	 * @throws IOException
	 */
	public abstract PdfDocumentDecorateur addBody() throws Exception;
	
	/**
	 * Ajoute un élément à la fin du document.
	 * @return
	 */
	public abstract PdfDocumentDecorateur addFooter() throws Exception;
	
}
