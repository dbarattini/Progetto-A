package tempLoginPackage;

import dominio.gui.Sfondo;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import menuPrincipale.MenuPrincipaleGui;

public class ScegliRegistrazioneLogin extends JFrame {
    
    private Sfondo sfondo;
    private JButton login, regis, indietro;
    
    public ScegliRegistrazioneLogin() {
        setTitle("Registrazione");
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
                
        login = new JButton(caricaImmagine("dominio/immagini/login.png"));
        regis = new JButton(caricaImmagine("dominio/immagini/registrati.png"));
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        
        login.setBounds(this.getWidth() / 2 - 100, 200, 200, 80);
        regis.setBounds(this.getWidth() / 2 - 100, 400, 200, 80);
        indietro.setBounds(this.getWidth() / 2 - 100, 600, 200, 80);
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket tempSocket = null;  // x mark: pensaci tu, non so cosa passargli :O
                new LoginMenu(tempSocket);
                dispose();
            }
        });
        
        regis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket tempSocket = null;  // x mark: idem :D
                new RegistrazioneMenu(tempSocket);
                dispose();
            }
        });
        
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipaleGui();
            }
        });
        
        sfondo.add(login);
        sfondo.add(regis);
        sfondo.add(indietro);
        sfondo.repaint();
    }
    
    private ImageIcon caricaImmagine(String nome) {
        ClassLoader loader = getClass().getClassLoader();
        URL percorso = loader.getResource(nome);
        return new ImageIcon(percorso);
    }
}