package modules;

import menuPrincipale.controller.MenuPrincipaleController;
import menuPrincipale.model.MenuPrincipaleModel;
import menuPrincipale.view.MenuPrincipaleConsoleView;


public class MenuPrincipaleConsole {
    private final MenuPrincipaleModel model;
    private final MenuPrincipaleConsoleView view;
    private final MenuPrincipaleController controller;

    public MenuPrincipaleConsole() {
        model = new MenuPrincipaleModel();
        view = new MenuPrincipaleConsoleView(model);
        controller = new MenuPrincipaleController(model, view);
        
        view.run();
    }
    
    
}
