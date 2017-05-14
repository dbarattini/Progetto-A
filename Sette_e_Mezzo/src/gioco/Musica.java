/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

import classi_dati.ImpostazioniMusica;
import eccezioni.CanzoneNonTrovataException;
import eccezioni.CaricamentoCanzoneException;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.Scanner;
import musica.AudioPlayer;

/**
 *
 * @author Max & family
 */
public class Musica {
    
    private AudioPlayer audio;
    private ImpostazioniMusica impostazioni;

    public Musica() {
       this.audio = new AudioPlayer();
    }
    
    public void selezionaImpostazione() throws InterruptedException, CanzoneNonTrovataException, CaricamentoCanzoneException {
        while(true) {
            printOpzioniMusica();
            ImpostazioniMusica impostazioni = null;
            impostazioni = richiediImpostazioneMusica();
            
            
        
            try {
                
                switch(impostazioni) {
                
                    case Play: 
                        audio.carica("LoungeBeat.wav", "soundTrack");
                        audio.riproduci("soundTrack");
                        break;
                    case Stop:
                        audio.ferma("soundTrack");
                        break;
                    case Riavvia:
                        audio.riavvia("soundTrack");
                        break;
                    case Loop:
                        audio.carica("LoungeBeat.wav", "soundTrack");
                        audio.riproduci_in_loop("soundTrack");
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
                }
            } catch (NullPointerException e) {    
          }
        }
    }
    
    public void printOpzioniMusica() {
        out.println("\n");
        out.println("SELEZIONA UN'IMPOSTAZIONE DELLA MUSICA");
        out.println("1. PLAY");
        out.println("2. STOP");
        out.println("3. RIAVVIA");
        out.println("4. RIPRODUCI IN LOOP");
        out.println("5. CARICA CANZONE");
    }
    
    private ImpostazioniMusica richiediImpostazioneMusica() {
        ImpostazioniMusica impostazioni_prov = null;
        Scanner scanner = new Scanner(in);        
        String input = scanner.next();
            
        try { 
                if(input.equals("1") || input.toLowerCase().equals("play")) {
                    impostazioni_prov=ImpostazioniMusica.Play;
                    System.out.println("Hai selezionato PLAY");
                }
                if (input.equals("2") || input.toLowerCase().equals("stop")) {
                    impostazioni_prov=ImpostazioniMusica.Stop;
                    System.out.println("Hai selezionato STOP");
                }
                if (input.equals("3") || input.toLowerCase().equals("riavvia")) {
                    impostazioni_prov=ImpostazioniMusica.Riavvia;
                    System.out.println("Hai selezionato RIAVVIA");
                }
                if (input.equals("4") || input.toLowerCase().equals("loop")) {
                    impostazioni_prov=ImpostazioniMusica.Loop;
                    System.out.println("Hai selezionato LOOP");
                }
                if (input.equals("5") || input.toLowerCase().equals("carica")) {
                    impostazioni_prov=ImpostazioniMusica.Carica;
                    System.out.println("Hai selezionato CARICA");
                }
            }   
                catch (IllegalArgumentException ex){
                System.out.println("IMPOSTAZIONE NON VALIDA");
                }

//                if(impostazioni_prov == null) {
//                    System.out.println("IMPOSTAZIONE NON VALIDA");
        return impostazioni_prov;
    }
    }
    

