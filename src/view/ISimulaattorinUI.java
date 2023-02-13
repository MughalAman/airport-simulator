package view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public int getC();
	public double getB();
	public double getRi();
	public double getW();
	public long getViive();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	public void setAsiakasMaara(int C);
	public void setCheckAktiiviAika(double B);
	public void setTurvaTarkastus(double Ri);
	public void setOleskeluAikaTurvaTarkastus(double W);
	
	// Kontrolleri tarvitsee 
	public IVisualisointi getVisualisointi();

}
