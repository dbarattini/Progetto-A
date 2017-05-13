/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoles;

import GUI.GuiMenuPrincipale;
import classi_dati.OpzioniMenu;

/**
 *
 * @author marco
 */
public class ConsoleGrafica implements Console {
    
    @Override
    public OpzioniMenu scegliModalità() {
        OpzioniMenu modalita = null;
        GuiMenuPrincipale menu = new GuiMenuPrincipale();
        while(modalita == null){
            modalita = menu.getModalita();
        }
        return modalita;
    }

    @Override
    public void GiocaOffline() {
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
