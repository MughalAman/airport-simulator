
package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
* Luokka, joka vastaa graafisen käyttöliittymän piirtämisestä ja
* päivittämisestä.
*
* @author Amin salamatin
* @version 1.1
*/


public class Liike {
	
	private double x, y, eroX, eroY;
	private int i;
	private GraphicsContext gc;
	
	
	/**
	 * Luo uuden Liike-olion.
	 * @param x X-koordinaatti.
	 * @param y Y-koordinaatti.
	 * @param eroX X-suuntainen nopeus.
	 * @param eroY Y-suuntainen nopeus.
	 * @param gc GraphicsContext-olio, johon liikkeen piirto tapahtuu.
	 * @param img Kuvan Image-olio, joka liikkuu.
	 * @param width Kuvan leveys.
	 * @param height Kuvan korkeus.
	 */
	public Liike(double x, double y, double eroX, double eroY, GraphicsContext gc, Image img, double width, double height) {
		this.x = x+eroX;
		this.y = y+eroY;
		this.eroX = eroX;
		this.eroY = eroY;
		this.gc = gc;
		i = 0;
		
	}
	
	/**
	 * Liikuttaa kuvaa ja piirtää sen uuteen paikkaan.
	 */
	private Color Vari = (Color) Color.RED;
	
	public void paivita() {
		if(i<10) {
			gc.setFill(Color.WHITE);
			gc.fillOval(x-eroX, y-eroY, 12, 12);
			gc.setFill(Vari);
			gc.fillOval(x,y,12,12);
			x+=eroX;
			y+=eroY;
			i++;
			
			
		} 
		
		if(isValmis()) {
			gc.setFill(Color.WHITE);
			gc.fillOval(x-eroX, y-eroY, 12, 12);
		}
	}
	
	/**
	 * Tarkistaa, onko liike valmis.
	 * @return true, jos liike on valmis, muuten false.
	 */
	public boolean isValmis() {
		return i>=10;
	}

	
	/**
	 * Palauttaa liikkeen värin.
	 * @return liikkeen väri.
	 */
	public Color getVari() {
		return Vari ;
		
	}

}
