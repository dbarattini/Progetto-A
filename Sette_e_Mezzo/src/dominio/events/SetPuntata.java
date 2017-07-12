package dominio.events;

/**
 *
 * @author xXEgoOneXx
 */
public class SetPuntata {
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
