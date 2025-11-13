
package vezerlo;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gomb[i][j].setEnabled(false);
            }
        }
        
        
        nezet.getBtnindit().addActionListener(e->newGame());
        nezet.getjButton1().addActionListener(e-> ujraindit());
        
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
        
        nezet.getMentesmenu().addActionListener(e -> mentes());
        nezet.getBetoltmenu().addActionListener(e -> betoltes());
        nezet.getKilepmenu().addActionListener(e -> kilepesMegerosites());
        
        frissites();
        
    }

    private void newGame() {
       String jatekos = nezet.getTxtjatekosnev().getText().trim();
       
       if (jatekos.isEmpty()) {
        JOptionPane.showMessageDialog(nezet, "Kérlek, add meg a neved!");
            
        return;
       }
       nezet.getTxtjatekosnev().setEditable(false);
       nezet.getBtnindit().setEnabled(false);
       nezet.getjButton1().setEnabled(false);
       
       for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            gomb[i][j].setEnabled(true);
            gomb[i][j].setBackground(Color.DARK_GRAY);
        }
       }
       
       model.ujgomb();
       frissites();
       
       JOptionPane.showMessageDialog(nezet, "Játék elindult, hajrá " + jatekos + "!"); 
    }

    private void kattint(int sorok, int oszlopok) {
        model.kapcsolo(sorok,oszlopok);
        frissites();
        if(model.nyertes()){
            String player = nezet.getTxtjatekosnev().getText();
            nezet.getTxtjateknyertes().setText(player);
            JOptionPane.showMessageDialog(nezet, "Grat" +  player + ", nyertél");
            nezet.getjButton1().setEnabled(true);
             for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    gomb[i][j].setEnabled(false);
                }
            }
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

   private void ujraindit() {
    try {
        model.mehet();
        model.ujgomb();
        nezet.getTxtjatekosnev().setText("");
        nezet.getTxtjatekosnev().setEditable(true);
        nezet.getBtnindit().setEnabled(true);
        nezet.getjButton1().setEnabled(false);
        nezet.getTxtjateknyertes().setText("");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gomb[i][j].setEnabled(false);
                gomb[i][j].setBackground(Color.DARK_GRAY);
            }
        }
        JOptionPane.showMessageDialog(nezet, "Új játék indult!");
        } catch (Exception e) {
        JOptionPane.showMessageDialog(nezet, "Hiba az új játék indításakor: " + e.getMessage());
        }
    }

   
       private void mentes() {
    String jatekos = nezet.getTxtjatekosnev().getText().trim();
    if (jatekos.isEmpty()) {
        JOptionPane.showMessageDialog(nezet, "Nem lehet menteni, add meg a neved!");
        return;
    }

    try {
        String alapHely = System.getProperty("user.dir");
        JFileChooser jfc = new JFileChooser(alapHely);

        
        File alapFajl = new File(alapHely + File.separator + jatekos + ".txt");
        jfc.setSelectedFile(alapFajl);

        int valasztas = jfc.showSaveDialog(nezet);
        if (valasztas == JFileChooser.APPROVE_OPTION) {
            File fajl = jfc.getSelectedFile();
            Path path = fajl.toPath();
            
            
            StringBuilder sb = new StringBuilder();
            sb.append(jatekos).append("\n"); 
            sb.append("0").append("\n"); 
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(model.getErtek(i, j) ? "1" : "0");
                    if (j < 2) sb.append(",");
                }
                sb.append("\n");
            }

           
            Files.writeString(path, sb.toString());
            JOptionPane.showMessageDialog(nezet, "Mentés kész!");
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(nezet, "Hiba a mentés során: " + ex.getMessage());
        ex.printStackTrace();
    }
}


    private void betoltes() {
        JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        int valasztas = jfc.showOpenDialog(nezet);

        if (valasztas == JFileChooser.APPROVE_OPTION) {
            File fajl = jfc.getSelectedFile();
            try {
                var sorok = Files.readAllLines(fajl.toPath());
                if (sorok.size() < 5) {
                    JOptionPane.showMessageDialog(nezet, "Érvénytelen mentés!");
                    return;
                }
                nezet.getTxtjatekosnev().setText(sorok.get(0));
                nezet.getTxtjatekosnev().setEditable(false);
                nezet.getBtnindit().setEnabled(false);
   
                for (int i = 0; i < 3; i++) {
                    String[] adatok = sorok.get(i + 2).split(",");
                    for (int j = 0; j < 3; j++) {
                        boolean allapot = adatok[j].equals("1");
                        if (allapot != model.getErtek(i, j)) {
                            model.kapcsolo(i, j);
                        }
                    }
                }             
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        gomb[i][j].setEnabled(true);
                frissites();
                JOptionPane.showMessageDialog(nezet, "Betöltés sikeres!");
            } catch (IOException | NumberFormatException | IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(nezet, "Betöltés sikertelen!\n" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private String tartalom() {
        StringBuilder sb = new StringBuilder();
        sb.append(nezet.getTxtjatekosnev().getText()).append("\n");
        sb.append("0").append("\n"); // lépések, ha szükséges
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(model.getErtek(i, j) ? "1" : "0");
                if (j < 2) sb.append(",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
