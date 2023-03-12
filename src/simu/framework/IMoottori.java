package simu.framework;

import simu.model.Palvelupisteryhma;

/**
Tämä rajapinta määrittelee metodit, joita kontrolleri käyttää yhteyden muodostamiseen moottorin kanssa.
@author [tekijä]
@version [versionumero]
*/
public interface IMoottori { // UUSI
		
	// Kontrolleri käyttää tätä rajapintaa
	
	/**
    Asettaa simulointiajan moottorille.
    @param aika simulointiaika.
    */
	public void setSimulointiaika(double aika);
	
	/**
    Asettaa moottorin viiveen.
    @param aika viive millisekunteina.
    */
	public void setViive(long aika);
	
	/**
    Palauttaa moottorin viiveen.
    @return viive millisekunteina.
    */
	public long getViive();
	
	/**
    Palauttaa kaikki palvelupisteryhmät.
    @return kaikki palvelupisteryhmät.
    */
	public Palvelupisteryhma[] getPalvelupisteet();
	
	/**
    Asettaa sisäänkäynnin jakautuman.
    @param sisJakauma sisäänkäynnin jakautuma.
    */
	public void setSisaankayntiJakauma(double sisJakauma);
	
	/**
    Asettaa check-in jakautuman.
    @param cheJakauma check-in jakautuma.
    */
	public void setCheckinJakauma(double cheJakauma);
	
	/**
    Asettaa palvelupisteiden määrän.
    @param infoLkm infopisteiden lukumäärä.
    @param manualLkm manuaalipisteiden lukumäärä.
    @param autoLkm autopisteiden lukumäärä.
    */
	public void setPalvelupisteetLkm(int infoLkm, int manualLkm, int autoLkm);
}
