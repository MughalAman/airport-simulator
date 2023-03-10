package simu.framework;

import controller.IKontrolleri;
import simu.model.Palvelupiste;
import simu.model.Palvelupisteryhma;

public abstract class Moottori extends Thread implements IMoottori{  // UUDET MÄÄRITYKSET
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	private Pallot pallot;
	
	protected Tapahtumalista tapahtumalista;
	protected Palvelupisteryhma[] palvelupisteet;
	
	protected IKontrolleri kontrolleri; // UUSI
	

	public Moottori(IKontrolleri kontrolleri){  // UUSITTU
		
		this.kontrolleri = kontrolleri;  //UUSI

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		pallot = Pallot.getInstance();
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	@Override
	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}
	
	@Override // UUSI
	public void setViive(long viive) {
		this.viive = viive;
	}
	
	@Override // UUSI 
	public long getViive() {
		return viive;
	}
	
	@Override
	public void run(){ // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive(); // UUSI
			kello.setAika(nykyaika());
			pallot.paivita();
			suoritaBTapahtumat();
			yritaCTapahtumat();
		}
		tulokset();
		
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat(){    // määrittele protectediksi, josa haluat ylikirjoittaa
		for (Palvelupisteryhma r: palvelupisteet){
			for (Palvelupiste p: r.getList()) {
				if (!p.onVarattu() && p.onJonossa()){
					p.aloitaPalvelu();
				}
			}
		}
	}

	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}
	
			
	private void viive() { // UUSI
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	

}