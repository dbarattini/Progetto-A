package partitaOnline.events;

import dominio.giocatori.Giocatore;
import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class FineRound implements Serializable{
    private String username;

    public FineRound(Giocatore giocatore) {
        this.username= giocatore.getNome();
    }

    public String getGiocatore() {
        return username;
    }
    
}
