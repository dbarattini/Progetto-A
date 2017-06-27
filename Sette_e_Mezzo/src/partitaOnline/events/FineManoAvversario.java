package partitaOnline.events;

import dominio.classi_dati.StatoMano;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class FineManoAvversario implements Serializable{
    
    private String nome;
    private ArrayList<Carta> carteScoperte;
    private StatoMano stato;
    private int puntata;

    public FineManoAvversario(String nome, ArrayList<Carta> carteScoperte, StatoMano stato, int puntata) {
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

    public StatoMano getStato() {
        return stato;
    }

    public int getPuntata() {
        return puntata;
    }

    /**
     *
     * @return "evento FineManoAvversario " + nome + " "+tutteLeCarteSeparateDaSpazio+" "+"fineCarte " + stato + " " + puntata;
     */
    @Override
    public String toString() {
        String ritorno="evento\tFineManoAvversario\t" + nome + " ";
        for(Carta carta : carteScoperte){
            ritorno+=carta.toString()+" ";
        }
        ritorno+="fineCarte " + stato + " " + puntata;
        return ritorno;
    }
    
    
    
}
