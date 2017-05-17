package menuPrincipale.view;

import dominio.classi_dati.OpzioniMenu;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import menuPrincipale.events.OpzioneScelta;
import menuPrincipale.events.ViewEvent;
import menuPrincipale.model.MenuPrincipaleModel;
import modules.MenuPrePartitaConsole;
import modules.RegoleConsole;
import menuPrincipale.events.ViewEventListener;


public class MenuPrincipaleConsoleView implements MenuPrincipaleView, Observer{
    private final CopyOnWriteArrayList<ViewEventListener> listeners;
    private String opzione;
    private MenuPrincipaleModel model;
    
    public MenuPrincipaleConsoleView(MenuPrincipaleModel model){
        listeners = new CopyOnWriteArrayList<>();
        this.model = model;
        model.addObserver(this);
    }
    
    public void run(){
        printScelte();
        richiediOpzione();
    }
    
    private void printScelte(){  
     System.out.println("  -----------------------------  ");
     System.out.println("< SELEZIONA UN OPZIONE DAL MENU >");
     System.out.println("  -----------------------------  ");
     System.out.println("         1. GiocaOffline         ");
     System.out.println("         2. GiocaOnline          ");
     System.out.println("         3. Impostazioni         ");
     System.out.println("         4. RegoleDiGioco        ");
     System.out.print("\n");
     System.out.print("            ");
    }
    
    private void richiediOpzione(){
        Scanner scanner = new Scanner(System.in); 
        String input = scanner.next();
        System.out.print("\n");
        opzione = input;
        fireViewEvent();
    }

    @Override
    public void addViewEventListener(ViewEventListener l) {
        listeners.add(l);
    }

    @Override
    public void removeViewEventListener(ViewEventListener l) {
        listeners.remove(l);
    }
    
    protected void fireViewEvent() {
        ViewEvent evt = new ViewEvent(this, new OpzioneScelta(opzione));

        for (ViewEventListener l : listeners) {
            l.ViewEventReceived(evt);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Error){
            System.err.println("ERRORE: "+ ((Error) arg).getMessage() + "\n");
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                
            }
            this.run(); 
        } else{
            OpzioniMenu opzione = (OpzioniMenu) arg;
            runOpzione(opzione);
            this.run();
        }
    }
    
    private void runOpzione(OpzioniMenu opzione){
        switch(opzione){
            
            case GiocaOffline: new MenuPrePartitaConsole();
                               break;
            case GiocaOnline : break;
            case Impostazioni: break;
            case RegoleDiGioco: new RegoleConsole();
                                break;
        }
    }
}
