package testi;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.OmaMoottori;
import org.junit.Test;

public class Simulaattori { //Tekstipohjainen

	public static void main(String[] args) {
		
		Trace.setTraceLevel(Level.INFO);
		Moottori m = new OmaMoottori();
		m.setSimulointiaika(1000);
		m.aja();
	}
}
