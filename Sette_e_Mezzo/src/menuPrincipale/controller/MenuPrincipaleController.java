package menuPrincipale.controller;

import menuPrincipale.events.OpzioneScelta;
import menuPrincipale.events.OpzioneSceltaListener;
import menuPrincipale.view.MenuPrincipaleView;
import menuPrincipale.model.MenuPrincipaleModel;


public class MenuPrincipaleController implements OpzioneSceltaListener {
    private MenuPrincipaleModel model; 
    private MenuPrincipaleView view;
    public MenuPrincipaleController(MenuPrincipaleModel model, MenuPrincipaleView view){
        this.model = model;
        this.view = view;
        this.view.addOpzioneSceltaListener(this);
    }

    @Override
    public void OpzioneSceltaEventReceived(OpzioneScelta evt) {
        model.setOpzione(evt.getOpzione());
    }
}
