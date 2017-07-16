package dominio.events;

import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class FineRound {
    private Giocatore giocatore;
    private ArrayList<Carta> carteScoperte;
    

    /**
     * ritorna il giocatore che termina la mano
     * 
     * @param giocatore utente che finisce il round
     */
    public FineRound(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    /**
     * 
     * @return giocatore che finisce la mano
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     * 
     * @param carteScoperte carte scoperte del giocatore
     */
    public void setCarteScoperte(ArrayList<Carta> carteScoperte) {
        this.carteScoperte = carteScoperte;
    }
    
    /**
     * 
     * @return carte scoperte del giocatore
     */
    public ArrayList<Carta> getCarteScoperte() {
        return carteScoperte;
    }
    
}
