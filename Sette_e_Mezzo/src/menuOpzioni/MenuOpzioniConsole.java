package menuOpzioni;

import dominio.classi_dati.ImpostazioniMenu;
import dominio.eccezioni.CanzoneNonTrovataException;
import dominio.eccezioni.CaricamentoCanzoneException;
import java.util.Scanner;


public class MenuOpzioniConsole {
    
    private ImpostazioniMenu impostazioni;
    private Musica musica;
    private Riconoscimenti riconoscimenti;
    private Profilo profilo;
    private boolean indietro = false;

    public MenuOpzioniConsole() {
        this.musica = new Musica();
        this.profilo = new Profilo();
        this.riconoscimenti = new Riconoscimenti();
    }
    
    /**
     * Stampa le impostazioni da scegliere, inizializza a null il valore di impostazioni (enum) e poi lo setta in base al valore inserito da terminale
     * 
     */
    
    public void run(){
        indietro = false;
        while(true) {
            printImpostazioni();
            impostazioni = null;
            impostazioni = richiediImpostazione();
        
            try {
                
                switch(impostazioni) {
                
                    case Musica: 
                        try {
                            try {
                                musica.run();
                            } catch (CanzoneNonTrovataException ex) {
                                System.err.println("Errore: Impossibile trovare la canzone.");
                            } catch (CaricamentoCanzoneException ex) {
                                System.err.println("Errore: Impossibile caricare la canzone.");
                            }
                        } catch (InterruptedException ex) {

                        }
                        break;
                    case Profilo:
                        profilo.selezionaProfilo();
                        break;
                    case Riconoscimenti:
                        riconoscimenti.printRiconoscimenti();
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
    
    private void printImpostazioni() {
        
        System.out.println("\n");
        System.out.println("  ---------------------------------------------------------------------------  ");
        System.out.println("                       < SELEZIONA UN OPZIONE DAL MENU >                       ");
        System.out.println("  ---------------------------------------------------------------------------  ");
        System.out.println("                                1. Musica                                      ");
        System.out.println("                                2. Profilo                                     ");
        System.out.println("                                3. Riconoscimenti                              ");
        System.out.println("                                4. Indietro                                    ");
    }
    
    private ImpostazioniMenu richiediImpostazione() {
        ImpostazioniMenu impostazioni_prov = null;
        Scanner scanner = new Scanner(System.in); 
        System.out.print("                                         ");
        String input = scanner.next();
        System.out.print("\n");
            
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
                if (input.equals("4") || input.toLowerCase().equals("indietro")){
                    impostazioni_prov = ImpostazioniMenu.Indietro;
                }
            }   
        catch (IllegalArgumentException ex){
                System.err.println("Errore: La scelta effettuata non é valida.\n");
            }
        
        return impostazioni_prov;
    }
}
