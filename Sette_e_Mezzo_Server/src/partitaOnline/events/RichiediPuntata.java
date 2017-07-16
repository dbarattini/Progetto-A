package partitaOnline.events;

import dominio.elementi_di_gioco.Carta;
import java.io.Serializable;


public class RichiediPuntata implements Serializable{
    Carta carta_coperta;
    double valore_mano;
    int fiches;

    /**
     * 
     * @param carta_coperta carta coperta del giocatore
     * @param valore_mano valore della mano del giocatore
     * @param fiches fiches del giocatore
     */
    public RichiediPuntata(Carta carta_coperta, double valore_mano, int fiches) {
        this.carta_coperta = carta_coperta;
        this.valore_mano = valore_mano;
        this.fiches = fiches;
    }

    public int getFiches() {
        return fiches;
    }

    public Carta getCarta_coperta() {
        return carta_coperta;
    }

    public double getValore_mano() {
        return valore_mano;
    }

    /**
     *
     * @return "evento RichiediPuntata " + carta_coperta.toString() + " " + valore_mano + " " + fiches ;
     */
    @Override
    public String toString() {
        return "evento\tRichiediPuntata\t" + carta_coperta.toString() + " " + valore_mano + " " + fiches ;
    }
    
    
    
    
    
}
