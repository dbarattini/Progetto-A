package menuRegole;

import java.util.Scanner;
import menuRegole.Regole;

public class RegoleConsole {
    private Regole regole;
    Scanner scanner = new Scanner(System.in);
    
    public RegoleConsole(){
        this.regole = new Regole();
    }
    
    public void run(){
        System.out.println("\nRegole di gioco:\n" + regole.getRegole());
        scanner.nextLine();
    }
}
