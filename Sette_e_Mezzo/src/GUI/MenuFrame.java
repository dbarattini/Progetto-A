package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MenuFrame extends JFrame {
    
    public MenuFrame() throws InterruptedException, UnsupportedLookAndFeelException{
        super();
        InizializzaFrame();
    }
    
    private void InizializzaFrame() throws InterruptedException, UnsupportedLookAndFeelException{
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize = kit.getScreenSize();
        this.setSize(screensize.width,screensize.height);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        MenuButtonsPanel buttons = new MenuButtonsPanel();
        MenuTitlePanel title = new MenuTitlePanel();
        
        this.add(buttons,BorderLayout.CENTER);
        this.add(title, BorderLayout.NORTH);
        
        this.setVisible(true);
    }
}
