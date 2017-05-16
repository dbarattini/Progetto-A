package partitaOffline.view;

import partitaOffline.events.SetGiocata;
import partitaOffline.events.SetPuntata;
import dominio.giocatori.Giocatore;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOffline.events.EstrattoMazziere;
import partitaOffline.events.GiocatoreLocaleEvent;
import partitaOffline.events.MazzoRimescolato;
import partitaOffline.events.Messaggio;
import partitaOffline.events.PartitaOfflineViewEvent;
import partitaOffline.events.PartitaOfflineViewEventListener;
import partitaOffline.events.RichiediGiocata;
import partitaOffline.events.RichiediNome;
import partitaOffline.events.RichiediPuntata;
import partitaOffline.events.SetNome;
import partitaOffline.model.PartitaOfflineModel;

public class PartitaOfflineConsoleView implements PartitaOfflineView, Observer{
    private final CopyOnWriteArrayList<PartitaOfflineViewEventListener> listeners;
    private PartitaOfflineModel model;
    private Scanner scanner;
    int pausa_breve = 1000; //ms
    int pausa_lunga = 2000; //ms

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
        System.out.println("\nCome ti chiami?\n");
        nome = scanner.next();        
        firePartitaOfflineViewEvent(new SetNome(nome));  
    }
    
    private void stampaSchermataEstrazioneMazziere() throws InterruptedException{
        for(Giocatore giocatore : model.getGiocatori()){
            mostra_carta_coperta_e_valore_mano(giocatore);
            Thread.sleep(pausa_breve);
        }
        stampa_messaggio_mazziere();
        Thread.sleep(pausa_lunga);
    }
    
    private void mostra_carta_coperta_e_valore_mano(Giocatore giocatore){
        System.out.println(giocatore.getNome() + " [" + giocatore.getCartaCoperta() + "] " + giocatore.getValoreMano());
    }
    
    private void stampa_messaggio_mazziere(){
        System.out.println("\nIl Mazziere é: " + model.getMazziere().getNome() + "\n");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof RichiediNome){
            richiediNome();
        }else if(arg instanceof Error){
            System.err.println(((Error) arg).getMessage());
        }else if(arg instanceof Messaggio){
            System.out.println(((Messaggio) arg).getMessaggio());
        } else if(arg instanceof EstrattoMazziere){
            try {
                stampaSchermataEstrazioneMazziere();
            } catch (InterruptedException ex) {
                Logger.getLogger(PartitaOfflineConsoleView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(arg instanceof MazzoRimescolato){
            System.out.println("\nRimescolo mazzo\n");
        }
    }

    @Override
    public void GiocatoreLocaleEventReceived(GiocatoreLocaleEvent evt) {
        if(evt.getArg() instanceof RichiediPuntata){
            richiediPuntata(evt);
        } else if(evt.getArg() instanceof Error){
            System.err.println(((Error)evt.getArg()).getMessage());
        } else if(evt.getArg() instanceof RichiediGiocata){
            richiediGiocata(evt);
        }
    }

    private void richiediPuntata(GiocatoreLocaleEvent evt) {
        System.out.println("Carta coperta: " + ((RichiediPuntata) evt.getArg()).getCarta_coperta());
        System.out.println("Valore Mano : " + ((RichiediPuntata) evt.getArg()).getValore_mano());
        System.out.println("Fiches: " + ((RichiediPuntata) evt.getArg()).getFiches());
        System.out.println("Quante fiches vuoi puntare?");
        String puntata = scanner.next();
        firePartitaOfflineViewEvent(new SetPuntata(puntata));
    }
    
    private void richiediGiocata(GiocatoreLocaleEvent evt){
        System.out.println("Valore Mano : " + ((RichiediGiocata) evt.getArg()).getValoreMano());
        System.out.println("Carta coperta: " + ((RichiediGiocata) evt.getArg()).getCartaCoperta());
        System.out.println("Carte scoperte: " + ((RichiediGiocata) evt.getArg()).getCarteScoperte());
        System.out.println("Carta o Stai?");
        String giocata = scanner.next();
        firePartitaOfflineViewEvent(new SetGiocata(giocata));
    }
    
}
