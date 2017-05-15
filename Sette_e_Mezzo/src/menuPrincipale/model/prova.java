package menuPrincipale.model;

import menuPrincipale.controller.MenuPrincipaleController;
import menuPrincipale.view.MenuPrincipaleConsoleView;
import menuPrincipale.view.MenuPrincipaleGuiView;

/**
 *
 * @author xXEgoOneXx
 */
public class prova {
    public static void main(String[] args) {
        MenuPrincipaleModel model = new MenuPrincipaleModel();
        MenuPrincipaleGuiView view = new MenuPrincipaleGuiView(model);
        MenuPrincipaleController controller = new MenuPrincipaleController(model, view);

    }
}
