package gioco;

import classi_dati.DifficoltaBot;


public class ProvaPartitaOffline {
    public static void main(String[] args) throws InterruptedException {
        PartitaOffline partita = new PartitaOffline(2, 100, DifficoltaBot.Difficile, System.in, System.out);
    }
}
