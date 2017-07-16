package menuOnlineGui;

import net.Client;
import dominio.gui.Sfondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import partitaOnline.controller.PartitaOnlineController;

public class MenuRecuperoPassword extends JFrame {

    private Sfondo sfondo;
    private JButton invia, riprova, indietro;
    private JTextField recupero;
    private JLabel richiediRecupero, messInfoCorretta;
    private String infoString = null;
    private Client client;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socketClient;
    private PartitaOnlineController controller;

    /**
     * 
     * @param client oggetto client
     */
    public MenuRecuperoPassword(Client client) {
        this.client = client;
        socketClient = client.getSocketClient();
        setTitle("Conferma registrazione");
        setPreferredSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(1000, 800));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        inizializzaGUI();
    }

    /**
     * inizializza la connessione tra client e server
     */
    public void run() {
        inizializzaConnessione(socketClient);
        this.setVisible(true);
    }

    private void inizializzaConnessione(Socket socketClient) {
        try {
            this.socketClient = socketClient;
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            out = new PrintWriter(socketClient.getOutputStream(), true);
        } catch (UnknownHostException ex) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.err.println("Impossibile connnettersi al server");
        }
    }

    private void inizializzaGUI() {
        sfondo = new Sfondo("dominio/immagini/sfondo.png", 995, 765);
        sfondo.setBounds(0, 0, 1000, 800);
        add(sfondo);

        richiediRecupero = new JLabel(caricaImmagine("dominio/immagini/richiediRecupero.png"));
        invia = new JButton(caricaImmagine("dominio/immagini/invia.png"));
        riprova = new JButton(caricaImmagine("dominio/immagini/riprova.png"));
        recupero = new JTextField();
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));

        Font font = new Font("Recupero Password", 1, 40);
        recupero.setFont(font);

        richiediRecupero.setBounds(this.getWidth() / 2 - 313, 50, 626, 132);
        invia.setBounds(this.getWidth() / 2 - 100, 400, 200, 80);
        riprova.setBounds(this.getWidth() / 2 - 100, 400, 200, 80);
        recupero.setBounds(this.getWidth() / 2 - 150, 300, 300, 80);;
        indietro.setBounds(this.getWidth() / 2 - 100, 600, 200, 80);

        recupero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaRecupero();
            }

        });
        invia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaRecupero();
            }
        });

        riprova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sfondo.remove(messInfoCorretta);
                sfondo.remove(riprova);
                sfondo.add(richiediRecupero);
                sfondo.add(invia);
                sfondo.add(recupero);
                sfondo.add(indietro);
                sfondo.repaint();
            }
        });

        sfondo.add(richiediRecupero);
        sfondo.add(invia);
        sfondo.add(recupero);
        sfondo.add(indietro);
        sfondo.repaint();
    }

    private boolean checkInfo(String info) {
        try {
            String messaggio_da_inviare = "recupero " + info;
            out.println(messaggio_da_inviare);
            String risposta = in.readLine();
            if (risposta.equals("recupero inviato")) {
                return true;
            }

        } catch (IOException ex) {
            Logger.getLogger(MenuRecuperoPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void inviaRecupero() {
        infoString = recupero.getText();
        if (checkInfo(infoString)) {
            infoCorretta();
        } else {
            infoErrata();
        }
    }

    private void infoErrata() {
        Font font = new Font("InfoErrataMsg", Font.BOLD, 60);
        messInfoCorretta = new JLabel("<html> Nessuna corrispondenza,"
                + "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp riprova per favore</html>");
        messInfoCorretta.setFont(font);
        messInfoCorretta.setForeground(Color.black);
        messInfoCorretta.setBounds(this.getWidth() / 2 - 350, 50, 800, 400);

        recupero.setText("");

        sfondo.add(messInfoCorretta);
        sfondo.add(riprova);
        sfondo.remove(richiediRecupero);
        sfondo.remove(invia);
        sfondo.remove(recupero);
        sfondo.remove(indietro);
        sfondo.repaint();
    }

    private void infoCorretta() {
        Font font = new Font("InfoCorrettaMsg", Font.BOLD, 35);
        messInfoCorretta = new JLabel("<html> La password è stata inviata all'email registrata,"
                + "<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp fare click su indietro per uscire</html>");
        messInfoCorretta.setFont(font);
        messInfoCorretta.setForeground(Color.black);
        messInfoCorretta.setBounds(this.getWidth() / 2 - 370, 50, 800, 400);

        sfondo.add(messInfoCorretta);
        sfondo.remove(richiediRecupero);
        sfondo.remove(invia);
        sfondo.remove(recupero);
        sfondo.repaint();
    }

    /**
     * 
     * @param l action listener
     */
    public void addIndietroActionListener(ActionListener l) {
        indietro.addActionListener(l);
    }

    private ImageIcon caricaImmagine(String nome) {
        ClassLoader loader = getClass().getClassLoader();
        URL percorso = loader.getResource(nome);
        return new ImageIcon(percorso);
    }
}
