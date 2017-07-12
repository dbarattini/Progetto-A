package menuOpzioni;

import dominio.classi_dati.ImpostazioniMusica;
import dominio.eccezioni.CanzoneNonTrovataException;
import dominio.eccezioni.CaricamentoCanzoneException;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.Scanner;
import dominio.musica.AudioPlayer;


public class Musica {
    
    private AudioPlayer audio;
    private ImpostazioniMusica impostazioni;
    private boolean on=false;
    private boolean off=true;
    private boolean indietro = false;

    public Musica() {
       this.audio = new AudioPlayer();
    }
    
    /**
     *  Stampa le scelte disponibili, inizializza la scelta a null poi la setta con il valore dell'enum in base all'opzione scelta da terminale
     * @throws InterruptedException
     * @throws CanzoneNonTrovataException
     * @throws CaricamentoCanzoneException 
     */
    
    public void run() throws InterruptedException, CanzoneNonTrovataException, CaricamentoCanzoneException {
        indietro = false;
        while(true) {
            printOpzioniMusica();
            ImpostazioniMusica impostazioni = null;
            impostazioni = richiediImpostazioneMusica();
            

            try {
                
                switch(impostazioni) {
                
                    case Play: 
                        if(off) {
                        audio.carica("LoungeBeat.wav", "soundTrack");
                        audio.riproduci("soundTrack");
                        setOn();
                        }
                        break;
                    case Stop:
                        if(on) {
                        audio.ferma("soundTrack");
                        setOff();
                        }
                        break;
                    case Riavvia:
                        if(on) {
                        audio.ferma("soundTrack");
                        audio.riavvia("soundTrack");
                        }
                        else {
                            audio.carica("LoungeBeat.wav", "soundTrack");
                            audio.riproduci("soundTrack");
                            setOn();
                        }
                        break;
                    case Loop:
                        if(off) {
                        audio.carica("LoungeBeat.wav", "soundTrack");
                        audio.riproduciInLoop("soundTrack");
                        setOn();
                        }
                        else {
                            audio.riproduciInLoop("soundTrack");
                        }
                        break;
                    case Carica:
                        System.out.println("Inserisci la canzone da caricare");
                        Scanner scanner = new Scanner(in);        
                        String input = scanner.next();
                        Scanner scanner2 = new Scanner(in);        
                        String input2 = scanner.next();
                        audio.carica(input, input2);
                        audio.riproduci(input2);
                        break;
                    case Indietro:
                        indietro = true;
                        break;
                }
            } catch (NullPointerException e) {    
          }
            if(indietro){
                break;
            }
        }
    }
    
    private void printOpzioniMusica() {
        out.println("\n");
        System.out.println("  ---------------------------------------------------------------------------  ");
        System.out.println("                       < SELEZIONA UN OPZIONE DAL MENU >                       ");
        System.out.println("  ---------------------------------------------------------------------------  ");
        System.out.println("                                1. Play                                        ");
        System.out.println("                                2. Stop                                        ");
        System.out.println("                                3. Riavvia                                     ");
        System.out.println("                                4. Riproduci in loop                           ");
        System.out.println("                                5. Carica canzone                              ");
        System.out.println("                                6. Indietro                                    ");
    }
    
    private ImpostazioniMusica richiediImpostazioneMusica() {
        ImpostazioniMusica impostazioni_prov = null;
        Scanner scanner = new Scanner(in);        
        System.out.print("                                         ");
        String input = scanner.next();
        System.out.print("\n");
            
        try { 
                if(input.equals("1") || input.toLowerCase().equals("play")) {
                    impostazioni_prov=ImpostazioniMusica.Play;
                }
                if (input.equals("2") || input.toLowerCase().equals("stop")) {
                    impostazioni_prov=ImpostazioniMusica.Stop;
                }
                if (input.equals("3") || input.toLowerCase().equals("riavvia")) {
                    impostazioni_prov=ImpostazioniMusica.Riavvia;
                }
                if (input.equals("4") || input.toLowerCase().equals("loop")) {
                    impostazioni_prov=ImpostazioniMusica.Loop;
                }
                if (input.equals("5") || input.toLowerCase().equals("carica")) {
                    impostazioni_prov=ImpostazioniMusica.Carica;
                }
                if (input.equals("6") || input.toLowerCase().equals("indietro")) {
                    impostazioni_prov=ImpostazioniMusica.Indietro;
                }
            }   
                catch (IllegalArgumentException ex){
                System.err.println("Errore: La scelta effettuata non é valida.\n");
                }

//                if(impostazioni_prov == null) {
//                    System.out.println("IMPOSTAZIONE NON VALIDA");
        return impostazioni_prov;
    }

    private void setOn() {
        this.on = true;
        this.off = false;
    }

    private void setOff() {
        this.on = false;
        this.off = true;
    }
    
    
}

