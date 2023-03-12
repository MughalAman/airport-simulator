package simu.model;

/**
*Rajapinta tulosten tallennus- ja lukemistoiminnoille.
*@author Aman Mughal
*@version 1.5
*/

public interface ITulosDAO {
	
	
	/**
	*Tallentaa uuden tuloksen tietokantaan.
	*@param tulos tallennettava Tulos-olio
	*@return true, jos tallennus onnistui, muuten false
	*/
	public boolean createTulos(Tulos tulos);
	
	
	/**
	*Lukee tietokannasta tallennetut tulokset.
	*@return Tulos-taulukko, joka sisältää kaikki tallennetut tulokset
	*/
	public Tulos[] readTulokset();
}
