package sette_e_mezzo;

import menuPrincipale.controller.MenuPrincipaleController;
import menuPrincipale.model.MenuPrincipaleModel;
import menuPrincipale.view.MenuPrincipaleConsoleView;


public class Sette_e_Mezzo_Textual {
    public static void main(String[] args) {
        MenuPrincipaleModel model = new MenuPrincipaleModel();
        MenuPrincipaleConsoleView view = new MenuPrincipaleConsoleView(model);
        MenuPrincipaleController controller = new MenuPrincipaleController(model, view);
        
        view.run();
    }
}
