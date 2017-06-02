package partitaOffline.view;

import dominio.giocatori.Giocatore;
import dominio.gui.Sfondo;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.awt.Color;
import partitaOffline.model.PartitaOfflineModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import partitaOffline.events.SetPuntata;
import partitaOffline.events.Vittoria;

public class PartitaOfflineGuiView extends JFrame implements PartitaOfflineView, Observer{
    private CopyOnWriteArrayList<ViewEventListener> listeners;
    private PartitaOfflineModel model;    
    private Sfondo sfondo;
    private String nome, puntataStr;
    private JTextField askNome, puntata;
    //private JButton askNomeButton;
    //private JLabel askNomeLabel;
    //private Map<String, JLabel> cartePlayer, carteG1, carteG2, carteG3, carteG4;
    //private JLabel nomePlayer, nomeG1, nomeG2, nomeG3, nomeG4;
    //private JLabel fichesPlayer, fichesG1, fichesG2, fichesG3, fichesG4;
    //private JLabel valoreMPlayer, valoreMG1, valoreMG2, valoreMG3, valoreMG4;
    private final int pausa_breve = 1000; //ms
    private final int pausa_lunga = 2000; //ms
    
    
    public PartitaOfflineGuiView(PartitaOfflineModel model) {
        listeners = new CopyOnWriteArrayList<>();                
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
        sfondo.setBounds(0, 0, 1280, 720);
        add(sfondo);
        
        setVisible(true);
    }
    
    public ImageIcon caricaImmagine(String nome) {
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
            //todo mostra la richiesta del nome al giocatore  
            richiediNome();            
        } else if(arg instanceof Error){
            //todo mostra l'errore a video
            String errore = arg.toString();
            JOptionPane.showMessageDialog(null, errore, "Errore !!!", JOptionPane.ERROR_MESSAGE);
        } else if(arg instanceof EstrattoMazziere){
            //todo mostra l'estrazione del mazziere
            estrazioneMazziere();
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
            richiediPuntata();
        } else if(evt.getArg() instanceof Error){
            //todo mostra l'errore al giocatore
        } else if(evt.getArg() instanceof RichiediGiocata){
            //todo richiede la giocata al giocatore
        }
    }
    
    public void richiediNome() {
        nome = null;
        askNome = new JTextField();
        JButton askNomeButton = new JButton(caricaImmagine("dominio/immagini/fatto.png"));
        JLabel askNomeLabel = new JLabel(caricaImmagine("dominio/immagini/richiediNome.png"));
            
        askNome.setFont(new Font("nome", 1, 40));
            
        askNome.setBounds(this.getWidth()/2 - 125, 300, 250, 80);
        askNomeButton.setBounds(this.getWidth()/2 - 100, 400, 200, 80);
        askNomeLabel.setBounds(this.getWidth()/2 - 200, 100, 400, 80);
            
        askNomeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nome = askNome.getText();
            };
        });
            
        sfondo.add(askNome);
        sfondo.add(askNomeButton);
        sfondo.add(askNomeLabel);            
        sfondo.repaint();
        
        while(nome == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
        }
            
        sfondo.removeAll(); 
        sfondo.repaint();
            
        fireViewEvent(new SetNome(nome));
    }
    
    public void estrazioneMazziere() {
        int nGiocatori = model.getGiocatori().size();
        
        for(int i = 0; i < nGiocatori; i++)
            stampaNomeFiches(i, nGiocatori - 1, model.getGiocatori().get(i));
        
        try {
                Thread.sleep(pausa_breve);
        } catch (InterruptedException ex) {}
        
        for(int i = 0; i < nGiocatori; i++) {
            stampaValoreMano(i, nGiocatori - 1, model.getGiocatori().get(i));
            if(i != nGiocatori - 1)
                stampaCarta((this.getWidth()*(2*i+1))/((nGiocatori-1)*2) - 125, 180, model.getGiocatori().get(i).getCartaCoperta().toString());
            else
                stampaCarta(this.getWidth()/2 - 125, 3*this.getHeight()/4 - 60, model.getGiocatori().get(i).getCartaCoperta().toString());
            try {
                Thread.sleep(pausa_breve);
            } catch (InterruptedException ex) {}            
        }
        
        try {
            Thread.sleep(pausa_breve);
        } catch (InterruptedException ex) {}
        
        Giocatore mazziere = model.getMazziere();
        Font font = new Font("Player", Font.BOLD, 70);
        JLabel messaggioMazziere = new JLabel("Il mazziere è: " + mazziere.getNome());
        messaggioMazziere.setFont(font);
        messaggioMazziere.setForeground(Color.black);
        int strWidth = messaggioMazziere.getFontMetrics(font).stringWidth("Il mazziere è: " + mazziere.getNome() + " !!");
        messaggioMazziere.setBounds(this.getWidth()/2 - strWidth/2, this.getHeight()/2 - 60, strWidth, 90);
        
        sfondo.add(messaggioMazziere);
        sfondo.repaint();
        
        try {
            Thread.sleep(pausa_lunga);
        } catch (InterruptedException ex) {}
        
        sfondo.removeAll();
        for(int i = 0; i < nGiocatori; i++)
            stampaNomeFiches(i, nGiocatori - 1, model.getGiocatori().get(i));
        
        
        
        
        
        sfondo.repaint();
    }
    
    // pensare a soluzione per cancellare i components locali da sfondo (possibile soluzione: tenere un indice che si incrementa per ogni comp aggiunto e creare un array con gli indici da eliminare al repaint finale)
    // soluzione temporanea ma penso finale: ridisegnare sempre i nomi e le fiches e/o i valori mano (veloce e semplice)
    public void stampaNomeFiches(int index, int nBot, Giocatore giocatore) {
        JLabel nomeGiocatore = new JLabel("Nome:   " + giocatore.getNome());
        JLabel fichesGiocatore = new JLabel("Fiches:   " + giocatore.getFiches());
        
        Font font = new Font("Player", Font.BOLD, 25);
        nomeGiocatore.setFont(font);
        nomeGiocatore.setForeground(Color.black);
        fichesGiocatore.setFont(font);
        fichesGiocatore.setForeground(Color.black);
        
        if(index != nBot) { // bot
            nomeGiocatore.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 40, 250, 40);
            fichesGiocatore.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 80, 250, 40);
        } else { // player
            nomeGiocatore.setBounds(this.getWidth()/4 - 175, 3*this.getHeight()/4 - 60, 250, 40);
            fichesGiocatore.setBounds(this.getWidth()/4 - 175, 3*this.getHeight()/4 - 20, 250, 40);
        }
        
        sfondo.add(nomeGiocatore);
        sfondo.add(fichesGiocatore);        
        sfondo.repaint();
    }
    
    public void stampaValoreMano(int index, int nBot, Giocatore giocatore) {
        JLabel valoreManoGiocatore = new JLabel("Valore mano:   " + giocatore.getValoreMano());
        
        Font font = new Font("Player", Font.BOLD, 25);
        valoreManoGiocatore.setFont(font);
        valoreManoGiocatore.setForeground(Color.black);
        
        if(index != nBot) { // bot
            valoreManoGiocatore.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 120, 250, 40);
        } else { // player
            valoreManoGiocatore.setBounds(this.getWidth()/4 - 175, 3*this.getHeight()/4 + 20, 250, 40);
        }
        
        sfondo.add(valoreManoGiocatore);
        sfondo.repaint();
    }
    
    public void stampaCarta(int x, int y, String carta) {
        JLabel cartaPescata = new JLabel(new ImageIcon(caricaImmagine("dominio/immagini/mazzo/" + carta + ".png").getImage().getScaledInstance(75, 75*3/2, Image.SCALE_DEFAULT)));
        
        cartaPescata.setBounds(x, y, 75, 75*3/2);        
        
        sfondo.add(cartaPescata);        
        sfondo.repaint();
    }
    
    public void stampaPlayersButtons() {
        JButton stai = new JButton();
        JButton punta = new JButton();
        JTextField puntata = new JTextField();
        
        stai.setBounds(1060, 580, 140, 56);  // mettere a posto le posizioni
        punta.setBounds(1060, 500, 140, 56);
        puntata.setBounds(900, 500, 140, 56);
        
        sfondo.add(stai);
        sfondo.add(punta);
        sfondo.add(puntata);
        sfondo.repaint();
    }
    
    public void richiediPuntata() {
        puntataStr = null;
        JButton punta = new JButton(caricaImmagine("dominio/immagini/punta.png"));
        puntata = new JTextField();
        
        punta.setBounds(1060, 500, 140, 56);
        puntata.setBounds(900, 500, 140, 56);
        
        Font font = new Font("Puntata", Font.BOLD, 15);
        puntata.setFont(font);
        puntata.setForeground(Color.black);
        
        punta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                puntataStr = puntata.getText();
            };
        });
        
        sfondo.add(punta);
        sfondo.add(puntata);
        sfondo.repaint();
        
        while(puntataStr == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
        }
            
        sfondo.removeAll();
        for(int i = 0; i < model.getGiocatori().size(); i++)
            stampaNomeFiches(i, model.getGiocatori().size() - 1, model.getGiocatori().get(i));
        sfondo.repaint();
            
        fireViewEvent(new SetPuntata(puntataStr));
    }
}