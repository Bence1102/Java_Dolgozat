package LightOnJatekTeszt;

import modell.LightOnJatekModell;
import nezet.LighOnJavaGUI_Nezet;
import vezerlo.LightOnJavaController;

public class LightOnControllerTeszt {

    private LighOnJavaGUI_Nezet nezet;
    private LightOnJatekModell model;
    private LightOnJavaController controller;

    
    public void setUp() {
        nezet = new LighOnJavaGUI_Nezet(); 
        model = new LightOnJatekModell();
        controller = new LightOnJavaController(nezet, model);
    }

  
    public void testKattint() {
        controller.kattint(0,0);
        assertTrue(model.getErtek(0,0));
        assertTrue(model.getErtek(0,1));
        assertTrue(model.getErtek(1,0));
        assertFalse(model.getErtek(1,1));
    }

    
    public void testUjraindit() {
        nezet.getTxtjatekosnev().setText("Bence");
        controller.newGame();
        controller.kattint(0,0);
        controller.ujraindit();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                assertFalse(model.getErtek(i,j));
    }
    private void assertTrue(boolean ertek) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    private void assertFalse(boolean ertek) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
