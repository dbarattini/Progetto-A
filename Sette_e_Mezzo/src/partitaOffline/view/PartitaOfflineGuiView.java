package partitaOffline.view;

import dominio.gui.Sfondo;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import partitaOffline.model.PartitaOfflineModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modules.PartitaOfflineGui;
import partitaOffline.events.AggiornamentoMazziere;
import partitaOffline.events.EstrattoMazziere;
import partitaOffline.events.FineManoAvversario;
import partitaOffline.events.FineRound;
import partitaOffline.events.GameOver;
import partitaOffline.events.GiocatoreLocaleEvent;
import partitaOffline.events.MazzierePerde;
import partitaOffline.events.MazzoRimescolato;
import partitaOffline.events.RichiediGiocata;
import partitaOffline.events.RichiediNome;
import partitaOffline.events.RichiediPuntata;
import partitaOffline.events.RisultatoManoParticolare;
import partitaOffline.events.SetNome;
import partitaOffline.events.Vittoria;

public class PartitaOfflineGuiView extends JFrame implements PartitaOfflineView, Observer{
    private final CopyOnWriteArrayList<ViewEventListener> listeners;
    private PartitaOfflineModel model;    
    private Sfondo sfondo;
    private String nome;
    private JTextField askNome;
    private JButton askNomeButton;
    private JLabel askNomeLabel;
    
    
    public PartitaOfflineGuiView(PartitaOfflineModel model) {
        this.listeners = new CopyOnWriteArrayList<>();
        this.model = model;
        this.model.addObserver(this);
        
        setTitle("Sette e Mezzo");
        setPreferredSize(new Dimension(1280, 720));
	setMinimumSize(new Dimension(1280, 720));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        
        sfondo = new Sfondo("dominio/immagini/sfondo.png", 1275, 690);
        sfondo.setBounds(0, 0, PartitaOfflineModel.LARGHEZZA, PartitaOfflineModel.ALTEZZA);
        add(sfondo);
        
        // carte di prova
//        JLabel carta = new JLabel(caricaImmagine("dominio/immagini/AssoDenari.png"));
//        carta.setBounds(100, 100, 76, 120);
//        sfondo.add(carta);
        
//        JLabel carta2 = new JLabel(caricaImmagine("dominio/immagini/AssoDenari.png"));
//        carta2.setBounds(600, 100, 76, 120);
//        sfondo.add(carta2);
        
        setVisible(true);
    }
    
    public ImageIcon caricaImmagine(String nome){
	ClassLoader caricatore = getClass().getClassLoader();
	URL percorso = caricatore.getResource(nome);
	return new ImageIcon(percorso);
    }
    
    @Override
    public void addPartitaOfflineViewEventListener(ViewEventListener l) {
        listeners.add(l);
    }

    @Override
    public void removePartitaOfflineViewEventListener(ViewEventListener l) {
        listeners.remove(l);
    }

    protected void fireViewEvent(Object arg) {
        ViewEvent evt = new ViewEvent(this, arg);

        for (ViewEventListener l : listeners) {
            l.ViewEventReceived(evt);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof RichiediNome){
            nome = null;
            askNome = new JTextField();
            askNomeButton = new JButton(caricaImmagine("dominio/immagini/fatto.png"));
            askNomeLabel = new JLabel() {
                @Override
                public void paint(Graphics g) {
                    g.drawString("Inserisci il tuo nome:", 0, 0);
                }
            };
            
            askNome.setFont(new Font("nome", 1, 40));
            askNomeLabel.setFont(new Font("nome2", 1, 30));
            
            askNome.setBounds(this.getWidth()/2 - 125, this.getHeight() - 125, 250, 125);
            askNomeButton.setBounds(this.getWidth()/2 - 100, this.getHeight() + 25, 200, 80);
            askNomeLabel.setBounds(this.getWidth()/2 - 100, this.getHeight() - 250, 400, 80);
            
            askNomeButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    nome = askNome.getText();
                };
            });
            
            sfondo.add(askNome);
            sfondo.add(askNomeButton);
            sfondo.add(askNomeLabel);
            
            while(nome == null) {}  // resta in attesa finchè non viene inserito il nome
            
            sfondo.remove(askNome);
            sfondo.remove(askNomeButton);
            sfondo.remove(askNomeLabel); 
            
            
        } else if(arg instanceof Error){
            //todo mostra l'errore a video
        } else if(arg instanceof EstrattoMazziere){
            //todo mostra l'estrazione del mazziere
        } else if(arg instanceof MazzoRimescolato){
            //todo mostra il rimescolamento del mazzo
        } else if(arg instanceof RisultatoManoParticolare){
            //todo mostra lo stato particolare di una mano (Sette e mezzo, reale, sballato)
        } else if(arg instanceof FineManoAvversario){
            //todo mostra il risultato della mano di un avversario
        } else if(arg instanceof FineRound){
            //todo mostra le statistiche di fine round
        } else if(arg instanceof MazzierePerde){
            //todo mostra che il mazziere ha perso
        } else if(arg instanceof AggiornamentoMazziere){
            //todo mostra che é stato scelto un nuovo mazziere
        } else if(arg instanceof GameOver){
            //todo mostra che il giocatore ha perso
        } else if(arg instanceof Vittoria){
            //todo mostra che il giocatore ha vinto
        }
    }

    @Override
    public void GiocatoreLocaleEventReceived(GiocatoreLocaleEvent evt) {
        if(evt.getArg() instanceof RichiediPuntata){
            //todo richiede la puntata al giocatore
        } else if(evt.getArg() instanceof Error){
            //todo mostra l'errore al giocatore
        } else if(evt.getArg() instanceof RichiediGiocata){
            //todo richiede la giocata al giocatore
        }
    }
}