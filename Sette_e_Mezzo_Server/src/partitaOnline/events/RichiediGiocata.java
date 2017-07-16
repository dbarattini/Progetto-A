package partitaOnline.events;

import dominio.elementi_di_gioco.Carta;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class RichiediGiocata implements Serializable{
    Carta cartaCoperta; 
    ArrayList<Carta> carteScoperte; 
    double valoreMano;
    
    /**
     * 
     * @param carta_coperta carta coperta del giocatore
     * @param carte_scoperte carte scoperte del giocatore
     * @param valore_mano valore della mano del giocatore
     */
    public RichiediGiocata(Carta carta_coperta, ArrayList<Carta> carte_scoperte, double valore_mano) {
        this.cartaCoperta = carta_coperta;
        this.carteScoperte = carte_scoperte;
        this.valoreMano = valore_mano;
    }

    public Carta getCartaCoperta() {
        return cartaCoperta;
    }

    public ArrayList<Carta> getCarteScoperte() {
        return carteScoperte;
    }

    public double getValoreMano() {
        return valoreMano;
    }
    
    /**
     *
     * @return "evento RichiediGiocata " + cartaCoperta + " " + tutteLeCarteSeparateDaSpazio + " " + "fineCarte " + valoreMano;
     */
    @Override
    public String toString() {
        String ritorno="evento\tRichiediGiocata\t" + cartaCoperta.toString() + " ";
        for(Carta carta : carteScoperte){
            ritorno+=carta.toString()+" ";
        }
        ritorno+="fineCarte " + valoreMano;
        return ritorno;
    }
    
}
