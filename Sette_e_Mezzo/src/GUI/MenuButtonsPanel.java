package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuButtonsPanel extends JPanel {
    
    public MenuButtonsPanel(){
        super();
        InizializzaPanel();
    }
    
    private void InizializzaPanel(){
        JButton gioca_offline, gioca_online, regole_di_gioco, opzioni;
        
        gioca_offline = new JButton("Gioca Offline");
        gioca_online = new JButton("Gioca Online");
        regole_di_gioco = new JButton("Regole di Gioco");
        opzioni = new JButton("Opzioni");
        
        gioca_offline.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //aapre gui gioco_offline
            };
        });
        
        gioca_online.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //apre gui gioco_online
            };
        });
                
        regole_di_gioco.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //apre gui regole di gioco
            };
        });
        
        opzioni.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //apre gui opzioni
            };
        });
        
        
        gioca_offline.setFont(new Font("custom", 10, 50));
        gioca_online.setFont(new Font("custom", 10, 50));
        regole_di_gioco.setFont(new Font("custom", 10, 50));
        opzioni.setFont(new Font("custom", 10, 50));
        
        this.setLayout(new GridLayout(4,1));
        this.setPreferredSize(new Dimension(1920,150));
        
        this.add(gioca_offline);
        this.add(gioca_online);
        this.add(regole_di_gioco);
        this.add(opzioni);
    }
}
