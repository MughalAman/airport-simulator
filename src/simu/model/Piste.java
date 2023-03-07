package simu.model;

import java.util.LinkedList;

public class Piste {
	
	private int x, y;
	private TapahtumanTyyppi tyyppi;
	private LinkedList<Asiakas> jono;
	
	public Piste(int x, int y, TapahtumanTyyppi tyyppi) {
		this.x = x;
		this.y = y;
		this.tyyppi = tyyppi;
		
	}
	
	public TapahtumanTyyppi getFirst() {
		return jono.peek().getTapahtuma();
	}
	
	public TapahtumanTyyppi getTapahtuma() {
		return tyyppi;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
