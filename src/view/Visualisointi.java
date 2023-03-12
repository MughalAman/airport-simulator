



package view;

import simu.framework.Pallot;
import simu.model.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;


/**
* Luokka, joka vastaa graafisen käyttöliittymän piirtämisestä ja
* päivittämisestä.
*
* @author Amin salamatin, Muhammed Özturk, Jeremia Kekkonen
* @version 1.1
*/


public class Visualisointi extends Canvas implements IVisualisointi{

    private GraphicsContext gc;
    private Piste[] pisteet;
    private Image img;

    double i = 0;
    double j = 10;

    /**
     * Luokan konstruktori.
     * @param w piirtoalueen leveys
     * @param h piirtoalueen korkeus
     */
    public Visualisointi(int w, int h) {
        super(w, h);
        gc = this.getGraphicsContext2D();
        pisteet = new Piste[8];
        pisteet[0] = new Piste((215/2), (580/2), TapahtumanTyyppi.ENTRANCE);
        pisteet[1] = new Piste((345/2), (395/2), TapahtumanTyyppi.INFO);
        pisteet[2] = new Piste((345/2), (845/2), TapahtumanTyyppi.CHECKINAUTO);
        pisteet[3] = new Piste((625/2), (845/2), TapahtumanTyyppi.CHECKINMANUAL);
        pisteet[4] = new Piste((605/2), (635/2), TapahtumanTyyppi.SECURITY);
        pisteet[5] = new Piste((900/2), (635/2), TapahtumanTyyppi.SECURITYGATE);
        pisteet[6] = new Piste((1325/2), (625/2), TapahtumanTyyppi.GATE);
        pisteet[7] = new Piste((1520/2), (705/2), TapahtumanTyyppi.PLANE);
        tyhjennaNaytto();
    }

    /**
     * Tyhjentää piirtoalueen ja lisää taustakuvan.
     */
    public void tyhjennaNaytto() {
        img = new Image("/img/airport.png");
        gc.setFill(new ImagePattern(img));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        gc.setFill(Color.BLACK);
    }

    /**
     * Palauttaa käytössä olevan kuvan.
     * @return käytössä oleva kuva
     */
    public Image Getimg() {
        return this.img;
    }

    /**
     * Piirtää uuden asiakkaan punaisena ympyränä.
     */
    public void uusiAsiakas() {
        gc.setFill(Color.RED);
        gc.fillOval(i,j,10,10);

        i = (i + 10) % this.getWidth();
        if (i == 0) j+=10;			
	}
    
    

    /**
     * Luokan konstruktori.
     * @param uusi onko asiakas uusi
     * @param vanha onko vanha asiakas
     */

	public void uusiAsiakas(TapahtumanTyyppi uusi, TapahtumanTyyppi vanha) {
		double oldY = 0, oldX = 0, newY = 0, newX = 0;

		for(Piste p: pisteet) {
			if(vanha == p.getTapahtuma()) {
				oldX = p.getX();
				oldY = p.getY();
			}
			if(uusi == p.getTapahtuma()) {
				newX = p.getX();
				newY = p.getY();
			}
		}
		
		double eroX = (newX-oldX)/10-1;
		double eroY = (newY-oldY)/10-1;
		
		Pallot.getInstance().getPallot().add(new Liike(oldX, oldY, eroX, eroY, gc, img, this.getWidth(), this.getHeight()));
		

	}
}
