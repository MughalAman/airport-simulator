package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simu.model.Palvelupisteryhma;

public class Visualisointi2 extends Canvas implements IVisualisointi{

	private GraphicsContext gc;

	int asiakasLkm = 0;

	public Visualisointi2(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}


	@Override
	public void tyhjennaNaytto() {
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	@Override
	public void uusiAsiakas() {

		asiakasLkm++;

		gc.setFill(Color.YELLOW);
		gc.fillRect(100,80, 100, 20);
		gc.setFill(Color.RED);
		gc.setFont(new Font(20));
		gc.fillText("Asiakas " + asiakasLkm, 100, 100);

	}


	@Override
	public void uusiAsiakas(Palvelupisteryhma[] p) {
		// TODO Auto-generated method stub
		
	}


}
