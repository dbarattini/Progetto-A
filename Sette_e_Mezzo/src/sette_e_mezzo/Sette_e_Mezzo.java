package sette_e_mezzo;

import menuPrincipale.controller.MenuPrincipaleController;
import menuPrincipale.model.MenuPrincipaleModel;
import menuPrincipale.view.MenuPrincipaleGuiView;


public class Sette_e_Mezzo {
    public static void main(String[] args) {
        MenuPrincipaleModel model = new MenuPrincipaleModel();
        MenuPrincipaleGuiView view = new MenuPrincipaleGuiView(model);
        MenuPrincipaleController controller = new MenuPrincipaleController(model, view);
    }
}
