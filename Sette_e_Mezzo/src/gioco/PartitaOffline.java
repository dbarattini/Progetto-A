package gioco;


import eccezioni.DifficoltaBotException;
import eccezioni.FichesInizialiException;
import eccezioni.NumeroBotException;
import elementi_di_gioco.Mazzo;
import giocatori.BotFacile;
import giocatori.GiocatoreUmano;
import giocatori.Giocatore;
import classi_dati.DifficoltaBot;
import classi_dati.Stato;
import eccezioni.FineMazzoException;
import eccezioni.MazzierePerdeException;
import elementi_di_gioco.Carta;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class PartitaOffline {
    private RegoleDiGioco regole_di_gioco = new RegoleDiGioco();
    private ArrayList<Giocatore> giocatori=new ArrayList<>();
    private final Mazzo mazzo = new Mazzo();
    private Giocatore mazziere = null;
    private Giocatore next_mazziere = null;
    int pausa_breve = 1000; //ms
    int pausa_lunga = 2000; //ms
    int n_bot;
    int n_bot_sconfitti = 0;
    InputStream in;
    PrintStream out;
    PrintStream err;
    
    /**
     *
     * @param numero_bot numero di bot iniziali
     * @param fiches_iniziali numero di fiches iniziali per ogni giocatore
     * @param difficolta_bot difficoltá di tutti i bot della partita
     * @param in InputStream (es. System.in)
     * @param out PrintStream (es. System.out)
     * @param err PrintStream (es. System.err)
     * @throws InterruptedException lanciata dai Thread.pause
     */
    public PartitaOffline(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot, InputStream in, PrintStream out, PrintStream err) throws InterruptedException{
        this.in = in;
        this.out = out;
        this.err = err;
        this.n_bot = numero_bot;
        try {
            inizializza_partita(numero_bot, fiches_iniziali, difficolta_bot);
        }catch (NumeroBotException ex) {
            this.err.println("Errore: Il numero di bot dev'essere un valore compreso tra 1 ed 11.");
        }catch (FichesInizialiException ex) {
            this.err.println("Errore: Il numero di fiches iniziali dev'essere maggiore di 0.");
        }catch (DifficoltaBotException ex) {
            this.err.println("Errore: Le difficolta disponibili sono: Facile. //Work in Progress\\");
        }
    }
    
    /**
     * Consente di giocare una partita di sette e mezzo.
     * @throws InterruptedException
     */
    public void gioca() throws InterruptedException{
        Thread.sleep(pausa_breve);
        
        out.println("Estrazione del mazziere:\n");
        Thread.sleep(pausa_breve);
        
        estrai_mazziere();
        stampa_schermata_estrai_mazziere();
        
        mazzo.aggiorna_fine_round();
        mazzo.rimescola();
        
        while(true){
            gioca_round();
            try {
                calcola_risultato();
            } catch (MazzierePerdeException ex) {
                //da fare, per ora sceglie solo un nuovo mazziere ed azzera le fiches del vecchio
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
    
    private void inizializza_partita(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot) throws NumeroBotException, FichesInizialiException, DifficoltaBotException{
        inizzializza_fiches(fiches_iniziali);
        inizializza_bots(numero_bot, fiches_iniziali, difficolta_bot);
        inizializza_giocatore(fiches_iniziali); 
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
                    giocatori.add(new BotFacile("bot"+i, fiches_iniziali)); //nomi bot: bot0, bot1, ...
                    break;
                }
                default: throw new DifficoltaBotException();       
            }
        }
    }
    
    private void inizializza_giocatore(int fiches_iniziali){
        out.println("Come ti chiami?");
        String nome = richiedi_nome_giocatore();
        giocatori.add(new GiocatoreUmano(nome,fiches_iniziali,in,out,err));
        out.print("\n");
    }
    
    private String richiedi_nome_giocatore(){
        String nome;
        Scanner scan = new Scanner(System.in);
        
        nome = scan.next();
        return nome;
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
    
    private void stampa_schermata_estrai_mazziere() throws InterruptedException{
        for(Giocatore giocatore : giocatori){
            mostra_carta_coperta_e_valore_mano(giocatore);
            Thread.sleep(pausa_breve);
        }
        stampa_messaggio_mazziere();
        Thread.sleep(pausa_lunga);
    }
    
    private void mostra_carta_coperta_e_valore_mano(Giocatore giocatore){
        out.println(giocatore.getNome() + " [" + giocatore.getCartaCoperta() + "] " + giocatore.getValoreMano());
    }
    
    private void stampa_messaggio_mazziere(){
        out.println("\nIl Mazziere é: " + mazziere.getNome() + "\n");
    }

    private void gioca_round() throws InterruptedException {
        int pos_mazziere = giocatori.indexOf(mazziere);
        int pos_next_giocatore = pos_mazziere + 1;
        Giocatore giocatore;
        
        inizializza_round();
        distribuisci_carta_coperta();
        for(int i = 0; i < giocatori.size(); i++){
            if(pos_next_giocatore == giocatori.size()){
                pos_next_giocatore = 0;
            }
            giocatore = getProssimoGiocatore(pos_next_giocatore);
            if(! giocatore.haPerso()){
                giocatore.gioca_mano(mazzo); //da cambiare, devo passare solo carta
                if(!giocatore.isMazziere() && giocatore.getStato() == Stato.Sballato){
                    giocatore_paga_mazziere(giocatore); //giocatore se sballa paga subito.
                }
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
                }
            }
        }
    }
    
    private void stampa_messaggio_rimescola_mazzo(){
        out.println("Rimescolo il mazzo.");
    }
    
    private Giocatore getProssimoGiocatore(int posizione){
        return giocatori.get(posizione);
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
        HashMap<String,String> risultato;       
        for(Giocatore giocatore : giocatori){
            if(! giocatore.isMazziere() && giocatore.getStato() != Stato.Sballato){              
                risultato = regole_di_gioco.risultato_mano(mazziere, giocatore);
                if(risultato.get("vincitore").equals("giocatore")){                   
                    if(risultato.get("tipo_pagamento").equals("normale")){
                        mazziere_paga_giocatore(giocatore);
                    } else {
                        mazziere_paga_reale_giocatore(giocatore);
                    }                   
                    if(risultato.get("cambia_mazziere").equals("si")){
                        next_mazziere = giocatore;
                    }
                } 
                else{
                    if(risultato.get("tipo_pagamento").equals("normale")){
                        giocatore_paga_mazziere(giocatore);
                    } else {
                        giocatore_paga_reale_mazziere(giocatore);
                    }                    
                    if(risultato.get("cambia_mazziere").equals("si")){
                        next_mazziere = giocatore;
                    }
                }               
            }
        }
    }
    
    private void giocatore_paga_mazziere(Giocatore giocatore){
        mazziere.riscuoti(giocatore.paga_mazziere());
    }
    
    private void giocatore_paga_reale_mazziere(Giocatore giocatore){
        mazziere.riscuoti(giocatore.paga_reale_mazziere());
    }
    
    private void mazziere_paga_giocatore(Giocatore giocatore) throws MazzierePerdeException{
        giocatore.riscuoti(mazziere.paga_giocatore(giocatore.getPuntata()));
    }
    
    private void mazziere_paga_reale_giocatore(Giocatore giocatore) throws MazzierePerdeException{
        giocatore.riscuoti(mazziere.paga_reale_giocatore(giocatore.getPuntata()));
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
                        out.println("Il mazziere ha perso\n");
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
}
