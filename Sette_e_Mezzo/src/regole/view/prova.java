package regole.view;

import regole.model.RegoleModel;

/**
 *
 * @author xXEgoOneXx
 */
public class prova {
    
    public static void main(String[] args) {
        RegoleModel regole =  new RegoleModel();
        RegoleConsoleView view = new RegoleConsoleView(regole);
        view.run();
    }
}
