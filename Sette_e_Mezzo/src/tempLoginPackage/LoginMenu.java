package tempLoginPackage;

import dominio.gui.Sfondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginMenu extends JFrame {
    
    private Sfondo sfondo;
    private JButton fatto, indietro;
    private JTextField id;
    private JPasswordField password;
    private JLabel richiediLogin, idLabel, passwordLabel, messLogErrato;
    private String idString = null, passwordString = null;
    private boolean loginConfermato = false;
    
    public LoginMenu() {
        setTitle("Sette e Mezzo");
        setPreferredSize(new Dimension(1000, 800));
	setMinimumSize(new Dimension(1000, 800));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);       
        
        inizializzaGUI();
        
        setVisible(true);
    }
    
    private void inizializzaGUI() {
        sfondo = new Sfondo("dominio/immagini/sfondo.png", 995, 765);
        sfondo.setBounds(0, 0, 1000, 800);
        add(sfondo);
        
        fatto = new JButton(caricaImmagine("dominio/immagini/fatto.png"));
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        id = new JTextField();
        password = new JPasswordField();
        richiediLogin = new JLabel(caricaImmagine("dominio/immagini/richiediLogin.png"));
        idLabel = new JLabel(caricaImmagine("dominio/immagini/idLogin.png"));
        passwordLabel = new JLabel(caricaImmagine("dominio/immagini/passwordLogin.png"));
        
        Font font = new Font("Login", 1, 40);
        id.setFont(font);
        password.setFont(font);
        
        fatto.setBounds(this.getWidth()/2 - 100, 600, 200, 80);
        indietro.setBounds(this.getWidth()/2 - 100, 600, 200, 80);
        id.setBounds(510, 250, 300, 80);
        password.setBounds(510, 350, 300, 80);
        richiediLogin.setBounds(this.getWidth()/2 - 308, 30, 617, 135);
        idLabel.setBounds(211, 251, 120, 78);
        passwordLabel.setBounds(100, 338, 342, 105);
        
        fatto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                idString = id.getText();
                passwordString = Arrays.toString(password.getPassword());
                if(checkLogin(idString, passwordString)) {
                    // apre gioco online
                } else {
                    loginErrato();
                }
            };
        });
        
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sfondo.remove(indietro);
                sfondo.remove(messLogErrato);
                sfondo.add(fatto);
                sfondo.add(id);
                sfondo.add(password);
                sfondo.add(richiediLogin);
                sfondo.add(idLabel);
                sfondo.add(passwordLabel);
                sfondo.repaint();
            };
        });
        
        sfondo.add(fatto);
        sfondo.add(id);
        sfondo.add(password);
        sfondo.add(richiediLogin);
        sfondo.add(idLabel);
        sfondo.add(passwordLabel);
    }
    
    private boolean checkLogin(String id, String pass) {
        // controlla da server e db se il login è corretto
        
        return false; // temporaneo
    }
    
    private void loginErrato() {
        Font font = new Font("LoginErratoMsg", Font.BOLD, 60);
        messLogErrato = new JLabel("<html>Login non riuscito,"
                                + "<br> riprova per favore</html>");
        messLogErrato.setFont(font);
        messLogErrato.setForeground(Color.black);
        messLogErrato.setBounds(this.getWidth()/2 - 270, 150, 800, 400);
        
        id.setText("");
        password.setText("");
        
        sfondo.add(messLogErrato);
        sfondo.add(indietro);
        sfondo.remove(fatto);
        sfondo.remove(id);
        sfondo.remove(password);
        sfondo.remove(richiediLogin);
        sfondo.remove(idLabel);
        sfondo.remove(passwordLabel);
        sfondo.repaint();
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }
}