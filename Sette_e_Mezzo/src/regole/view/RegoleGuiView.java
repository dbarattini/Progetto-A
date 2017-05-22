package regole.view;

import javax.swing.JOptionPane;
import regole.model.RegoleModel;

public class RegoleGuiView extends JOptionPane{
    public RegoleGuiView(RegoleModel model){
        this.showMessageDialog(null, model.getRegole(), "REGOLE DI GIOCO", JOptionPane.INFORMATION_MESSAGE);
    }
}
