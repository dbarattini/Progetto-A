/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

import classi_dati.ImpostazioniMusica;
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
       
    }
    
    public void selezionaImpostazione() throws InterruptedException {
        while(true) {
            printOpzioniMusica();
            ImpostazioniMusica impostazioni = null;
            impostazioni = richiediImpostazioneMusica();
        
            try {
                
                switch(impostazioni) {
                
                    case Play: 
                        System.out.println("da implementare");
                        break;
                    case Stop:
                        System.out.println("da implementare");
                        break;
                    case Riavvia:
                        System.out.println("da implementare");
                        break;
                    case Loop:
                        System.out.println("Da implementare");
                        break;
                    case Carica:
                        System.out.println("da implementare");
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
            }   
                catch (IllegalArgumentException ex){
                System.out.println("IMPOSTAZIONE NON VALIDA");
                }

//                if(impostazioni_prov == null) {
//                    System.out.println("IMPOSTAZIONE NON VALIDA");
        return impostazioni_prov;
    }
    }
    

