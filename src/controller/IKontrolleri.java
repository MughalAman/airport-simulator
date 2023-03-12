package controller;

import simu.model.TapahtumanTyyppi;

/**
Rajapinta, joka määrittelee käyttöliittymän ja moottorin väliset toiminnot.

Käyttöliittymä voi käyttää tätä rajapintaa käynnistääkseen simuloinnin, muuttaakseen simuloinnin nopeutta, tallentaakseen tuloksia

ja näyttääkseen tuloksia.

Moottori voi käyttää tätä rajapintaa välittääkseen tietoja simuloinnin tilasta käyttöliittymälle.

Tämän rajapinnan toteuttavan luokan pitää toteuttaa kaikki rajapinnassa määritellyt metodit.

@author [tekijä]
@version [versionumero]
*/

public interface IKontrolleri {

		// Rajapinta, joka tarjotaan  käyttöliittymälle:

    	// Käynnistää simuloinnin.
		public void kaynnistaSimulointi();
		
		// Nopeuttaa simulointia.
		public void nopeuta();
		
		// Hidastaa simulointia.
		public void hidasta();
		
		/**
	    Tallentaa tuloksia simuloinnin loputtua.
	    
	    @param Loppuaika Simuloinnin lopetusaika.
	    @param AsiakasMaara Asiakkaiden määrä simuloinnin aikana.
	    @param CheckAktiiviAika Check-in-pisteiden aktiiviaika simuloinnin aikana.
	    @param TurvaTarkastus Turvatarkastusjonon pituus simuloinnin aikana.
	    @param OleskeluAikaTurvaTarkastus Turvatarkastuksen läpäisseiden asiakkaiden keskimääräinen oleskeluaika turvatarkastusjonossa.
	    @param CheckInKayttoaste Check-in-pisteiden käyttöaste simuloinnin aikana.
	    @param LentokentanTeho Lentokentän tehokkuus simuloinnin aikana.
	    @param CheckinPalveluaika Check-in-pisteiden palveluaika.
	    @param TurvatarkastusJono Turvatarkastusjonon pituus simuloinnin aikana.
	    @param TurvatarkastusJononPituus Turvatarkastusjonon pituus simuloinnin aikana.
	    */
		public void tallennaTulokset(double Loppuaika, int AsiakasMaara, double CheckAktiiviAika, double TurvaTarkastus, double OleskeluAikaTurvaTarkastus, double CheckInKayttoaste, double LentokentanTeho, double CheckinPalveluaika, double TurvatarkastusJono, double TurvatarkastusJononPituus);
		
		/**
	    Palauttaa tallennetut tulokset merkkijonomuodossa.
	    
	    @return Merkkijonomuotoinen esitys tallennetuista tuloksista.
	    */
		public abstract String[] naytaTulokset();

		// Rajapinta, joka tarjotaan moottorille:
		
		/**
	    Näyttää simuloinnin lopetushetken.
	    
	    @param aika Simuloinnin lopetushetki.
	    */
		public void naytaLoppuaika(double aika);
		// public void visualisoiAsiakas();

		/**
	    Asettaa asiakasmäärän käyttöliittymässä näytettäväksi.
	    
	    @param C Asiakasmäärä, joka näytetään käyttöliittymässä.
	    */
		public void naytaAsiakasMaara(int C);
		
		/**
	    Asettaa Check-in aktiiviajan käyttöliittymässä näytettäväksi.
	    
	    @param B Check-in aktiiviaika, joka näytetään käyttöliittymässä.
	    */
		public void checkAktiiviAika(double B);
		
		/**
	    Asettaa turvatarkastuksen läpäisyajan käyttöliittymässä näytettäväksi.
	    
	    @param Ri Turvatarkastuksen läpäisyajan keskimääräinen arvo, joka näytetään käyttöliittymässä.
	    */
		public void turvaTarkastus(double Ri);
		
		/**
		Asettaa oleskeluajan turvatarkastuksessa käyttöliittymässä näytettäväksi.
		
	    @param W Oleskeluaika turvatarkastuksessa, joka näytetään käyttöliittymässä.
	    */
		public void oleskeluAikaTurvaTarkastus(double W);

		/**
	    Asettaa Check-in -jonon käyttöasteen käyttöliittymässä näytettäväksi.
	    
	    @param K Check-in -jonon käyttöaste, joka näytetään käyttöliittymässä.
	    */
		public void checkInKayttoaste(double K);
		
		/**
	    Asettaa lentokentän tehon käyttöliittymässä näytettäväksi.
	    
	    @param S Lentokentän teho, joka näytetään käyttöliittymässä.
	    */
		public void lentokentanTeho(double S);
		
		/**
	    Asettaa Check-in -palveluajan käyttöliittymässä näytettäväksi.
	    
	    @param P Check-in -palveluaika, joka näytetään käyttöliittymässä.
	    */
		public void checkinPalveluaika(double P);
		
		/**
	    Asettaa turvatarkastusjonon pituuden käyttöliittymässä näytettäväksi.
	    
	    @param A Turvatarkastusjonon pituus, joka näytetään käyttöliittymässä.
	    */
		public void turvatarkastusJono(double A);
		
		/**
	    Asettaa turvatarkastusjonon odotusajan käyttöliittymässä näytettäväksi.
	    
	    @param R Turvatarkastusjonon odotusaika, joka näytetään käyttöliittymässä.
	    */
		public void turvatarkastusJononPituus(double R);

		/**
 		* Metodi visualisoi asiakkaan nykyisen ja edellisen tapahtuman
 		*
 		* @param uusi uusi tapahtuma
 		* @param vanha vanha tapahtuma
 		*/
		public void visualisoiAsiakas(TapahtumanTyyppi uusi, TapahtumanTyyppi vanha);

}
