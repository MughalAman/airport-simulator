package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	private int C = 1, B = 1, T = 1, Ri = 1, W = 1;
	
	
	public OmaMoottori(){
			
		palvelupisteet = new Palvelupiste[8];
	
		palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.ENTRANCE);	
		palvelupisteet[1]=new Palvelupiste(new Normal(15,8), tapahtumalista, TapahtumanTyyppi.INFO);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECKINAUTO);
		palvelupisteet[3]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECKINMANUAL);
		palvelupisteet[4]=new Palvelupiste(new Normal(15,3), tapahtumalista, TapahtumanTyyppi.SECURITY);
		palvelupisteet[5]=new Palvelupiste(new Normal(18,3), tapahtumalista, TapahtumanTyyppi.SECURITYGATE);
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
	            int randomCheck = (int) (Math.random() * 3); // generates a random number between 0 and 2
	            switch (randomCheck) {
	                case 0: t.setTyyppi(TapahtumanTyyppi.INFO);
	                        break;
	                case 1: t.setTyyppi(TapahtumanTyyppi.CHECKINAUTO);
	                        break;
	                case 2: t.setTyyppi(TapahtumanTyyppi.CHECKINMANUAL);
	                        break;
	            }
	            break;
	        case INFO: a = palvelupisteet[0].otaJonosta();
	        			int randosmCheck = (int) (Math.random() * 3); // generates a random number between 0 and 2
	    	            switch (randosmCheck) {
	    	             
	    	                case 0: t.setTyyppi(TapahtumanTyyppi.CHECKINAUTO);
	    	                        break;
	    	                case 1: t.setTyyppi(TapahtumanTyyppi.CHECKINMANUAL);
	    	                        break;
	    	            }
	                    palvelupisteet[1].lisaaJonoon(a);

	            break;
	        case CHECKINAUTO: a = palvelupisteet[0].otaJonosta();
	                    palvelupisteet[2].lisaaJonoon(a); 
	            break;  
	        case CHECKINMANUAL: a = palvelupisteet[0].otaJonosta();
	                    palvelupisteet[3].lisaaJonoon(a); 
	            break;
	        case SECURITY:a = palvelupisteet[1].otaJonosta();
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
	               C++;
	            break;
	            
	    }   
	}


	
	@Override
	protected void tulokset() {	
		T = (int)Kello.getInstance().getAika();
		B = palvelupisteet[2].getAktiiviaika() + palvelupisteet[3].getAktiiviaika(); 
		Ri = palvelupisteet[4].getViiveaika();
		W = palvelupisteet[4].getOleskeluaika();
		
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
		System.out.println("Määrä asiakkaita, jotka pääsivät lentokoneeseen: "+C);
		System.out.println("Check-in aktiiviaika: "+B);
		System.out.println("Simuloinnin kokonaisaika: "+T);
		System.out.println("Check-in käyttöaste: "+(B/T));
		System.out.println("Lentokentän suoritusteho: "+(C/T));
		System.out.println("Check-in keskimääräinen palveluaika: "+(B/C));
		System.out.println("Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+Ri);
		System.out.println("Kokonaisoleskeluaika turvatarkastuksessa. Tämä on asiakkaiden läpimenoaikojen summa turvatarkastuksesta: "+W);
		System.out.println("Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+(W/C));
		System.out.println("Turvatarkastuksen keskimääräinen jononpituus: "+(W/T));
		
	}
	
	public void addBusyTime(int time) {
		B += time;
	}

	
}
