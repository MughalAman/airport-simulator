package simu.model;




/**
 * @author Aman Mughal
 * Tulos-luokka kuvaa lentokentän toiminnan tuloksia simuloinnin aikana.
 * @version 1.1
 */


public class Tulos {
	private double Loppuaika;
	private int AsiakasMaara;
	private double CheckAktiiviAika;
	private double TurvaTarkastus;
	private double OleskeluAikaTurvaTarkastus;
	private double CheckInKayttoaste;
	private double LentokentanTeho;
	private double CheckinPalveluaika;
	private double TurvatarkastusJono;
	private double TurvatarkastusJononPituus;


    /**
     * Tulos-luokan parametritön konstruktori.
     */
	
	public Tulos() {

	}
	
	   /**
     * Tulos-luokan parametrillinen konstruktori.
     * @param Loppuaika lentokentän sulkemisaika.
     * @param AsiakasMaara asiakkaiden määrä.
     * @param CheckAktiiviAika turvatarkastuspisteen aktiiviaika.
     * @param TurvaTarkastus turvatarkastuspisteen kapasiteetti.
     * @param OleskeluAikaTurvaTarkastus turvatarkastuspisteen läpimenoaika.
     * @param CheckInKayttoaste check-in tiskin käyttöaste.
     * @param LentokentanTeho lentokentän kokonaiskapasiteetti.
     * @param CheckinPalveluaika check-in tiskin palveluaika.
     * @param TurvatarkastusJono turvatarkastusjonon pituus.
     * @param TurvatarkastusJononPituus turvatarkastusjonon pituus suhteessa kapasiteettiin.
     */

	public Tulos(double Loppuaika, int AsiakasMaara, double CheckAktiiviAika, double TurvaTarkastus, double OleskeluAikaTurvaTarkastus, double CheckInKayttoaste, double LentokentanTeho, double CheckinPalveluaika, double TurvatarkastusJono, double TurvatarkastusJononPituus) {
		this.setLoppuaika(Loppuaika);
		this.setAsiakasMaara(AsiakasMaara);
		this.setCheckAktiiviAika(CheckAktiiviAika);
		this.setTurvaTarkastus(TurvaTarkastus);
		this.setOleskeluAikaTurvaTarkastus(OleskeluAikaTurvaTarkastus);
		this.setCheckInKayttoaste(CheckInKayttoaste);
		this.setLentokentanTeho(LentokentanTeho);
		this.setCheckinPalveluaika(CheckinPalveluaika);
		this.setTurvatarkastusJono(TurvatarkastusJono);
		this.setTurvatarkastusJononPituus(TurvatarkastusJononPituus);
	}

	public double getLoppuaika() {
		return this.Loppuaika;
	}

	public void setLoppuaika(double Loppuaika) {
		this.Loppuaika = Loppuaika;
	}

	public int getAsiakasMaara() {
		return this.AsiakasMaara;
	}

	public void setAsiakasMaara(int AsiakasMaara) {
		this.AsiakasMaara = AsiakasMaara;
	}

	public double getCheckAktiiviAika() {
		return this.CheckAktiiviAika;
	}

	public void setCheckAktiiviAika(double CheckAktiiviAika) {
		this.CheckAktiiviAika = CheckAktiiviAika;
	}

	public double getTurvaTarkastus() {
		return this.TurvaTarkastus;
	}

	public void setTurvaTarkastus(double TurvaTarkastus) {
		this.TurvaTarkastus = TurvaTarkastus;
	}

	public double getOleskeluAikaTurvaTarkastus() {
		return this.OleskeluAikaTurvaTarkastus;
	}

	public void setOleskeluAikaTurvaTarkastus(double OleskeluAikaTurvaTarkastus) {
		this.OleskeluAikaTurvaTarkastus = OleskeluAikaTurvaTarkastus;
	}

	public double getCheckInKayttoaste() {
		return this.CheckInKayttoaste;
	}

	public void setCheckInKayttoaste(double CheckInKayttoaste) {
		this.CheckInKayttoaste = CheckInKayttoaste;
	}

	public double getLentokentanTeho() {
		return this.LentokentanTeho;
	}

	public void setLentokentanTeho(double LentokentanTeho) {
		this.LentokentanTeho = LentokentanTeho;
	}

	public double getCheckinPalveluaika() {
		return this.CheckinPalveluaika;
	}

	public void setCheckinPalveluaika(double CheckinPalveluaika) {
		this.CheckinPalveluaika = CheckinPalveluaika;
	}

	public double getTurvatarkastusJono() {
		return this.TurvatarkastusJono;
	}

	public void setTurvatarkastusJono(double TurvatarkastusJono) {
		this.TurvatarkastusJono = TurvatarkastusJono;
	}

	public double getTurvatarkastusJononPituus() {
		return this.TurvatarkastusJononPituus;
	}

	public void setTurvatarkastusJononPituus(double TurvatarkastusJononPituus) {
		this.TurvatarkastusJononPituus = TurvatarkastusJononPituus;
	}

	
    /**
     * Palauttaa tiedot toStringillä
     */

	@Override
	public String toString() {
		return "{" +
			" \"Loppuaika\":" + getLoppuaika() +
			", \"AsiakasMaara\":" + getAsiakasMaara() +
			", \"CheckAktiiviAika\":" + getCheckAktiiviAika() +
			", \"TurvaTarkastus\":" + getTurvaTarkastus() +
			", \"OleskeluAikaTurvaTarkastus\":" + getOleskeluAikaTurvaTarkastus() +
			", \"CheckInKayttoaste\":" + getCheckInKayttoaste() +
			", \"LentokentanTeho\":" + getLentokentanTeho() +
			", \"CheckinPalveluaika\":" + getCheckinPalveluaika() +
			", \"TurvatarkastusJono\":" + getTurvatarkastusJono() +
			", \"TurvatarkastusJononPituus\":" + getTurvatarkastusJononPituus() +
			"}";
	}

}
