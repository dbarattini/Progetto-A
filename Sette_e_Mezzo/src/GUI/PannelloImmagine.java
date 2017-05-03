package GUI;

import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PannelloImmagine extends JPanel {
    
    public ImageIcon immagine;
    
    // istanzia il pannello
    public PannelloImmagine(ImageIcon imm, LayoutManager layout) {
        immagine = imm;
        setLayout(layout);
        setOpaque(false);
    }
    
    // disegna l'immagine sull'interfaccia
    public void paint(Graphics g) {
        immagine.paintIcon(this, g, 0, 0);
	super.paint(g);
    }
}