package view;

import simu.model.TapahtumanTyyppi;

public interface IVisualisointi {

	public void tyhjennaNaytto();
	
	public void uusiAsiakas();
	
	public void uusiAsiakas(TapahtumanTyyppi uusi, TapahtumanTyyppi vanha);
		
}

