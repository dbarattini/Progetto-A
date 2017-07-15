package menuOpzioni;

import database.SQL;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Max & family
 */
public class Profilo {
    
    private SQL db;
    

    public Profilo() {
         this.db = new SQL();
    }

    /**
     * sceglie il profilo
     */
    public void selezionaProfilo() {
        while(true) {
            printOpzioniProfilo();
            scegliProfilo();
        }
    }
    
    private void printOpzioniProfilo() {
        out.println("\n");
        out.println("Inserire nome dell'utente per visualizzare il profilo:\n");
    }
    
    private void scegliProfilo() {
        Scanner scanner = new Scanner(in);        
        String input = scanner.next();
        
        if (db.esisteNome(input)) {
            System.out.println("PROFILO:\n");
            System.out.println("  -NOME: " +input);
            System.out.println("  -FICHES: " +db.getFiches(input));
            System.out.println("  -VITTORIE: " +db.getVittorie(input));
        } else
            System.out.println("Profilo non esistente\n");
    }  
}
