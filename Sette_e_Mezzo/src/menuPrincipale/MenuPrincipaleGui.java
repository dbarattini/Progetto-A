/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuPrincipale;

import menuOpzioni.OpzioniGui;
import menuRegole.RegoleGui;
import dominio.classi_dati.OpzioniMenu;
import dominio.gui.Sfondo;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import menuPrePartita.MenuPrePartitaGui;

public class MenuPrincipaleGui extends JFrame implements ActionListener{
    MenuPrePartitaGui menu_pre_partita;
    RegoleGui menu_regole;
    OpzioniGui menu_opzioni;
    private JButton partita_off, partita_on, regole, opzioni;
    private Sfondo sfondo;
    
    public MenuPrincipaleGui() { 
        this.menu_regole = new RegoleGui();
        this.menu_regole.addIndietroActionListener(this);
        this.menu_pre_partita = new MenuPrePartitaGui();
        menu_pre_partita.addIndietroActionListener(this);
        this.menu_opzioni = new OpzioniGui();
        menu_opzioni.addIndietroActionListener(this);
        
        
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
                runOpzione(OpzioniMenu.GiocaOffline);
            };
        });
        
        partita_on.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                runOpzione(OpzioniMenu.GiocaOnline);
            };
        });
        
        regole.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                runOpzione(OpzioniMenu.RegoleDiGioco);
            }
        });
        
        opzioni.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                runOpzione(OpzioniMenu.Impostazioni);
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
    
    private void runOpzione(OpzioniMenu opzione){
        this.setVisible(false);
        switch(opzione){
            case GiocaOffline:  menu_pre_partita.setVisible(true);
                                break;
            case GiocaOnline : System.out.println("on");
                                break;
            case Impostazioni: menu_opzioni.setVisible(true);
                                break;
            case RegoleDiGioco: menu_regole.setVisible(true);
                                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(menu_pre_partita.isVisible())
            menu_pre_partita.setVisible(false);
        else if(menu_regole.isVisible())
            menu_regole.setVisible(false);
        else if (menu_opzioni.isVisible())
            menu_opzioni.setVisible(false);
        this.setVisible(true);
    }
}
