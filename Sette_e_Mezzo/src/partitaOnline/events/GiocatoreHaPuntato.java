/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.events;

/**
 *
 * @author root
 */
public class GiocatoreHaPuntato {
     
    private String giocatore;

    public GiocatoreHaPuntato(String giocatore) {
        this.giocatore = giocatore;
    }

    public String getGiocatore() {
        return giocatore;
    }

    @Override
    public String toString() {
        return "evento\tGiocatoreHaPuntato\t" + giocatore;
    }
    
    
}
