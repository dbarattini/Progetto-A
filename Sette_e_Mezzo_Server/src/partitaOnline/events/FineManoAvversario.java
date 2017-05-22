package partitaOnline.events;

import dominio.classi_dati.Stato;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class FineManoAvversario {
    
    private String nome;
    private ArrayList<Carta> carteScoperte;
    private Stato stato;
    private int puntata;

    public FineManoAvversario(String nome, ArrayList<Carta> carteScoperte, Stato stato, int puntata) {
        this.nome = nome;
        this.carteScoperte = carteScoperte;
        this.stato = stato;
        this.puntata = puntata;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Carta> getCarteScoperte() {
        return carteScoperte;
    }

    public Stato getStato() {
        return stato;
    }

    public int getPuntata() {
        return puntata;
    }
    
}
