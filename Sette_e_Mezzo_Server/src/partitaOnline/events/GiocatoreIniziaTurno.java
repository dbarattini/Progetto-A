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
public class GiocatoreIniziaTurno {
    
    private String giocatore;

    public GiocatoreIniziaTurno(String giocatore) {
        this.giocatore = giocatore;
    }

    public String getGiocatore() {
        return giocatore;
    }

    /**
     * 
     * @return evento GiocatoreIniziaTurno
     */
    @Override
    public String toString() {
        return "evento\tGiocatoreIniziaTurno\t" + giocatore;
    }
    
    
    
}
