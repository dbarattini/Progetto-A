package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class RiconoscimentiGui extends JFrame{
    private JButton indietro;
    
  public RiconoscimentiGui(){
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RICONOSCIMENTI");
        
        
        setLayout(new BorderLayout());
        JPanel pannelloG = new JPanel();
        pannelloG.setBackground(Color.WHITE);
        
        JTextArea developers = new JTextArea("DEVELOPERS: ");
        JTextArea riconoscimenti = new JTextArea ("\n"
                            + "Barattini Daniel\n" 
                            + "Bergamaschi Martino\n" 
                            + "Colmi Samuele\n"
                            + "Guido Marco\n" 
                            + "Mbaye Samba\n" 
                            + "Prina Marco\n");
        developers.setEditable(false);
        riconoscimenti.setEditable(false);
        
        add(developers, BorderLayout.NORTH);
        //add(riconoscimenti, BorderLayout.CENTER);
        
        JPanel pannelloNomi = new JPanel();
        pannelloNomi.setBackground(Color.WHITE);
        pannelloNomi.setLayout(new FlowLayout());
        pannelloNomi.add(riconoscimenti);
        add(pannelloNomi, BorderLayout.CENTER);
        
        JPanel pulsanteIndietro = new JPanel();
        pulsanteIndietro.setBackground(Color.WHITE);
        pulsanteIndietro.setLayout(new FlowLayout());
        
        indietro = new JButton("INDIETRO");
        pulsanteIndietro.add(indietro);
        add(pulsanteIndietro, BorderLayout.SOUTH);
        
        setVisible(true);
  }
     public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }
}
