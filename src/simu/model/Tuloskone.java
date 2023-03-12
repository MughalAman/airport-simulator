package simu.model;


/**
 * Tämä Luokka luo uuden Tuloskone-olion, alustaa tulokset DAO:sta ja Palauttaa tuloskoneen tulokset merkkijonotaulukossa.
 * @author Aman Mughal
 * @version 1.0
 */

public class Tuloskone implements ITuloskone{
	private ITulosDAO tulosDAO;
	private Tulos[] tulokset;
	/**
	 * Luo uuden Tuloskone-olion ja alustaa tulokset DAO:sta.
	 */
	public Tuloskone() {
		tulosDAO = new TulosAccessObject();
		tulokset = tulosDAO.readTulokset();
	}

	/**
	 * Palauttaa tuloskoneen tulokset merkkijonotaulukossa.
	 *
	 * @return tuloskoneen tulokset merkkijonotaulukossa
	 */
	@Override
	public String[] getTulokset() {
		String[] tuloksetStr = new String[tulokset.length];
        for (int i = 0; i < tulokset.length; i++) {
            tuloksetStr[i] = tulokset[i].toString();
        }
        return tuloksetStr;
	}
	
}


