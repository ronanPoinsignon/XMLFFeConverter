package prog.tache.iText;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

/**
 * Crée une ligne en dessous de la cellule.
 * @author ronan
 *
 */
public class BottomCellBorder extends CellRenderer {
	public BottomCellBorder(Cell modelElement) {
        super(modelElement);
    }

    @Override
    public void draw(DrawContext drawContext) {
        drawContext.getCanvas().roundRectangle(getOccupiedAreaBBox().getX() + 6.5f, getOccupiedAreaBBox().getY(),
        		getOccupiedAreaBBox().getWidth() - 20, 0.1f, 0);
        drawContext.getCanvas().stroke();
        super.draw(drawContext);
    }
    
    //Cette méthode est appelée au lieu de la méthode draw quand la cellule chevauche 2 pages.
    @Override
    public IRenderer getNextRenderer() {
        return new BottomCellBorder(getModelElement());
    }
}
