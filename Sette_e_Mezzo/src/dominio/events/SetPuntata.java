package dominio.events;

/**
 *
 * @author xXEgoOneXx
 */
public class SetPuntata {
    private String puntata;
    
    /**
     * 
     * @param puntata puntata effettuata dal giocatore
     */
    public SetPuntata(String puntata) {
        this.puntata = puntata;
    }

    /**
     * 
     * @return puntata effettuata dal giocatore
     */
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
