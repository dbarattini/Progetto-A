package GUI;

import classi_dati.DifficoltaBot;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GuiPreOffline extends JFrame {
    
    private Sfondo sfondo;
    private JSlider nBot;
    private JButton diffFacile, diffMedia, diffDifficile, indietro, gioca;
    private JTextField fiches;
    private int numeroBot, fichesIniziali;
    private DifficoltaBot difficolta;
    private ImageIcon facile1, facile2, medio1, medio2, difficile1, difficile2;
    
    public GuiPreOffline() {
        setTitle("Impostazioni partita offline");
        setPreferredSize(new Dimension(1000, 800));
	setMinimumSize(new Dimension(1000, 800));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        inizializzaGUI();
        
        setVisible(true);
    }
    
    private void inizializzaGUI() {
        sfondo = new Sfondo("immagini/sfondoPreOffline.jpg", 1000, 800);
        sfondo.setBounds(0, 0, 1000, 800);
        add(sfondo);                
                      
        facile1 = caricaImmagine("immagini/facile.png");
        facile2 = caricaImmagine("immagini/facile2.png");
        medio1 = caricaImmagine("immagini/medio.png");
        medio2 = caricaImmagine("immagini/medio2.png");
        difficile1 = caricaImmagine("immagini/difficile.png");
        difficile2 = caricaImmagine("immagini/difficile2.png");
        
        nBot = new JSlider(1, 4, 2);
        diffFacile = new JButton(facile1);
        diffMedia = new JButton(medio1);
        diffDifficile = new JButton(difficile1);
        indietro = new JButton(caricaImmagine("immagini/indietro.png"));
        gioca = new JButton(caricaImmagine("immagini/gioca!.png"));
        fiches = new JTextField();
        
        
        nBot.setBounds(325, 200, 300, 80);
        diffFacile.setBounds(250, 350, 200, 80);
        diffMedia.setBounds(500, 350, 200, 80);
        diffDifficile.setBounds(750, 350, 200, 80);
        indietro.setBounds(100, 680, 200, 80);
        gioca.setBounds(700, 680, 200, 80);
        fiches.setBounds(375, 500, 200, 80);
        
        fiches.setFont(new Font("fiches", 1, 35));
        nBot.setOpaque(false);
        nBot.setMajorTickSpacing(1);
        nBot.setPaintTicks(true);
        nBot.setPaintLabels(true);
        
        diffFacile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                diffFacile.setIcon(facile2);
                diffMedia.setIcon(medio1);
                diffDifficile.setIcon(difficile1);
                diffFacile.repaint();
                diffMedia.repaint();
                diffDifficile.repaint();
                difficolta = DifficoltaBot.Facile;
            };
        });
        
        diffMedia.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                
                diffMedia.setIcon(medio2);
                diffFacile.setIcon(facile1);
                diffDifficile.setIcon(difficile1);
                diffFacile.repaint();
                diffMedia.repaint();
                diffDifficile.repaint();
                difficolta = DifficoltaBot.Medio;
            };
        });
        
        diffDifficile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                
                diffDifficile.setIcon(difficile2);
                diffFacile.setIcon(facile1);
                diffMedia.setIcon(medio1);
                diffFacile.repaint();
                diffMedia.repaint();
                diffDifficile.repaint();
                difficolta = DifficoltaBot.Difficile;
            };
        });        
        
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiMenu();
                chiudi();
            };
        });
        
        gioca.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroBot = nBot.getValue();
                fichesIniziali = Integer.parseInt(fiches.getText());
                
                // c'è da gestire con un pop up di errore, le eccezioni per i dati non validi o nulli
                
                // e fa partire il game con le impostazioni settate
            };
        });
        
        sfondo.add(nBot);
        sfondo.add(diffFacile);
        sfondo.add(diffMedia);
        sfondo.add(diffDifficile);
        sfondo.add(indietro);
        sfondo.add(gioca);
        sfondo.add(fiches);
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }
    
    private void chiudi() {
        dispose();
    }
}