package testi;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import simu.framework.Trace;
import view.SimulaattorinGUI;

public class SimulaattorinGUITest {

    @Test
    public void testSimulaattorinGUI() {
    	Trace.setTraceLevel(Trace.Level.INFO);
        SimulaattorinGUI gui = new SimulaattorinGUI();
        assertNotNull(gui);
        
    }
}
