package view;
import simu.framework.Kello;
import simu.model.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Visualisointi extends Canvas implements IVisualisointi{

	private GraphicsContext gc;
	private Piste[] pisteet;
	private Image img;
	
	double i = 0;
	double j = 10;

	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		pisteet = new Piste[8];
		pisteet[0] = new Piste(120, 450, TapahtumanTyyppi.ENTRANCE);
		pisteet[1] = new Piste(175, 285, TapahtumanTyyppi.INFO);
		pisteet[2] = new Piste(190, 735, TapahtumanTyyppi.CHECKINAUTO);
		pisteet[3] = new Piste(290, 225, TapahtumanTyyppi.CHECKINMANUAL);
		pisteet[4] = new Piste(355, 615, TapahtumanTyyppi.SECURITY);
		pisteet[5] = new Piste(475, 640, TapahtumanTyyppi.SECURITYGATE);
		pisteet[6] = new Piste(690, 690, TapahtumanTyyppi.GATE);
		pisteet[7] = new Piste(770, 750, TapahtumanTyyppi.PLANE);
		tyhjennaNaytto();
	}
	

	public void tyhjennaNaytto() {
		img = new Image("/img/airport.png");
		gc.setFill(new ImagePattern(img));
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
	    gc.setFill(Color.BLACK);
	 
	}
	
	public void uusiAsiakas() {
		gc.setFill(Color.RED);
		gc.fillOval(i,j,10,10);
		
		i = (i + 10) % this.getWidth();
		//j = (j + 12) % this.getHeight();
		if (i==0) j+=10;			
	}

	public void uusiAsiakas(Palvelupisteryhma[] p) {
		TapahtumanTyyppi tyyppi;
		double oldY = 0, oldX = 0, newY = 0, newX = 0;
		for(Palvelupisteryhma r: p) {
			tyyppi = r.teeLiike();
			if(tyyppi != r.getTapahtuma()) {
				for(Piste g: pisteet) {
					if(tyyppi == g.getTapahtuma()) {
						oldX = g.getX();
						oldY = g.getY();
					}
					if(r.getTapahtuma() == g.getTapahtuma()) {
						newX = g.getX();
						newY = g.getY();
					}
				}
			}
		}
		double eroX = (newX-oldX)/10-1;
		double eroY = (newY-oldY)/10-1;

		Kello.getPallot().add(new Liike(oldX, oldY, eroX, eroY, gc, img, this.getWidth(), this.getHeight()));
		

	}
}
