package simu.model;

import java.util.LinkedList;

/**
 * @author Amin Salamatin
 *Piste-luokka kuvaa pistettä kartalla, jolla voi olla asiakkaiden jono.
 *Jokaisella pisteellä on x- ja y-koordinaatit, tyyppi sekä jonoon liittyvät asiakkaat.
*/

public class Piste {
	
	private int x, y;
	private TapahtumanTyyppi tyyppi;
	private LinkedList<Asiakas> jono;
	
	
	/**
	*Luokan Piste konstruktori.
	*@param x Pisteen x-koordinaatti.
	*@param y Pisteen y-koordinaatti.
	*@param tyyppi Pisteen TapahtumanTyyppi.
	*/
	public Piste(int x, int y, TapahtumanTyyppi tyyppi) {
		this.x = x;
		this.y = y;
		this.tyyppi = tyyppi;
		
	}
	
	/**
	*Palauttaa ensimmäisen jonossa olevan asiakkaan TapahtumanTyyppi.
	*@return Palauttaa TapahtumanTyyppi -enumeration ensimmäisen asiakkaan jonossa.
	*/
	
	public TapahtumanTyyppi getFirst() {
		return jono.peek().getTapahtuma();
	}
	
	/**
	*Palauttaa pisteen TapahtumanTyyppi-arvon.
	*@return Palauttaa pisteen TapahtumanTyyppi-arvon.
	*/
	
	public TapahtumanTyyppi getTapahtuma() {
		return tyyppi;
	}
	
	
	/**
	*Palauttaa pisteen x-koordinaatin.
	*@return Palauttaa pisteen x-koordinaatin.
	*/
	
	public int getX() {
		return x;
	}
	
	
	/**
	*Palauttaa pisteen y-koordinaatin.
	*@return Palauttaa pisteen y-koordinaatin.
	*/
	
	public int getY() {
		return y;
	}

}
