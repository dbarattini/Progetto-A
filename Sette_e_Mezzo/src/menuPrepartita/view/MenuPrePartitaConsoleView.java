package menuPrepartita.view;


import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import menuPrepartita.events.SetInfo;
import menuPrepartita.events.SetInfoListener;
import menuPrepartita.model.MenuPrePartitaModel;


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
        richiediNumeroBot();
        richiediDifficoltaBot();
        richiediFichesIniziali();
        fireSetInfoEvent();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Error){
            System.err.println(((Error) arg).getMessage());
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
        System.out.println("Inserisci il numero di Bot che vuoi sfidare (min = 1 ; max = 12)");
        nbot = scanner.next();
    }

    private void richiediDifficoltaBot() {
        System.out.println("Inserisci la difficoltá dei Bot (Facile, Medio, Difficile)");
        difficolta_bot = scanner.next();
    }

    private void richiediFichesIniziali() {
        System.out.println("Inserisci il numero di fiches iniziali (min = 1 ; max = 100000000)");
        fiches_iniziali = scanner.next();
    }
}
