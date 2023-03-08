package testi;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.Liike;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiikeTest1 {

	private GraphicsContext gc;
    Liike liike = new Liike(50, 50, 2, 2, gc, null, 200, 200);


    @Test
    public void testPaivita() {
        
        
        
        //onko väri punainen?
        liike.getVari();
        Color expectedColor = Color.RED;
        Color actualColor = liike.getVari();
        assertEquals(expectedColor, actualColor);
        
        //Väri on punainen ja palauttaa True;
        //Jos false ei mene läpi ofc
    }

    @Test
    public void testIsValmis() {
       //Onko liike valmis
    	
    	
    	boolean i = liike.isValmis();
    	
    	
        assertEquals(false, i);

    }
}
