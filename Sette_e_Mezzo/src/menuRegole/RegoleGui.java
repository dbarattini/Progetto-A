package menuRegole;

import dominio.gui.Sfondo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RegoleGui extends JFrame{
    private JButton indietro;
    private Regole regole_gioco;
    private Sfondo sfondo;
    
    public RegoleGui(){
        regole_gioco = new Regole();
        
        setTitle("REGOLE DI GIOCO");
        setPreferredSize(new Dimension(800, 400));
	setMinimumSize(new Dimension(800, 400));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        
        sfondo = new Sfondo("dominio/immagini/sfondoRegole.jpg", 800, 380);
        add(sfondo);
        
        JLabel regole  = new JLabel(getRegole());
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro2.png"));
        
        Font font = new Font("Regole", Font.BOLD, 15);
        regole.setFont(font);
        regole.setForeground(Color.black);
        
        regole.setBounds(35, -40, 800, 400);
        indietro.setBounds(this.getWidth()/2 - 50, 310, 100, 40);
        
        sfondo.add(regole);
        sfondo.add(indietro);        
        
//        setLayout(new BorderLayout());
//        
//        JPanel pannello = new JPanel();
//        pannello.setBackground(Color.WHITE);
//        JTextArea regole  = new JTextArea("\n" + regole_gioco.getRegole());   
//        regole.setEditable(false);
//        add(regole, BorderLayout.CENTER);
//        
//        JPanel pulsanteIndietro = new JPanel();
//        pulsanteIndietro.setBackground(Color.WHITE);
//        pulsanteIndietro.setLayout(new FlowLayout());
//        
//        indietro = new JButton("INDIETRO");
//        pulsanteIndietro.add(indietro);
//        add(pulsanteIndietro, BorderLayout.SOUTH);
    }
    
    public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }
    
    private String getRegole() {
        String regole = "<html>All'inizio del gioco ogni giocatore riceve una carta, non visibile agli altri giocatori.<br>"
                + "Successivamente si sceglie la puntata del round, che va da un minimo di 1,<br>"
                + "a un massimo di tutto ciò che si possiede.<br>"
                + "Durante il proprio turno, si può scegliere di ricevere carte o rimanere con la/le carta/e già in possesso.<br>"
                + "Lo scopo del gioco è di fermarsi il più vicino possibile a 7 e mezzo, dove quest'ultimo è la somma delle<br>"
                + "carte in proprio possesso (le figure valgono 1/2, le carte da asso a 7 valgono il loro valore numerico).<br>"
                + "Se si va oltre 7 e mezzo, si 'sballa' e si perde la puntata e il round.<br>"
                + "Si gioca solo contro il banco (o mazziere), che è l'avversario da battere<br>"
                + "ed è scelto casualmente a inizio gioco.<br>"
                + "In aggiunta: il re di denari è 'la matta', ovvero una carta particolare che<br>"
                + "assume il valore (intero da 1 a 7, o 1/2) desiderato dal giocatore,<br>"
                + "mentre in caso di sette e mezzo reale, si verrà pagati il doppio della posta e, al turno successivo,<br>"
                + "si diverrà mazzieri.<br>"
                + "Buon sette e mezzo!</html>";
        
        return regole;
    }
  
    public ImageIcon caricaImmagine(String nome) {
	ClassLoader caricatore = getClass().getClassLoader();
	URL percorso = caricatore.getResource(nome);
	return new ImageIcon(percorso);
    }
}