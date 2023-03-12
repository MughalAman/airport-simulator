package simu.model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;



/**
 * @author Amin Salamatin
*Palvelupisteryhmä sisältää useita palvelupisteitä ja jonon asiakkaita, jotka odottavat palvelua.
*Palvelupisteet ovat tallennettuna PriorityQueueen, jossa palvelupisteet ovat järjestetty niiden käyttöasteen mukaan.
*/

public class Palvelupisteryhma {
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	private PriorityQueue<Palvelupiste> palvelupisteet;
	private LinkedList<Integer> jono = new LinkedList<Integer>();
	
	private TapahtumanTyyppi asiakasPalvelupisteeseen;
	

	/**
	*Palvelupisteryhmän konstruktori, joka luo yhden palvelupisteen ja lisää sen PriorityQueueen.
	*@param generator ContinuousGenerator-tyyppinen satunnaislukugeneraattori
	*@param tapahtumalista Tapahtumalista, johon tapahtumat lisätään
	*@param tyyppi TapahtumanTyyppi-tyyppinen tapahtuman tyyppi
	*/
	
	
	public Palvelupisteryhma(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		
		asiakasPalvelupisteeseen = skeduloitavanTapahtumanTyyppi;
		
		palvelupisteet = new PriorityQueue<Palvelupiste>();
		palvelupisteet.add(new Palvelupiste(generator, tapahtumalista, skeduloitavanTapahtumanTyyppi));
	}
	
	
	/**
	*Asettaa palvelupisteiden määrän halutuksi ja luo uudet palvelupisteet.
	*@param uusiMaara haluttu palvelupisteiden määrä
	*/
	
	
	public void setPalvelupisteetLkm(int uusiMaara) {
		palvelupisteet = new PriorityQueue<Palvelupiste>();
		for(int i = 0; i<uusiMaara; i++) {
			Palvelupiste p = new Palvelupiste(generator, tapahtumalista, skeduloitavanTapahtumanTyyppi);
			p.setId(i);
			palvelupisteet.add(p);
		}

	}

	
	
	
	
	/**
	*Lisää asiakkaan jonoon.
	*Jos jokin palvelupiste on vapaa, lisätään asiakas sen jonoon.
	*Muussa tapauksessa asiakas lisätään jonoon ja palvelupiste, joka on ensimmäisenä käytössä, hoitaa asiakkaan.
	*Muistaa palvelupisteen, johon asiakas on lisätty, ja lisää sen ID:n omaan listaan.
	*@param a lisättävä asiakas
	*/
	public void lisaaJonoon(Asiakas a) {
		boolean lisatty = false;
		for(Palvelupiste p: palvelupisteet) {
			if(!p.onVarattu()) {
				p.lisaaJonoon(a);
				jono.add(p.getId());
				lisatty = true;
				break;
			}
		}
		if(!lisatty) {
			jono.add(palvelupisteet.peek().getId());
			palvelupisteet.peek().lisaaJonoon(a);
		}
		asiakasPalvelupisteeseen = a.getTapahtuma();
		
	}
	
	
	/**
	*Ottaa asiakkaan siitä palvelupisteestä, jonka ID täsmää jonossa ensimmäistä olevaa.
	*/
	public Asiakas otaJonosta() {
		int id = jono.poll();
		for(Palvelupiste p : palvelupisteet) {
			if(id == p.getId()) {
				Asiakas a = p.otaJonosta();
				return a;
			}
		}
		return null;
	}
	
	public Palvelupiste getFirst() {
		return palvelupisteet.peek();
	}
	
	public TapahtumanTyyppi getTapahtuma() {
		return skeduloitavanTapahtumanTyyppi;
	}
	
	public TapahtumanTyyppi teeLiike() {
		TapahtumanTyyppi tyyppi = asiakasPalvelupisteeseen;
		asiakasPalvelupisteeseen = skeduloitavanTapahtumanTyyppi;
		return tyyppi;
	}
	
	public PriorityQueue<Palvelupiste> getList() {
		return palvelupisteet;
	}
	
	public int getAktiiviaika() {
		int aktiiviaika = 0;
		for(Palvelupiste p: palvelupisteet) {
			aktiiviaika += p.getAktiiviaika();
		}
		return aktiiviaika;
	}

	public int getOleskeluaika() {
		int oleskeluaika = 0;
		for(Palvelupiste p: palvelupisteet) {
			oleskeluaika += p.getOleskeluaika();
		}
		return oleskeluaika;
	}

	public int getViiveaika() {
		return getOleskeluaika()-getAktiiviaika();
	}
	
}
