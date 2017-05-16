package partitaOffline.events;

import elementi_di_gioco.Carta;


public class RichiediPuntata {
    Carta carta_coperta;
    double valore_mano;

    public RichiediPuntata(Carta carta_coperta, double valore_mano) {
        this.carta_coperta = carta_coperta;
        this.valore_mano = valore_mano;
    }

    public Carta getCarta_coperta() {
        return carta_coperta;
    }

    public double getValore_mano() {
        return valore_mano;
    }
    
    
    
}
