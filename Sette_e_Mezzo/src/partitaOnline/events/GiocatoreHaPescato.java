/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.events;

import dominio.giocatori.GiocatoreOnline;


/**
 *
 * @author root
 */
public class GiocatoreHaPescato {
    private GiocatoreOnline giocatore;

    /**
     * 
     * @param giocatore giocatore che ha pescato
     */
    public GiocatoreHaPescato(GiocatoreOnline giocatore) {
        this.giocatore = giocatore;
    }

    /**
     * 
     * @return giocatore
     */
    public GiocatoreOnline getGiocatore() {
        return giocatore;
    }
    
    
    
    
}
