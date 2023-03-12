

package view;

import simu.model.TapahtumanTyyppi;



/**
* @version 1.1
*/


public interface IVisualisointi {

    /**
     * Tyhjentää esityksen näytön.
     */
    public void tyhjennaNaytto();

    /**
     * Lisää uuden asiakkaan esitykseen.
     */
    public void uusiAsiakas();

    /**
     * Lisää uuden asiakkaan esitykseen ja päivittää sen tilan.
     * @param uusi uusi tapahtuma
     * @param vanha vanha tapahtuma
     */
    public void uusiAsiakas(TapahtumanTyyppi uusi, TapahtumanTyyppi vanha);

}
