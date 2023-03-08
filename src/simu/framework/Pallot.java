package simu.framework;

import java.util.ArrayList;

import view.Liike;

public class Pallot {
	
	private ArrayList<Liike> pallot;
	private static Pallot instanssi;
	
	private Pallot() {
		pallot = new ArrayList<Liike>();
	}
	
	public static Pallot getInstance(){
		if (instanssi == null){
			instanssi = new Pallot();	
		}
		return instanssi;
	}
	
	public void paivita() {
		for(int i = 0; i<pallot.size(); i++ ) {
			pallot.get(i).paivita();
			if(pallot.get(i).isValmis()) {
				pallot.remove(i);
			}
		}
	}
	
	public ArrayList<Liike> getPallot(){
		return pallot;
	}
	

}
