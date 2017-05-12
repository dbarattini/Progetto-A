package GUI;

import java.awt.Dimension;
import javax.swing.JFrame;

public class GuiOpzioni  extends JFrame {
    
    public GuiOpzioni() {
        setTitle("Menu");
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));		
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        setVisible(true);
    }
    
}