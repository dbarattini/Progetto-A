package junit_tests;

import dominio.classi_dati.Giocata;
import dominio.giocatori.Giocatore;
import dominio.pagamenti.PagamentoReale;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class testPagamentoReale {
    private PagamentoReale pagamento_normale;
    
    public testPagamentoReale(){
        pagamento_normale = new PagamentoReale();
    }
    
    @Test
    public void testGiocatorePagaNormaleMazziere(){
        
        Giocatore giocatore = new Giocatore("giocatore", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                return 10;
            }
        };
        
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
        
        mazziere.setMazziere(true);
        giocatore.effettuaPuntata();
        
        pagamento_normale.normale(giocatore, mazziere, 1);
        
        assertEquals(90, giocatore.getFiches());
        assertEquals(110, mazziere.getFiches());
    }
    
        @Test
    public void testMazzierePagaNormaleGiocatore(){
        
        Giocatore giocatore = new Giocatore("giocatore", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                return 10;
            }
        };
        
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
        
        mazziere.setMazziere(true);
        giocatore.effettuaPuntata();
        
        pagamento_normale.normale(mazziere, giocatore, 1);
        
        assertEquals(110, giocatore.getFiches());
        assertEquals(90, mazziere.getFiches());
    }
    
    @Test
    public void testGiocatorePagaRealeMazziere(){
        
        Giocatore giocatore = new Giocatore("giocatore", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                return 10;
            }
        };
        
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
        
        mazziere.setMazziere(true);
        giocatore.effettuaPuntata();
        
        pagamento_normale.reale(giocatore, mazziere, 1);
        
        assertEquals(80, giocatore.getFiches());
        assertEquals(120, mazziere.getFiches());
    }
    
    @Test
    public void testMazzierePagaRealeGiocatore(){
        Giocatore giocatore = new Giocatore("giocatore", 100) {
            @Override
            protected Giocata decidiGiocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidiPuntata() {
                return 10;
            }
        };
        
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

        mazziere.setMazziere(true);
        giocatore.effettuaPuntata();
        
        pagamento_normale.reale(mazziere, giocatore, 1);
        
        assertEquals(120, giocatore.getFiches());
        assertEquals(80, mazziere.getFiches());
    }
}
