package partitaOnline.events;

import dominio.giocatori.Giocatore;
import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class FineRound implements Serializable{
    private Giocatore giocatore;

    public FineRound(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }
    
}
