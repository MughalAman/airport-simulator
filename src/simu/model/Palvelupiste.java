package simu.model;

import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

/**
*Palvelupiste-luokka mallintaa palvelupistettä, jossa asiakkaat saavat palvelua.
*Palvelupisteellä on jono, joka toimii tietorakenteena. Palvelupisteellä on myös
*palveluaika, joka arvotaan asiakkaan palvelun alkaessa, sekä aktiivi- ja oleskeluaika,
*jotka kertovat palvelupisteen käyttöasteen.
*@author Jeremia Kekkonen, Aman Mughal ja Amin Salamatin
*@version 2.0
*/

public class Palvelupiste implements Comparable<Palvelupiste>{

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;

	private int aktiiviaika = 0;
	private int oleskeluaika = 0;
	
	private int id = 0;
	
	/**
	 * Konstruktori luo uuden Palvelupiste-olion annetuilla parametreilla.
	 * @param generator - generoi palveluajan
	 * @param tapahtumalista - tapahtumalista
	 * @param tyyppi - tapahtuman tyyppi
	 */
	
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
				
	}
	
	
	/**
	 * Lisää uuden asiakkaan jonoon.
	 * @param a - uusi asiakas
	 */

	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}
	
	
	/**
	 * Palauttaa jonon ensimmäisen asiakkaan ja poistaa sen jonosta.
	 * @return palvelussa ollut asiakas
	 */

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		Asiakas a = jono.poll();
		varattu = false;
		oleskeluaika += (int) (Kello.getInstance().getAika()-a.getSaapumisaika());
		return a;		
	}

	
	/**
	 * Aloitetaan uusi palvelu, jolloin asiakas on jonossa palvelun aikana.
	 */
	
	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		varattu = true;
		double palveluaika = generator.sample();
		aktiiviaika += palveluaika;
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}
	
	
	/**
	*Kertoo, onko palvelupiste varattu vai ei.
	*@return True, jos varattu. False, jos ei varattu.
	*/

	public boolean onVarattu(){
		return varattu;
	}
	
	
	/**
	*Kertoo, onko palvelupisteessä jonoa vai ei.
	*@return True, jos jonossa on asiakkaita. False, jos ei.
	*/
	
	public boolean onJonossa(){
		return jono.size() != 0;
	}
	
	/**
	*Palauttaa palvelun aktiiviajan.
	*@return Palvelun aktiiviaika.
	*/

	
	public int getAktiiviaika() {
		return aktiiviaika;
	}
	
	
	/**
	*Palauttaa palvelun oleskeluajan.
	*@return Palvelun oleskeluaika.
	*/

	public int getOleskeluaika() {
		return oleskeluaika;
	}
	
	
	/**
	*Palauttaa palvelun aktiivisuusajan.
	*@return Palvelun aktiivisuusaika.
	*/

	public int getViiveaika() {
		return oleskeluaika-aktiiviaika;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

    //Vertaa, kuinka monta ihmistä on palvelupisteen jonossa, ja sen mukaan palauttaa järjestyksen.
	@Override
	public int compareTo(Palvelupiste arg) {
		return arg.jono.size() - this.jono.size();
	}
	

}
