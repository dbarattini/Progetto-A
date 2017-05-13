/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoles;

import classi_dati.DifficoltaBot;
import classi_dati.OpzioniMenu;
import gioco.PartitaOffline;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.MenuPrePartita;
import menu.MenuPrincipale;


public class ConsoleTestuale implements Console {
    
    @Override
    public OpzioniMenu scegliModalità() {
        MenuPrincipale menu = new MenuPrincipale();
        return menu.selezionaOpzione();
    }

    @Override
    public void GiocaOffline() {
        MenuPrePartita menu = new MenuPrePartita();
        int numero_bot = menu.richiediNbot();
        int numero_fiches_iniziali = menu.richiediFichesIniziali();
        DifficoltaBot difficolta_bot = menu.richiediDifficoltaBot();
        
        try {
            PartitaOffline partita = new PartitaOffline(numero_bot,numero_fiches_iniziali, difficolta_bot, System.in, System.out, System.err);
            partita.gioca();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConsoleTestuale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void GiocaOnline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RegoleDiGioco() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Impostazioni() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Esci() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
