package junit_tests;

import classi_dati.Giocata;
import eccezioni.FineMazzoException;
import eccezioni.PuntataNegativaException;
import eccezioni.PuntataNullaException;
import eccezioni.PuntataTroppoAltaException;
import gioco.Carta;
import gioco.Giocatore;
import gioco.Mazzo;
import org.junit.Test;
import static org.junit.Assert.*;

public class testGiocatore {
            private Giocatore giocatore = new Giocatore("player1", 0, 100){
            @Override
            public Mazzo gioca_mano(Mazzo mazzo) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

                @Override
                public int decidi_puntata() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Giocata decidi_giocata() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
        };
            
    public testGiocatore() {
    }
    
    @Test
    public void testPrendiCartaIniziale() throws FineMazzoException{
        Mazzo mazzo = new Mazzo();
        mazzo.mischia();
        Carta prima_carta = mazzo.getCarteDaGiocare().get(0);
        
        giocatore.prendi_carta_iniziale(mazzo);
        
        assertEquals(prima_carta, giocatore.getCartaCoperta());
    }
    
    @Test
    public void testPunta() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        int fiches_iniziali = giocatore.getFiches();
        int valore_puntato = (int) (fiches_iniziali * Math.random());  
        
        giocatore.punta(valore_puntato);
        
        assertEquals(valore_puntato, giocatore.getPuntata());
        assertEquals(fiches_iniziali - valore_puntato, giocatore.getFiches());
    }
    
    @Test (expected = PuntataTroppoAltaException.class)
    public void testPuntataTroppoAltaException() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        int fiches_iniziali = giocatore.getFiches();
        int valore_puntato = fiches_iniziali + 1;  
        
        giocatore.punta(valore_puntato);
    }
    
    @Test (expected = PuntataNegativaException.class)
    public void testPuntataNegativaException() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        int valore_puntato = -10;  
        
        giocatore.punta(valore_puntato);
    }
    
    @Test (expected = PuntataNullaException.class)
    public void testPuntataNullaException() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        int valore_puntato = 0;  
        
        giocatore.punta(valore_puntato);
    }
    
}
