package dominio.events;

/**
 *
 * @author xXEgoOneXx
 */
public class SetGiocata {
    private String giocata;
    
    /**
     * 
     * @param giocata giocata effettuata dal giocatore
     */
    public SetGiocata(String giocata) {
        this.giocata = giocata;
    }

    /**
     * 
     * @return giocata effettuata dal giocatore
     */
    public String getGiocata() {
        return giocata;
    }
    
    /**
     *
     * @return "evento SetGiocata "+giocata;
     */
    @Override
    public String toString() {
        return "evento SetGiocata "+giocata;
    }
    
}
