package dominio.events;

import partitaOffline.events.*;
import dominio.classi_dati.StatoMano;
import dominio.elementi_di_gioco.Carta;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class FineManoAvversario {
    
    private String nome;
    private ArrayList<Carta> carteScoperte;
    private StatoMano stato;
    private int puntata;

    /**
     * gestisce la fine della mano di un avversario
     * 
     * @param nome nome avversario
     * @param carteScoperte carte scoperte dell'avversario che termina la mano
     * @param stato stato dell'avversario che termina la mano
     * @param puntata puntata dell'avversario che termina la mano
     */
    public FineManoAvversario(String nome, ArrayList<Carta> carteScoperte, StatoMano stato, int puntata) {
        this.nome = nome;
        this.carteScoperte = carteScoperte;
        this.stato = stato;
        this.puntata = puntata;
    }

    /**
     * 
     * @return  nome avversario
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return arrayList di carte scoperte dell'avversario
     */
    public ArrayList<Carta> getCarteScoperte() {
        return carteScoperte;
    }

    /**
     * 
     * @return stato dell'avversario che termina la mano
     */
    public StatoMano getStato() {
        return stato;
    }

    /**
     * 
     * @return puntata dell'avversario
     */
    public int getPuntata() {
        return puntata;
    }
    
}