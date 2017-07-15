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
public class GiocatoreSta {
     
    private String giocatore;

    /**
     * 
     * @param giocatore giocatore che sta
     */
    public GiocatoreSta(String giocatore) {
        this.giocatore = giocatore;
    }

    /**
     * 
     * @return giocatore che sta
     */
    public String getGiocatore() {
        return giocatore;
    }

    @Override
    public String toString() {
        return "evento\tGiocatoreSta\t" + giocatore;
    }
    
    
}
