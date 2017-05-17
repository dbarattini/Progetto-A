package menuPrepartita.view;


import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import menuPrepartita.events.SetInfo;
import menuPrepartita.events.SetInfoListener;
import menuPrepartita.model.MenuPrePartitaModel;
import modules.PartitaOfflineConsole;


public class MenuPrePartitaConsoleView implements Observer, MenuPrePartitaView {
    private final CopyOnWriteArrayList<SetInfoListener> listeners;
    private String nbot;
    private String difficolta_bot;
    private String fiches_iniziali;
    private final Scanner scanner;
    private final MenuPrePartitaModel model;
    
    public MenuPrePartitaConsoleView(MenuPrePartitaModel model){
        listeners = new CopyOnWriteArrayList<>();
        scanner = new Scanner(System.in);
        this.model = model;
        model.addObserver(this);
    }
    
    public void run(){
        System.out.println("  -----------------------------  ");
        System.out.println("<      IMPOSTAZIONI PARTITA     >");
        System.out.println("  -----------------------------  ");
        richiediNumeroBot();
        richiediDifficoltaBot();
        richiediFichesIniziali();
        fireSetInfoEvent();
        new PartitaOfflineConsole(model.getNumeroBot(), model.getDifficoltaBot(), model.getFichesIniziali());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Error){
            System.err.println(((Error) arg).getMessage() + "\n");
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            run();
        }
    }

    @Override
    public void addSetInfoListener(SetInfoListener l) {
        listeners.add(l);
    }

    @Override
    public void removeSetInfoListener(SetInfoListener l) {
        listeners.remove(l);
    }
    
    protected void fireSetInfoEvent() {
        SetInfo evt = new SetInfo(this, nbot, difficolta_bot, fiches_iniziali);

        for (SetInfoListener l : listeners) {
            l.SetInfoEventReceived(evt);
        }
    }

    private void richiediNumeroBot() {
        System.out.println("Inserisci il numero di Bot che vuoi sfidare");
        System.out.println("       (min = 1 ; max = 12)                ");
        System.out.print("                  ");
        nbot = scanner.next();
        System.out.print("\n");
    }

    private void richiediDifficoltaBot() {
        System.out.println("    Inserisci la difficoltá dei Bot   ");
        System.out.println("      (Facile, Medio, Difficile)      ");
        System.out.print("              ");
        difficolta_bot = scanner.next();
        System.out.print("\n");
    }

    private void richiediFichesIniziali() {
        System.out.println("Inserisci il numero di fiches iniziali"); 
        System.out.println("      (min = 1 ; max = 100000000)     ");
        System.out.print("              ");
        fiches_iniziali = scanner.next();
        System.out.print("\n");
    }
}
