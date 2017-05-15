package modules;

import regole.model.RegoleModel;
import regole.view.RegoleConsoleView;


public class RegoleConsole {
    private final RegoleModel model;
    private final RegoleConsoleView view;
    
    public RegoleConsole() {
        model = new RegoleModel();
        view = new RegoleConsoleView(model);
        
        view.run();
    }
}
