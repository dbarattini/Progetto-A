package GUI;

import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PannelloImmagine extends JPanel {
    
    private ImageIcon immagine;
    
    // istanzia il pannello
    public PannelloImmagine(ImageIcon imm, LayoutManager layout) {
        immagine = imm;
        setLayout(layout);
        setOpaque(false);
        setVisible(true);
    }
    
    // disegna l'immagine sull'interfaccia (deve essere in inglese il nome)
    public void paint(Graphics g) {
        immagine.paintIcon(this, g, 0, 0);
	super.paint(g);
    }
    
    public void posiziona(int x, int y) {
        setBounds(x, y, immagine.getIconWidth(), immagine.getIconHeight());
    }
}