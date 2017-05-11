
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
            
        annuncio();
        richiediOpzione();
        
        try{ 
            switch(opzione){
          
                case GiocaOffline :
                    PartitaOffline partita = new PartitaOffline(3, 100, DifficoltaBot.Facile, System.in, System.out);
                    break;
                case GiocaOnline :
                    System.out.println("OPZIONE NON ANCORA DISPONIBILE");
                    break;
                case Impostazioni :
                    System.out.println("OPZIONE NON ANCORA DISPONIBILE");
                    break;
                case RegoleDiGioco :
                    System.out.println("OPZIONE NON ANCORA DISPONIBILE");
                    break;
                }   
            }catch (NullPointerException e){
        }
      }
    }
        
    
    private void annuncio(){
        
     out.println("\n");   
     out.println("SELEZIONA UN OPZIONE DAL MENU");
     out.println("le opzioni sono: ");
     out.println("1. GiocaOffline");
     out.println("2. GiocaOnline ");
     out.println("3. Impostazioni");
     out.println("4. RegoleDiGioco");
    }
    
    private OpzioniMenu richiediOpzione(){
       
        Scanner scanner = new Scanner(in);        
        String input = scanner.next();
        try{
              opzione = OpzioniMenu.valueOf(input);
              } catch (IllegalArgumentException exc){
                  System.out.println("OPZIONE NON VALIDA");   
                  }
         return opzione;
        }
    
}

   
