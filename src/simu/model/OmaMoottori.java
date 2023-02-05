package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	
	public OmaMoottori(){
			
		palvelupisteet = new Palvelupiste[8];
	
		palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.ENTRANCE);	
		palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.INFO);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECKINAUTO);
		palvelupisteet[3]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECKINMANUAL);
		palvelupisteet[4]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.SECURITY);
		palvelupisteet[5]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.SECURITYGATE);
		palvelupisteet[6]=new Palvelupiste(new Normal(20,3), tapahtumalista, TapahtumanTyyppi.GATE);
		palvelupisteet[7]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.PLANE);
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.ENTRANCE);

	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()){
			
			case ENTRANCE: palvelupisteet[0].lisaaJonoon(new Asiakas());	
				       saapumisprosessi.generoiSeuraava();	
				break;
			case INFO: a = palvelupisteet[0].otaJonosta();
				   	   palvelupisteet[1].lisaaJonoon(a);
				break;
			case CHECKINAUTO: a = palvelupisteet[1].otaJonosta();
				   	   palvelupisteet[2].lisaaJonoon(a); 
				break;  
			case CHECKINMANUAL: a = palvelupisteet[2].otaJonosta();
		   	   palvelupisteet[3].lisaaJonoon(a); 
				      
			     break;
			case SECURITY:a = palvelupisteet[3].otaJonosta();
		   	   palvelupisteet[4].lisaaJonoon(a); 
				
				break;
				
			case SECURITYGATE:a = palvelupisteet[4].otaJonosta();
		   	   palvelupisteet[5].lisaaJonoon(a); 
			
				break;
				
			case GATE:a = palvelupisteet[5].otaJonosta();
		   	   palvelupisteet[6].lisaaJonoon(a); 
					
				break;
			
			case PLANE:
				 a = palvelupisteet[6].otaJonosta();
				   a.setPoistumisaika(Kello.getInstance().getAika());
		           a.raportti(); 
				break;
				
		}	
	}

	
	@Override
	protected void tulokset() {	
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

	
}
