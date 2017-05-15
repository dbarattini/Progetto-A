package menuPrepartita.controller;

import menuPrepartita.events.SetInfo;
import menuPrepartita.events.SetInfoListener;
import menuPrepartita.model.MenuPrePartitaModel;
import menuPrepartita.view.MenuPrePartitaView;

public class MenuPrePartitaController implements SetInfoListener {
    private final MenuPrePartitaModel model;
    private final MenuPrePartitaView view;

    public MenuPrePartitaController(MenuPrePartitaModel model, MenuPrePartitaView view) {
        this.model = model;
        this.view = view;
        view.addSetInfoListener(this);    
    }

    @Override
    public void SetInfoEventReceived(SetInfo evt) {
        model.SetSetting(evt.getNbot(), evt.getDifficoltaBot(), evt.getFichesIniziali());
    }
    
    
    
}
