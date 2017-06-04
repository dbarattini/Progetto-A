package partitaOffline.view;

import dominio.eccezioni.CanzoneNonTrovataException;
import dominio.eccezioni.MattaException;
import dominio.elementi_di_gioco.Carta;
import dominio.giocatori.Giocatore;
import dominio.gui.Sfondo;
import dominio.musica.AudioPlayer;
import dominio.view.ViewEvent;
import dominio.view.ViewEventListener;
import java.awt.Color;
import partitaOffline.model.PartitaOfflineModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import partitaOffline.events.SetGiocata;
import partitaOffline.events.SetNome;
import partitaOffline.events.SetPuntata;
import partitaOffline.events.Vittoria;

public class PartitaOfflineGuiView extends JFrame implements PartitaOfflineView, Observer{
    private CopyOnWriteArrayList<ViewEventListener> listeners;
    private PartitaOfflineModel model;    
    private Sfondo sfondo;
    private String nome, puntataStr, giocata;
    private JTextField askNome, puntata;
    private JButton carta, stai;
    private boolean needCartaCoperta = true;
    private ArrayList<JLabel> carteCoperteBots = new ArrayList<>(), valoriManoBots = new ArrayList<>();
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
        if(arg instanceof RichiediNome) {
            //mostra la richiesta del nome al giocatore  
            richiediNome();            
        } else if(arg instanceof Error) {
            //mostra l'errore a video
            String errore = ((Error) arg).getMessage();
            JOptionPane.showMessageDialog(null, errore, "Errore !!!", JOptionPane.ERROR_MESSAGE);
        } else if(arg instanceof EstrattoMazziere) {
            //mostra l'estrazione del mazziere
            estrazioneMazziere();
        } else if(arg instanceof MazzoRimescolato) {
            //mostra il rimescolamento del mazzo
            rimescoloMazzo();
        } else if(arg instanceof RisultatoManoParticolare) {
            //todo mostra lo stato particolare di una mano (Sette e mezzo, reale, sballato)
            manoParticolarePlayer();
        } else if(arg instanceof FineManoAvversario) {
            //todo mostra il risultato della mano di un avversario
            if(needCartaCoperta)
                stampaCartaCoperta();
            stampaManoAvversario(((FineManoAvversario) arg).getNome());
        } else if(arg instanceof FineRound) { //provvisorio
            //todo mostra le statistiche di fine round
            if(!needCartaCoperta)
                pausa(pausa_breve);
            needCartaCoperta = true;
            sfondo.removeAll();            
            for(int i = 0; i < model.getGiocatori().size(); i++)
                stampaNomeFiches(i, model.getGiocatori().size() - 1, model.getGiocatori().get(i));            
        } else if(arg instanceof MazzierePerde) {
            //todo mostra che il mazziere ha perso
        } else if(arg instanceof AggiornamentoMazziere) {
            //todo mostra che é stato scelto un nuovo mazziere
        } else if(arg instanceof GameOver) {
            //todo mostra che il giocatore ha perso
        } else if(arg instanceof Vittoria) {
            //todo mostra che il giocatore ha vinto
        }
    }

    @Override
    public void GiocatoreLocaleEventReceived(GiocatoreLocaleEvent evt) {
        if(evt.getArg() instanceof RichiediPuntata) {
            //richiede la puntata al giocatore
            if(needCartaCoperta)
                stampaCartaCoperta();
            richiediPuntata();
        } else if(evt.getArg() instanceof Error) {
            //mostra l'errore al giocatore
            String errore = ((Error) evt.getArg()).getMessage();
            JOptionPane.showMessageDialog(null, errore, "Errore !!!", JOptionPane.ERROR_MESSAGE);
        } else if(evt.getArg() instanceof RichiediGiocata) {
            //richiede la giocata al giocatore
            stampaGiocataPlayer();
            if(needCartaCoperta)
                stampaCartaCoperta();
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
            pausa(100);
        }
            
        sfondo.removeAll(); 
        sfondo.repaint();
            
        fireViewEvent(new SetNome(nome));
    }
    
    public void estrazioneMazziere() {
        int nGiocatori = model.getGiocatori().size();
        
        for(int i = 0; i < nGiocatori; i++)
            stampaNomeFiches(i, nGiocatori - 1, model.getGiocatori().get(i));
        
        pausa(pausa_breve);
        
        for(int i = 0; i < nGiocatori; i++) {
            stampaValoreMano(model.getGiocatori().get(i));
            if(i != nGiocatori - 1)
                stampaCarta((this.getWidth()*(2*i+1))/((nGiocatori-1)*2) - 125, 180, model.getGiocatori().get(i).getCartaCoperta().toString());
            else
                stampaCarta(this.getWidth()/2 - 125, 3*this.getHeight()/4 - 60, model.getGiocatori().get(i).getCartaCoperta().toString());
            
            pausa(pausa_breve);
        }
        
        pausa(pausa_breve);
        
        Giocatore mazziere = model.getMazziere();
        Font font = new Font("Player", Font.BOLD, 70);
        JLabel messaggioMazziere = new JLabel("Il mazziere è: " + mazziere.getNome());
        messaggioMazziere.setFont(font);
        messaggioMazziere.setForeground(Color.black);
        int strWidth = messaggioMazziere.getFontMetrics(font).stringWidth("Il mazziere è: " + mazziere.getNome() + " !!");
        messaggioMazziere.setBounds(this.getWidth()/2 - strWidth/2, this.getHeight()/2 - 60, strWidth, 90);
        
        sfondo.add(messaggioMazziere);
        sfondo.repaint();
        
        pausa(pausa_lunga);
        
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
    
    public JLabel stampaValoreMano(Giocatore giocatore) {
        int nBot = model.getGiocatori().size() - 1;
        int index = model.getGiocatori().indexOf(giocatore);
        JLabel valoreManoGiocatore = new JLabel("Valore mano:   " + giocatore.getValoreMano());

        Font font = new Font("Player", Font.BOLD, 25);
        valoreManoGiocatore.setFont(font);
        valoreManoGiocatore.setForeground(Color.black);
        
        if(index != nBot) { // bot
            valoreManoGiocatore.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 120, 350, 40);
        } else { // player
            valoreManoGiocatore.setBounds(this.getWidth()/4 - 175, 3*this.getHeight()/4 + 20, 350, 40);
        }
        
        sfondo.add(valoreManoGiocatore);
        sfondo.repaint();
        
        return valoreManoGiocatore;
    }
    
    public JLabel stampaCarta(int x, int y, String carta) {
        JLabel card = new JLabel(caricaImmagine("dominio/immagini/mazzo/" + carta + ".png"));
        
        card.setBounds(x, y, 75, 113);        
        
        sfondo.add(card);        
        sfondo.repaint();
        
        return card;
    }
    
    public void richiediPuntata() {
        puntataStr = null;
        JButton punta = new JButton(caricaImmagine("dominio/immagini/punta.png"));
        JButton allIN = new JButton(caricaImmagine("dominio/immagini/all_in.png"));
        JLabel valoreMano = stampaValoreMano(model.getGiocatoreLocale());
        puntata = new JTextField();
        
        punta.setBounds(1060, 500, 140, 56);
        allIN.setBounds(1060, 570, 140, 56);
        puntata.setBounds(900, 500, 140, 56);
        
        Font font = new Font("Puntata", Font.BOLD, 25);
        puntata.setFont(font);
        puntata.setForeground(Color.black);
        
        punta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                puntataStr = puntata.getText();
            };
        });
        
        allIN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                puntataStr = String.valueOf(model.getGiocatoreLocale().getFiches());
            };
        });
        
        sfondo.add(punta);
        sfondo.add(allIN);
        sfondo.add(puntata);
        sfondo.add(valoreMano);
        sfondo.repaint();
        
        while(puntataStr == null) {
            pausa(100);
        }
            
        sfondo.remove(punta);
        sfondo.remove(allIN);
        sfondo.remove(puntata);
        sfondo.repaint();
        sfondo.remove(valoreMano);
            
        fireViewEvent(new SetPuntata(puntataStr));
        
        pausa(pausa_breve);
    }
    
    public void stampaGiocataPlayer() {
        giocata = null;
        Carta lastCard;
        if(!model.getGiocatoreLocale().getCarteScoperte().isEmpty()) {
            lastCard = model.getGiocatoreLocale().getUltimaCartaOttenuta();
            int index = model.getGiocatoreLocale().getCarteScoperte().indexOf(lastCard);            
            stampaCarta(this.getWidth()/2 - 95 + index*35, 3*this.getHeight()/4 - 60, lastCard.toString());
        }
        JLabel valoreMano = stampaValoreMano(model.getGiocatoreLocale());
        carta = new JButton(caricaImmagine("dominio/immagini/carta.png"));
        stai = new JButton(caricaImmagine("dominio/immagini/stai.png"));
        
        carta.setBounds(1060, 580, 140, 56);
        stai.setBounds(1060, 500, 140, 56);
        
        carta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                giocata = "carta";
            };
        });
        
        stai.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                giocata = "sto";
            };
        });
        
        sfondo.add(carta);
        sfondo.add(stai);
        sfondo.repaint();
        
        while(giocata == null) {
            pausa(pausa_breve);
        }
        
        fireViewEvent(new SetGiocata(giocata));
            
        sfondo.remove(carta);
        sfondo.remove(stai);
        sfondo.remove(valoreMano);
        sfondo.repaint();
    }
    
    public void rimescoloMazzo() {
        ArrayList<JLabel> carte = new ArrayList<>();
        Font font = new Font("Deck Shuffle", Font.BOLD, 70);
        JLabel rimescoloMsg = new JLabel("Rimescolo il mazzo...");
        rimescoloMsg.setFont(font);
        rimescoloMsg.setForeground(Color.black);
        int strWidth = rimescoloMsg.getFontMetrics(font).stringWidth("Rimescolo il mazzo...");
        rimescoloMsg.setBounds(this.getWidth()/2 - strWidth/2, this.getHeight()/2, strWidth, 90);
        
        sfondo.add(rimescoloMsg);
        sfondo.repaint();
        
        AudioPlayer audio = model.getAudio();
        try {
            audio.ferma("soundTrack");
            audio.riproduci("deckShuffle");
        } catch (CanzoneNonTrovataException ex) {
            Logger.getLogger(PartitaOfflineGuiView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < 12; i++)
            carte.add(stampaCartaMobileDeckShuffle(this.getWidth()/2 - 187 - i*5, this.getHeight()/2 - 100, "retroCarta", i));
        
        pausa(pausa_breve);
        
        try {
            audio.riavvia_in_loop("soundTrack");
            audio.riavvolgi("deckShuffle");
        } catch (CanzoneNonTrovataException ex) {
            Logger.getLogger(PartitaOfflineGuiView.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        sfondo.remove(rimescoloMsg);
        for(JLabel cartaTmp : carte)
            sfondo.remove(cartaTmp);
        sfondo.repaint();
    }
    
    public JLabel stampaCartaMobileDeckShuffle(int x, int y, String carta, int spazio) {
        JLabel card = new JLabel(caricaImmagine("dominio/immagini/mazzo/" + carta + ".png"));
        
        card.setBounds(x, y, 75, 113);
        
        sfondo.add(card);
        sfondo.repaint();
        
        for(int i = 0; i < ((this.getWidth()/2 + 20) - x - 8*spazio); i++) {
            card.setLocation(x + i, y);
            pausa(1);
            sfondo.repaint();
        }
        return card;
    }
    
    private void manoParticolarePlayer() {
        Carta ultimaOttenuta = model.getGiocatoreLocale().getUltimaCartaOttenuta();
        int index = model.getGiocatoreLocale().getCarteScoperte().indexOf(ultimaOttenuta);
        stampaCarta(this.getWidth()/2 - 95 + index*35, 3*this.getHeight()/4 - 60, ultimaOttenuta.toString());
    }
    
    private void stampaCartaCoperta() {
        pausa(pausa_breve);
        int nGiocatori = model.getGiocatori().size();
        
        JLabel valoreMano = null;
        Font font = new Font("Carte Coperte msg", Font.BOLD, 70);
        JLabel messaggioCartaCoperta = new JLabel("Distribuisco carta coperta...");
        messaggioCartaCoperta.setFont(font);
        messaggioCartaCoperta.setForeground(Color.black);
        int strWidth = messaggioCartaCoperta.getFontMetrics(font).stringWidth("Distribuisco carta coperta...");
        messaggioCartaCoperta.setBounds(this.getWidth()/2 - strWidth/2, this.getHeight()/2 - 60, strWidth, 90);
        
        sfondo.add(messaggioCartaCoperta);
        sfondo.repaint();
        
        for(int i = 0; i < nGiocatori; i++) {
            if(i != nGiocatori - 1)
                carteCoperteBots.add(stampaCarta((this.getWidth()*(2*i+1))/((nGiocatori-1)*2) - 125, 180, "retroCarta"));
            else {
                stampaCarta(this.getWidth()/2 - 125, 3*this.getHeight()/4 - 60, model.getGiocatori().get(i).getCartaCoperta().toString());
                valoreMano = stampaValoreMano(model.getGiocatoreLocale());
            }
            pausa(pausa_breve);
        }
        
        sfondo.remove(messaggioCartaCoperta);
        sfondo.repaint();
        sfondo.remove(valoreMano);
        
        needCartaCoperta = false;
    }
    
    public void stampaManoAvversario(String nome) {
        Giocatore giocatore = null;
        JLabel valoreMano = null;
        for(Giocatore gioc : model.getGiocatori()) {
            if(gioc.getNome().equals(nome))
                giocatore = gioc;
        }
        int index = model.getGiocatori().indexOf(giocatore);
        
        if(!giocatore.getCarteScoperte().isEmpty()) {
            for(int i = 0; i < giocatore.getCarteScoperte().size(); i++) {                
                valoreMano = stampaValoreManoAvversario(giocatore, i+1);
                stampaCarta((this.getWidth()*(2*index+1))/((model.getGiocatori().size()-1)*2) - 95 + i*35, 180, giocatore.getCarteScoperte().get(i).toString());                                
                pausa(pausa_breve);
                sfondo.remove(valoreMano);
            }
            valoriManoBots.add(stampaValoreManoAvversario(giocatore, giocatore.getCarteScoperte().size()));
        }
    }
    
    public JLabel stampaValoreManoAvversario(Giocatore giocatore, int carte) {
        int nBot = model.getGiocatori().size() - 1;
        int index = model.getGiocatori().indexOf(giocatore);
        double valoreMano = 0;
        
        for(int i = 0; i < carte; i++) {
            try {
                valoreMano += giocatore.getCarteScoperte().get(i).getValoreNumerico();
            } catch (MattaException ex) {
                Logger.getLogger(PartitaOfflineGuiView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        JLabel valoreManoGiocatore = new JLabel("Valore mano:   " + valoreMano);

        Font font = new Font("Player", Font.BOLD, 25);
        valoreManoGiocatore.setFont(font);
        valoreManoGiocatore.setForeground(Color.black);
        
        valoreManoGiocatore.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 120, 350, 40);
        
        sfondo.add(valoreManoGiocatore);
        sfondo.repaint();
        
        return valoreManoGiocatore;
    }
    
    private void pausa(int tempo){
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(PartitaOfflineConsoleView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
