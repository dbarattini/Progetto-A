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
    

    public FineRound(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setCarteScoperte(ArrayList<Carta> carteScoperte) {
        this.carteScoperte = carteScoperte;
    }
    
    
    public ArrayList<Carta> getCarteScoperte() {
        return carteScoperte;
    }
    
}
