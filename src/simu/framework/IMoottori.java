package simu.framework;

import simu.model.Palvelupisteryhma;

public interface IMoottori { // UUSI
		
	// Kontrolleri käyttää tätä rajapintaa
	
	public void setSimulointiaika(double aika);
	public void setViive(long aika);
	public long getViive();
	public Palvelupisteryhma[] getPalvelupisteet();
}
