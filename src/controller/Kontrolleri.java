package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleri{   // UUSI
	
	private IMoottori moottori; 
	private ISimulaattorinUI ui;
	
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
	}

	
	// Moottorin ohjausta:
		
	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		ui.getVisualisointi().tyhjennaNaytto();
		((Thread)moottori).start();
		//((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?
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
		
	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(()->ui.setLoppuaika(aika)); 
	}
	
	public void naytaAsiakasMaara(int C) {
		Platform.runLater(()->ui.setAsiakasMaara(C)); 
	}
	
	public void checkAktiiviAika(double B) {
		Platform.runLater(()->ui.setCheckAktiiviAika(B)); 
	}
	
	public void turvaTarkastus(double Ri) {
		Platform.runLater(()->ui.setTurvaTarkastus(Ri)); 
	}
	
	public void oleskeluAikaTurvaTarkastus(double W) {
		Platform.runLater(()->ui.setOleskeluAikaTurvaTarkastus(W)); 
	}
	
	public void checkInKayttoaste(double K) {
		Platform.runLater(()->ui.setCheckInKayttoaste(K)); 
	}
	
	public void lentokentanTeho(double S) {
		Platform.runLater(()->ui.setLentokentanTeho(S)); 
	}
	
	public void checkinPalveluaika(double P) {
		Platform.runLater(()->ui.setCheckinPalveluaika(P)); 
	}
	
	public void turvatarkastusJono(double A) {
		Platform.runLater(()->ui.setTurvatarkastusJono(A)); 
	}
	
	public void turvatarkastusJononPituus(double R) {
		Platform.runLater(()->ui.setTurvatarkastusJononPituus(R)); 
	}

	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable(){
			public void run(){
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}

}
