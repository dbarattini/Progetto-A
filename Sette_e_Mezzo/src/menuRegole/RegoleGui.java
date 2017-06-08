package menuRegole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RegoleGui extends JFrame{
    private JButton indietro;
    private Regole regole_gioco;
    
    public RegoleGui(){
        this.regole_gioco = new Regole();
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("REGOLE DI GIOCO");
        setResizable(false);
        
        setLayout(new BorderLayout());
        
        JPanel pannello = new JPanel();
        pannello.setBackground(Color.WHITE);
        JTextArea regole  = new JTextArea("\n" + regole_gioco.getRegole());   
        regole.setEditable(false);
        add(regole, BorderLayout.CENTER);
        
        JPanel pulsanteIndietro = new JPanel();
        pulsanteIndietro.setBackground(Color.WHITE);
        pulsanteIndietro.setLayout(new FlowLayout());
        
        indietro = new JButton("INDIETRO");
        pulsanteIndietro.add(indietro);
        add(pulsanteIndietro, BorderLayout.SOUTH);
    }
    
  public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }
}
