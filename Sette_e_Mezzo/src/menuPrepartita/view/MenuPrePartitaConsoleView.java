package menuPrepartita.view;


import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import menuPrepartita.events.SetInfo;
import menuPrepartita.model.MenuPrePartitaModel;
import modules.PartitaOfflineConsole;


public class MenuPrePartitaConsoleView implements Observer, MenuPrePartitaView {
    private final CopyOnWriteArrayList<ViewEventListener> listeners;
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
        fireViewEvent();
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
    public void addViewEventListener(ViewEventListener l) {
        listeners.add(l);
    }

    @Override
    public void removeViewEventListener(ViewEventListener l) {
        listeners.remove(l);
    }
    
    protected void fireViewEvent() {
        ViewEvent evt = new ViewEvent(this, new SetInfo(nbot, difficolta_bot, fiches_iniziali));

        for (ViewEventListener l : listeners) {
            l.ViewEventReceived(evt);
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
