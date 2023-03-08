package testi;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import view.Liike;
import view.Visualisointi;

class VisualisointiTest {
    Visualisointi vs = new Visualisointi(1,1);
    Image img;
    Image expectedImg;


	@Test
	void testTyhjennaNaytto() {
		vs.Getimg();
		 expectedImg = new Image("/img/airport.png");
		 img = vs.Getimg();
		
        assertEquals(expectedImg, img);

	}

}
