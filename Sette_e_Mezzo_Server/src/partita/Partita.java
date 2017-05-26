package partita;

import dominio.eccezioni.GiocatoreDisconnessoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;
import partitaOnline.model.PartitaOfflineModel;
import dominio.giocatori.Giocatore;


public class Partita implements Runnable {
    private ArrayList<Giocatore> giocatori;
    private ArrayList<Giocatore> giocatori_in_attesa;
    private ArrayList<Giocatore> giocatori_disconnessi;
    private PartitaOfflineModel model;
    private boolean iniziata=false;
    
    public Partita(){
        this.giocatori = new ArrayList<>(); 
        this.giocatori_in_attesa = new ArrayList<>();
        this.giocatori_disconnessi = new ArrayList<>();
        this.model= new PartitaOfflineModel();
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
            setGiocatori();
            giocaPartita();
            sleep(20);
            run();
        } catch (IOException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    private void giocaPartita(){
        if(giocatori.size()>1){
            if(iniziata){
                giocaTurno();
            }
            else
                iniziaPartita();            
        }
    }
    
    private void giocaTurno(){
        try {
            this.model.gioca();
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void iniziaPartita(){
        try {
            this.model.inizializza_partita(giocatori);
        } catch (InterruptedException ex) {
            Logger.getLogger(Partita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    private void setGiocatori() throws IOException, InterruptedException{
        if(iniziata){
            aggiungiGiocatori();
            rimuoviGiocatori();
        }
        else{
            if(! giocatori_in_attesa.isEmpty()){
                giocatori.addAll(giocatori_in_attesa);
                giocatori_in_attesa.clear();
            }
            controllaConnessione();
            giocatori_disconnessi.clear();       
        }
    }

    private void rimuoviGiocatori() throws IOException, InterruptedException {
        controllaConnessione();
        if(!giocatori_disconnessi.isEmpty()){
            this.model.rimuoviGiocatori(giocatori_disconnessi);
            giocatori_disconnessi.clear();
        }
    }

    private void aggiungiGiocatori() {
        if(! giocatori_in_attesa.isEmpty()){
            giocatori.addAll(giocatori_in_attesa);
            this.model.aggiungiGiocatori(giocatori_in_attesa);
            giocatori_in_attesa.clear();
        }
    }
    
    private void controllaConnessione() throws IOException{
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

        if(! giocatori_disconnessi.isEmpty()){
            giocatori.removeAll(giocatori_disconnessi);
            for(Giocatore giocatore_disconnesso : giocatori_disconnessi){
                for(Giocatore giocatore : giocatori){
                    giocatore.scrivi(giocatore_disconnesso.getUsername() + " diconnesso");
                }
            }
        }
    }
    
}
