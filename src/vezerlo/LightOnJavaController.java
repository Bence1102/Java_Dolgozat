
package vezerlo;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
        
        nezet.getBtnindit().addActionListener(e->newGame());
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int sorok = i;
                int oszlopok = j;
                
                gomb[i][j].addActionListener(e->kattint(sorok,oszlopok));
            }
        }
        
        nezet.addWindowListener(new WindowListener() {
            @Override
                public void windowClosing(WindowEvent e) {
                    kilepesMegerosites();
            }   
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        frissites();
        
    }

    private void newGame() {
      try {
            model.mehet();
            nezet.getTxtjatekosnev().setText("");
            nezet.getTxtjateknyertes();
            nezet.getTxtjatekosnev().setEditable(true);
            nezet.getBtnindit().setEnabled(true);
            frissites();
            JOptionPane.showConfirmDialog(nezet, "Új játék indult");
      }catch(Exception e){
          JOptionPane.showMessageDialog(nezet, "Hiba az új játék indításakor: " + e.getMessage());
      }
      
    }

    private void kattint(int sorok, int oszlopok) {
        model.kapcsolo(sorok,oszlopok);
        frissites();
        if(model.nyertes()){
            String player = nezet.getTxtjatekosnev().getText();
            nezet.getTxtjateknyertes().setText(player);
            JOptionPane.showMessageDialog(nezet, "Grat" +  player + ", nyertél");
        }
    }
    
    private void kilepesMegerosites() {
       int kilepvalasz = JOptionPane.showConfirmDialog(
               nezet,
               "Biztos Kilépsz?",
               "Kilépés megerősítése",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE
       );
            if(kilepvalasz == JOptionPane.YES_OPTION){
                System.exit(0);
            }
    }

    private void frissites() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean mezoaktiv = model.getErtek(i,j);
                gomb[i][j].setBackground(mezoaktiv ?  Color.YELLOW : Color.DARK_GRAY);
            }
        }
    }
}
