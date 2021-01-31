package prog.tache.etiquette;

import java.awt.image.BufferedImage;

/**
 * Classe représentant les informations utiles à la création des étiquettes.
 * @author ronan
 *
 */
public class ImageEtiquette implements Comparable<ImageEtiquette> {

	BufferedImage image;
	String nom;
	
	/**
	 * 
	 * @param image
	 * @param nom
	 */
	public ImageEtiquette(BufferedImage image, String nom) {
		this.image = image;
		this.nom = nom;
	}
	
	/**
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * 
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "ImageEtiquette [image=" + image + ", nom=" + nom + "]";
	}
	
	@Override
	public int compareTo(ImageEtiquette img) { //Méthode permettant de trier une liste d'images
		return img.getNom().compareTo(this.getNom());
	}
	
}
