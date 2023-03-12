package view;

/**
 * Tämä rajapinta määrittelee simulaattorin käyttöliittymän (UI) tarvittavat
 * metodit. Kontrolleri käyttää näitä metodeja käyttöliittymän kautta syötteen
 * välittämiseen Moottorille ja tulosten vastaanottamiseen Moottorilta.
 * @version 1.1
 */
public interface ISimulaattorinUI {

    /**
     * Palauttaa ajan.
     *
     * @return aika
     */
    public double getAika();

    /**
     * Palauttaa ajan välittömässä lähtötilanteessa.
     *
     * @return ajan lähtötilanne
     */
    public double getT();

    /**
     * Palauttaa asiakkaiden määrän.
     *
     * @return asiakkaiden määrä
     */
    public int getC();

    /**
     * Palauttaa tarkastuslipun käsittelijöiden määrän.
     *
     * @return käsittelijöiden määrä
     */
    public double getB();

    /**
     * Palauttaa turvatarkastuksen aktiivisuusajan.
     *
     * @return turvatarkastuksen aktiivisuusaika
     */
    public double getRi();

    /**
     * Palauttaa oleskeluajan turvatarkastuksessa.
     *
     * @return oleskeluaika turvatarkastuksessa
     */
    public double getW();

    /**
     * Palauttaa check-in -järjestelmän käyttöasteen.
     *
     * @return check-in -järjestelmän käyttöaste
     */
    public double getK();

    /**
     * Palauttaa lentokentän tehon.
     *
     * @return lentokentän teho
     */
    public double getS();

    /**
     * Palauttaa check-in -palvelun keston.
     *
     * @return check-in -palvelun kesto
     */
    public double getP();

    /**
     * Palauttaa turvatarkastuksen asiakkaiden keskimääräisen koon.
     *
     * @return turvatarkastuksen asiakkaiden keskimääräinen koko
     */
    public double getA();

    /**
     * Palauttaa turvatarkastuksen jonon keskimääräisen pituuden.
     *
     * @return turvatarkastuksen jonon keskimääräinen pituus
     */
    public double getR();

    /**
     * Palauttaa viiveen.
     *
     * @return viive
     */
    public long getViive();

    /**
     * Palauttaa sisääntulon asiakkaiden jakauman.
     *
     * @return sisääntulon asiakkaiden jakauma
     */
    public double getSisJakauma();

    /**
     * Palauttaa turvatarkastuksen asiakkaiden jakauman.
     *
     * @return turvatarkastuksen asiakkaiden jakauma
     */
    /**
     * Palauttaa lentomatkustajien saapumisaikojen jakautuman.
     *
     * @return lentomatkustajien saapumisaikojen jakautuma
     */
    public double getCheJakauma();

    /**
     * Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa.
     */
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

    /**
     * Palauttaa käyttöliittymän visualisointi-rajapinnan.
     *
     * @return käyttöliittymän visualisointi-rajapinta
     */
    public IVisualisointi getVisualisointi();

    /**
     * Palauttaa informaatioluokan tulosten määrän.
     *
     * @return informaatioluokan tulosten määrä
     */
    public int getInfoLkm();

    /**
     * Palauttaa manuaalisen luokan tulosten määrän.
     *
     * @return manuaalisen luokan tulosten määrä
     */
    public int getManualLkm();

    /**
     * Palauttaa automaattisen luokan tulosten määrän.
     *
     * @return automaattisen luokan tulosten määrä
     */
	public int getAutoLkm();

}
