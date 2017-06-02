/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuPrePartita;

import dominio.classi_dati.DifficoltaBot;
import dominio.gui.Sfondo;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modules.PartitaOfflineGui;



public class MenuPrePartitaGui extends JFrame{
    private Sfondo sfondo;
    private JButton bot1, bot2, bot3, bot4;
    private ImageIcon dado1, dado1x, dado2, dado2x, dado3, dado3x, dado4, dado4x;
    private JButton diffFacile, diffMedia, diffDifficile, gioca;    
    private JButton indietro;
    private ImageIcon facile1, facile2, medio1, medio2, difficile1, difficile2;
    private JTextField fiches;
    private String fiches_iniziali_inserite;
    private int numero_bot, fiches_iniziali;
    private DifficoltaBot difficolta_bot;
    
    public MenuPrePartitaGui() {      
        setTitle("Impostazioni partita offline");
        setPreferredSize(new Dimension(1000, 800));
	setMinimumSize(new Dimension(1000, 800));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        inizializzaGUI();
    }
    
    private void inizializzaGUI() {
        sfondo = new Sfondo("dominio/immagini/sfondoPreOffline.jpg", 1000, 800);
        sfondo.setBounds(0, 0, 1000, 800);
        add(sfondo);                
                      
        facile1 = caricaImmagine("dominio/immagini/facile.png");
        facile2 = caricaImmagine("dominio/immagini/facile2.png");
        medio1 = caricaImmagine("dominio/immagini/medio.png");
        medio2 = caricaImmagine("dominio/immagini/medio2.png");
        difficile1 = caricaImmagine("dominio/immagini/difficile.png");
        difficile2 = caricaImmagine("dominio/immagini/difficile2.png");
        
        dado1 = caricaImmagine("dominio/immagini/dado1.png");
        dado1x = caricaImmagine("dominio/immagini/dado1X.png");
        dado2 = caricaImmagine("dominio/immagini/dado2.png");
        dado2x = caricaImmagine("dominio/immagini/dado2X.png");
        dado3 = caricaImmagine("dominio/immagini/dado3.png");
        dado3x = caricaImmagine("dominio/immagini/dado3X.png");
        dado4 = caricaImmagine("dominio/immagini/dado4.png");
        dado4x = caricaImmagine("dominio/immagini/dado4X.png");
        
        bot1 = new JButton(dado1);
        bot2 = new JButton(dado2);
        bot3 = new JButton(dado3);
        bot4 = new JButton(dado4);
        diffFacile = new JButton(facile1);
        diffMedia = new JButton(medio1);
        diffDifficile = new JButton(difficile1);
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro.png"));
        gioca = new JButton(caricaImmagine("dominio/immagini/gioca!.png"));
        fiches = new JTextField();
        
        bot1.setBounds(250, 215, 54, 54);
        bot2.setBounds(400, 215, 54, 54);
        bot3.setBounds(550, 215, 54, 54);
        bot4.setBounds(700, 215, 54, 54);
        diffFacile.setBounds(250, 350, 200, 80);
        diffMedia.setBounds(500, 350, 200, 80);
        diffDifficile.setBounds(750, 350, 200, 80);
        indietro.setBounds(100, 680, 200, 80);
        gioca.setBounds(700, 680, 200, 80);
        fiches.setBounds(375, 500, 200, 80);
        
        fiches.setFont(new Font("fiches", 1, 35));
        bot1.setOpaque(false);
        bot1.setContentAreaFilled(false);
        bot1.setBorderPainted(false);
        bot2.setOpaque(false);
        bot2.setContentAreaFilled(false);
        bot2.setBorderPainted(false);
        bot3.setOpaque(false);
        bot3.setContentAreaFilled(false);
        bot3.setBorderPainted(false);
        bot4.setOpaque(false);
        bot4.setContentAreaFilled(false);
        bot4.setBorderPainted(false);
        
        bot1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bot1.setIcon(dado1x);
                bot2.setIcon(dado2);
                bot3.setIcon(dado3);
                bot4.setIcon(dado4);
                bot1.repaint();
                bot2.repaint();
                bot3.repaint();
                bot4.repaint();
                numero_bot = 1;
            };
        });
        
        bot2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bot1.setIcon(dado1);
                bot2.setIcon(dado2x);
                bot3.setIcon(dado3);
                bot4.setIcon(dado4);
                bot1.repaint();
                bot2.repaint();
                bot3.repaint();
                bot4.repaint();
                numero_bot = 2;
            };
        });
        
        bot3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bot1.setIcon(dado1);
                bot2.setIcon(dado2);
                bot3.setIcon(dado3x);
                bot4.setIcon(dado4);
                bot1.repaint();
                bot2.repaint();
                bot3.repaint();
                bot4.repaint();
                numero_bot = 3;
            };
        });
        
        bot4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bot1.setIcon(dado1);
                bot2.setIcon(dado2);
                bot3.setIcon(dado3);
                bot4.setIcon(dado4x);
                bot1.repaint();
                bot2.repaint();
                bot3.repaint();
                bot4.repaint();
                numero_bot = 4;
            };
        });
        
        diffFacile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                diffFacile.setIcon(facile2);
                diffMedia.setIcon(medio1);
                diffDifficile.setIcon(difficile1);
                diffFacile.repaint();
                diffMedia.repaint();
                diffDifficile.repaint();
                difficolta_bot = DifficoltaBot.Facile;
            };
        });
        
        diffMedia.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                
                diffMedia.setIcon(medio2);
                diffFacile.setIcon(facile1);
                diffDifficile.setIcon(difficile1);
                diffFacile.repaint();
                diffMedia.repaint();
                diffDifficile.repaint();
                difficolta_bot = DifficoltaBot.Medio;
            };
        });
        
        diffDifficile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                
                diffDifficile.setIcon(difficile2);
                diffFacile.setIcon(facile1);
                diffMedia.setIcon(medio1);
                diffFacile.repaint();
                diffMedia.repaint();
                diffDifficile.repaint();
                difficolta_bot = DifficoltaBot.Difficile;
            };
        });        
        
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                new GuiMenu();
//                chiudi();
            };
        });
        
        gioca.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fiches_iniziali_inserite = fiches.getText();
                try {
                    checkFichesIniziali();
                    checkNumeroBot();
                    checkDifficoltaBot();
                    dispose();
                    System.out.println(numero_bot + " " + difficolta_bot + " " + fiches_iniziali);
                    new PartitaOfflineGui(numero_bot, difficolta_bot, fiches_iniziali);
                } catch (FichesInizialiException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: Il numero di fiches iniziali dev'essere un numero compreso tra 1 e 100000000.", "Errore", JOptionPane.ERROR_MESSAGE);
                } catch (NumeroBotException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: Selezionare il numero di bot.", "Errore", JOptionPane.ERROR_MESSAGE);
                } catch (DifficoltaBotException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: Selezionare la difficolta dei bot.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            };
        });
        
        sfondo.add(bot1);
        sfondo.add(bot2);
        sfondo.add(bot3);
        sfondo.add(bot4);
        sfondo.add(diffFacile);
        sfondo.add(diffMedia);
        sfondo.add(diffDifficile);
        sfondo.add(indietro);
        sfondo.add(gioca);
        sfondo.add(fiches);
    }
    
    private ImageIcon caricaImmagine(String nome){
	ClassLoader loader = getClass().getClassLoader();
	URL percorso = loader.getResource(nome);
	return new ImageIcon(percorso);
    }
    
    private void checkFichesIniziali() throws FichesInizialiException{
        try{
            fiches_iniziali = Integer.valueOf(fiches_iniziali_inserite);
            if(fiches_iniziali < 1 || fiches_iniziali > 100000000){
                throw new FichesInizialiException();
            }
        }catch(NumberFormatException e){
            throw new FichesInizialiException();
        }
    }
    
    
    private void checkNumeroBot() throws NumeroBotException {
        if(numero_bot == 0){
            throw new NumeroBotException();
        }
    }

    private void checkDifficoltaBot() throws DifficoltaBotException {
        if(difficolta_bot == null){
            throw new DifficoltaBotException();
        }
    }
    
    public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }
}