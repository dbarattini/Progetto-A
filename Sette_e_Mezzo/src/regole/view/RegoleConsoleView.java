package regole.view;

import java.util.Scanner;
import regole.model.RegoleModel;

public class RegoleConsoleView {
    private RegoleModel model;
    Scanner scanner = new Scanner(System.in);
    
    public RegoleConsoleView(RegoleModel model){
        this.model = model;
    }
    
    public void run(){
        System.out.println("\n" + model.getRegole());
        scanner.nextLine();
    }
}
