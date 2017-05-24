package partitaOnline.events;

import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class SetGiocata implements Serializable{
    private String giocata;
    public SetGiocata(String giocata) {
        this.giocata = giocata;
    }

    public String getGiocata() {
        return giocata;
    }
    
    
}
