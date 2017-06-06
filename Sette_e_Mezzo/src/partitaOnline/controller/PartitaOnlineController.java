package partitaOnline.controller;

import partitaOffline.controller.*;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOffline.events.SetGiocata;
import partitaOffline.events.SetNome;
import partitaOffline.events.SetPuntata;
import partitaOffline.model.PartitaOfflineModel;
import partitaOffline.view.PartitaOfflineView;


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
    }
    
}
