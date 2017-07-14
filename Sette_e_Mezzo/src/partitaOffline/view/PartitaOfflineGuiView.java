package partitaOffline.view;

import dominio.classi_dati.StatoMano;
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
import java.util.HashMap;
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
import dominio.events.FineRound;
import dominio.events.*;
import partitaOffline.events.GiocatoreLocaleEvent;
import dominio.events.MazzierePerde;
import dominio.events.MazzoRimescolato;
import dominio.events.RichiediGiocata;
import dominio.events.RichiediNome;
import dominio.events.RichiediPuntata;
import dominio.events.RisultatoManoParticolare;
import dominio.events.SetGiocata;
import dominio.events.SetNome;
import dominio.events.SetPuntata;
import dominio.events.Vittoria;
import menuPrePartita.MenuPrePartitaGui;

public class PartitaOfflineGuiView extends JFrame implements PartitaOfflineView, Observer{
    private CopyOnWriteArrayList<ViewEventListener> listeners;
    private PartitaOfflineModel model;    
    private Sfondo sfondo;
    private String nome, puntataStr, giocataStr;
    private JTextField askNome, puntata;
    private JButton carta, stai;
    private boolean needCartaCoperta = true, needToMarkMazziere = false;
    private ArrayList<JLabel> carteCoperteBots = new ArrayList<>();
    private Map<String, JLabel> valoriMano = new HashMap<>();
    private final int pausa_breve = 1000; //ms
    private final int pausa_lunga = 2000; //ms
    private MenuPrePartitaGui menu_pre_partita;
    private AudioPlayer audio;
    private JLabel msgDaStampare;
    private JButton esci;
    
    
    public PartitaOfflineGuiView(PartitaOfflineModel model, MenuPrePartitaGui menu_pre_partita) {
        listeners = new CopyOnWriteArrayList<>();                
        this.model = model;
        this.model.addObserver(this);
        this.menu_pre_partita = menu_pre_partita;
        
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
        
        audio = model.getAudio();
        inizializzaCartaStaiButtons();
        inizializzaExitButton();
        
        setVisible(true);
    }
    
    private void inizializzaCartaStaiButtons() {
        carta = new JButton(caricaImmagine("dominio/immagini/carta.png"));
        stai = new JButton(caricaImmagine("dominio/immagini/stai.png"));

        carta.setBounds(1060, 580, 140, 56);
        stai.setBounds(1060, 500, 140, 56);

        carta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giocataStr = "carta";
            }
        });
        
        stai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giocataStr = "sto";
            }
        });
    }
    
    private void inizializzaExitButton() {
        esci = new JButton(caricaImmagine("dominio/immagini/esci.png"));
        esci.setBounds(35, 600, 96, 58);
        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    audio.ferma("soundTrack");
                    audio.riavvolgi("soundTrack");
                } catch (CanzoneNonTrovataException ex) {
                    ex.printStackTrace();
                }
                menu_pre_partita.setVisible(true);
                dispose();
            }
        });
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
            richiediNome();            
        } else if(arg instanceof Error) {
            String errore = ((Error) arg).getMessage();
            JOptionPane.showMessageDialog(null, errore, "Errore", JOptionPane.ERROR_MESSAGE);
        } else if(arg instanceof EstrattoMazziere) {
            estrazioneMazziere();
        } else if(arg instanceof MazzoRimescolato) {
            rimescoloMazzo();
        } else if(arg instanceof RisultatoManoParticolare) {
            manoParticolarePlayer();
        } else if(arg instanceof FineManoAvversario) {
            if(needCartaCoperta)
                stampaCartaCoperta();
            stampaManoAvversario(((FineManoAvversario) arg).getNome());
        } else if(arg instanceof FineRound) {
            Giocatore giocatore = ((FineRound) arg).getGiocatore();
            if(giocatore != model.getGiocatoreLocale())
                scopriCartaCoperta(giocatore);
            if(!needCartaCoperta)
                needCartaCoperta = true;
            stampaValoreManoFineRound(giocatore);
            stampaMessaggioFineRound(giocatore);
            checkFineRound(giocatore);
        } else if(arg instanceof MazzierePerde) {
            //todo mostra che il mazziere ha perso
            stampaMsg(model.getMazziere().getNome() + " ha perso!", 60);
            pausa(pausa_breve);
            sfondo.remove(msgDaStampare);
            sfondo.repaint();
        } else if(arg instanceof AggiornamentoMazziere) {
            //todo mostra che é stato scelto un nuovo mazziere
            needToMarkMazziere = false;
            sfondo.removeAll();
            estrazioneMazziere();
        } else if(arg instanceof GameOver) {
            stampaMsg("Hai terminato le fiches! > Game Over <", 50);
            pausa(pausa_lunga);
            try {
                audio.ferma("soundTrack");
                audio.riavvolgi("soundTrack");
            } catch (CanzoneNonTrovataException ex) {
                ex.printStackTrace();
            }
            menu_pre_partita.setVisible(true);
            dispose();
        } else if(arg instanceof Vittoria) {
            stampaMsg("Gli avversari hanno perso! > Vittoria <", 50);
            pausa(pausa_lunga);
            try {
                audio.ferma("soundTrack");
                audio.riavvolgi("soundTrack");
            } catch (CanzoneNonTrovataException ex) {
                ex.printStackTrace();
            }
            menu_pre_partita.setVisible(true);
            dispose();
        }
    }

    @Override
    public void GiocatoreLocaleEventReceived(GiocatoreLocaleEvent evt) {
        if(evt.getArg() instanceof RichiediPuntata) {
            if(needCartaCoperta)
                stampaCartaCoperta();
            richiediPuntata();
        } else if(evt.getArg() instanceof Error) {
            String errore = ((Error) evt.getArg()).getMessage();
            JOptionPane.showMessageDialog(null, errore, "Errore", JOptionPane.ERROR_MESSAGE);
        } else if(evt.getArg() instanceof RichiediGiocata) {
            stampaGiocataPlayer();
            if(needCartaCoperta)
                stampaCartaCoperta();
        }
    }
    
    // stampa la richiesta del nome del giocatore e attende fino all'inserimento
    private void richiediNome() {
        nome = null;
        askNome = new JTextField();
        JButton askNomeButton = new JButton(caricaImmagine("dominio/immagini/fatto.png"));
        JLabel askNomeLabel = new JLabel(caricaImmagine("dominio/immagini/richiediNome.png"));
        ActionListener action_nome_inserito = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nome = askNome.getText();
            };
        };
            
        askNome.setFont(new Font("nome", 1, 40));
            
        askNome.setBounds(this.getWidth()/2 - 125, 300, 250, 80);
        askNomeButton.setBounds(this.getWidth()/2 - 100, 400, 200, 80);
        askNomeLabel.setBounds(this.getWidth()/2 - 200, 100, 400, 80);
        
        askNome.addActionListener(action_nome_inserito);  
        askNomeButton.addActionListener(action_nome_inserito);
            
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
    
    // stampa l'animazione di estrazione mazziere, con messaggio finale a mazziere estratto
    private void estrazioneMazziere() {
        int nGiocatori = model.getGiocatori().size();
        
        for(int i = 0; i < nGiocatori; i++)
            stampaNomeFiches(model.getGiocatori().get(i));
        
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
        needToMarkMazziere = true;
        for(int i = 0; i < nGiocatori; i++)
            stampaNomeFiches(model.getGiocatori().get(i));
                
        sfondo.repaint();
    }
    
    // stampa nome e fiches del giocatore passato, i giocatori hanno nome nero, il mazziere arancione
    private void stampaNomeFiches(Giocatore giocatore) {
        int nBot = model.getGiocatori().size() - 1;
        int index = model.getGiocatori().indexOf(giocatore);
        JLabel nomeGiocatore = new JLabel("Nome:   " + giocatore.getNome());
        JLabel fichesGiocatore = new JLabel("Fiches:   " + giocatore.getFiches());
        
        Font font = new Font("Player", Font.BOLD, 25);
        nomeGiocatore.setFont(font);
        fichesGiocatore.setFont(font);
        fichesGiocatore.setForeground(Color.black);
        
        if(needToMarkMazziere) {
            if(giocatore.isMazziere())
                nomeGiocatore.setForeground(Color.orange);
            else
                nomeGiocatore.setForeground(Color.black);        
        } else
            nomeGiocatore.setForeground(Color.black);
        
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
    
    // stampa il valore della mano del giocatore passato, già correttamente posizionato
    private JLabel stampaValoreMano(Giocatore giocatore) {
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
    
    // stampa la carta "carta" a x, y
    private JLabel stampaCarta(int x, int y, String carta) {
        JLabel card = new JLabel(caricaImmagine("dominio/immagini/mazzo/" + carta + ".png"));
        
        card.setBounds(x, y, 75, 113);        
        
        sfondo.add(card);        
        sfondo.repaint();
        
        return card;
    }
    
    // stampa i bottoni e il campo di testo per permettere al giocatore di puntare quanto vuole ( minimo 1, massimo ALL IN )
    private void richiediPuntata() {
        puntataStr = null;
        JButton punta = new JButton(caricaImmagine("dominio/immagini/punta.png"));
        JButton allIN = new JButton(caricaImmagine("dominio/immagini/all_in.png"));
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
        sfondo.repaint();
        
        while(puntataStr == null) {
            pausa(100);
        }
            
        sfondo.remove(punta);
        sfondo.remove(allIN);
        sfondo.remove(puntata);
        sfondo.repaint();
            
        fireViewEvent(new SetPuntata(puntataStr));
        
        pausa(pausa_breve);
    }
    
    // stampa i bottoni stai e carta per permettere al giocatore di scegliere la mossa, nel caso di carta stampa la carta
    private void stampaGiocataPlayer() {
        giocataStr = null;
        Carta lastCard;
        if(!model.getGiocatoreLocale().getCarteScoperte().isEmpty()) {
            lastCard = model.getGiocatoreLocale().getUltimaCartaOttenuta();
            int index = model.getGiocatoreLocale().getCarteScoperte().indexOf(lastCard);            
            stampaCarta(this.getWidth()/2 - 95 + index*35, 3*this.getHeight()/4 - 60, lastCard.toString());
        }
        aggiornaValoreManoPlayer();
        
        sfondo.add(carta);
        sfondo.add(stai);
        sfondo.repaint();
        
        while(giocataStr == null) {
            pausa(pausa_breve);
        }
        
        fireViewEvent(new SetGiocata(giocataStr));
            
        sfondo.remove(carta);
        sfondo.remove(stai);
        sfondo.repaint();
    }
    
    // stampa l'ultima carta del giocatore in seguito a una mano particolare ( sballato, 7 e mezzo o 7 e mezzo reale )
    private void manoParticolarePlayer() {
        Carta ultimaOttenuta = model.getGiocatoreLocale().getUltimaCartaOttenuta();
        int index = model.getGiocatoreLocale().getCarteScoperte().indexOf(ultimaOttenuta);
        stampaCarta(this.getWidth()/2 - 95 + index*35, 3*this.getHeight()/4 - 60, ultimaOttenuta.toString());
        
        sfondo.remove(valoriMano.get(model.getGiocatoreLocale().getNome()));
        valoriMano.remove(model.getGiocatoreLocale().getNome());
        valoriMano.put(model.getGiocatoreLocale().getNome(), stampaStato(model.getGiocatoreLocale()));
        pausa(pausa_breve);
    }
    
    // stampa il mazzo che viene rimescolato con relativo messaggio
    private void rimescoloMazzo() {
        ArrayList<JLabel> carte = new ArrayList<>();
        Font font = new Font("Deck Shuffle", Font.BOLD, 70);
        JLabel rimescoloMsg = new JLabel("Rimescolo il mazzo...");
        rimescoloMsg.setFont(font);
        rimescoloMsg.setForeground(Color.black);
        int strWidth = rimescoloMsg.getFontMetrics(font).stringWidth("Rimescolo il mazzo...");
        rimescoloMsg.setBounds(this.getWidth()/2 - strWidth/2, this.getHeight()/2, strWidth, 90);
        
        sfondo.add(rimescoloMsg);
        sfondo.repaint();
        
        try {
            audio.ferma("soundTrack");
            audio.riproduci("deckShuffle");
        } catch (CanzoneNonTrovataException ex) {
            ex.printStackTrace();
        }
        
        for(int i = 0; i < 12; i++)
            carte.add(stampaCartaMobileDeckShuffle(this.getWidth()/2 - 187 - i*5, this.getHeight()/2 - 100, "retroCarta", i));
        
        pausa(pausa_breve);
        
        try {
            audio.riavviaInLoop("soundTrack");
            audio.riavvolgi("deckShuffle");
        } catch (CanzoneNonTrovataException ex) {
            ex.printStackTrace();
        }
                
        sfondo.remove(rimescoloMsg);
        for(JLabel cartaTmp : carte)
            sfondo.remove(cartaTmp);
        sfondo.repaint();
    }
    
    // serve solo come oggetto del deck shuffle
    private JLabel stampaCartaMobileDeckShuffle(int x, int y, String carta, int spazio) {
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
    
    // stampa la carta coperta degli avversari non visibile e quella del giocatore locale visibile
    private void stampaCartaCoperta() {
        int nGiocatori = model.getGiocatori().size();
        sfondo.removeAll();
        sfondo.repaint();
        for(int i = 0; i < nGiocatori; i++)
            stampaNomeFiches(model.getGiocatori().get(i));
        
        pausa(pausa_breve);
        
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
                try{
                    stampaCarta(this.getWidth()/2 - 125, 3*this.getHeight()/4 - 60, model.getGiocatori().get(i).getCartaCoperta().toString());
                } catch(java.lang.NullPointerException e){
                    // pass # eccezione dovuta al tentativo di stampare una carta anche a partita finita
                }
                valoriMano.put(model.getGiocatoreLocale().getNome(), stampaValoreMano(model.getGiocatoreLocale()));
            }
            pausa(pausa_breve);
        }
        
        sfondo.remove(messaggioCartaCoperta);
        sfondo.add(esci);
        sfondo.repaint();
        
        needCartaCoperta = false;
    }
    
    // stampa le carte pescate dall'avversario ( e lo stato alterato, nel caso si sia alterato )    
    private void stampaManoAvversario(String nome) {
        Giocatore giocatore = getGiocatore(nome);
        JLabel valoreMano = null;
        int index = model.getGiocatori().indexOf(giocatore);
        
        if(!giocatore.getCarteScoperte().isEmpty()) {
            for(int i = 0; i < giocatore.getCarteScoperte().size(); i++) {                
                valoreMano = stampaValoreManoAttualeAvversario(giocatore, i+1);
                stampaCarta((this.getWidth()*(2*index+1))/((model.getGiocatori().size()-1)*2) - 95 + i*35, 180, giocatore.getCarteScoperte().get(i).toString());                                
                pausa(pausa_breve);
                sfondo.remove(valoreMano);
            }
            if(giocatore.getStatoMano() == StatoMano.OK)
                valoriMano.put(giocatore.getNome(), stampaValoreManoAttualeAvversario(giocatore, giocatore.getCarteScoperte().size()));
            else {
                valoriMano.put(giocatore.getNome(), stampaStato(giocatore));
                scopriCartaCoperta(giocatore);
                pausa(pausa_breve);
            }                 
        } else {
            valoriMano.put(giocatore.getNome(), stampaValoreManoAttualeAvversario(giocatore, -1));
            pausa(pausa_breve);
        }
    }
    
    // serve durante la stampa della mano avversario per aggiornare il valore mano
    private JLabel stampaValoreManoAttualeAvversario(Giocatore giocatore, int carte) {
        int nBot = model.getGiocatori().size() - 1;
        int index = model.getGiocatori().indexOf(giocatore);
        double valoreMano = 0;
        
        for(int i = 0; i < carte; i++) {
            try {
                valoreMano += giocatore.getCarteScoperte().get(i).getValoreNumerico();
            } catch (MattaException ex) {
                valoreMano = 7;
            }
        }
        
        JLabel valoreManoGiocatore;
        if(carte != -1)
            valoreManoGiocatore = new JLabel("Valore attuale:   " + valoreMano);
        else
            valoreManoGiocatore = new JLabel("Valore attuale:   0");

        Font font = new Font("Player", Font.BOLD, 25);
        valoreManoGiocatore.setFont(font);
        valoreManoGiocatore.setForeground(Color.black);
        
        valoreManoGiocatore.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 120, 350, 40);
        
        sfondo.add(valoreManoGiocatore);
        sfondo.repaint();
        
        return valoreManoGiocatore;
    }
    
    // scopre la carta coperta (usato se stato alterato o a fine round per vedere il valore a fine round)
    private void scopriCartaCoperta(Giocatore giocatore) {
        int index = model.getGiocatori().indexOf(giocatore);

        carteCoperteBots.get(index).setIcon(caricaImmagine("dominio/immagini/mazzo/" + giocatore.getCartaCoperta().toString() + ".png"));
        sfondo.repaint();
    }
    
    // stampa il cambio di stato di un giocatore: SBALLATO in rosso, SEM in ciano e SEMR in magenta
    private JLabel stampaStato(Giocatore giocatore) {
        int nBot = model.getGiocatori().size() - 1;
        int index = model.getGiocatori().indexOf(giocatore);
        JLabel stato = null;
        
        Font font = new Font("StatoMano", Font.BOLD, 25);
        
        switch (giocatore.getStatoMano()) {
            case Sballato:
                stato = new JLabel("SBALLATO");
                stato.setFont(font);
                stato.setForeground(Color.red);
                break;
            case SetteeMezzo:
                stato = new JLabel("SETTE E MEZZO");
                stato.setFont(font);
                stato.setForeground(Color.cyan);
                try {
                    audio.riproduci("applausiSEM");
                    audio.riavvolgi("applausiSEM");
                } catch (CanzoneNonTrovataException ex) {
                    ex.printStackTrace();
                }
                break;
            case SetteeMezzoReale:
                stato = new JLabel("SETTE E MEZZO REALE");
                stato.setFont(font);
                stato.setForeground(Color.magenta);
                try {
                    audio.riproduci("applausiSEM");
                    audio.riavvolgi("applausiSEM");
                } catch (CanzoneNonTrovataException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }
        
        if(giocatore != model.getGiocatoreLocale())
            stato.setBounds((this.getWidth()*(2*index+1))/(nBot*2) - 125, 120, 350, 40);
        else
            stato.setBounds(this.getWidth()/4 - 175, 3*this.getHeight()/4 + 20, 350, 40);
        
        sfondo.add(stato);
        sfondo.repaint();
        
        return stato;
    }
    
    // ritorna il giocatore dato il nome
    private Giocatore getGiocatore(String nome) {
        Giocatore giocatore = null;
        for(Giocatore gioc : model.getGiocatori()) {
            if(gioc.getNome().equals(nome))
                giocatore = gioc;
        }
        return giocatore;
    }
    
    // stampa il valore mano a fine round ( solo per gli avversari perchè quello del giocatore è sempre visibile )
    private void stampaValoreManoFineRound(Giocatore giocatore) {
        if(giocatore != model.getGiocatoreLocale()) {
            if(giocatore.getStatoMano() != StatoMano.Sballato) {
                stampaValoreMano(giocatore);
                valoriMano.get(giocatore.getNome()).setText("Valore mano:   " + giocatore.getValoreMano());
                sfondo.repaint();

                pausa(pausa_breve);
            }
        }
    }
    
    // aggiorna il valore mano del giocatore ( usato ad ogni giocata )
    private void aggiornaValoreManoPlayer() {
        Giocatore giocatore = model.getGiocatoreLocale();
        valoriMano.get(giocatore.getNome()).setText("Valore mano:   " + giocatore.getValoreMano());
        sfondo.repaint();
    }
    
    // stampa per il giocatore passato il messaggio di fine round di vincita o perdita
    private void stampaMessaggioFineRound(Giocatore giocatore) {
        Giocatore mazziere = model.getMazziere();
        String msg = "";
        
        if(!giocatore.isMazziere()) {
            if(mazziere.getStatoMano() == StatoMano.Sballato) {
                if(giocatore.getStatoMano() == StatoMano.Sballato)
                    msg = giocatore.getNome() + " paga " + giocatore.getPuntata() + " al mazziere";
                else if ((giocatore.getStatoMano() == StatoMano.OK) || (giocatore.getStatoMano() == StatoMano.SetteeMezzo))
                    msg = giocatore.getNome() + " riceve " + giocatore.getPuntata() + " dal mazziere";
                else if(giocatore.getStatoMano() == StatoMano.SetteeMezzoReale)
                    msg = giocatore.getNome() + " riceve " + 2*giocatore.getPuntata() + " dal mazziere";
            } else {
                if((giocatore.getStatoMano() == StatoMano.Sballato) || (giocatore.getValoreMano() <= mazziere.getValoreMano()))
                    msg = giocatore.getNome() + " paga " + giocatore.getPuntata() + " al mazziere";
                else if (giocatore.getValoreMano() > mazziere.getValoreMano())
                    msg = giocatore.getNome() + " riceve " + giocatore.getPuntata() + " dal mazziere";
                else if ((giocatore.getStatoMano() == StatoMano.SetteeMezzoReale) && (giocatore.getValoreMano() > mazziere.getValoreMano()))
                    msg = giocatore.getNome() + " riceve " + 2*giocatore.getPuntata() + " dal mazziere";
                else if(mazziere.getStatoMano() == StatoMano.SetteeMezzoReale)
                    msg = giocatore.getNome() + " paga " + 2*giocatore.getPuntata() + " al mazziere";
            }
        } else
            msg = "Il mazziere regola i suoi conti";
        
        Font font = new Font("MsgFineRound", Font.BOLD, 70);
        JLabel msgFineRound = new JLabel(msg);
        msgFineRound.setFont(font);
        msgFineRound.setForeground(Color.black);
        int strWidth = msgFineRound.getFontMetrics(font).stringWidth(msg);
        msgFineRound.setBounds(this.getWidth()/2 - strWidth/2, this.getHeight()/2 - 60, strWidth, 90);
        
        sfondo.add(msgFineRound);
        sfondo.repaint();
        
        pausa(pausa_lunga);
        
        sfondo.remove(msgFineRound);
    }
    
    // controlla la fine effettiva del round per tutti i giocatori e resetta carte coperte e valori mano
    private void checkFineRound(Giocatore giocatore) {
        if(giocatore == model.getGiocatoreLocale()) {
            carteCoperteBots.clear();
            valoriMano.clear();
            sfondo.remove(esci);
            sfondo.repaint();
        }
    }
    
    // stampa il messaggio passato
    private void stampaMsg(String msg, int dimensione) {
        Font font = new Font("MsgDaStampare", Font.BOLD, dimensione);
        msgDaStampare = new JLabel(msg);
        msgDaStampare.setFont(font);
        msgDaStampare.setForeground(Color.black);
        int strWidth = msgDaStampare.getFontMetrics(font).stringWidth(msg);
        msgDaStampare.setBounds(this.getWidth() / 2 - strWidth / 2, this.getHeight() / 2 - 60, strWidth, 90);

        sfondo.add(msgDaStampare);
        sfondo.repaint();
    }
    
    // stoppa il thread per tempo ms
    private void pausa(int tempo){
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
