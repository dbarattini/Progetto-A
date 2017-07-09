package tempLoginPackage;

import comunicazione.Client;
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
import menuPrincipale.MenuPrincipaleGui;
import partitaOnline.controller.PartitaOnlineController;

public class ConfermaRegistrazione  extends JFrame {
    
    private Sfondo sfondo;
    private JButton fatto, riprova, indietro;
    private JTextField codice;
    private JLabel richiediCodice, messCodErrato;
    private String codiceString = null;
    
    private Client client;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socketClient;
    private PartitaOnlineController controller;
    
    public ConfermaRegistrazione() {   // x Mark: se vuoi passargli client o socket della registrazione modifica come vuoi
        client = new Client();
        inizializzaConnessione(client.getSocketClient());
        setTitle("Conferma registrazione");
        setPreferredSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(1000, 800));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        inizializzaGUI();

        setVisible(true);        
    }
    
    private void inizializzaConnessione(Socket socketClient1) {
        try {
            this.socketClient = socketClient1;
            in = new BufferedReader(new InputStreamReader(socketClient1.getInputStream()));
            out = new PrintWriter(socketClient1.getOutputStream(), true);
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
                
        richiediCodice = new JLabel(caricaImmagine("dominio/immagini/richiediCodice.png"));
        fatto = new JButton(caricaImmagine("dominio/immagini/fatto.png"));
        riprova = new JButton(caricaImmagine("dominio/immagini/riprova.png"));
        codice = new JTextField();
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        
        Font font = new Font("Codice Conferma", 1, 40);
        codice.setFont(font);
        
        richiediCodice.setBounds(this.getWidth()/2 - 313, 50, 626, 132);
        fatto.setBounds(this.getWidth()/2 - 100, 400, 200, 80);
        riprova.setBounds(this.getWidth()/2 - 100, 400, 200, 80);
        codice.setBounds(this.getWidth()/2 - 100, 300, 200, 80);;
        indietro.setBounds(this.getWidth()/2 - 100, 600, 200, 80);
        
        fatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codiceString = codice.getText();
                if(checkCodice(codiceString)) {
                    // vai su LoginMenu per loggarti e giocare
                } else
                    codiceErrato();
            }
        });
        
        riprova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sfondo.remove(messCodErrato);
                sfondo.remove(riprova);
                sfondo.add(richiediCodice);
                sfondo.add(fatto);
                sfondo.add(codice);
                sfondo.add(indietro);
                sfondo.repaint();
            }
        });
        
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // torna indietro a RegistrazioneMenu
            }
        });
        
        sfondo.add(richiediCodice);
        sfondo.add(fatto);
        sfondo.add(codice);
        sfondo.add(indietro);
        sfondo.repaint();
    }
    
    private boolean checkCodice(String codice) {
        
        // controlla codice, tutto per te Mark :P
        
        return false;
    }
    
    private void codiceErrato() {
        Font font = new Font("CodErratoMsg", Font.BOLD, 60);
        messCodErrato = new JLabel("<html>&nbsp&nbsp Codice errato,"
                + "<br> riprova per favore</html>");
        messCodErrato.setFont(font);
        messCodErrato.setForeground(Color.black);
        messCodErrato.setBounds(this.getWidth() / 2 - 270, 50, 800, 400);

        codice.setText("");

        sfondo.add(messCodErrato);
        sfondo.add(riprova);
        sfondo.remove(richiediCodice);
        sfondo.remove(fatto);
        sfondo.remove(codice);
        sfondo.remove(indietro);
        sfondo.repaint();
    }
    
    private ImageIcon caricaImmagine(String nome) {
        ClassLoader loader = getClass().getClassLoader();
        URL percorso = loader.getResource(nome);
        return new ImageIcon(percorso);
    }
}