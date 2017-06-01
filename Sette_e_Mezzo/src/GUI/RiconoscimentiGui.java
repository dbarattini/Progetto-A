
package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import regole.view.ChiudiRegole;

/**
 *
 * @author samba
 */
public class RiconoscimentiGui extends JFrame{
    
  public RiconoscimentiGui(){
        setSize(500, 300);
        addWindowListener(new ChiudiRiconoscimenti());
        setTitle("RICONOSCIMENTI");
        
        setLayout(new BorderLayout());
        JPanel pannello = new JPanel();
        JTextArea riconoscimenti = new JTextArea ("Developed by:\n"
                            + "Barattini Daniel\n" 
                            + "Bergamaschi Martino\n" 
                            + "Colmi Samuele\n"
                            + "Guido Marco\n" 
                            + "Mbaye Samba\n" 
                            + "Prina Marco\n");
        riconoscimenti.setEditable(false);
        add(riconoscimenti, BorderLayout.CENTER);
        
        
        setVisible(true);
  }
   
}
