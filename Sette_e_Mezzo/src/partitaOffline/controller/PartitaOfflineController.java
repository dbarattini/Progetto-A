package partitaOffline.controller;

import partitaOffline.events.PartitaOfflineViewEvent;
import partitaOffline.events.PartitaOfflineViewEventListener;
import partitaOffline.events.SetNome;
import partitaOffline.model.PartitaOfflineModel;
import partitaOffline.view.PartitaOfflineView;


public class PartitaOfflineController implements PartitaOfflineViewEventListener{
    private PartitaOfflineModel model;
    private PartitaOfflineView view;

    public PartitaOfflineController(PartitaOfflineModel model, PartitaOfflineView view) {
        this.model = model;
        this.view = view;
        view.addPartitaOfflineViewEventListener(this);
    }

    @Override
    public void PartitaOfflineViewEventReceived(PartitaOfflineViewEvent evt) {
        if(evt.getArg() instanceof SetNome){
            model.setNomeGiocatore(((SetNome)evt.getArg()).getNome());
        }
    }
    
    
    
}
