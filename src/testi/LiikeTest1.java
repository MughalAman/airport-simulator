

package testi;



import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import view.Liike;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
@author [tekijä]
@version [versionumero]
*/

public class LiikeTest1 {
	/**
	* Testaa Liike-luokkaa
	* @author Muhammed Özturk
	* @version 1.2
	*/

	private GraphicsContext gc;
    Liike liike = new Liike(50, 50, 2, 2, gc, 200, 200);


    @Test
    
    /**
    * Metodin tarkoitus
    *
    * @param testPaivita paivittaa väria
    * @param testIsValmis liikkuuko pallo
    */
    // 

    public void testPaivita() {
        
        
    	/**
    	* tässä tarkistetaan onko liike luokan pallon väri punainen
    	*/

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
       //liikkuuko pallo
    	boolean i = liike.isValmis();
    	
    	
        assertEquals(false, i);

    }
}
