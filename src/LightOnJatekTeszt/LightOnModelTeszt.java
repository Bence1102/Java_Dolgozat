package LightOnJatekTeszt;

import modell.LightOnJatekModell;

public class LightOnModelTeszt {

    private LightOnJatekModell model;

    
    public void setUp() {
        model = new LightOnJatekModell();
    }

    
    public void testMehetAlaphelyzet() {
        model.mehet();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertFalse(model.getErtek(i, j), "Minden mezőnek false-nak kell lennie");
            }
        }
    }

    
    public void testKapcsoloMegvaltoztatjaErteket() {
        model.mehet();
        model.kapcsolo(1, 1);
        assertTrue(model.getErtek(1, 1), "Középső mezőnek true-nak kell lennie");
        assertTrue(model.getErtek(0, 1), "Felül szomszéd mező true");
        assertTrue(model.getErtek(2, 1), "Alul szomszéd mező true");
        assertTrue(model.getErtek(1, 0), "Bal szomszéd mező true");
        assertTrue(model.getErtek(1, 2), "Jobb szomszéd mező true");
    }

    
    public void testNyertesFalse() {
        model.mehet();
        assertFalse(model.nyertes(), "Üres mezőkkel nem lehet nyertes");
    }

    
    public void testNyertesTrue() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                model.kapcsolo(i, j); 
        boolean allTrue = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                allTrue &= model.getErtek(i, j);
        if(allTrue)
            assertTrue(model.nyertes(), "Minden mező true, nyertesnek kell lennie");
    }

    
    public void testUjgomb() {
        model.ujgomb();
        int countTrue = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if(model.getErtek(i,j)) countTrue++;
        assertEquals(1, countTrue, "Csak 1 mező legyen true az ujgomb után");
    }

    private void assertFalse(boolean ertek, String minden_mezőnek_falsenak_kell_lennie) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertTrue(boolean ertek, String középső_mezőnek_truenak_kell_lennie) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertEquals(int i, int countTrue, String csak_1_mező_legyen_true_az_ujgomb_után) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
