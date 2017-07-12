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
    
    public RichiediGiocata(Carta carta_coperta, ArrayList<Carta> carte_scoperte, double valore_mano) {
        this.carta_coperta = carta_coperta;
        this.carte_scoperte = carte_scoperte;
        this.valore_mano = valore_mano;
    }

    public Carta getCartaCoperta() {
        return carta_coperta;
    }

    public ArrayList<Carta> getCarteScoperte() {
        return carte_scoperte;
    }

    public double getValoreMano() {
        return valore_mano;
    }
    
    
    
}
