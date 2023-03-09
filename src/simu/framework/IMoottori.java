package simu.framework;

import simu.model.Palvelupisteryhma;

public interface IMoottori { // UUSI
		
	// Kontrolleri käyttää tätä rajapintaa
	
	public void setSimulointiaika(double aika);
	public void setViive(long aika);
	public long getViive();
	public Palvelupisteryhma[] getPalvelupisteet();
	public void setSisaankayntiJakauma(double sisJakauma);
	public void setCheckinJakauma(double cheJakauma);
	public void setPalvelupisteetLkm(int infoLkm, int manualLkm, int autoLkm, int securityLkm, int sgateLkm, int gateLkm, int planeLkm);
}
