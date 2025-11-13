
package vezerlo;

import javax.swing.JButton;
import nezet.LighOnJavaGUI_Nezet;
import modell.LightOnJatekModell;

public class LightOnJavaController {
    private LighOnJavaGUI_Nezet nezet;
    private LightOnJatekModell model;
    private JButton[][] gomb;
    
    public LightOnJavaController(LighOnJavaGUI_Nezet nezet,LightOnJatekModell model){
        this.nezet=nezet;
        this.model=model;
        
        gomb= new JButton[][]{
            {nezet.getjButton11(), nezet.getjButton12(), nezet.getjButton13()},
            {nezet.getjButton14(), nezet.getjButton2(), nezet.getjButton3()},
            {nezet.getjButton4(), nezet.getjButton5(), nezet.getjButton6()}
        };
    }
}
