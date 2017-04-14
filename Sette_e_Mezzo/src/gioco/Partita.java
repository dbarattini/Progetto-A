package gioco;


import classi_dati.DifficoltaBot;
import classi_dati.Stato;
import eccezioni.FineMazzoException;
import java.util.ArrayList;


public class Partita {
    private ArrayList<Giocatore> giocatori=new ArrayList<>();
    private Mazzo mazzo = new Mazzo();
    
    public Partita(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot){
        inizializza_partita(numero_bot, fiches_iniziali, difficolta_bot);
        estrai_mazziere();
        mazzo.mischia();
        for(int i = 0; i < 100; i++){
            gioca_round();
            fine_round();
            mazzo.aggiorna_fine_mano();
        }
        fine_partita();
    }
    
    private void inizializza_partita(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot){
        for(int i = 0; i < numero_bot; i++){
            switch(difficolta_bot){
                case Facile : giocatori.add(new BotFacile("bot"+i, i, fiches_iniziali));
                default:
                   
            }
        }
        giocatori.add(new GiocatoreUmano("darichiederenome",numero_bot,fiches_iniziali,System.in,System.out));
    }

    private void estrai_mazziere() {
        //per ora imposta il primo giocatore...da sviluppare.
        giocatori.get(0).setMazziere(true);
    }

    private void gioca_round() {
        inizializza_round();
        distribuisci_carta_coperta();
        for(Giocatore giocatore : giocatori){
            giocatore.gioca_mano(mazzo);
            if(giocatore instanceof GiocatoreUmano && giocatore.stato != Stato.OK){
                System.out.println("Carta Ottenuta: " + giocatore.carte_scoperte.get(giocatore.carte_scoperte.size() - 1));
                System.out.println(giocatore.stato);
                System.out.print("\n");
            }
        }
    }
    
    private void inizializza_round(){
        for(Giocatore giocatore : giocatori){
            giocatore.inizializza_mano();
        }
    }
    
    private void distribuisci_carta_coperta(){
        for(Giocatore giocatore : giocatori){
            while(true){
                try {
                    giocatore.prendi_carta_iniziale(mazzo);
                    break;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();
                }
            }
        }
    }
    
    private void fine_round(){
        for(Giocatore giocatore : giocatori){
            System.out.println(giocatore.nome + " " + giocatore.getValoreMano() + " " + giocatore.getStato());
        }
        System.out.println("\n");
    }

    private void fine_partita() {
        //da fare
    }
    
    
    
}
