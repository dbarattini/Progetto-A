package junit_tests;

import dominio.classi_dati.Giocata;
import dominio.eccezioni.FineMazzoException;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import dominio.pagamenti.PagamentoReale;
import dominio.elementi_di_gioco.Regole;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xXEgoOneXx
 */
public class testRegoleDiGIoco {
    Regole regole;
    
    public testRegoleDiGIoco() {
        regole = new Regole();
    }
    
    @Test
    public void tesBacoMazzierePagaGiocatoreESecondoGIocatorePagaMazziere(){
        PagamentoReale pagamento_normale = new PagamentoReale();
        Giocatore mazziere = new Giocatore("mazziere", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Giocatore giocatore1 = new Giocatore("giocatore1", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                return 10;
            }
        };
        
        Giocatore giocatore2 = new Giocatore("giocatore2", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                return 10;
            }
        };
        
        mazziere.setMazziere(true);
        
        try {
            mazziere.inizializzaMano();
            giocatore1.inizializzaMano();
            giocatore2.inizializzaMano();
            
            giocatore1.effettuaPuntata();
            giocatore2.effettuaPuntata();
            
            mazziere.prendiCartaIniziale(new Carta("5", "c"));
            giocatore1.prendiCartaIniziale(new Carta("1", "c"));
            giocatore2.prendiCartaIniziale(new Carta("7", "c"));
        } catch (FineMazzoException ex) {
            Logger.getLogger(testRegoleDiGIoco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        regole.risultatoMano(mazziere, giocatore2, null, 1, pagamento_normale);
        regole.risultatoMano(mazziere, giocatore1, null, 1, pagamento_normale);
        
        assertEquals(100, mazziere.getFiches());
        assertEquals(90, giocatore1.getFiches());
    }
}
