package controller;

import javafx.application.Platform;

import simu.framework.IMoottori;
import simu.model.*;
import view.ISimulaattorinUI;

/**
Kontrolleri-luokka hallinnoi simulaattorin toimintaa ja kommunikoi käyttöliittymän ja moottorin kanssa.
Se myös ylläpitää yhteyttä tuloskoneeseen ja tuloskantaan.

@author tekijä
@version versionumero
*/
public class Kontrolleri implements IKontrolleri{   // UUSI
	
	/**
    Staattinen muuttuja tulosDAO, joka sisältää viittauksen tuloskantaan.
    */
	static ITulosDAO tulosDAO = new TulosAccessObject();

	private IMoottori moottori;
	private ITuloskone tuloskone;
	private ISimulaattorinUI ui;
	
	/**
    Kontrollerin konstruktori, joka luo uuden Kontrolleri-olion.
    
    @param ui Käyttöliittymä, jonka kanssa Kontrolleri kommunikoi.
    */
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		this.tuloskone = new Tuloskone();
	}


	// Moottorin ohjausta:

	@Override
	/**
    Käynnistää simuloinnin
    Luo uuden moottorisäikeen jokaista simulointia varten.
    Asettaa säikeelle simulointiajan, viiveen, sisäänkäynnin jakautuman, check-in jakautuman
    ja palvelupisteiden lukumäärän käyttöliittymästä.
    Tyhjentää käyttöliittymän visualisoinnin näytön.
    Käynnistää moottorisäikeen.
    */
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		moottori.setSisaankayntiJakauma(ui.getSisJakauma());
		moottori.setCheckinJakauma(ui.getCheJakauma());
		moottori.setPalvelupisteetLkm(ui.getInfoLkm(), ui.getManualLkm(), ui.getAutoLkm());
		ui.getVisualisointi().tyhjennaNaytto();
		((Thread)moottori).start();
	}

	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*0.9));
	}



	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata JavaFX-säikeeseen:

	/**
    @param aika loppuaika, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(()->ui.setLoppuaika(aika));
	}

	/**
    @param C asiakasmäärä, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void naytaAsiakasMaara(int C) {
		Platform.runLater(()->ui.setAsiakasMaara(C));
	}

	/**
    @param B Check-in aktiiviaika, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void checkAktiiviAika(double B) {
		Platform.runLater(()->ui.setCheckAktiiviAika(B));
	}

	/**
    @param Ri Turvatarkastuksen läpimenoaika, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void turvaTarkastus(double Ri) {
		Platform.runLater(()->ui.setTurvaTarkastus(Ri));
	}

	/**
    @param W Turvatarkastuksen oleskeluaika, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void oleskeluAikaTurvaTarkastus(double W) {
		Platform.runLater(()->ui.setOleskeluAikaTurvaTarkastus(W));
	}

	/**
    @param K Check-in käyttöaste, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void checkInKayttoaste(double K) {
		Platform.runLater(()->ui.setCheckInKayttoaste(K));
	}

	/**
    @param S Lentokentän teho, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void lentokentanTeho(double S) {
		Platform.runLater(()->ui.setLentokentanTeho(S));
	}

	/**
    @param P Check-in palveluaika, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void checkinPalveluaika(double P) {
		Platform.runLater(()->ui.setCheckinPalveluaika(P));
	}

	/**
    @param A Turvatarkastuksen jono, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void turvatarkastusJono(double A) {
		Platform.runLater(()->ui.setTurvatarkastusJono(A));
	}

	/**
    @param R Turvatarkastuksen jonon pituus, joka asetetaan käyttöliittymässä näytettäväksi
    */
	@Override
	public void turvatarkastusJononPituus(double R) {
		Platform.runLater(()->ui.setTurvatarkastusJononPituus(R));
	}

	@Override
	/**
    Visualisoi uuden asiakkaan tapahtuman tapahtumatyypin uusi ja vanhan tapahtumatyypin vanha välillä.
    
    @param uusi uusi asiakas
    @param vanha vanha asiakas
    */
	public void visualisoiAsiakas(TapahtumanTyyppi uusi, TapahtumanTyyppi vanha) {
		/**
	    Moottorisäie, joka käynnistetään simulaation aikana
	    */
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				ui.getVisualisointi().uusiAsiakas(uusi, vanha);
			}
		});
	}
	
	@Override
	public void tallennaTulokset(double Loppuaika, int AsiakasMaara, double CheckAktiiviAika, double TurvaTarkastus, double OleskeluAikaTurvaTarkastus, double CheckInKayttoaste, double LentokentanTeho, double CheckinPalveluaika, double TurvatarkastusJono, double TurvatarkastusJononPituus) {
		tulosDAO.createTulos(new Tulos(Loppuaika, AsiakasMaara, CheckAktiiviAika, TurvaTarkastus, OleskeluAikaTurvaTarkastus, CheckInKayttoaste, LentokentanTeho, CheckinPalveluaika, TurvatarkastusJono, TurvatarkastusJononPituus));
	}
	
	@Override
	public String[] naytaTulokset() {
		this.tuloskone = new Tuloskone();
	
		return tuloskone.getTulokset();
		
		
	
	}


}
