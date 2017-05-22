package dominio.gioco;

import partitaOffline.model.PartitaOfflineModel;
import dominio.classi_dati.DifficoltaBot;


public class ProvaPartitaOffline {
    public static void main(String[] args) throws InterruptedException {
        PartitaOfflineModel partita = new PartitaOfflineModel(2,DifficoltaBot.Facile, 100);
        partita.gioca();
    }
}
