
package gioco;

import classi_dati.DifficoltaBot;
import classi_dati.OpzioniMenu;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.Scanner;


public class Menu {
    
     OpzioniMenu opzione;
 
     public Menu(){
        
        }
    
    public void selezionaOpzione() throws InterruptedException {
        
        while(true){  
            printScelte();
            opzione=richiediOpzione();
        
      }
    }

    public OpzioniMenu getOpzione() {
        return opzione;
    }
           
    public void printScelte(){
        
     out.println("\n");   
     out.println("SELEZIONA UN OPZIONE DAL MENU");
     out.println("le opzioni sono: ");
     out.println("1. GiocaOffline");
     out.println("2. GiocaOnline ");
     out.println("3. Impostazioni");
     out.println("4. RegoleDiGioco");
    }
    
    private OpzioniMenu richiediOpzione(){
       OpzioniMenu opzione=null;
        Scanner scanner = new Scanner(in);        
        String input = scanner.next();
        try{
              opzione = OpzioniMenu.valueOf(input);
              } catch (IllegalArgumentException exc){
                  System.out.println("OPZIONE NON VALIDA");   
                  richiediOpzione();
               }
         return opzione;
        }
    
    private int richiediNbot() {
        
        System.out.println("\n");
        System.out.println("Quanti bot vuoi affrontare?");
        
        Scanner scanner = new Scanner(in);
        int n_bot;
        n_bot = scanner.nextInt();
        
        return n_bot;
    }
    
    private int richiediFichesIniziali(){
        System.out.println("\n");
        System.out.println("Con quante fiches vuoi iniziare?");
        
        Scanner scanner = new Scanner(in);
        int fiches_iniziali;
        fiches_iniziali = scanner.nextInt();
        
        return fiches_iniziali;
        
    }
    
}

   
