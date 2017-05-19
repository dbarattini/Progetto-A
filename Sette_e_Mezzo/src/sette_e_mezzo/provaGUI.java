package sette_e_mezzo;

import static dominio.classi_dati.DifficoltaBot.Facile;
import modules.PartitaOfflineGui;

public class provaGUI {
    
    public static void main(String[] args) throws InterruptedException {
        new PartitaOfflineGui(3, Facile, 500);
    }    
}