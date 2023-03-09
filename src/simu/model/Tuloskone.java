package simu.model;

public class Tuloskone implements ITuloskone{
	private ITulosDAO tulosDAO;
	private Tulos[] tulokset;
	
	public Tuloskone() {
		tulosDAO = new TulosAccessObject();
		tulokset = tulosDAO.readTulokset();
	}
	
	@Override
	public String[] getTulokset() {
		String[] tuloksetStr = new String[tulokset.length];
        for (int i = 0; i < tulokset.length; i++) {
            tuloksetStr[i] = tulokset[i].toString();
        }
        return tuloksetStr;
	}
	
}
