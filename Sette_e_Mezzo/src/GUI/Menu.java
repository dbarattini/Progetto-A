package GUI;

import java.awt.Dimension;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class Menu extends JFrame {
    
    private JButton partita_off, partita_on, regole, opzioni;
    private Sfondo sfondo;
    
    public Menu() {
        setTitle("Menu");
        setPreferredSize(new Dimension(800, 600));
	setMinimumSize(new Dimension(800, 600));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        sfondo = new Sfondo("immagini/sfondomenu.jpg", 800, 570);
        sfondo.setBounds(0, 0, 800, 600);
        add(sfondo);
        
        partita_off = new JButton(caricaImmagine("immagini/offline.png"));
        partita_on = new JButton(caricaImmagine("immagini/online.png"));
        regole = new JButton(caricaImmagine("immagini/regole.png"));
        opzioni = new JButton(caricaImmagine("immagini/opzioni.png"));
        
        partita_off.setBounds(this.getWidth()/2 - 100, 150, 200, 80);
        partita_on.setBounds(this.getWidth()/2 - 100, 250, 200, 80);
        regole.setBounds(this.getWidth()/2 - 100, 350, 200, 80);
        opzioni.setBounds(this.getWidth()/2 - 100, 450, 200, 80);
        
        sfondo.add(partita_off);
        sfondo.add(partita_on);
        sfondo.add(regole);
        sfondo.add(opzioni);
        
        setVisible(true);
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }
}