package gioco;

import classi_dati.DifficoltaBot;


public class Prova_Partita {
    public static void main(String[] args) throws InterruptedException {
        Partita partita = new Partita(2, 100, DifficoltaBot.Facile, System.in, System.out);
    }
}
