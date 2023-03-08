package controller;

import javafx.application.Platform;

import simu.framework.IMoottori;
import simu.model.*;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleri{   // UUSI
	static ITulosDAO tulosDAO = new TulosAccessObject();

	private IMoottori moottori;
	private ITuloskone tuloskone;
	private ISimulaattorinUI ui;


	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		this.tuloskone = new Tuloskone();
	}


	// Moottorin ohjausta:

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		moottori.setSisaankayntiJakauma(ui.getSisJakauma());
		moottori.setCheckinJakauma(ui.getCheJakauma());
		ui.getVisualisointi().tyhjennaNaytto();
		((Thread)moottori).start();
		//((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?
//		@Override
//		public void checkInJakauma(double checkinJakauma) {
//			
//		}
//		
//		@Override
//		public void sisaanKayntiJakauma(double sisaankayntiJakauma) {
//			
//		}
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

	@Override
	public void naytaAsiakasMaara(int C) {
		Platform.runLater(()->ui.setAsiakasMaara(C));
	}

	@Override
	public void checkAktiiviAika(double B) {
		Platform.runLater(()->ui.setCheckAktiiviAika(B));
	}

	@Override
	public void turvaTarkastus(double Ri) {
		Platform.runLater(()->ui.setTurvaTarkastus(Ri));
	}

	@Override
	public void oleskeluAikaTurvaTarkastus(double W) {
		Platform.runLater(()->ui.setOleskeluAikaTurvaTarkastus(W));
	}

	@Override
	public void checkInKayttoaste(double K) {
		Platform.runLater(()->ui.setCheckInKayttoaste(K));
	}

	@Override
	public void lentokentanTeho(double S) {
		Platform.runLater(()->ui.setLentokentanTeho(S));
	}

	@Override
	public void checkinPalveluaika(double P) {
		Platform.runLater(()->ui.setCheckinPalveluaika(P));
	}

	@Override
	public void turvatarkastusJono(double A) {
		Platform.runLater(()->ui.setTurvatarkastusJono(A));
	}

	@Override
	public void turvatarkastusJononPituus(double R) {
		Platform.runLater(()->ui.setTurvatarkastusJononPituus(R));
	}

	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable(){
			@Override
			public void run(){
				ui.getVisualisointi().uusiAsiakas(moottori.getPalvelupisteet());
			}
		});
	}
	
	@Override
	public void tallennaTulokset(double Loppuaika, int AsiakasMaara, double CheckAktiiviAika, double TurvaTarkastus, double OleskeluAikaTurvaTarkastus, double CheckInKayttoaste, double LentokentanTeho, double CheckinPalveluaika, double TurvatarkastusJono, double TurvatarkastusJononPituus) {
		tulosDAO.createTulos(new Tulos(Loppuaika, AsiakasMaara, CheckAktiiviAika, TurvaTarkastus, OleskeluAikaTurvaTarkastus, CheckInKayttoaste, LentokentanTeho, CheckinPalveluaika, TurvatarkastusJono, TurvatarkastusJononPituus));
	}
	
	@Override
	public String[] naytaTulokset() {
		return tuloskone.getTulokset();
	}
}
