package testi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import simu.framework.Trace;
import view.SimulaattorinGUI;

public class SimulaattorinGUITest {

    @Test
    public void testSimulaattorinGUI() {
    	/**
    	* Testaa GUI-luokkaa
    	* @author Jeremia Kekkonen
    	* @version 1.1
    	*/
    	Trace.setTraceLevel(Trace.Level.INFO);
        SimulaattorinGUI gui = new SimulaattorinGUI();
        assertNotNull(gui);

    }
}
