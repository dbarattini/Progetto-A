package dominio.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sfondo extends JPanel {

    private BufferedImage immagine;
    private final int larghezza, altezza;

    /**
     * 
     * @param nome_immagine nome dell'immagine sfondo
     * @param larghezza larghezza dell'immagine
     * @param altezza altezza immagine
     */
    public Sfondo(String nome_immagine, int larghezza, int altezza) {
        this.larghezza = larghezza;
        this.altezza = altezza;
        inizializza(nome_immagine);
    }

    private void inizializza(String nome_immagine) {
        caricaSfondo(nome_immagine);
        setLayout(null);
        setOpaque(false);
        setVisible(true);
    }

    /**
     * Disegna l'immagine sull'interfaccia con le giuste proporzioni.
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(immagine, 0, 0, larghezza, altezza, this);
        super.paint(g);
    }

    /**
     * Carica l'immagine dello sfondo dal package "dominio.immagini".
     *
     * @param nome_immagine nome dell'immagine
     */
    public void caricaSfondo(String nome_immagine) {
        try {
            immagine = ImageIO.read(getClass().getClassLoader().getResource(nome_immagine));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
