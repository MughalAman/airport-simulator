package view;

public interface ISimulaattorinUI {

	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public double getT();
	public int getC();
	public double getB();
	public double getRi();
	public double getW();
	public double getK();
	public double getS();
	public double getP();
	public double getA();
	public double getR();
	public long getViive();

	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa
	public void setLoppuaika(double aika);
	public void setAsiakasMaara(int C);
	public void setCheckAktiiviAika(double B);
	public void setTurvaTarkastus(double Ri);
	public void setOleskeluAikaTurvaTarkastus(double W);
	public void setCheckInKayttoaste(double K);
	public void setLentokentanTeho(double S);
	public void setCheckinPalveluaika(double P);
	public void setTurvatarkastusJono(double A);
	public void setTurvatarkastusJononPituus(double R);

	// Kontrolleri tarvitsee
	public IVisualisointi getVisualisointi();

}