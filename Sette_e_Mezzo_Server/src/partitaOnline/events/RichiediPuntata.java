package partitaOnline.events;

import dominio.elementi_di_gioco.Carta;


public class RichiediPuntata {
    Carta carta_coperta;
    double valore_mano;
    int fiches;

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
    
    
    
}
