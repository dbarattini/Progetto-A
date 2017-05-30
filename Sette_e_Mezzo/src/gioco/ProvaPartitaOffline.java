package gioco;

import classi_dati.DifficoltaBot;
import eccezioni.datoGiaPresente;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProvaPartitaOffline {
    public static void main(String[] args) {
        PartitaOffline partita;
        try {
            partita = new PartitaOffline(2, 100, DifficoltaBot.Facile, System.in, System.out, System.err);
            partita.gioca();
        } catch (InterruptedException | datoGiaPresente ex) {
            Logger.getLogger(ProvaPartitaOffline.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
