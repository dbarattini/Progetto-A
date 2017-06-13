package junit_tests;


import dominio.eccezioni.FineMazzoException;
import dominio.elementi_di_gioco.Carta;
import dominio.elementi_di_gioco.Mazzo;
import org.junit.Test;
import static org.junit.Assert.*;


public class testMazzo {
    private final Mazzo mazzo = new Mazzo();
    private Carta carta_estratta;
   
    @Test
    public void testEstraiCarta() throws FineMazzoException {
        mazzo.mischia();
        
        carta_estratta = mazzo.estraiCarta();
        assertFalse(mazzo.getCarteDaGiocare().contains(carta_estratta));
        assertTrue(mazzo.getCarteInGioco().contains(carta_estratta));
    }

    @Test
    public void testAggiornaFineRound() throws FineMazzoException{
        mazzo.mischia();
        carta_estratta = mazzo.estraiCarta();
        
        mazzo.aggiornaFineRound();
        assertFalse(mazzo.getCarteInGioco().contains(carta_estratta));
        assertTrue(mazzo.getCarteGiocate().contains(carta_estratta));
    }
    
    @Test(expected = FineMazzoException.class) 
    public void testFineMazzoException() throws FineMazzoException{
        mazzo.mischia();
        this.estrai_40_Carte();
        
        carta_estratta = mazzo.estraiCarta(); //prova ad estrarre la 41esima
    }
    
    @Test 
    public void testRimescola(){
        mazzo.mischia();
        try {
            this.estrai_40_Carte(); //tutte le carte sono in gioco
            carta_estratta = mazzo.estraiCarta();
        } catch (FineMazzoException ex) {
            mazzo.aggiornaFineRound(); //tutte le carte sono giocate
            mazzo.rimescola();
            assertEquals(mazzo.getCarteDaGiocare().size(),40);
            assertEquals(mazzo.getCarteGiocate().size(),0);
            assertEquals(mazzo.getCarteInGioco().size(),0);
            
        }
        
    }
    
    
    private void estrai_40_Carte() throws FineMazzoException{
        for(int i=0; i < 40; i++){
            carta_estratta = mazzo.estraiCarta();
        }
    }
}
