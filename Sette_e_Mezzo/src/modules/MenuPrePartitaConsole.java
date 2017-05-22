package modules;

import menuPrepartita.controller.MenuPrePartitaController;
import menuPrepartita.model.MenuPrePartitaModel;
import menuPrepartita.view.MenuPrePartitaConsoleView;


public class MenuPrePartitaConsole {
    private final MenuPrePartitaModel model;
    private final MenuPrePartitaConsoleView view;
    private final MenuPrePartitaController controller;

    public MenuPrePartitaConsole() {
        model = new MenuPrePartitaModel();
        view = new MenuPrePartitaConsoleView(model);
        controller = new MenuPrePartitaController(model, view);
        
        view.run();
    }
}
