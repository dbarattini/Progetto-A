package dominio.events;

/**
 *
 * @author xXEgoOneXx
 */
public class SetGiocata {
    private String giocata;
    public SetGiocata(String giocata) {
        this.giocata = giocata;
    }

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
