package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sfondo extends JPanel {
    
    private BufferedImage image;
    private int larghezza, altezza;
    
    public Sfondo(String nome, int l, int a) {
        larghezza = l;
        altezza = a;
        caricaSfondo(nome); 
        setLayout(null);
        setOpaque(false);
        setVisible(true);
    }
    
    // disegna l'immagine sull'interfaccia con le giuste proporzioni (deve essere in inglese il nome del metodo)
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, larghezza, altezza, this);
	super.paint(g);
    }
    
    // carica l'immagine dello sfondo dal package "immagini"
    public void caricaSfondo(String nome) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource(nome));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}