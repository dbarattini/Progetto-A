package junit_tests;

import dominio.classi_dati.Giocata;
import dominio.classi_dati.StatoMano;
import dominio.eccezioni.FineMazzoException;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import dominio.gioco.GestorePagamenti;
import dominio.gioco.RegoleDiGioco;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xXEgoOneXx
 */
public class testRegoleDiGIoco {
    RegoleDiGioco regole;
    GestorePagamenti pagamenti;
    public testRegoleDiGIoco() {
        regole = new RegoleDiGioco();
        pagamenti = new GestorePagamenti();
    }
    
    @Test
    public void tesBacoMazzierePagaGiocatoreESecondoGIocatorePagaMazziere(){
        Giocatore mazziere = new Giocatore("mazziere", 100) {
            @Override
            protected Giocata decidi_giocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidi_puntata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Giocatore giocatore1 = new Giocatore("giocatore1", 100) {
            @Override
            protected Giocata decidi_giocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidi_puntata() {
                return 10;
            }
        };
        
        Giocatore giocatore2 = new Giocatore("giocatore2", 100) {
            @Override
            protected Giocata decidi_giocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidi_puntata() {
                return 10;
            }
        };
        
        mazziere.setMazziere(true);
        
        try {
            mazziere.inizializza_mano();
            giocatore1.inizializza_mano();
            giocatore2.inizializza_mano();
            
            giocatore1.effettua_puntata();
            giocatore2.effettua_puntata();
            
            mazziere.prendi_carta_iniziale(new Carta("5", "c"));
            giocatore1.prendi_carta_iniziale(new Carta("1", "c"));
            giocatore2.prendi_carta_iniziale(new Carta("7", "c"));
        } catch (FineMazzoException ex) {
            Logger.getLogger(testRegoleDiGIoco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        regole.risultato_mano(mazziere, giocatore2, null);
        regole.risultato_mano(mazziere, giocatore1, null);
        
        assertEquals(100, mazziere.getFiches());
        assertEquals(90, giocatore1.getFiches());
    }
}
