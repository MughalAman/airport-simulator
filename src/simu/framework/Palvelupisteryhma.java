package simu.model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;


public class Palvelupisteryhma {
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	private PriorityQueue<Palvelupiste> palvelupisteet;
	private LinkedList<Integer> jono = new LinkedList<Integer>();
	
	public Palvelupisteryhma(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		
		palvelupisteet = new PriorityQueue<Palvelupiste>();
		palvelupisteet.add(new Palvelupiste(generator, tapahtumalista, skeduloitavanTapahtumanTyyppi));
	}
	
	public void setPalvelupisteetLkm(int uusiMaara) {
		palvelupisteet = new PriorityQueue<Palvelupiste>();
		for(int i = 0; i<uusiMaara; i++) {
			Palvelupiste p = new Palvelupiste(generator, tapahtumalista, skeduloitavanTapahtumanTyyppi);
			p.setId(i);
			palvelupisteet.add(p);
		}

	}

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
		
	}
	
	public Asiakas otaJonosta() {
		int id = jono.poll();
		for(Palvelupiste p : palvelupisteet) {
			if(id == p.getId()) {
				return p.otaJonosta();
			}
		}
		return null;
	}
	
	public Palvelupiste getFirst() {
		return palvelupisteet.peek();
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
			oleskeluaika += p.getAktiiviaika();
		}
		return oleskeluaika;
	}

	public int getViiveaika() {
		return getOleskeluaika()-getAktiiviaika();
	}

}
