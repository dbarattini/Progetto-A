package menuRegole;

import java.util.Scanner;
import menuRegole.Regole;

public class RegoleConsole {
    private Regole regole;
    Scanner scanner = new Scanner(System.in);
    
    public RegoleConsole(){
        this.regole = new Regole();
    }
    
    /**
     * stampa le regole di gioco
     */
    public void run(){
        System.out.println("\n  ---------------------------------------------------------------------------  ");
        System.out.println("                             < REGOLE DI GIOCO >                               ");
        System.out.println("  ---------------------------------------------------------------------------  \n");
        System.out.println(regole.getRegole());
        scanner.nextLine();
    }
}
