package simu.framework;

import java.util.ArrayList;


import view.Liike;

public class Kello {

	private double aika;
	private static Kello instanssi;
	private static ArrayList<Liike> pallot;
	
	private Kello(){
		aika = 0;
	}
	
	public static Kello getInstance(){
		if (instanssi == null){
			pallot = new ArrayList<Liike>();
			instanssi = new Kello();	
		}
		return instanssi;
	}
	
	public void setAika(double aika){
		this.aika = aika;
		for(int i = 0; i<pallot.size(); i++ ) {
			pallot.get(i).paivita();
			if(pallot.get(i).isValmis()) {
				pallot.remove(i);
			}
		}
	}

	public double getAika(){
		return aika;
	}
	
	public static ArrayList<Liike> getPallot(){
		return pallot;
	}

}
