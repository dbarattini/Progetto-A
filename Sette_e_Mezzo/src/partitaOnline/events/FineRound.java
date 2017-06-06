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

    public String getNome() {
        return nome;
    }

    public Carta getCartaCoperta() {
        return cartaCoperta;
    }

    public ArrayList<Carta> getCarteScoperte() {
        return carteScoperte;
    }

    public int getFiches() {
        return fiches;
    }

    public int getPuntata() {
        return puntata;
    }

    public boolean isMazziere() {
        return isMazziere;
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
