package partitaOnline.events;

import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import java.io.Serializable;

/**
 *
 * @author xXEgoOneXx
 */
public class FineRound implements Serializable {

    private Giocatore giocatore;

    public FineRound(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     *
     * @return "evento FineRound " + username + " " +
     * leCarteScoperteSeparateDaSpazi + " fineCarte " + "puntata" + " " +
     * "fiches";
     */
    @Override
    public String toString() {
        String ritorno = "evento FineRound " + giocatore.getNome() + " ";
        for (Carta carta : giocatore.getCarteScoperte()) {
            ritorno += carta.toString() + " ";
        }
        ritorno += "fineCarte " + giocatore.getPuntata() + " " + giocatore.getFiches();
        return ritorno;
    }

}
