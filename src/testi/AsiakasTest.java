package testi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import simu.model.Asiakas;

import static org.junit.Assert.assertEquals;



public class AsiakasTest {
	  @Test
	  public void testAsiakkaanSaapuminen() {
	    Asiakas asiakas = new Asiakas();
	    asiakas.setSaapumisaika(1000);

	    assertEquals(1000, asiakas.getSaapumisaika());
	  }

	  @Test
	  public void testAsiakkaanPoistuminen() {
	    Asiakas asiakas = new Asiakas();
	    asiakas.setPoistumisaika(2000);

	    assertEquals(2000, asiakas.getPoistumisaika());
	  }
	}
