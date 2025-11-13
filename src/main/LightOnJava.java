
package main;

import modell.LightOnJatekModell;
import nezet.LighOnJavaGUI_Nezet;
import vezerlo.LightOnJavaController;

public class LightOnJava {

    
    public static void main(String[] args) {
        LighOnJavaGUI_Nezet nezet = new LighOnJavaGUI_Nezet();
        LightOnJatekModell model = new LightOnJatekModell();
        new LightOnJavaController(nezet, model);
        nezet.setVisible(true);
    }
    
}
