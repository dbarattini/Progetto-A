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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Partita {
    private ArrayList<Giocatore> giocatori=new ArrayList<>();
    private final Mazzo mazzo = new Mazzo();
    private Giocatore mazziere;
    int pausa_breve = 1000; //ms
    int pausa_lunga = 2000; //ms
    
    public Partita(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot) throws InterruptedException{
        try {
            inizializza_partita(numero_bot, fiches_iniziali, difficolta_bot);
            estrai_mazziere();
            mazzo.mischia();
            for(int i = 0; i < 100; i++){ //ci sono 100 round solo per prova
                gioca_round();
                fine_round();
                mazzo.aggiorna_fine_round();
            }
            fine_partita();
        } catch (NumeroBotException ex) {
            System.out.println("Il numero di bot dev'essere un valore compreso tra 1 ed 11.");
        } catch (FichesInizialiException ex) {
            System.out.println("Il numero di fiches iniziali dev'essere maggiore di 0");
        } catch (DifficoltaBotException ex) {
            System.out.println("Le difficolta disponibili sono: Facile.");
        }
    }
    
    private void inizializza_partita(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot) throws NumeroBotException, FichesInizialiException, DifficoltaBotException{
        if(fiches_iniziali <= 0){
            throw new FichesInizialiException();
        }
        inizializza_bots(numero_bot, fiches_iniziali, difficolta_bot);
        inizializza_giocatore(fiches_iniziali);
    }
    
    private void inizializza_bots(int numero_bot, int fiches_iniziali, DifficoltaBot difficolta_bot) throws NumeroBotException, DifficoltaBotException{
        if(numero_bot <= 0 || numero_bot >= 12){
            throw new NumeroBotException();
        }
        for(int i = 0; i < numero_bot; i++){
            switch(difficolta_bot){
                case Facile : {
                    giocatori.add(new BotFacile("bot"+i, fiches_iniziali));
                    break;
                }
                default: throw new DifficoltaBotException();
                   
            }
        }
    }
    
    private void inizializza_giocatore(int fiches_iniziali){
        System.out.println("Come ti chiami?");
        String nome = richiedi_nome_giocatore();
        giocatori.add(new GiocatoreUmano(nome,fiches_iniziali,System.in,System.out));
        System.out.print("\n");
    }
    
    private String richiedi_nome_giocatore(){
        String nome;
        Scanner scan = new Scanner(System.in);
        
        nome = scan.next();
        return nome;
    }

    private void estrai_mazziere() throws InterruptedException {
        //rivedere il caso di paritá
        mazzo.mischia();
        Thread.sleep(pausa_breve);
        System.out.println("Estrazione del mazziere:\n");
        Thread.sleep(pausa_breve);
        mazziere = null;
        for(Giocatore giocatore : giocatori){
            while(true){
                try {
                    giocatore.prendi_carta_iniziale(mazzo);
                    giocatore.aggiorna_valore_mano();
                    System.out.println(giocatore.getNome() + " " + giocatore.getCartaCoperta() + " " + giocatore.getValoreMano());
                    Thread.sleep(pausa_breve);
                    break;
                } catch (FineMazzoException ex) {
                    mazzo.rimescola();
                }
            }
            if(mazziere == null){
                mazziere = giocatore;
            } else if(giocatore.getValoreMano() > mazziere.getValoreMano()){
                mazziere = giocatore;
            } 
        }
        System.out.println("\nIl Mazziere é: " + mazziere.getNome() + "\n");
        Thread.sleep(pausa_lunga);
        mazziere.setMazziere(true);
        mazzo.aggiorna_fine_round();
        mazzo.rimescola();
    }

    private void gioca_round() throws InterruptedException {
        inizializza_round();
        distribuisci_carta_coperta();
        int pos_mazziere = giocatori.indexOf(mazziere);
        int pos_next = pos_mazziere + 1;
        Giocatore giocatore;
        for(int i = 0; i < giocatori.size(); i++){
            if(pos_next == giocatori.size()){
                pos_next = 0;
            }
            giocatore = giocatori.get(pos_next);
            if(! giocatore.haPerso()){
                giocatore.gioca_mano(mazzo);
                if(!giocatore.isMazziere() && giocatore.getStato() == Stato.Sballato){
                    mazziere.riscuoti(giocatore.paga_mazziere()); //giocatore se sballa paga subito.
                }
                if(giocatore instanceof GiocatoreUmano && giocatore.getStato() != Stato.OK){
                    System.out.println("Carta Ottenuta: " + giocatore.getUltimaCartaOttenuta());
                    System.out.println("Valore Mano: " + giocatore.getValoreMano());
                    System.out.println(giocatore.getStato());
                    System.out.print("\n");
                    Thread.sleep(pausa_lunga);
                }
            }
            pos_next += 1;
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
    
    private void fine_round() throws InterruptedException{
        for(Giocatore giocatore : giocatori){
            System.out.println(giocatore.isMazziere() + " " + giocatore.getNome() + " " + giocatore.getVettoreCarte() + " " + giocatore.getValoreMano() + " "+ giocatore.getStato());
            Thread.sleep(pausa_breve);
        }
        System.out.print("\n");
        Thread.sleep(pausa_lunga);
    }
    
    private void calcola_risultato(){
        for(Giocatore giocatore : giocatori){
            if(! giocatore.isMazziere()){
                if(mazziere.getStato() == Stato.Sballato){
                    switch(giocatore.getStato()){
                        case Sballato:
                    }
                }
            }
        }
    }

    private void fine_partita() {
        //da fare
    }   
}
