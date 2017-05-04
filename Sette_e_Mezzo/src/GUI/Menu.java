package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame {
    
    private JButton partita_off, partita_on, regole, opzioni, indietro;
    private Sfondo sfondo;
    private JLabel regole_scritte;
    
    public Menu() {
        setTitle("Menu");
        setPreferredSize(new Dimension(800, 600));
	setMinimumSize(new Dimension(800, 600));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        inizializza_GUI();
        
        setVisible(true);
    }
    
    private void inizializza_GUI() {
        sfondo = new Sfondo("immagini/sfondomenu.jpg", 800, 570);
        sfondo.setBounds(0, 0, 800, 600);
        add(sfondo);
        
        partita_off = new JButton(caricaImmagine("immagini/offline.png"));
        partita_on = new JButton(caricaImmagine("immagini/online.png"));
        regole = new JButton(caricaImmagine("immagini/regole.png"));
        opzioni = new JButton(caricaImmagine("immagini/opzioni.png"));
        indietro = new JButton(caricaImmagine("immagini/indietro.png"));
        regole_scritte = new JLabel(caricaImmagine("immagini/AssoDenari.png"));
        
        partita_off.setBounds(this.getWidth()/2 - 100, 150, 200, 80);
        partita_on.setBounds(this.getWidth()/2 - 100, 250, 200, 80);
        regole.setBounds(this.getWidth()/2 - 100, 350, 200, 80);
        opzioni.setBounds(this.getWidth()/2 - 100, 450, 200, 80);
        indietro.setBounds(this.getWidth()/2 - 100, 470, 200, 80);
        regole_scritte.setBounds(this.getWidth()/2 - 50, 150, 500, 50);
        
        partita_off.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // codice per gioca offline
            };
        });
        
        partita_on.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // codice per gioca online
            };
        });
        
        regole.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Regole di gioco:\nScrivere le regole di gioco",
                                            "REGOLE DI GIOCO", JOptionPane.INFORMATION_MESSAGE);
            };
        });
        
        opzioni.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // codice per opzioni
            };
        });
        
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // codice per indietro
            };
        });
        
        sfondo.add(partita_off);
        sfondo.add(partita_on);
        sfondo.add(regole);
        sfondo.add(opzioni);
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }
}