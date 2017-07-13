/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuPrePartita;

import dominio.classi_dati.DifficoltaBot;
import java.util.Scanner;
import moduli.PartitaOfflineConsole;


public class MenuPrePartitaConsole{
    private String numero_bot_inserito, difficolta_bot_inserita, fiches_iniziali_inserite;
    private int numero_bot, fiches_iniziali;
    DifficoltaBot difficolta_bot;
    
    private final Scanner scanner;
    
    public MenuPrePartitaConsole(){
        scanner = new Scanner(System.in);
        run();
    }
    
    private void run(){
        System.out.println("  ---------------------------------------------------------------------------  ");
        System.out.println("<                             IMPOSTAZIONI PARTITA                            >");
        System.out.println("  ---------------------------------------------------------------------------  ");
        richiediNumeroBot();
        richiediDifficoltaBot();
        richiediFichesIniziali();
        new PartitaOfflineConsole(numero_bot,difficolta_bot, fiches_iniziali);
    }

    private void richiediNumeroBot() {
        while(true){
            System.out.println("                  Inserisci il numero di Bot che vuoi sfidare                  ");
            System.out.println("                              (min = 1 ; max = 12)                             ");
            System.out.print("                                         ");
            numero_bot_inserito = scanner.next();
            System.out.print("\n");
            try {
                checkNumeroBot();
                break;
            } catch (NumeroBotException ex) {
                System.err.println("       Errore: Il numero di bot dev'essere un numero compreso tra 1 e 12.      ");
            }
        }
    }

    private void richiediDifficoltaBot() {
       while(true){
            System.out.println("                        Inserisci la difficoltá dei Bot                        ");
            System.out.println("                           (Facile, Medio, Difficile)                          ");
            System.out.print("                                         ");
            difficolta_bot_inserita = scanner.next();
            System.out.print("\n");
            try {
                checkDifficoltaBot();
                break;
            } catch (DifficoltaBotException ex) {
                System.err.println("        Errore: le difficolta disponibili sono Facile, Medio, Difficile.       ");
            }
       }
    }

    private void richiediFichesIniziali() {
        while(true){
            System.out.println("                     Inserisci il numero di fiches iniziali                    "); 
            System.out.println("                          (min = 1 ; max = 100000000)                          ");
            System.out.print("                                         ");
            fiches_iniziali_inserite = scanner.next();
            System.out.print("\n");
            try {
                checkFichesIniziali();
                break;
            } catch (FichesInizialiException ex) {                    
                System.err.println("Errore: Il numero di fiches iniziali dev'essere un numero compreso tra 1 e "
                                 + "                                   100000000.                                   ");
            }
        }
    }
    
    /**
     * controlla le fiches iniziali della partita
     * 
     * @throws FichesInizialiException 
     */
    public void checkFichesIniziali() throws FichesInizialiException{        
            try{
                fiches_iniziali = Integer.valueOf(fiches_iniziali_inserite);
                if(fiches_iniziali < 1 || fiches_iniziali > 100000000){
                    throw new FichesInizialiException();
                }
            }catch(NumberFormatException e){
                throw new FichesInizialiException();
            }
   
    }
    
    /**
     * controlla la difficolatà dei bot
     * @throws DifficoltaBotException 
     */
    public void checkDifficoltaBot() throws DifficoltaBotException{
        if(difficolta_bot_inserita.equalsIgnoreCase("facile")){
            difficolta_bot = DifficoltaBot.Facile;
        }else if(difficolta_bot_inserita.equalsIgnoreCase("medio")){
            difficolta_bot = DifficoltaBot.Medio;
        }else if(difficolta_bot_inserita.equalsIgnoreCase("difficile")){
            difficolta_bot = DifficoltaBot.Difficile;
        } else {
            throw new DifficoltaBotException();
        }
    }
    
    /**
     * effettua un controllo sul numero di bot
     * @throws NumeroBotException 
     */
    public void checkNumeroBot() throws NumeroBotException{
        try{
            this.numero_bot = Integer.valueOf(numero_bot_inserito);
            if(numero_bot < 1 || numero_bot > 12){
                throw new NumeroBotException();
            }
        } catch(NumberFormatException e){
            throw new NumeroBotException();
           
        }
    }
}
