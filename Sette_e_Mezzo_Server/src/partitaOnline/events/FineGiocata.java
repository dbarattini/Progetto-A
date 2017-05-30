/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOnline.events;

import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;

/**
 *
 * @author root
 */
public class FineGiocata {
    private Giocatore giocatore;

    public FineGiocata(Giocatore giocatore) {
        this.giocatore=giocatore;
    }

    /**
     *
     * @return "evento FineGiocata " + username + " " + leCarteSeparateDaSpazio + " " + "fineCarte " + puntata(NonSeMazziere);
     */
    @Override
    public String toString() {
        String ritorno="evento FineGiocata " + giocatore.getNome() + " ";
        for(Carta carta : giocatore.getCarteScoperte()){
            ritorno+=carta.toString()+ " ";
        }
        ritorno+="fineCarte ";
        if(!giocatore.isMazziere())ritorno+= giocatore.getPuntata();
        return ritorno;
    }
    
    
    
}
