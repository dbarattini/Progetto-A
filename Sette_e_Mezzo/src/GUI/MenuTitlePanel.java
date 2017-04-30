package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuTitlePanel extends JPanel {
    
    public MenuTitlePanel(){
        super();
        InizializzaPanel();
    }
    
    private void InizializzaPanel(){
       JTextField title = new JTextField("Sette e Mezzo");
       ModificaStile(title);
       
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(1920, 300));
        
    }
    
    
    public void ModificaStile(JTextField base){
        base.setFont(new Font("Prova", 50, 70));
        base.setHorizontalAlignment(JTextField.CENTER);
        base.setEditable(false);
    }
}
