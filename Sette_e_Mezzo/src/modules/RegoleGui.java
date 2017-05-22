package modules;

import regole.model.RegoleModel;
import regole.view.RegoleGuiView;

public class RegoleGui {
        
    private final RegoleModel model;
    private final RegoleGuiView view;
    
    public RegoleGui() {
        model = new RegoleModel();
        view = new RegoleGuiView(model);

    }
}
