package menuOpzioni;

import menuRiconoscimenti.RiconoscimentiGui;
import dominio.gui.Sfondo;
import dominio.musica.AudioPlayer;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class OpzioniGui  extends JFrame implements ActionListener {
    RiconoscimentiGui opzioni_riconoscimenti; 
    private Sfondo sfondo;
    private JButton musica, profilo, riconoscimenti, indietro;
    private ImageIcon musicaOn, musicaOff;
    
    public OpzioniGui() {
        this.opzioni_riconoscimenti = new RiconoscimentiGui();
        opzioni_riconoscimenti.addIndietroActionListener(this);
        
        setTitle("Opzioni");
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));		
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        inizializzaGui();
        
    }
    
    private void inizializzaGui() {
        sfondo = new Sfondo("dominio/immagini/sfondomenu.jpg", 800, 570);
        sfondo.setBounds(0, 0, 800, 600);
        add(sfondo);
        
        musicaOn = caricaImmagine("dominio/immagini/musicaOn.png");
        musicaOff = caricaImmagine("dominio/immagini/musicaOff.png");
        
        musica = new JButton(musicaOn);
        profilo = new JButton(caricaImmagine("dominio/immagini/profilo.png"));
        riconoscimenti = new JButton(caricaImmagine("dominio/immagini/riconoscimenti.png"));
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        
        musica.setBounds(this.getWidth()/2 - 40, 150, 80, 80);
        profilo.setBounds(this.getWidth()/2 - 100, 250, 200, 80);
        riconoscimenti.setBounds(this.getWidth()/2 - 100, 350, 200, 80);
        indietro.setBounds(this.getWidth()/2 - 100, 450, 200, 80);
        
//        musica.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(AudioPlayer.MUTO == true) {
//                    AudioPlayer.MUTO = false;
//                    musica.setIcon(musicaOn);
//                    musica.repaint();
//                } else {
//                    AudioPlayer.MUTO = true;
//                    musica.setIcon(musicaOff);
//                    musica.repaint();
//                }
//            };
//        });
        
        profilo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // codice per gioca online
            };
        });
        
        riconoscimenti.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                opzioni_riconoscimenti.setVisible(true);
                chiudi();
            };
        });
        
        
        sfondo.add(musica);
        sfondo.add(profilo);
        sfondo.add(riconoscimenti);
        sfondo.add(indietro);
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }
    
    private void chiudi() {
        dispose();
    }
    
    public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(opzioni_riconoscimenti.isVisible())
          opzioni_riconoscimenti.setVisible(false);
        this.setVisible(true);
    }
}