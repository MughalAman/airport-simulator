package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Liike {
	
	private double x, y, eroX, eroY;
	private int i;
	private GraphicsContext gc;
	
	
	public Liike(double x, double y, double eroX, double eroY, GraphicsContext gc, Image img, double width, double height) {
		this.x = x;
		this.y = y;
		this.eroX = eroX;
		this.eroY = eroY;
		this.gc = gc;
		i = 0;
		
	}
	
	public void paivita() {
		if(i<10) {
			gc.clearRect(x-eroX, y-eroY, 10, 10);
			gc.setFill(Color.RED);
			gc.fillOval(x,y,10,10);
			x+=eroX;
			y+=eroY;
			i++;
		} 
		
		if(isValmis()) gc.clearRect(x-eroX, y-eroY, 10, 10);
	}
	
	public boolean isValmis() {
		return i>=10;
	}


}
