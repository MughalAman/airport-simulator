package simu.model;

import controller.IKontrolleri;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;


public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	private double B, T, Ri, W, K, S, P, A, R;
	private int C = 0;
	
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
			
        	case ENTRANCE: palvelupisteet[0].lisaaJonoon(new Asiakas());      
        			saapumisprosessi.generoiSeuraava();	
        			kontrolleri.visualisoiAsiakas(); // UUSI
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
            	
	        case SECURITYGATE: a = palvelupisteet[4].otaJonosta();
            		palvelupisteet[5].lisaaJonoon(a); 
            	break;
    
	        case GATE:a = palvelupisteet[5].otaJonosta();
            		palvelupisteet[6].lisaaJonoon(a); 
            	break;

	        case PLANE:
	        		a = palvelupisteet[6].otaJonosta();
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
		K = B/T;
		S = C/T;
		P = B/C;
		A = W/C;
		R = W/T;
		
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset: ");
		System.out.println("Määrä asiakkaita, jotka pääsivät lentokoneeseen: "+C);
		System.out.println("Check-in aktiiviaika: "+B);
		System.out.println("Simuloinnin kokonaisaika: "+T);
		System.out.println("Check-in käyttöaste: "+(K));
		System.out.println("Lentokentän suoritusteho: "+(S));
		System.out.println("Check-in keskimääräinen palveluaika: "+(P));
		System.out.println("Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+Ri);
		System.out.println("Kokonaisoleskeluaika turvatarkastuksessa. Tämä on asiakkaiden läpimenoaikojen summa turvatarkastuksesta: "+W);
		System.out.println("Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+(A));
		System.out.println("Turvatarkastuksen keskimääräinen jononpituus: "+(R));
		
		// UUTTA graafista
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
		kontrolleri.naytaAsiakasMaara(C);
		kontrolleri.checkAktiiviAika(B);
		kontrolleri.turvaTarkastus(Ri);
		kontrolleri.oleskeluAikaTurvaTarkastus(W);
		kontrolleri.checkInKayttoaste(K);
		kontrolleri.lentokentanTeho(S);
		kontrolleri.checkinPalveluaika(P);
		kontrolleri.turvatarkastusJono(A);
		kontrolleri.turvatarkastusJononPituus(R);
		
		String 	tulosStr = "Simulointi päättyi kello " + Kello.getInstance().getAika() + "\n" +  "Tulokset: " + "\n" + "Määrä asiakkaita, jotka pääsivät lentokoneeseen: "+C + "\n"  +  "Check-in aktiiviaika: "+B + "\n"
				+ "Simuloinnin kokonaisaika: "+T + "\n"	+ "Check-in käyttöaste: "+(B/T) + "\n" + "Lentokentän suoritusteho: "+(C/T) + "\n" + "Check-in keskimääräinen palveluaika: "+(B/C) +  "\n" + "Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+Ri + "\n"+
				"Kokonaisoleskeluaika turvatarkastuksessa. Tämä on asiakkaiden läpimenoaikojen summa turvatarkastuksesta: "+W + "\n"
				+ "Aika asiakkaan turvatarkastuksen jonoon saapumisesta turvatarkastuksen päättymiseen: "+(W/C) + "\n" + "Turvatarkastuksen keskimääräinen jononpituus: "+(W/T);
		
		
		kontrolleri.naytaLoppuTuloste(tulosStr);
		
		
	}
	
	public int getC() {
		return C;
	}
	
	public double getB() {
		return B;
	}
	
	public double getRi() {
		return Ri;
	}
	
	public double getW() {
		return W;
	}
	
	public double getK() {
		return K;
	}
	
	public double getS() {
		return S;
	}
	
	public double getP() {
		return P;
	}
	
	public double getA() {
		return A;
	}

	public double getR() {
		return R;
	}
	
	public void setC(int C){
		this.C = C;
	}
	
	public void setB(double B){
		this.B = B;
	}
	
	public void setRi(double Ri){
		this.Ri = Ri;
	}
	
	public void setW(double W){
		this.W = W;
	}
	
	public void setK(double K) {
		this.K = K;
	}

	public void setS(double S) {
		this.S = S;
	}

	public void setP(double P) {
		this.P = P;
	}

	public void setA(double A) {
		this.A = A;
	}

	public void setR(double R) {
		this.R = R;
	}
}
