package prog.tache.iText;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

/**
 * Crée une ligne à gauche de la cellule.
 * @author ronan
 *
 */
public class LeftCellBorder extends CellRenderer {
    public LeftCellBorder(Cell modelElement) {
        super(modelElement);
    }

    /**
     * Permet de créer des objets et de les placer sur le pdf selon la position de la cellule.
     */
    @Override
    public void draw(DrawContext drawContext) {
        drawContext.getCanvas().roundRectangle(getOccupiedAreaBBox().getX(), getOccupiedAreaBBox().getY() + 1,
                0.1f, getOccupiedAreaBBox().getHeight() - 3, 0);
        drawContext.getCanvas().stroke();
        super.draw(drawContext);
    }
    
    /**
     * Cette méthode est appelée au lieu de la méthode draw quand la cellule chevauche 2 pages.
     */
    @Override
    public IRenderer getNextRenderer() {
        return new LeftCellBorder(getModelElement());
    }
    
    
}