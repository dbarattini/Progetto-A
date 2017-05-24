package client;

import eccezioni.GiocatoreDisconnessoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import giocatore.Giocatore;
import static java.lang.Thread.sleep;


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
        try {
            gioca();
            sleep(20);
            run();
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gioca() throws IOException{
        if(! giocatori_in_attesa.isEmpty()){
            giocatori.addAll(giocatori_in_attesa);
            giocatori_in_attesa.clear();
        }
        String msg = null;
        if(!giocatori.isEmpty()){
            for(Giocatore giocatore : giocatori){
                try {
                    msg = giocatore.leggi();
                } catch (GiocatoreDisconnessoException ex) {
                    System.out.println(giocatore.getUsername() + " disconnesso");
                    this.giocatori_disconnessi.add(giocatore);
                }        
            }
        }
        if(!giocatori_disconnessi.isEmpty())
            giocatori.removeAll(giocatori_disconnessi);

        if(! giocatori_disconnessi.isEmpty()){
            for(Giocatore giocatore_disconnesso : giocatori_disconnessi){
                for(Giocatore giocatore : giocatori){
                    giocatore.scrivi(giocatore_disconnesso.getUsername() + " diconnesso");
                }
            }
            giocatori_disconnessi.clear();
        }
    }
    
}
