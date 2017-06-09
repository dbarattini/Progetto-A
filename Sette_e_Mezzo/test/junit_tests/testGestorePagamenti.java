package junit_tests;

import dominio.classi_dati.Giocata;
import dominio.giocatori.Giocatore;
import dominio.gioco.GestorePagamenti;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class testGestorePagamenti {
    private GestorePagamenti gestore_pagamenti;
    
    public testGestorePagamenti(){
        gestore_pagamenti = new GestorePagamenti();
    }
    
    @Test
    public void testGiocatorePagaRealeMazziere(){
        
        Giocatore giocatore = new Giocatore("giocatore", 100) {
            @Override
            protected Giocata decidi_giocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidi_puntata() {
                return 10;
            }
        };
        
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
        
        mazziere.setMazziere(true);
        giocatore.effettua_puntata();
        
        gestore_pagamenti.paga_reale(giocatore, mazziere);
        
        assertEquals(80, giocatore.getFiches());
        assertEquals(120, mazziere.getFiches());
    }
    
    @Test
    public void testMazzierePagaRealeGiocatore(){
        Giocatore giocatore = new Giocatore("giocatore", 100) {
            @Override
            protected Giocata decidi_giocata() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int decidi_puntata() {
                return 10;
            }
        };
        
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

        mazziere.setMazziere(true);
        giocatore.effettua_puntata();
        
        gestore_pagamenti.paga_reale(mazziere, giocatore);
        
        assertEquals(120, giocatore.getFiches());
        assertEquals(80, mazziere.getFiches());
    }
}
