package partitaOnline.model;

import partitaOnline.events.Vittoria;
import partitaOnline.events.AggiornamentoMazziere;
import partitaOnline.events.GameOver;
import partitaOnline.events.MazzierePerde;
import partitaOnline.events.FineRound;
import partitaOnline.events.FineManoAvversario;
import partitaOnline.events.RisultatoManoParticolare;
import dominio.eccezioni.FichesInizialiException;
import dominio.elementi_di_gioco.Mazzo;
import dominio.giocatori.Giocatore;
import dominio.classi_dati.Stato;
import dominio.eccezioni.FineMazzoException;
import dominio.eccezioni.MazzierePerdeException;
import dominio.eccezioni.SballatoException;
import dominio.eccezioni.SetteeMezzoException;
import dominio.eccezioni.SetteeMezzoRealeException;
import dominio.elementi_di_gioco.Carta;
import dominio.gioco.RegoleDiGioco;
import dominio.gioco.StatoGioco;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOnline.events.EstrattoMazziere;
import partitaOnline.events.GiocatoreLocaleEventListener;
import partitaOnline.events.MazzoRimescolato;
import partitaOnline.events.RichiediNome;


public class PartitaOfflineModel extends Observable {
    private RegoleDiGioco regole_di_gioco = new RegoleDiGioco();
    private ArrayList<Giocatore> giocatori=new ArrayList<>();
    private final Mazzo mazzo = new Mazzo();
    private Giocatore mazziere = null;
    private Giocatore next_mazziere = null;
    public static StatoGioco stato_gioco = StatoGioco.menu;
    public static int LARGHEZZA = 1280, ALTEZZA = 720;
    private int pausa_breve = 1000; //ms
    private int pausa_lunga = 2000; //ms
    private int n_giocatori;

    
    /**
     *
     * @param fiches_iniziali da cambiare
     */
    public PartitaOfflineModel( ){

        
        
    }
    
    /**
     * Consente di giocare una partita di sette e mezzo.
     * @throws InterruptedException
     */
    public void gioca() throws InterruptedException{
        try {
            gioca_round();
            calcola_risultato();
        } catch (MazzierePerdeException ex) {
            this.eventoPerTutti(new MazzierePerde());
            mazziere.azzera_fiches();
            mazziere.perde();
            mazziere_successivo();

        }
        fine_round();
        mazzo.aggiorna_fine_round();

    }
   
    
    public void inizializza_partita(ArrayList giocatori) throws InterruptedException{
        this.giocatori=giocatori;
        try {
            inizzializza_fiches();
        }catch (FichesInizialiException ex) {
            this.setChanged();
            this.notifyObservers(new Error("Errore: Il numero di fiches iniziali dev'essere maggiore di 0."));
        }
        Thread.sleep(pausa_breve);
        
        Thread.sleep(pausa_breve);
        
        estrai_mazziere();
        
        this.eventoPerTutti(new EstrattoMazziere());
        
        mazzo.aggiorna_fine_round();
        mazzo.rimescola();
        
        this.eventoPerTutti(new MazzoRimescolato());
        
    }
    
    public void aggiungiGiocatori(ArrayList giocatori){
        this.giocatori.addAll(giocatori);
    }
    
    public void rimuoviGiocatori(ArrayList giocatori) throws InterruptedException {
        for (Object giocatore : giocatori) {
            if (((Giocatore) giocatore).isMazziere()) {
                estrai_mazziere();

                this.eventoPerTutti(new EstrattoMazziere());

                mazzo.aggiorna_fine_round();
                mazzo.rimescola();

                this.eventoPerTutti(new MazzoRimescolato());
            }
        }
        this.giocatori.removeAll(giocatori);
    }

    private void inizzializza_fiches() throws FichesInizialiException {
        for(Giocatore giocatore: giocatori){
            giocatore.inizializzaFiches();
        }
    }
    
  

    private void estrai_mazziere() throws InterruptedException {
        Carta carta_estratta;
        
        mazzo.mischia();
        
        for(Giocatore giocatore : giocatori){
            while(true){
                try {
                    carta_estratta = mazzo.estrai_carta();
                    giocatore.prendi_carta_iniziale(carta_estratta);
                    break;
                }catch (FineMazzoException ex) {
                    mazzo.rimescola(); //non dovrebbe accadere
                }
            }
            mazziere = regole_di_gioco.carta_piu_alta(mazziere, giocatore);
        }
        mazziere.setMazziere(true);
    }

    private void gioca_round() throws InterruptedException, MazzierePerdeException {
        int pos_mazziere = giocatori.indexOf(mazziere);
        int pos_next_giocatore = pos_mazziere + 1;
        Giocatore giocatore;
        
        inizializza_round();
        distribuisci_carta_coperta();
        effettua_puntate();
        for(int i = 0; i < giocatori.size(); i++){
            if(pos_next_giocatore == giocatori.size()){
                pos_next_giocatore = 0;
            }
            giocatore = getProssimoGiocatore(pos_next_giocatore);
            if(! giocatore.haPerso()){  
                esegui_mano(giocatore);
                if(giocatore instanceof Giocatore && giocatore.getStato() != Stato.OK){
                    
                    this.setChanged();
                    this.notifyObservers(new RisultatoManoParticolare());
                    
                    Thread.sleep(pausa_lunga);
                }
            }
            if(! (giocatore instanceof Giocatore)){
                this.setChanged();
                this.notifyObservers(new FineManoAvversario(giocatore.getNome(), giocatore.getCarteScoperte(),giocatore.getStato(), giocatore.getPuntata()));
                Thread.sleep(pausa_breve);
            }
            pos_next_giocatore += 1;
        }
    }
    
    private void inizializza_round(){
        for(Giocatore giocatore : giocatori){
            giocatore.inizializza_mano();
        }
        next_mazziere = null;
    }
    
    private void distribuisci_carta_coperta(){
        Carta carta_estratta;
        
        for(Giocatore giocatore : giocatori){
            while(true){
                try {
                    if(! giocatore.haPerso()){
                        carta_estratta = mazzo.estrai_carta();
                        giocatore.prendi_carta_iniziale(carta_estratta);
                    }
                    break;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();
                    
                    this.setChanged();
                    this.notifyObservers(new MazzoRimescolato());
                    
                    this.mazziere_successivo();
                }
            }
        }
    }
    
    private void effettua_puntate() {
        for(Giocatore giocatore : giocatori){
            if(! giocatore.equals(mazziere)){
                giocatore.effettua_puntata();
            }
        }
        if(mazziere instanceof Giocatore){
                Giocatore giocatore = (Giocatore) mazziere;
//                giocatore.stampaCartaCoperta();
            }
    }
    
    private Giocatore getProssimoGiocatore(int posizione){
        return giocatori.get(posizione);
    }
    
    private void esegui_mano(Giocatore giocatore) throws MazzierePerdeException{
        Carta carta_estratta = null;
        boolean continua = true;
        
        while(continua){
            continua = giocatore.effettua_giocata();
            if(continua){
                try {
                    carta_estratta = mazzo.estrai_carta();
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();
                    
                    this.setChanged();
                    this.notifyObservers(new MazzoRimescolato());
                    
                    mazziere_successivo();
                    try {
                        carta_estratta = mazzo.estrai_carta();
                    } catch (FineMazzoException ex1) {
                        //////////////////////////////
                    }
                }
                try {
                    giocatore.chiedi_carta(carta_estratta);
                } catch (SballatoException ex) {
                    giocatore.setStato(Stato.Sballato);
                    if(!giocatore.isMazziere()){
                        giocatore.paga(mazziere); //giocatore se sballa paga subito.
                    }
                    continua = false;
                } catch (SetteeMezzoRealeException ex) {
                    giocatore.setStato(Stato.SetteeMezzoReale);
                    continua = false;
                } catch (SetteeMezzoException ex) {
                    giocatore.setStato(Stato.SetteeMezzo);
                    continua = false;
                }
            }
        }
    }
    
    private void calcola_risultato() throws MazzierePerdeException{ 
        int fichesMazziere=controllaMazziere();
        if(fichesMazziere>0){
            for(Giocatore giocatore : giocatori){
                if(! giocatore.isMazziere()){              
                    next_mazziere = regole_di_gioco.risultato_mano(mazziere, giocatore, next_mazziere);
                }
            }
        }
        else {
            double fichesAttualiMazziere=(double)mazziere.getFiches();
            double percentuale=(double)(fichesAttualiMazziere/(fichesAttualiMazziere-fichesMazziere));            
            for(Giocatore giocatore : giocatori){
                if(! giocatore.isMazziere()){              
                    next_mazziere = regole_di_gioco.risultato_mano_percentuale(mazziere, giocatore, next_mazziere, percentuale);
                }
            }
             throw new MazzierePerdeException();
        }
           
    }        

    private int controllaMazziere() {
        int guadagno=mazziere.getFiches();
        for(Giocatore giocatore : giocatori){
            if(! giocatore.isMazziere()){
                guadagno+=regole_di_gioco.controlla_finanze_mazziere(mazziere, giocatore);                       
            }
        }
        return guadagno;
    }
    
   private void mazziere_successivo(){
       int pos_next_mazziere = giocatori.indexOf(mazziere) + 1;
       if(pos_next_mazziere == giocatori.size()){
           pos_next_mazziere = 0;
       }
       for(int i = 0; i < giocatori.size(); i++){
           if(giocatori.get(pos_next_mazziere).haPerso()){
                pos_next_mazziere += 1;
                if(pos_next_mazziere == giocatori.size()){
                    pos_next_mazziere = 0;                
                }              
           } else {
               next_mazziere = giocatori.get(pos_next_mazziere);
               break;
           }
       }
   }
    
    private void fine_round() throws InterruptedException{
        boolean game_over = false;
        for(Giocatore giocatore : giocatori){
            
            this.setChanged();
            this.notifyObservers(new FineRound(giocatore));
            
            Thread.sleep(pausa_breve);
            if(giocatore.getFiches() == 0 && ! giocatore.haPerso()){
                if(giocatore instanceof Giocatore){
                    giocatore.perde();
                    game_over = true;
                } else {
                    giocatore.perde();
                    if(giocatore.isMazziere()){
                        this.setChanged();
                        this.notifyObservers(new MazzierePerde());
                        
                        mazziere_successivo();
                    }
                }
            }
        }
        if(game_over){
            this.setChanged();
            this.notifyObservers(new GameOver());
            game_over();
        }
        if(next_mazziere != null){
            aggiorna_mazziere();
            this.setChanged();
            this.notifyObservers(new AggiornamentoMazziere());
        }
        Thread.sleep(pausa_lunga);
    }
    
    private void aggiorna_mazziere(){
        mazziere.setMazziere(false);
        next_mazziere.setMazziere(true);
        mazziere = next_mazziere;
    }
    
    private void eventoPerTutti(Object evento){
        for(Giocatore giocatore: giocatori){
            try {
                giocatore.scriviOggetto(evento);
            } catch (IOException ex) {
                Logger.getLogger(PartitaOfflineModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void game_over(){
        System.exit(0);
    }

    private void vittoria(){
        System.exit(0);
    }

    public int getN_giocatori() {
        return n_giocatori;
    }


    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Giocatore getMazziere() {
        return mazziere;
    }


}
