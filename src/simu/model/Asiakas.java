package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;

public class Asiakas {
/**
Tämä luokka määrittää asiakkaan metodit ja muuttuja
@author Aman Mughal ja Amin Salamatin
@version 1.8
*/
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;	
	private TapahtumanTyyppi tyyppi = TapahtumanTyyppi.ENTRANCE;

	
	public Asiakas(){
	    id = i++;
	    
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":"+saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public void raportti(){
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui:" +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi:" +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
	}
	
	public void setTapahtuma(TapahtumanTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}
	
	public TapahtumanTyyppi getTapahtuma() {
		return tyyppi;
	}

}
