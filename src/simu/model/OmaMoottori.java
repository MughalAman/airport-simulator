package simu.model;

import controller.IKontrolleri;
import eduni.distributions.Bernoulli;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;


public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	private double B, T, Ri, W;
	private int C = 0;
	private double checkinJakauma = 0.5, sisaankayntiJakauma = 0.5; // Jakaumat oletuksena 50/50
	
	public OmaMoottori(IKontrolleri kontrolleri){ // UUSI

		super(kontrolleri); //UUSI
		
		palvelupisteet = new Palvelupiste[7];
	
		palvelupisteet[0]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.INFO);
		palvelupisteet[1]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECKINAUTO);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.CHECKINMANUAL);
		palvelupisteet[3]=new Palvelupiste(new Normal(15,3), tapahtumalista, TapahtumanTyyppi.SECURITY);
		palvelupisteet[4]=new Palvelupiste(new Normal(18,3), tapahtumalista, TapahtumanTyyppi.SECURITYGATE);
		palvelupisteet[5]=new Palvelupiste(new Normal(20,3), tapahtumalista, TapahtumanTyyppi.GATE);
		palvelupisteet[6]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.PLANE);
		
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

		case ENTRANCE:
				switch((int)(new Bernoulli(sisaankayntiJakauma).sample())) {
						
        			case 0: palvelupisteet[0].lisaaJonoon(new Asiakas());    
					break;

				case 1: switch((int)(new Bernoulli(checkinJakauma).sample())) {
						
					case 0: palvelupisteet[1].lisaaJonoon(new Asiakas());
						break;
						
					case 1: palvelupisteet[2].lisaaJonoon(new Asiakas());
						break;
					}
					break;
        			}  
        			saapumisprosessi.generoiSeuraava();	
        			kontrolleri.visualisoiAsiakas(); // UUSI
				break;
				
		case INFO: a = palvelupisteet[0].otaJonosta();
			switch((int)(new Bernoulli(checkinJakauma).sample())) {
					
			case 0: palvelupisteet[1].lisaaJonoon(a);
				break;
					
			case 1: palvelupisteet[2].lisaaJonoon(a);
				break;
			}
			break;
				
		case CHECKINAUTO: a = palvelupisteet[1].otaJonosta();
				palvelupisteet[3].lisaaJonoon(a); 
				break;
				
		case CHECKINMANUAL: a = palvelupisteet[2].otaJonosta();
            			palvelupisteet[3].lisaaJonoon(a); 
            			break;
         
	        case SECURITY: a = palvelupisteet[3].otaJonosta();
            		palvelupisteet[4].lisaaJonoon(a); 
            		break;
            	
	        case SECURITYGATE: a = palvelupisteet[4].otaJonosta();
            		palvelupisteet[5].lisaaJonoon(a); 
            		break;
    
	        case GATE: a = palvelupisteet[5].otaJonosta();
            		palvelupisteet[6].lisaaJonoon(a); 
            		break;

	        case PLANE: a = palvelupisteet[6].otaJonosta();
	        	a.setPoistumisaika(Kello.getInstance().getAika());
	        	C++;
	        	a.raportti();
	        	break;
		}	
	}
	
	
	@Override
	public void tulokset() {
		
		// VANHAA tekstipohjaista
		// System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		// System.out.println("Tulokset ... puuttuvat vielä");
		T = Kello.getInstance().getAika();
		System.out.println("T: "+T);
		B = palvelupisteet[1].getAktiiviaika() + palvelupisteet[2].getAktiiviaika(); 
		System.out.println("B: "+B);
		Ri = palvelupisteet[3].getViiveaika();
		W = palvelupisteet[3].getOleskeluaika();
		
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset: ");
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
		
		// UUTTA graafista
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
		
		String 	tulosStr = "Simulointi päättyi kello " + Kello.getInstance().getAika() + "\n" +  "Tulokset: " + "\n" + "Määrä asiakkaita, jotka pääsivät lentokoneeseen: "+C + "\n"  +  "Check-in aktiiviaika: "+B + "\n"
				+ "Simuloinnin kokonaisaika: "+T + "\n"	+ "Check-in käyttöaste: "+(B/T) + "\n" + "Lentokentän suoritusteho: "+(C/T) + "\n" + "Check-in keskimääräinen palveluaika: "+(B/C) +  "\n" + "Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+Ri + "\n"+
				"Kokonaisoleskeluaika turvatarkastuksessa. Tämä on asiakkaiden läpimenoaikojen summa turvatarkastuksesta: "+W + "\n"
				+ "Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+(W/C) + "\n" + "Turvatarkastuksen keskimääräinen jononpituus: "+(W/T);
		
		
		kontrolleri.naytaLoppuTuloste(tulosStr);
		
		
	}

	// Sisäänkäyti jakauma INFO/Check-in
	public void setSisaankayntiJakauma(double jakauma) {
		sisaankayntiJakauma = jakauma;
	}
		
	// Check-in jakauma CHECKINAUTO/CHECKIMANUAL
	public void setCheckinJakauma(double jakauma) {
		checkinJakauma = jakauma;
	}

	
	
}
