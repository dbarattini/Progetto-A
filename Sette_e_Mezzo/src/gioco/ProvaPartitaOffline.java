package gioco;

import classi_dati.DifficoltaBot;


public class ProvaPartitaOffline {
    public static void main(String[] args) throws InterruptedException {
        PartitaOffline partita = new PartitaOffline(2, 5, DifficoltaBot.Facile, System.in, System.out, System.err);
        partita.gioca();
    }
}
