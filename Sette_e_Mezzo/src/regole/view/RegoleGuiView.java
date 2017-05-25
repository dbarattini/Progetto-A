package regole.view;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.BorderLayout;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import regole.model.RegoleModel;

public class RegoleGuiView{
    
    public RegoleGuiView(RegoleModel model){
        JFrame finestra = new JFrame();
        finestra.setSize(700, 500);
        WindowListener chiudi = null;
        finestra.addWindowListener(chiudi);
        finestra.setTitle("REGOLE DI GIOCO");
        
        //BorderLayout gestore = new BorderLayout();
        //setLayout(gestore);
        JPanel pannelloRegole = new JPanel();
        
        
        /*Container contenuto = getContentPane();
        contenuto.setBackground(Color.CYAN);
        */
        JTextArea stampaRegole = new JTextArea(model.getRegole());
        
        finestra.add(stampaRegole, BorderLayout.CENTER);
        
        finestra.setVisible(true);
    }

   
}
