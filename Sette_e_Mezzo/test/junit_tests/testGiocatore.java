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
                public int decidiPuntata() {
                    return 20;
                }

                @Override
                public Giocata decidiGiocata() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
        }; 
    
    @Test
    public void testPrendiCartaIniziale() throws FineMazzoException{
        Mazzo mazzo = new Mazzo();
        mazzo.mischia();
        Carta carta = mazzo.estraiCarta();
        giocatore.prendiCartaIniziale(carta);
        
        assertEquals(carta, giocatore.getCartaCoperta());
    }
    
    @Test
    public void testPunta() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        int fiches_iniziali = giocatore.getFiches();
        int valore_puntato = 20;  
        
        giocatore.effettuaPuntata();
        
        assertEquals(valore_puntato, giocatore.getPuntata());
        assertEquals(fiches_iniziali - valore_puntato, giocatore.getFiches());
    }

    @Test
    public void testAggiornaValoreMano() throws FineMazzoException, SballatoException, SetteeMezzoRealeException, SetteeMezzoException{
        Mazzo mazzo = new Mazzo(); //non sono mischiate apposta
        Carta carta = mazzo.estraiCarta();
        giocatore.prendiCartaIniziale(carta); //1c, non bisogna aggiornare la mano
        carta = mazzo.estraiCarta();
        giocatore.chiediCarta(carta); //2c
        assertEquals(3.0, giocatore.getValoreMano(), 0.001);
    }
}
