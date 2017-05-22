package menuPrincipale.view;

import dominio.gui.Sfondo;
import dominio.classi_dati.OpzioniMenu;
import java.awt.Dimension;
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
import menuPrincipale.events.OpzioneScelta;
import dominio.view.ViewEvent;
import menuPrincipale.model.MenuPrincipaleModel;
import modules.MenuPrePartitaGui;
import modules.RegoleGui;
import dominio.view.ViewEventListener;

public class MenuPrincipaleGuiView extends JFrame implements MenuPrincipaleView, Observer{
    private String opzione;
    private JButton partita_off, partita_on, regole, opzioni;
    private Sfondo sfondo;
    private final CopyOnWriteArrayList<ViewEventListener> listeners;
    
    public MenuPrincipaleGuiView(MenuPrincipaleModel model) { 
        listeners = new CopyOnWriteArrayList<>();
        model.addObserver(this);
        inizializza_GUI();       
        setVisible(true);
    }
    
    
    private void inizializza_GUI() {
        setTitle("Menu");
        setPreferredSize(new Dimension(800, 600));
	setMinimumSize(new Dimension(800, 600));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        
        sfondo = new Sfondo("dominio/immagini/sfondomenu.jpg", 800, 570);
        sfondo.setBounds(0, 0, 800, 600);
        add(sfondo);
        
        partita_off = new JButton(caricaImmagine("dominio/immagini/offline.png"));
        partita_on = new JButton(caricaImmagine("dominio/immagini/online.png"));
        regole = new JButton(caricaImmagine("dominio/immagini/regole.png"));
        opzioni = new JButton(caricaImmagine("dominio/immagini/opzioni.png"));
        
        partita_off.setBounds(this.getWidth()/2 - 100, 150, 200, 80);
        partita_on.setBounds(this.getWidth()/2 - 100, 250, 200, 80);
        regole.setBounds(this.getWidth()/2 - 100, 350, 200, 80);
        opzioni.setBounds(this.getWidth()/2 - 100, 450, 200, 80);
        partita_off.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                opzione = "GiocaOffline";
                fireOpzioneSceltaEvent();
                dispose();
            };
        });
        
        partita_on.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                opzione = "GiocaOnline";
                fireOpzioneSceltaEvent();
                dispose();
            };
        });
        
        regole.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                opzione = "RegoleDiGioco";
                fireOpzioneSceltaEvent();
            }
        });
        
        opzioni.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                opzione = "Impostazioni";
                fireOpzioneSceltaEvent();
                //lanciaimpostazioni
                dispose();
            };
        });
        sfondo.add(partita_off);
        sfondo.add(partita_on);
        sfondo.add(regole);
        sfondo.add(opzioni);
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }

    @Override
    public void addViewEventListener(ViewEventListener l) {
        listeners.add(l);
    }

    @Override
    public void removeViewEventListener(ViewEventListener l) {
        listeners.remove(l);
    }
    
    protected void fireOpzioneSceltaEvent() {
        ViewEvent evt = new ViewEvent(this, new OpzioneScelta(opzione));

        for (ViewEventListener l : listeners) {
            l.ViewEventReceived(evt);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Error){
            JOptionPane.showMessageDialog(null, ((Error) arg).getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
        } else{            
            OpzioniMenu opzione = (OpzioniMenu) arg;
            runOpzione(opzione);
        }
    }
    
    private void runOpzione(OpzioniMenu opzione){
        switch(opzione){
            case GiocaOffline: new MenuPrePartitaGui();
                                break;
            case GiocaOnline : break;
            case Impostazioni: break;
            case RegoleDiGioco: new RegoleGui();
                                break;
        }
    }
}