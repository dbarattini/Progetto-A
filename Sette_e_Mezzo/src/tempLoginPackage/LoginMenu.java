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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import partitaOnline.controller.PartitaOnlineController;
import partitaOnline.view.PartitaOnlineGuiView;

public class LoginMenu extends JFrame {

    private Sfondo sfondo;
    private JButton fatto, riprova, indietro;
    private JTextField id;
    private JPasswordField password;
    private JLabel richiediLogin, idLabel, passwordLabel, messLogErrato;
    private String idString = null, passwordString = null;
    private boolean loginConfermato = false;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socketClient;
    private PartitaOnlineController controller;
    private Client client;

    public LoginMenu(Client client) {
        this.client=client;
        this.socketClient=client.getSocketClient();
        setTitle("Login");
        setPreferredSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(1000, 800));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        inizializzaGUI();
    }
    
    public void run(){
        inizializzaConnessione(socketClient);
        this.setVisible(true);
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

        fatto = new JButton(caricaImmagine("dominio/immagini/fatto.png"));
        riprova = new JButton(caricaImmagine("dominio/immagini/riprova.png"));
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        id = new JTextField();
        password = new JPasswordField();
        richiediLogin = new JLabel(caricaImmagine("dominio/immagini/richiediLogin.png"));
        idLabel = new JLabel(caricaImmagine("dominio/immagini/idLogin.png"));
        passwordLabel = new JLabel(caricaImmagine("dominio/immagini/passwordLogin.png"));

        Font font = new Font("Login", 1, 40);
        id.setFont(font);
        password.setFont(font);

        fatto.setBounds(this.getWidth() / 2 - 100, 480, 200, 80);
        riprova.setBounds(this.getWidth() / 2 - 100, 480, 200, 80);
        indietro.setBounds(this.getWidth()/2 - 100, 600, 200, 80);
        id.setBounds(510, 250, 300, 80);
        password.setBounds(510, 350, 300, 80);
        richiediLogin.setBounds(this.getWidth() / 2 - 308, 30, 617, 135);
        idLabel.setBounds(211, 251, 120, 78);
        passwordLabel.setBounds(100, 338, 342, 105);

        fatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idString = id.getText();
                passwordString = new String(password.getPassword());
                if (checkLogin(idString, passwordString)) {
                    controller = new PartitaOnlineController(socketClient, in);
                    new PartitaOnlineGuiView(controller);
                    dispose();
                } else {
                    loginErrato();
                }
            }
        });
        
        riprova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sfondo.remove(riprova);
                sfondo.remove(messLogErrato);
                sfondo.add(fatto);
                sfondo.add(id);
                sfondo.add(password);
                sfondo.add(richiediLogin);
                sfondo.add(idLabel);
                sfondo.add(passwordLabel);
                sfondo.repaint();
            }
        });
        
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.close();
            }
        });
        
        sfondo.add(fatto);
        sfondo.add(indietro);
        sfondo.add(id);
        sfondo.add(password);
        sfondo.add(richiediLogin);
        sfondo.add(idLabel);
        sfondo.add(passwordLabel);
    }

    private boolean checkLogin(String id, String pass) {
        if(id.equals(""))
            id = "-";
        if(pass.equals(""))
            pass = "-";
        try {
            String credenziali = id + " " + pass;
            String messaggio_da_inviare = "login " + credenziali;
            out.println(messaggio_da_inviare);
            String risposta = in.readLine();
            if (risposta.equals("login effetuato")) {
                return true;
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void loginErrato() {
        Font font = new Font("LoginErratoMsg", Font.BOLD, 60);
        messLogErrato = new JLabel("<html>Login non riuscito,"
                + "<br> riprova per favore</html>");
        messLogErrato.setFont(font);
        messLogErrato.setForeground(Color.black);
        messLogErrato.setBounds(this.getWidth() / 2 - 270, 100, 800, 400);

        id.setText("");
        password.setText("");

        sfondo.add(messLogErrato);
        sfondo.add(riprova);
        sfondo.remove(fatto);
        sfondo.remove(id);
        sfondo.remove(password);
        sfondo.remove(richiediLogin);
        sfondo.remove(idLabel);
        sfondo.remove(passwordLabel);
        sfondo.repaint();
    }
    
    public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }

    private ImageIcon caricaImmagine(String nome) {
        ClassLoader loader = getClass().getClassLoader();
        URL percorso = loader.getResource(nome);
        return new ImageIcon(percorso);
    }
}
