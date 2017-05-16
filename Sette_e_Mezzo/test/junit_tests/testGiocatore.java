package junit_tests;

import dominio.classi_dati.Giocata;
import dominio.eccezioni.FineMazzoException;
import dominio.eccezioni.PuntataNegativaException;
import dominio.eccezioni.PuntataNullaException;
import dominio.eccezioni.PuntataTroppoAltaException;
import dominio.eccezioni.SballatoException;
import dominio.eccezioni.SetteeMezzoException;
import dominio.eccezioni.SetteeMezzoRealeException;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import dominio.elementi_di_gioco.Mazzo;
import org.junit.Test;
import static org.junit.Assert.*;

public class testGiocatore {
            private Giocatore giocatore = new Giocatore("player1", 100){
                @Override
                public int decidi_puntata() {
                    return 20;
                }

                @Override
                public Giocata decidi_giocata() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
        }; 
    
    @Test
    public void testPrendiCartaIniziale() throws FineMazzoException{
        Mazzo mazzo = new Mazzo();
        mazzo.mischia();
        Carta carta = mazzo.estrai_carta();
        giocatore.prendi_carta_iniziale(carta);
        
        assertEquals(carta, giocatore.getCartaCoperta());
    }
    
    @Test
    public void testPunta() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        int fiches_iniziali = giocatore.getFiches();
        int valore_puntato = 20;  
        
        giocatore.effettua_puntata();
        
        assertEquals(valore_puntato, giocatore.getPuntata());
        assertEquals(fiches_iniziali - valore_puntato, giocatore.getFiches());
    }

    @Test
    public void testAggiornaValoreMano() throws FineMazzoException, SballatoException, SetteeMezzoRealeException, SetteeMezzoException{
        Mazzo mazzo = new Mazzo(); //non sono mischiate apposta
        Carta carta = mazzo.estrai_carta();
        giocatore.prendi_carta_iniziale(carta); //1c, non bisogna aggiornare la mano
        carta = mazzo.estrai_carta();
        giocatore.chiedi_carta(carta); //2c
        assertEquals(3.0, giocatore.getValoreMano(), 0.001);
    }
}
