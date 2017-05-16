package partitaOffline.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import partitaOffline.events.Messaggio;
import partitaOffline.events.PartitaOfflineViewEvent;
import partitaOffline.events.PartitaOfflineViewEventListener;
import partitaOffline.events.RichiediNome;
import partitaOffline.events.SetNome;
import partitaOffline.model.PartitaOfflineModel;

public class PartitaOfflineConsoleView implements PartitaOfflineView, Observer{
    private final CopyOnWriteArrayList<PartitaOfflineViewEventListener> listeners;
    private PartitaOfflineModel model;
    private Scanner scanner;

    public PartitaOfflineConsoleView(PartitaOfflineModel model) {
        this.listeners = new CopyOnWriteArrayList<>();
        this.model = model;
        this.model.addObserver(this);
        scanner = new Scanner(System.in);
    }

    @Override
    public void addPartitaOfflineViewEventListener(PartitaOfflineViewEventListener l) {
        listeners.add(l);
    }

    @Override
    public void removePartitaOfflineViewEventListener(PartitaOfflineViewEventListener l) {
        listeners.remove(l);
    }

    protected void firePartitaOfflineViewEvent(Object arg) {
        PartitaOfflineViewEvent evt = new PartitaOfflineViewEvent(this, arg);

        for (PartitaOfflineViewEventListener l : listeners) {
            l.PartitaOfflineViewEventReceived(evt);
        }
    }
    
    private void richiediNome(){
        String nome;
        System.out.println("Come ti chiami?");
        nome = scanner.next();        
        firePartitaOfflineViewEvent(new SetNome(nome));  
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof RichiediNome){
            richiediNome();
        }else if(arg instanceof Error){
            System.err.println(((Error) arg).getMessage());
        }else if(arg instanceof Messaggio){
            System.out.println(((Messaggio) arg).getMessaggio());
        }
    }
    
}
