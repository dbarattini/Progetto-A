package dominio.events;

import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.ValoreMano;


public class RichiediPuntata {
    Carta carta_coperta;
    double valore_mano;
    int fiches;

    /**
     * 
     * @param carta_coperta carta coperta del giocatore
     * @param valore_mano valore totale della mano del giocatore
     * @param fiches quantità di fiches del giocatore
     */
    public RichiediPuntata(Carta carta_coperta, ValoreMano valore_mano, int fiches) {
        this.carta_coperta = carta_coperta;
        this.valore_mano = valore_mano.getValore();
        this.fiches = fiches;
    }
    
    /**
     * 
     * @param carta_coperta carta coperta del giocatore
     * @param valore_mano valore totale della mano del giocatore
     * @param fiches quantità di fiches del giocatore
     */
    public RichiediPuntata(Carta carta_coperta, double valore_mano, int fiches) {
        this.carta_coperta = carta_coperta;
        this.valore_mano = valore_mano;
        this.fiches = fiches;
    }

    /**
     * 
     * @return quantità di fiches del giocatore
     */
    public int getFiches() {
        return fiches;
    }

    /**
     * 
     * @return carta coperta del giocatore
     */
    public Carta getCarta_coperta() {
        return carta_coperta;
    }

    /**
     * 
     * @return valore della mano del giocatore
     */
    public double getValore_mano() {
        return valore_mano;
    }
    
    
    
}
