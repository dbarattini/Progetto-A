
package modules;

import menuPrepartita.controller.MenuPrePartitaController;
import menuPrepartita.model.MenuPrePartitaModel;
import menuPrepartita.view.MenuPrePartitaGuiView;

public class MenuPrePartitaGui {
    private final MenuPrePartitaModel model;
    private final MenuPrePartitaGuiView view;
    private final MenuPrePartitaController controller;

    public MenuPrePartitaGui() {
        model = new MenuPrePartitaModel();
        view = new MenuPrePartitaGuiView(model);
        controller = new MenuPrePartitaController(model, view);
    }
}
