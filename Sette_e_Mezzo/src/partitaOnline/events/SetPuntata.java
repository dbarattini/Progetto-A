package partitaOnline.events;

import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class SetPuntata implements Serializable{
    private String puntata;
    public SetPuntata(String puntata) {
        this.puntata = puntata;
    }

    public String getPuntata() {
        return puntata;
    }
    
    /**
     *
     * @return "evento SetPuntata "+puntata;
     */
    @Override
    public String toString() {
        return "evento SetPuntata "+puntata;
    }
    
}
