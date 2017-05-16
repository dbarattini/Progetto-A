package partitaOffline.controller;

import dominio.classi_dati.DifficoltaBot;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void run(){
        this.model.inizializza_partita(this.model.getN_bot(), this.model.getDifficolta_bot(), this.model.getFiches_iniziali());
        model.addGiocatoreLocaleEventListener(view);
        try {
            this.model.gioca();
        } catch (InterruptedException ex) {
            Logger.getLogger(PartitaOfflineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void PartitaOfflineViewEventReceived(PartitaOfflineViewEvent evt) {
        if(evt.getArg() instanceof SetNome){
            model.setNomeGiocatore(((SetNome)evt.getArg()).getNome());
        }
    }
    
    
    
}
