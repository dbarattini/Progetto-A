package partitaOnline.events;

import dominio.giocatori.Giocatore;

/**
 *
 * @author xXEgoOneXx
 */
public class FineRound {
    private Giocatore giocatore;

    public FineRound(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }
    
}
