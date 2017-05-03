package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
    
    PannelloImmagine pannello;
    
    public GUI(String nome) {
        setTitle(nome);
        setPreferredSize(new Dimension(1280, 720));
	setMinimumSize(new Dimension(1280, 720));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);        
        
        pannello = new PannelloImmagine(caricaImmagine("immagini/AssoDenari.png"), new BorderLayout());
        pannello.posiziona(55, 500);
        add(pannello);
        
        setVisible(true);
    }
    
    public ImageIcon caricaImmagine(String nome){
	ClassLoader caricatore = getClass().getClassLoader();
	URL percorso = caricatore.getResource(nome);
	return new ImageIcon(percorso);
    }
    
    
}