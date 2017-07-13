package dominio.events;

import dominio.elementi_di_gioco.Carta;
import java.util.ArrayList;

/**
 *
 * @author xXEgoOneXx
 */
public class RichiediGiocata {
    Carta carta_coperta; 
    ArrayList<Carta> carte_scoperte; 
    double valore_mano;
    
    /**
     * 
     * @param carta_coperta carta coperta del giocatore
     * @param carte_scoperte carte scoperte del giocatore
     * @param valore_mano valore totale della mano del giocatore
     */
    public RichiediGiocata(Carta carta_coperta, ArrayList<Carta> carte_scoperte, double valore_mano) {
        this.carta_coperta = carta_coperta;
        this.carte_scoperte = carte_scoperte;
        this.valore_mano = valore_mano;
    }

    /**
     * 
     * @return carta coperta del giocatore
     */
    public Carta getCartaCoperta() {
        return carta_coperta;
    }

    /**
     * 
     * @return carte scoperte del giocatore
     */
    public ArrayList<Carta> getCarteScoperte() {
        return carte_scoperte;
    }

    /**
     * 
     * @return valore mano del giocatore
     */
    public double getValoreMano() {
        return valore_mano;
    }
    
    
    
}
