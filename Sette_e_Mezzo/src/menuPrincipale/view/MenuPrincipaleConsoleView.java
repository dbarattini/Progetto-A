package menuPrincipale.view;

import menuPrincipale.view.MenuPrincipaleView;
import classi_dati.OpzioniMenu;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import menuPrincipale.events.OpzioneScelta;
import menuPrincipale.events.OpzioneSceltaListener;
import menuPrincipale.events.SceltaNonValida;
import menuPrincipale.model.MenuPrincipaleModel;
import modules.RegoleConsole;


public class MenuPrincipaleConsoleView implements MenuPrincipaleView, Observer{
    private final CopyOnWriteArrayList<OpzioneSceltaListener> listeners;
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
     System.out.println("\n");   
     System.out.println("SELEZIONA UN OPZIONE DAL MENU");
     System.out.println("1. GiocaOffline");
     System.out.println("2. GiocaOnline ");
     System.out.println("3. Impostazioni");
     System.out.println("4. RegoleDiGioco");
    }
    
    private void richiediOpzione(){
        Scanner scanner = new Scanner(System.in); 
        String input = scanner.next();
        opzione = input;
        fireOpzioneSceltaEvent();
    }

    @Override
    public void addOpzioneSceltaListener(OpzioneSceltaListener l) {
        listeners.add(l);
    }

    @Override
    public void removeOpzioneSceltaListener(OpzioneSceltaListener l) {
        listeners.remove(l);
    }
    
    protected void fireOpzioneSceltaEvent() {
        OpzioneScelta evt = new OpzioneScelta(this, opzione);

        for (OpzioneSceltaListener l : listeners) {
            l.OpzioneSceltaEventReceived(evt);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof SceltaNonValida){
            System.err.println("Errore: La scelta effettuata non é valida.");
            run();
        } else{
            OpzioniMenu opzione = (OpzioniMenu) arg;
            switch(opzione){
                case GiocaOffline: break;
                case GiocaOnline : break;
                case Impostazioni: break;
                case RegoleDiGioco: RegoleConsole regoleConsole = new RegoleConsole();
                                    break;
            }
            run();
        }
    }
}
