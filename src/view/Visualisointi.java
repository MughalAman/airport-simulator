package view;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import simu.model.HaeAsiakas;
import simu.model.TapahtumanTyyppi;

public class Visualisointi extends Canvas implements IVisualisointi{

	private GraphicsContext gc;

	double i = 0;
	double j = 10;


	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}


	@Override
	public void tyhjennaNaytto() {
		Image img = new Image("/img/airport.png");
		gc.setFill(new ImagePattern(img));
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());


		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
	    gc.setFill(Color.BLACK);


//
//	    switch(a.getTapahtuma()) {
//
//	    case ENTRANCE:
//	    	 gc.fillText("1. ", 120, 450);
//	    	 break;
//	    }

	    HaeAsiakas a = new HaeAsiakas();

	    //Aman korjaa jos pystyt

	    if(a.getTapahtuma() == TapahtumanTyyppi.ENTRANCE) {
	        gc.fillText("1.", 120, 450);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.INFO) {
	    	gc.fillText("2.", 120, 300);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.CHECKINAUTO) {
	    	gc.fillText("3.", 200, 730);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.CHECKINMANUAL) {
	    	gc.fillText("4.", 260, 220);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.SECURITY) {
	    	gc.fillText("5.", 350, 580);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.SECURITYGATE) {
	    	gc.fillText("6.", 450, 580);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.GATE) {
	    	gc.fillText("7.", 700, 700);
	    }

	    if(a.getTapahtuma() == TapahtumanTyyppi.PLANE) {
	    	gc.fillText("8. ", 760, 650);
	    }
     
	}

	@Override
	public void uusiAsiakas() {
		gc.setFill(Color.RED);
		gc.fillOval(i,j,10,10);

		i = (i + 10) % this.getWidth();
		//j = (j + 12) % this.getHeight();
		if (i==0) j+=10;
	}

}
