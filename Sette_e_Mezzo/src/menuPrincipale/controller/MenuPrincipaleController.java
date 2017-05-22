package menuPrincipale.controller;

import menuPrincipale.events.OpzioneScelta;
import dominio.view.ViewEvent;
import menuPrincipale.view.MenuPrincipaleView;
import menuPrincipale.model.MenuPrincipaleModel;
import dominio.view.ViewEventListener;


public class MenuPrincipaleController implements ViewEventListener {
    private MenuPrincipaleModel model; 
    private MenuPrincipaleView view;
    
    public MenuPrincipaleController(MenuPrincipaleModel model, MenuPrincipaleView view){
        this.model = model;
        this.view = view;
        this.view.addViewEventListener(this);
    }

    @Override
    public void ViewEventReceived(ViewEvent evt) {
       if(evt.getArg() instanceof OpzioneScelta){
            OpzioneScelta opt = (OpzioneScelta) evt.getArg();
            model.setOpzione(opt.getOpzione());
       }
    }
}
