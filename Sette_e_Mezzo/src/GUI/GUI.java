package GUI;

import gioco.PartitaOffline;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {
    
    Sfondo sfondo;
    
    
    public GUI() {
        setTitle("Sette e mezzo");
        setPreferredSize(new Dimension(1280, 720));
	setMinimumSize(new Dimension(1280, 720));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        
        sfondo = new Sfondo("immagini/sfondo.png", 1275, 690);
        sfondo.setBounds(0, 0, PartitaOffline.LARGHEZZA, PartitaOffline.ALTEZZA);
        add(sfondo);
        
        // carte di prova
        JLabel carta = new JLabel(caricaImmagine("immagini/AssoDenari.png"));
        carta.setBounds(100, 100, 76, 120);
        sfondo.add(carta);
        
        JLabel carta2 = new JLabel(caricaImmagine("immagini/AssoDenari.png"));
        carta2.setBounds(600, 100, 76, 120);
        sfondo.add(carta2);
        
        setVisible(true);
    }
    
    public ImageIcon caricaImmagine(String nome){
	ClassLoader caricatore = getClass().getClassLoader();
	URL percorso = caricatore.getResource(nome);
	return new ImageIcon(percorso);
    }
}