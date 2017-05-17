package client;

import eccezioni.GiocatoreDisconnessoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sette_e_mezzo_server.Giocatore;


public class Partita implements Runnable {
    private ArrayList<Giocatore> giocatori;
    private ArrayList<Giocatore> giocatori_in_attesa;
    private ArrayList<Giocatore> giocatori_disconnessi;
    
    public Partita(){
        this.giocatori = new ArrayList<>(); 
        this.giocatori_in_attesa = new ArrayList<>();
        this.giocatori_disconnessi = new ArrayList<>();
    }
     
    public void aggiungiGiocatore(Giocatore giocatore){
        this.giocatori_in_attesa.add(giocatore);
        try {
            Thread.sleep(100); //da il tempo di stabilizzare la connessione e caricare eventuali gui
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
//        while(true){
//            this.gioca();
//        }
    }
    
    private void gioca() throws IOException{
        if(! giocatori_in_attesa.isEmpty()){
            giocatori.addAll(giocatori_in_attesa);
            giocatori_in_attesa.clear();
        }
        String msg = null;
        for(Giocatore giocatore : giocatori){
            try {
                msg = giocatore.Leggi();
            } catch (GiocatoreDisconnessoException ex) {
                System.out.println(giocatore.getNome() + " disconnesso");
                this.giocatori_disconnessi.add(giocatore);
            }        
        }
        
        giocatori.removeAll(giocatori_disconnessi);

        if(! giocatori_disconnessi.isEmpty()){
            for(Giocatore giocatore_disconnesso : giocatori_disconnessi){
                for(Giocatore giocatore : giocatori){
                    giocatore.Scrivi(giocatore_disconnesso.getNome() + " diconnesso");
                }
            }
            giocatori_disconnessi.clear();
        }
    }
    
}
