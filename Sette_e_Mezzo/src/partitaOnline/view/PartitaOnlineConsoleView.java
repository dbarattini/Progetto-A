package partitaOnline.view;

import partitaOnline.events.SetGiocata;
import partitaOnline.events.SetPuntata;
import dominio.giocatori.GiocatoreOnline;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOnline.events.*;
import partitaOnline.controller.PartitaOnlineController;

public class PartitaOnlineConsoleView implements  Observer{
    private final CopyOnWriteArrayList<ViewEventListener> listeners;
    private PartitaOnlineController controller;
    private Scanner scanner;
    int pausa_breve = 1000; //ms
    int pausa_lunga = 2000; //ms

    public PartitaOnlineConsoleView(PartitaOnlineController controller) {
        this.listeners = new CopyOnWriteArrayList<>();
        this.controller = controller;
        this.controller.addObserver(this);
        scanner = new Scanner(System.in);
        listeners.add(controller);
 
    }

    protected void fireViewEvent(Object arg) {
        ViewEvent evt = new ViewEvent(this, arg);

        for (ViewEventListener l : listeners) {
            l.ViewEventReceived(evt);
        }
    }
    
    private void stampaSchermataEstrazioneMazziere(){
        System.out.println("  -----------------------------  ");
        System.out.println("<      ESTRAZIONE MAZZIERE      >");
        System.out.println("  -----------------------------  \n");
        try {
            Thread.sleep(pausa_breve);
        } catch (InterruptedException ex) {
        }
        for(GiocatoreOnline giocatore : controller.getGiocatori()){
            mostra_carta_coperta_e_valore_mano(giocatore);
            try {
                Thread.sleep(pausa_breve);
            } catch (InterruptedException ex) {
            }
        }
        stampa_messaggio_mazziere();
        System.out.println("---------------------------------"); 
        try {
            Thread.sleep(pausa_lunga);
        } catch (InterruptedException ex) {
        }
    }
    
    private void mostra_carta_coperta_e_valore_mano(GiocatoreOnline giocatore){
        System.out.println(giocatore.getNome() + " [" + giocatore.getCartaCoperta() + "] " + giocatore.getValoreMano());
    }
    
    private void stampa_messaggio_mazziere(){
        System.out.println("\n--> il Mazziere é: " + controller.getMazziere().getNome() + " <--");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Error){
            System.err.println(((Error) arg).getMessage());
        } else if(arg instanceof EstrattoMazziere){
            stampaSchermataEstrazioneMazziere();
        } else if(arg instanceof MazzoRimescolato){
            stampaSchermataRimescolaMazzo();
        } else if(arg instanceof RisultatoManoParticolare){
            stampaSchermataManoParticolare();
        } else if(arg instanceof FineManoAvversario){
            FineManoAvversario avversario = (FineManoAvversario) arg;
            System.out.println(avversario.getNome() + " " + avversario.getCarteScoperte() + " " + avversario.getStato() + " " + avversario.getPuntata());
        } else if(arg instanceof FineRound){
            if(((FineRound) arg).equals(controller.getGiocatori().get(0))){
                System.out.print("\n");
            }
            System.out.println(((FineRound) arg).isMazziere() + " " + ((FineRound) arg).getNome() + " " + ((FineRound) arg).getCartaCoperta()+((FineRound) arg).getCarteScoperte() + " " + ((FineRound) arg).getValoreMano() + " "+ ((FineRound) arg).getStato() + " " + ((FineRound) arg).getFiches());
            if(((FineRound) arg).equals(controller.getGiocatori().get(controller.getGiocatori().size() - 1))){
                System.out.println("---------------------------------\n");
            }
        
        } else if(arg instanceof MazzierePerde){
            System.out.println("\n");
            System.out.println("--> Il mazziere ha perso <--");
        } else if(arg instanceof AggiornamentoMazziere){
            System.out.println("\n");
            System.out.println("--> il nuovo mazziere é: " + controller.getMazziere().getNome() + " <--\n");
        } else if(arg instanceof GameOver){
            System.out.println("\n");
            System.out.println("--> Game Over <--");
        } else if(arg instanceof Vittoria){
            System.out.println("\n");
            System.out.println("--> Complimenti! Hai sconfitto tutti i bot <--");
        }
        else if(arg instanceof RichiediPuntata){
            richiediPuntata(arg);
        } else if(arg instanceof Error){
            System.err.println(((Error)arg).getMessage());
        } else if(arg instanceof RichiediGiocata){
            richiediGiocata(arg);
        }
    }

    private void richiediPuntata(Object richiediPuntata) {
        System.out.print("\n");
        System.out.println("Carta coperta: " + ((RichiediPuntata) richiediPuntata).getCarta_coperta());
        System.out.println("Valore Mano : " + ((RichiediPuntata) richiediPuntata).getValore_mano());
        System.out.println("Fiches: " + ((RichiediPuntata) richiediPuntata).getFiches());
        System.out.println("Quante fiches vuoi puntare?");
        String puntata = scanner.next();
        controller.riceviEventoDaVista(new SetPuntata(puntata));
        System.out.print("\n");
    }
    
    private void richiediGiocata(Object richiediGiocata){
        System.out.print("\n");
        System.out.println("Valore Mano : " + ((RichiediGiocata) richiediGiocata).getValoreMano());
        System.out.println("Carta coperta: " + ((RichiediGiocata) richiediGiocata).getCartaCoperta());
        System.out.println("Carte scoperte: " + ((RichiediGiocata) richiediGiocata).getCarteScoperte());
        System.out.println("Carta o Stai?");
        String giocata = scanner.next();
        controller.riceviEventoDaVista(new SetGiocata(giocata));
        System.out.print("\n");
    }

    private void stampaSchermataRimescolaMazzo() {
        System.out.println("\n--> Rimescolo il mazzo <--\n");
        try {
            Thread.sleep(pausa_lunga);
        } catch (InterruptedException ex) {
            Logger.getLogger(PartitaOnlineConsoleView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void stampaSchermataManoParticolare() {
        System.out.println("Carta Ottenuta: " + controller.getGiocatoreLocale().getUltimaCartaOttenuta());
        System.out.println("Valore Mano: " + controller.getGiocatoreLocale().getValoreMano());
        System.out.println("--> " + controller.getGiocatoreLocale().getStato() + " <--");
        System.out.print("\n");
    }

    
    
}
