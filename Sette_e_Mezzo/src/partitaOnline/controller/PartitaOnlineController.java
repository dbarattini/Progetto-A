package partitaOnline.controller;

import dominio.classi_dati.Stato;
import dominio.elementi_di_gioco.Carta;
import partitaOffline.controller.*;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
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


public class PartitaOnlineController implements ViewEventListener, Observer{
    private PartitaOfflineModel model;
    private PartitaOfflineView view;

    public PartitaOnlineController(PartitaOfflineModel model, PartitaOfflineView view) {
        this.model = model;
        this.view = view;
        view.addPartitaOfflineViewEventListener(this);
    }
    
    public void run(){
        this.model.inizializza_partita(this.model.getN_bot(), this.model.getDifficolta_bot(), this.model.getFiches_iniziali());
        model.addGiocatoreLocaleEventListener(view);
        try {
            this.model.gioca();
        } catch (InterruptedException ex) {
            Logger.getLogger(PartitaOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ViewEventReceived(ViewEvent evt) {
        if(evt.getArg() instanceof SetNome){
            model.setNomeGiocatore(((SetNome)evt.getArg()).getNome());
        } else if(evt.getArg() instanceof SetPuntata){
        model.getGiocatoreLocale().PuntataInserita(((SetPuntata)evt.getArg()).getPuntata());
        } else if(evt.getArg() instanceof SetGiocata){
            model.getGiocatoreLocale().GiocataInserita(((SetGiocata)evt.getArg()).getGiocata());
        }
    }   

    @Override
    public void update(Observable o, Object arg) {
        String messaggio=arg.toString();
        String dati[]=messaggio.split("\t");
        Object ritorno;
        if(dati[0].equals("evento")){
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
        }

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
    
}
