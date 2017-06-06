package partitaOnline.controller;

import comunicazione.Leggi;
import dominio.classi_dati.Stato;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import dominio.giocatori.GiocatoreOnline;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOffline.events.SetGiocata;
import partitaOffline.events.SetNome;
import partitaOffline.events.SetPuntata;
import partitaOffline.model.PartitaOfflineModel;
import partitaOffline.view.PartitaOfflineView;
import partitaOnline.events.*;


public class PartitaOnlineController extends Observable implements ViewEventListener, Observer{
    private PartitaOfflineView view;
    private Leggi leggi;
    private ArrayList<GiocatoreOnline> giocatori=new ArrayList<>();
    private PrintWriter aServer;
    private String nomeLocale;

    public PartitaOnlineController( PartitaOfflineView view, Socket socket) {
        try {
            
            this.view = view;
            view.addPartitaOfflineViewEventListener(this);
            this.leggi=new Leggi(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            this.aServer = new PrintWriter(socket.getOutputStream(), true);
            leggi.addObserver(this);
            Thread t = new Thread(leggi);
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(PartitaOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        
    }

    @Override
    public void ViewEventReceived(ViewEvent evt) {
        if(evt.getArg() instanceof SetPuntata){
            aServer.println(((SetPuntata)evt.getArg()).toString());
        } else if(evt.getArg() instanceof SetGiocata){
            aServer.println(((SetGiocata)evt.getArg()).toString());;
        }
    }   

    @Override
    public void update(Observable o, Object arg) {
        String messaggio=arg.toString();
        String dati[]=messaggio.split("\t");
        
        if(dati[0].equals("evento")){
            gestisciEvento(dati);
        }
        else if(dati[0].equals("setta")){
            gestisciSettaggio(dati);
        }
        else if(dati[0].equals("cambia")){
            gestisciCambiamento(dati);
        }

    }
    
    private void gestisciCambiamento(String[] dati) {
        switch (dati[1]){
            case "NuovoGiocatore":
                String componenti[]=dati[2].split(" ");
                giocatori.add(new GiocatoreOnline(componenti[0], Integer.valueOf(componenti[1])));
                break;
            case "CartaCoperta":
                componenti=dati[2].split(" ");
                giocatoreDaNome(componenti[0]).setCartaCoperta(new Carta(componenti[1].substring(0, 1), componenti[2].substring(1, 2)));
                break;
            case "GiocatoreDisconnesso":
                giocatori.remove(giocatoreDaNome(dati[2]));
                break
        }
    }

    private void gestisciSettaggio(String[] dati) {
        if(dati[1].equals("Nome"))
            this.nomeLocale=dati[2];
    }

    private void gestisciEvento(String[] dati) throws NumberFormatException {
        Object ritorno = null;
        switch (dati[1]){
            case "Error":
                ritorno=new Error(dati[2]);
                break;
            case "EstrattoMazziere":
                ritorno= new EstrattoMazziere();
                break;
            case "MazzoRimescolato":
                ritorno= new MazzoRimescolato();
                break;
            case "RisultatoManoParticolare":
                ritorno= new RisultatoManoParticolare();
                break;
            case "FineManoAvversario":
                ritorno=fineManoAvversario(dati);
                break;
            case "FineRound":
                ritorno=fineRound(dati);
                break;
            case "MazzierePerde":
                ritorno= new MazzierePerde();
                break;
            case "AggiornamentoMazziere":
                ritorno= new AggiornamentoMazziere();
                break;
            case "GameOver":
                ritorno= new GameOver();
                break;
                
        }
        this.setChanged();
        this.notifyObservers(ritorno);
    }

    private Object fineRound(String[] dati) throws NumberFormatException {
        Object ritorno;
        int i=1;
        String componentiFR[]=dati[2].split(" ");
        String nome=componentiFR[0];
        Carta cartaCoperta=new Carta(componentiFR[i].substring(0, 1),componentiFR[i].substring(1, 2));
        ArrayList<Carta> carteScoperte = null;
        for(i=2; ;i++){
            if(componentiFR[i].equals("fineCarte"))
                break;
            else
                carteScoperte.add(new Carta(componentiFR[i].substring(0, 1),componentiFR[i].substring(1, 2)));
        }
        i++;
        int fiches= Integer.valueOf(componentiFR[i]);
        i++;
        boolean isMazziere=Boolean.getBoolean(componentiFR[i]);
        i++;
        int puntata=0;
        if(!isMazziere) puntata=Integer.valueOf(componentiFR[i]);
        return ritorno= new FineRound(nome,cartaCoperta, carteScoperte, fiches,isMazziere, puntata);
    }

    private Object fineManoAvversario(String[] dati) throws NumberFormatException {
        Object ritorno;
        int i=1;
        String componenti[]=dati[2].split(" ");
        String nome=componenti[0];
        ArrayList<Carta> carteScoperte = null;
        for(i=1; ;i++){
            if(componenti[i].equals("fineCarte"))
                break;
            else
                carteScoperte.add(new Carta(componenti[i].substring(0, 1),componenti[i].substring(1, 2)));
        }
        i++;
        Stato stato=Stato.valueOf(componenti[i]);
        i++;
        int puntata= Integer.valueOf(componenti[i]);
        return ritorno= new FineManoAvversario(nome, carteScoperte, stato, puntata);
    }

    private GiocatoreOnline giocatoreDaNome(String nome){
        for(GiocatoreOnline giocatore :giocatori){
            if(nome.equals(giocatore.getNome()))
                return giocatore;
        }
        return null;
    }
    public GiocatoreOnline getGiocatoreLocale() {
        return giocatoreDaNome(nomeLocale);
    }

    public ArrayList<GiocatoreOnline> getGiocatori() {
        return giocatori;
    }

    public Giocatore getMazziere() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
