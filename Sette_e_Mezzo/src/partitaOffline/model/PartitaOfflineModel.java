package partitaOffline.model;

import dominio.eccezioni.DifficoltaBotException;
import dominio.eccezioni.FichesInizialiException;
import dominio.eccezioni.NumeroBotException;
import dominio.elementi_di_gioco.Mazzo;
import dominio.giocatori.BotFacile;
import dominio.giocatori.Giocatore;
import dominio.classi_dati.DifficoltaBot;
import dominio.classi_dati.Stato;
import dominio.eccezioni.CanzoneNonTrovataException;
import dominio.eccezioni.CaricamentoCanzoneException;
import dominio.eccezioni.FineMazzoException;
import dominio.eccezioni.MazzierePerdeException;
import dominio.eccezioni.SballatoException;
import dominio.eccezioni.SetteeMezzoException;
import dominio.eccezioni.SetteeMezzoRealeException;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.BotDifficile;
import dominio.giocatori.BotMedio;
import dominio.gioco.RegoleDiGioco;
import dominio.gioco.StatoGioco;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;
import dominio.musica.AudioPlayer;
import partitaOffline.events.EstrattoMazziere;
import partitaOffline.events.GiocatoreLocaleEventListener;
import partitaOffline.events.MazzoRimescolato;
import partitaOffline.events.Messaggio;
import partitaOffline.events.RichiediNome;


public class PartitaOfflineModel extends Observable {
    private RegoleDiGioco regole_di_gioco = new RegoleDiGioco();
    private AudioPlayer audio = new AudioPlayer();
    private ArrayList<Giocatore> giocatori=new ArrayList<>();
    private GiocatoreUmano giocatore_locale;
    private final Mazzo mazzo = new Mazzo();
    private Giocatore mazziere = null;
    private Giocatore next_mazziere = null;
    public static StatoGioco stato_gioco = StatoGioco.menu;
    public static int LARGHEZZA = 1280, ALTEZZA = 720;
    int pausa_breve = 1000; //ms
    int pausa_lunga = 2000; //ms
    int n_bot;
    int n_bot_sconfitti = 0;
    DifficoltaBot difficolta_bot;
    int fiches_iniziali;
    String nome_giocatore;
    InputStream in;
    PrintStream out;
    PrintStream err;
    
    /**
     *
     * @param numero_bot numero di bot iniziali
     * @param fiches_iniziali numero di fiches iniziali per ogni giocatore
     * @param difficolta_bot difficoltá di tutti i bot della partita
     */
    public PartitaOfflineModel(int numero_bot, DifficoltaBot difficolta_bot, int fiches_iniziali){
        this.n_bot = numero_bot;
        this.difficolta_bot = difficolta_bot;
        this.fiches_iniziali = fiches_iniziali;
        
        try {
            inizializza_audio();
            audio.riproduci_in_loop("soundTrack");
        } catch (CaricamentoCanzoneException ex) {
            this.setChanged();
            this.notifyObservers(new Error("Errore: Impossibile caricare la canzone " + ex.getCanzone()));
        } catch (CanzoneNonTrovataException ex) {
            this.setChanged();
            this.notifyObservers(new Error("Errore: " +ex.getCanzone() + " non caricata/o"));
        }
    }
    
    /**
     * Consente di giocare una partita di sette e mezzo.
     * @throws InterruptedException
     */
    public void gioca() throws InterruptedException{
        Thread.sleep(pausa_breve);
        
        this.setChanged();
        this.notifyObservers(new Messaggio("Estrazione del mazziere"));
        
        Thread.sleep(pausa_breve);
        
        estrai_mazziere();
        
        this.setChanged();
        this.notifyObservers(new EstrattoMazziere());
        
        mazzo.aggiorna_fine_round();
        mazzo.rimescola();
        
        this.setChanged();
        this.notifyObservers(new MazzoRimescolato());
        
        while(true){
            try {
                gioca_round();
                calcola_risultato();
            } catch (MazzierePerdeException ex) {
                out.println("Il mazziere ha perso\n");
                mazziere.azzera_fiches();
                mazziere_successivo();
                for(Giocatore giocatore : giocatori){
                    if(! giocatore.isMazziere()){
                        giocatore.riscuoti(0);
                    }
                }
            }
            fine_round();
            mazzo.aggiorna_fine_round();
            if(n_bot_sconfitti == n_bot){
                stampa_schermata_vittoria();
                vittoria();
            }
        }
    }
    
    private void inizializza_audio() throws CaricamentoCanzoneException{
        audio.carica("LoungeBeat.wav", "soundTrack");
    }
    
    public void inizializza_partita(int numero_bot, DifficoltaBot difficolta_bot, int fiches_iniziali){
        try {
            inizzializza_fiches(fiches_iniziali);
            inizializza_bots(numero_bot, fiches_iniziali, difficolta_bot); 
            inizializza_giocatore(fiches_iniziali);
        }catch (NumeroBotException ex) {
            this.setChanged();
            this.notifyObservers(new Error("Errore: Il numero di bot dev'essere un valore compreso tra 1 ed 11."));
        }catch (FichesInizialiException ex) {
            this.setChanged();
            this.notifyObservers(new Error("Errore: Il numero di fiches iniziali dev'essere maggiore di 0."));
        }catch (DifficoltaBotException ex) {
            this.setChanged();
            this.notifyObservers(new Error("Errore: Le difficolta disponibili sono: Facile, Medio, Difficile."));
        } 
    }

    private void inizzializza_fiches(int fiches_iniziali) throws FichesInizialiException {
        if(fiches_iniziali <= 0){
            throw new FichesInizialiException();
        }
    }
    
    private void inizializza_bots(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot) throws NumeroBotException, DifficoltaBotException{
        if(numero_bot <= 0 || numero_bot >= 12){
            throw new NumeroBotException();
        }
        for(int i = 0; i < numero_bot; i++){
            switch(difficolta_bot){
                case Facile : {
                    giocatori.add(new BotFacile("bot"+i, fiches_iniziali, mazzo)); //nomi bot: bot0, bot1, ...
                    break;
                }
                case Medio : {
                    giocatori.add(new BotMedio("bot"+i, fiches_iniziali, mazzo));
                    break;
                }
                case Difficile : {
                    giocatori.add(new BotDifficile("bot"+i, fiches_iniziali, mazzo));
                    break;
                }
                default: throw new DifficoltaBotException();       
            }
        }
    }
    
    private void inizializza_giocatore(int fiches_iniziali){
        this.setChanged();
        this.notifyObservers(new RichiediNome());
        giocatore_locale = new GiocatoreUmano(nome_giocatore,fiches_iniziali);
        giocatori.add(giocatore_locale);
    }
    
    public void setNomeGiocatore(String nome_giocatore){
        this.nome_giocatore = nome_giocatore;
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
    
    private void mostra_carta_coperta_e_valore_mano(Giocatore giocatore){
        out.println(giocatore.getNome() + " [" + giocatore.getCartaCoperta() + "] " + giocatore.getValoreMano());
    }
    
    private void stampa_messaggio_mazziere(){
        out.println("\nIl Mazziere é: " + mazziere.getNome() + "\n");
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
                if(giocatore instanceof GiocatoreUmano && giocatore.getStato() != Stato.OK){
                    stampa_se_stato_non_ok(giocatore);
                    Thread.sleep(pausa_lunga);
                }
            }
            if(! (giocatore instanceof GiocatoreUmano)){
                stampa_giocata_bot(giocatore);
                Thread.sleep(pausa_breve);
            }
            pos_next_giocatore += 1;
        }
        out.print("\n");
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
                    stampa_messaggio_rimescola_mazzo();
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
        if(mazziere instanceof GiocatoreUmano){
                GiocatoreUmano giocatore = (GiocatoreUmano) mazziere;
//                giocatore.stampaCartaCoperta();
            }
    }
    
    private void stampa_messaggio_rimescola_mazzo(){
        out.println("Rimescolo il mazzo.");
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
                    stampa_messaggio_rimescola_mazzo();
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
    
    private void stampa_se_stato_non_ok(Giocatore giocatore){
        out.println("Carta Ottenuta: " + giocatore.getUltimaCartaOttenuta());
        out.println("Valore Mano: " + giocatore.getValoreMano() + "\n");
        out.println(giocatore.getStato());
        out.print("\n");
    }
    
    private void stampa_giocata_bot(Giocatore giocatore){
        out.println(giocatore.getNome() + " " + giocatore.getCarteScoperte() + " " + giocatore.getStato() + " " + giocatore.getPuntata());
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
            stampa_schermata_risultato_round(giocatore);
            Thread.sleep(pausa_breve);
            if(giocatore.getFiches() == 0 && ! giocatore.haPerso()){
                if(giocatore instanceof GiocatoreUmano){
                    giocatore.perde();
                    game_over = true;
                } else {
                    giocatore.perde();
                    n_bot_sconfitti += 1;
                    if(giocatore.isMazziere()){
                        out.println("\nIl mazziere ha perso\n");
                        mazziere_successivo();
                    }
                }
            }
        }
        out.print("\n");
        if(game_over){
            stampa_schermata_game_over();
            game_over();
        }
        if(next_mazziere != null){
            aggiorna_mazziere();
            stampa_schermata_aggiorna_mazziere();
        }
        Thread.sleep(pausa_lunga);
    }
    
    private void stampa_schermata_risultato_round(Giocatore giocatore){
        out.println(giocatore.haPerso() + " " + giocatore.isMazziere() + " " + giocatore.getNome() + " " + giocatore.getTutteLeCarte() + " " + giocatore.getValoreMano() + " "+ giocatore.getStato() + " " + giocatore.getFiches());
    }
    
    private void aggiorna_mazziere(){
        mazziere.setMazziere(false);
        next_mazziere.setMazziere(true);
        mazziere = next_mazziere;
    }
    
    private void stampa_schermata_aggiorna_mazziere(){
        out.println("il nuovo mazziere é: " + mazziere.getNome() + "\n");
    }

    private void game_over(){
        System.exit(0);
    }
    
    private void stampa_schermata_game_over(){
        out.println("Game Over");
    }

    private void vittoria(){
        System.exit(0);
    }
    
    private void stampa_schermata_vittoria(){
        out.println("Complimenti! Hai Sconfitto tutti i bot.");
    }

    public int getN_bot() {
        return n_bot;
    }

    public DifficoltaBot getDifficolta_bot() {
        return difficolta_bot;
    }

    public int getFiches_iniziali() {
        return fiches_iniziali;
    }
    
    public void addGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l){
        giocatore_locale.addGiocatoreLocaleEventListener(l);
    }
    
    public void removeGiocatoreLocaleEventListener(GiocatoreLocaleEventListener l){
        giocatore_locale.removeGiocatoreLocaleEventListener(l);
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Giocatore getMazziere() {
        return mazziere;
    }

    public GiocatoreUmano getGiocatoreLocale() {
        return giocatore_locale;
    }
}
