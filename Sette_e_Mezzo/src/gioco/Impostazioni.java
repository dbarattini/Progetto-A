/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

import classi_dati.ImpostazioniMenu;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Max & family
 */
public class Impostazioni {
    
    ImpostazioniMenu impostazioni;

    public Impostazioni() {
    }
    
    public void selezionaImpostazione() throws InterruptedException {
        while(true) {
            printImpostazioni();
            ImpostazioniMenu impostazioni = null;
            impostazioni = richiediImpostazione();
        
            try {
                
                switch(impostazioni) {
                
                    case Musica: 
                        Musica music = new Musica();
                        music.selezionaImpostazione();
                        break;
                    case Profilo:
                        System.out.println("da implementare");
                        break;
                    case Riconoscimenti:
                        System.out.println("da implementare");
                        break;
                }
            } catch (NullPointerException e) {    
          }
        }
    }
    
    private void printImpostazioni() {
        
        out.println("\n");
        out.println("SELEZIONA UN'IMPOSTAZIONE:");
        out.println("1. Musica");
        out.println("2. Profilo");
        out.println("3. Riconoscimenti\n");
    }
    
    private ImpostazioniMenu richiediImpostazione() {
        ImpostazioniMenu impostazioni_prov = null;
        Scanner scanner = new Scanner(in);        
        String input = scanner.next();
            
        try { 
                if(input.equals("1") || input.toLowerCase().equals("musica")) {
                    impostazioni_prov=ImpostazioniMenu.Musica;
                }
                if (input.equals("2") || input.toLowerCase().equals("profilo")) {
                    impostazioni_prov=ImpostazioniMenu.Profilo;
                }
                if (input.equals("3") || input.toLowerCase().equals("riconoscimenti")) {
                impostazioni_prov=ImpostazioniMenu.Riconoscimenti;
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
