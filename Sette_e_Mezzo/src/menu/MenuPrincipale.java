
package menu;

import classi_dati.OpzioniMenu;
import java.util.Scanner;


public class MenuPrincipale {
    
     OpzioniMenu opzione;
 
     public MenuPrincipale(){
        
        }
    
    public OpzioniMenu selezionaOpzione(){
        
        printScelte();
        return richiediOpzione();
    }
           
    public void printScelte(){
        
     System.out.println("\n");   
     System.out.println("SELEZIONA UN OPZIONE DAL MENU");
     System.out.println("1. GiocaOffline");
     System.out.println("2. GiocaOnline ");
     System.out.println("3. Impostazioni");
     System.out.println("4. RegoleDiGioco");
    }
    
    private OpzioniMenu richiediOpzione(){
        OpzioniMenu opzione = null;
        Scanner scanner = new Scanner(System.in); 
        while(true){
            String input = scanner.next();
            try{
                  opzione = OpzioniMenu.valueOf(input);
                  break;
                } catch (IllegalArgumentException exc){
                      System.out.println("OPZIONE NON VALIDA");   
                      this.printScelte();
                }
        }
        return opzione;
    }
    
}

   
