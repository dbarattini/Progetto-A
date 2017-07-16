package partitaOffline.controller;

import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import dominio.events.SetGiocata;
import dominio.events.SetNome;
import dominio.events.SetPuntata;
import partitaOffline.model.PartitaOfflineModel;
import partitaOffline.view.PartitaOfflineView;


public class PartitaOfflineController implements ViewEventListener{
    private PartitaOfflineModel model;
    private PartitaOfflineView view;

    /**
     * 
     * @param model modello della partita offline
     * @param view view della partita offline
     */
    public PartitaOfflineController(PartitaOfflineModel model, PartitaOfflineView view) {
        this.model = model;
        this.view = view;
        this.view.addPartitaOfflineViewEventListener(this);
    }
    
    /**
     * lancia la partita
     */
    public void run(){
        this.model.inizializza_partita();
        model.addGiocatoreLocaleEventListener(view);
        this.model.gioca();
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
    
}