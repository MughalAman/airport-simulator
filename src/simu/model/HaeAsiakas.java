package simu.model;

/**
 * @author 
 *HaeAsiakas-luokka, joka kuvaa tapahtumia, joilla haetaan asiakkaita tiettyyn tapahtumaan.
 */
public class HaeAsiakas {

	private TapahtumanTyyppi tyyppi;

	/**
	 * Asettaa haettavan tapahtuman tyypin.
	 * 
	 * @param tyyppi haettavan tapahtuman tyyppi
	 */
	public void setTapahtuma(TapahtumanTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}

	/**
	 * Palauttaa haettavan tapahtuman tyypin.
	 * 
	 * @return haettavan tapahtuman tyyppi
	 */
	public TapahtumanTyyppi getTapahtuma() {
		return tyyppi;
	}
}