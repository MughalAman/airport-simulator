package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Liike {
	
	private double x, y, eroX, eroY;
	private int i;
	private GraphicsContext gc;
	
	
	public Liike(double x, double y, double eroX, double eroY, GraphicsContext gc, Image img, double width, double height) {
		this.x = x+eroX;
		this.y = y+eroY;
		this.eroX = eroX;
		this.eroY = eroY;
		this.gc = gc;
		i = 0;
		
	}
	
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
	
	public boolean isValmis() {
		return i>=10;
	}

	
	public Color getVari() {
		return Vari ;
		
	}

}
