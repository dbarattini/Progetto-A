package partitaOnline.events;

import dominio.classi_dati.Stato;
import dominio.elementi_di_gioco.Carta;
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
    private double valoreMano;
    private Stato stato;
    

    public FineRound(String nome, Carta cartaCoperta,  ArrayList<Carta> carteScoperte, int fiches, double valoreMano, Stato stato, boolean isMazziere, int puntata) {
        this.nome=nome;
        this.cartaCoperta=cartaCoperta;
        this.carteScoperte=carteScoperte;
        this.fiches=fiches;
        this.isMazziere=isMazziere;
        this.puntata=puntata;
        this.valoreMano=valoreMano;
        this.stato=stato;
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
    
     public double getValoreMano() {
        return valoreMano;
    }

    public Stato getStato() {
        return stato;
    }


    /**
     *
     * @return "evento FineRound " + username + " " +
     * CarteSeparateDaSpazi + " fineCarte " + " " +
     * "fiches" +" " + valoreMano + " " + stato + isMazziere + "puntata(seNonMazziere)" ;
     */
    @Override
    public String toString() {
        String ritorno = "evento\tFineRound\t" + nome+ " " + cartaCoperta + " ";
        for (Carta carta : carteScoperte) {
            ritorno += carta.toString() + " ";
        }
        ritorno += "fineCarte "+fiches+" " + valoreMano + " " + stato + " " + isMazziere;
        if(!isMazziere) ritorno+=" "+puntata;
        return ritorno;
    }

   
}
