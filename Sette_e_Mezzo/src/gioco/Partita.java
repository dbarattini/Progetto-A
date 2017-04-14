package gioco;


import classi_dati.DifficoltaBot;
import eccezioni.FineMazzoException;
import java.util.ArrayList;


public class Partita {
    private ArrayList<Giocatore> giocatori=new ArrayList<>();
    private Mazzo mazzo = new Mazzo();
    
    public Partita(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot){
        inizializza_partita(numero_bot, fiches_iniziali, difficolta_bot);
        estrai_mazziere();
        gioca_round();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void gioca_round() {
        distribuisci_carta_coperta();
        for(Giocatore giocatore : giocatori){
            giocatore.gioca_mano(mazzo);
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

    private void fine_partita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
