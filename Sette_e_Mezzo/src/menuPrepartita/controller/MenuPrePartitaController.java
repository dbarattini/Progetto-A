package menuPrepartita.controller;

import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import menuPrepartita.events.SetInfo;
import menuPrepartita.model.MenuPrePartitaModel;
import menuPrepartita.view.MenuPrePartitaView;

public class MenuPrePartitaController implements ViewEventListener{
    private final MenuPrePartitaModel model;
    private final MenuPrePartitaView view;

    public MenuPrePartitaController(MenuPrePartitaModel model, MenuPrePartitaView view) {
        this.model = model;
        this.view = view;
        view.addViewEventListener(this);    
    }

    @Override
    public void ViewEventReceived(ViewEvent evt) {
        if(evt.getArg() instanceof SetInfo){
            SetInfo info = (SetInfo) evt.getArg();
            model.SetSetting(info.getNbot(), info.getDifficoltaBot(), info.getFichesIniziali());
        }
    }
    
    
    
}
