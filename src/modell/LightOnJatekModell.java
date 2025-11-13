
package modell;


public class LightOnJatekModell {
    private boolean[][] jatekter;
    public LightOnJatekModell(){
        jatekter= new boolean[3][3];
    }
    
    public void kapcsolo(int sorok, int oszlopok){
        csere(sorok,oszlopok);
        if (sorok > 0) csere (sorok -1,oszlopok);
        if (sorok < 2) csere (sorok+1,oszlopok);
        if (oszlopok > 0) csere (sorok, oszlopok -1);
        if (oszlopok < 2) csere (sorok, oszlopok +1);
        
    }
    
    private void csere(int sorok,int oszlopok){
        jatekter[sorok][oszlopok] =! jatekter[sorok][oszlopok];
    }
    
    public boolean getErtek(int sorok,int oszlopok){
        
        return jatekter[sorok][oszlopok];   
    }
    
    public void mehet(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jatekter[i][j] = false;
            }
        }
    }
    
    public boolean nyertes(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!jatekter[i][j]) return false;
            }
        }
        return true;
    }
    
    public void ujgomb(){
        jatekter = new boolean[3][3];
        int sorok =(int) (Math.random()*3);
        int oszlopok =(int) (Math.random()*3);
        jatekter[sorok][oszlopok]=true;
    }
}
