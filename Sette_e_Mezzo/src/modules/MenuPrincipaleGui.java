package modules;

import menuPrincipale.controller.MenuPrincipaleController;
import menuPrincipale.model.MenuPrincipaleModel;
import menuPrincipale.view.MenuPrincipaleGuiView;



public class MenuPrincipaleGui {
    private final MenuPrincipaleModel model;
    private final MenuPrincipaleGuiView view;
    private final MenuPrincipaleController controller;
    
    public MenuPrincipaleGui() {
        model = new MenuPrincipaleModel();
        view = new MenuPrincipaleGuiView(model);
        controller = new MenuPrincipaleController(model, view);
    }
    
    
}
