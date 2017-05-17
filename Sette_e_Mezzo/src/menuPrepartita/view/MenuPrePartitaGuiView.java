package menuPrepartita.view;

import dominio.gui.Sfondo;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import menuPrepartita.events.SetInfo;
import menuPrepartita.model.MenuPrePartitaModel;
import menuPrincipale.events.OpzioneScelta;

public class MenuPrePartitaGuiView extends JFrame implements Observer, MenuPrePartitaView {
    private final CopyOnWriteArrayList<ViewEventListener> listeners;
    private Sfondo sfondo;
    private JSlider nBot;
    private JButton diffFacile, diffMedia, diffDifficile, indietro, gioca;
    private JTextField fiches;
    private ImageIcon facile1, facile2, medio1, medio2, difficile1, difficile2;
    private final MenuPrePartitaModel model;
    private String nbot, difficolta_bot, fiches_iniziali;
    
    public MenuPrePartitaGuiView(MenuPrePartitaModel model) {
        this.model = model;
        this.model.addObserver(this);
        listeners = new CopyOnWriteArrayList<>();
        
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
        sfondo = new Sfondo("dominio/immagini/sfondoPreOffline.jpg", 1000, 800);
        sfondo.setBounds(0, 0, 1000, 800);
        add(sfondo);                
                      
        facile1 = caricaImmagine("dominio/immagini/facile.png");
        facile2 = caricaImmagine("dominio/immagini/facile2.png");
        medio1 = caricaImmagine("dominio/immagini/medio.png");
        medio2 = caricaImmagine("dominio/immagini/medio2.png");
        difficile1 = caricaImmagine("dominio/immagini/difficile.png");
        difficile2 = caricaImmagine("dominio/immagini/difficile2.png");
        
        nBot = new JSlider(1, 4, 2);
        diffFacile = new JButton(facile1);
        diffMedia = new JButton(medio1);
        diffDifficile = new JButton(difficile1);
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        gioca = new JButton(caricaImmagine("dominio/immagini/gioca!.png"));
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
                difficolta_bot = "Facile";
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
                difficolta_bot = "Medio";
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
                difficolta_bot = "Difficile";
            };
        });        
        
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                new GuiMenu();
//                chiudi();
            };
        });
        
        gioca.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nbot = String.valueOf(nBot.getValue());
                fiches_iniziali = fiches.getText();
                fireViewEvent();
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


    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Error){
            JOptionPane.showMessageDialog(null, (((Error) arg).getMessage()), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addViewEventListener(ViewEventListener l) {
        listeners.add(l);
    }

    @Override
    public void removeViewEventListener(ViewEventListener l) {
        listeners.remove(l);
    }
    
    protected void fireViewEvent() {
        ViewEvent evt = new ViewEvent(this, new SetInfo(nbot, difficolta_bot, fiches_iniziali));

        for (ViewEventListener l : listeners) {
            l.ViewEventReceived(evt);
        }
    }

}