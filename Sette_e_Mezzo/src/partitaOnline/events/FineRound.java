package partitaOnline.events;

import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class FineRound implements Serializable {

    private String nome;
    private Carta cartaCoperta;
    private ArrayList<Carta> carteScoperte;
    private int fiches, puntata;
    private boolean isMazziere;
    

    public FineRound(String nome, Carta cartaCoperta,  ArrayList<Carta> carteScoperte, int fiches, boolean isMazziere, int puntata) {
        this.nome=nome;
        this.cartaCoperta=cartaCoperta;
        this.carteScoperte=carteScoperte;
        this.fiches=fiches;
        this.isMazziere=isMazziere;
        this.puntata=puntata;
    }

    /**
     *
     * @return "evento FineRound " + username + " " +
     * CarteSeparateDaSpazi + " fineCarte " + " " +
     * "fiches" + isMazziere + "puntata(seNonMazziere)" ;
     */
    @Override
    public String toString() {
        String ritorno = "evento\tFineRound\t" + nome+ " " + cartaCoperta + " ";
        for (Carta carta : carteScoperte) {
            ritorno += carta.toString() + " ";
        }
        ritorno += "fineCarte "+fiches+" " + isMazziere;
        if(!isMazziere) ritorno+=" "+puntata;
        return ritorno;
    }

}
