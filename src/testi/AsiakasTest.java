package testi;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import simu.framework.Kello;
import simu.framework.Trace;
import simu.model.Asiakas;

public class AsiakasTest {
	/**
	* Testaa Asiakas-luokkaa
	* @author Jeremia Kekkonen
	* @version 1.1
	*/
    @Test
    public void testAsiakasProperties() {
    	Trace.setTraceLevel(Trace.Level.INFO);
        Asiakas asiakas = new Asiakas();
        double saapumisaika = Kello.getInstance().getAika();
        asiakas.setPoistumisaika(saapumisaika + 5);

        assertEquals(saapumisaika, asiakas.getSaapumisaika(), 0.001);
        assertEquals(saapumisaika + 5, asiakas.getPoistumisaika(), 0.001);
    }
}