package controller;

public interface IKontrolleri {
	
		// Rajapinta, joka tarjotaan  käyttöliittymälle:
	
		public void kaynnistaSimulointi();
		public void nopeuta();
		public void hidasta();
		
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		
		public void naytaLoppuTuloste(String LoppuTuloste);
		public void visualisoiAsiakas();
		
		public void naytaAsiakasMaara(int C);
		public void checkAktiiviAika(double B);
		public void turvaTarkastus(double Ri);
		public void oleskeluAikaTurvaTarkastus(double W);
		
		public void checkInKayttoaste(double K);
		public void lentokentanTeho(double S);
		public void checkinPalveluaika(double P);
		public void turvatarkastusJono(double A);
		public void turvatarkastusJononPituus(double R);

}
