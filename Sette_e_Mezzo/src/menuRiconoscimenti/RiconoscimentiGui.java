package menuRiconoscimenti;

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


public class RiconoscimentiGui extends JFrame{
    private JButton indietro;
    private Sfondo sfondo;
    
    public RiconoscimentiGui(){
        setTitle("RICONOSCIMENTI");
        setPreferredSize(new Dimension(500, 400));
	setMinimumSize(new Dimension(500, 400));		
	pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
        
        sfondo = new Sfondo("dominio/immagini/sfondoRiconoscimenti.jpg", 495, 380);
        add(sfondo);
        
        JLabel developers = new JLabel(caricaImmagine("dominio/immagini/developers.png"));
        JLabel riconoscimenti = new JLabel (
                              "<html>Barattini Daniel<br>" 
                            + "Bergamaschi Martino<br>" 
                            + "Colmi Samuele<br>"
                            + "Guido Marco<br>" 
                            + "Mbaye Samba<br>" 
                            + "Prina Marco</html>");
        indietro = new JButton(caricaImmagine("dominio/immagini/indietro2.png"));
        
        Font font = new Font("Riconoscimenti", Font.BOLD, 20);
        riconoscimenti.setFont(font);
        riconoscimenti.setForeground(Color.black);
        
        riconoscimenti.setBounds(175, -40, 400, 400);
        indietro.setBounds(this.getWidth()/2 - 50, 310, 100, 40);        
        developers.setBounds(5, 5, 200, 80);
        
        sfondo.add(riconoscimenti);
        sfondo.add(indietro);
        sfondo.add(developers);              
        
//        setLayout(new BorderLayout());
//        
//        JTextArea developers = new JTextArea("DEVELOPERS: ");
//        JTextArea riconoscimenti = new JTextArea ("\n"
//                            + "Barattini Daniel\n" 
//                            + "Bergamaschi Martino\n" 
//                            + "Colmi Samuele\n"
//                            + "Guido Marco\n" 
//                            + "Mbaye Samba\n" 
//                            + "Prina Marco\n");
//        developers.setEditable(false);
//        riconoscimenti.setEditable(false);
//        
//        add(developers, BorderLayout.NORTH);
//        
//        JPanel pannelloNomi = new JPanel();
//        pannelloNomi.setBackground(Color.WHITE);
//        pannelloNomi.setLayout(new FlowLayout());
//        pannelloNomi.add(riconoscimenti);
//        add(pannelloNomi, BorderLayout.CENTER);
//        
//        JPanel pulsanteIndietro = new JPanel();
//        pulsanteIndietro.setBackground(Color.WHITE);
//        pulsanteIndietro.setLayout(new FlowLayout());
//        
//        indietro = new JButton("INDIETRO");
//        pulsanteIndietro.add(indietro);
//        add(pulsanteIndietro, BorderLayout.SOUTH);
    }
    
    /**
     * 
     * @param l listener
     */
    public void addIndietroActionListener(ActionListener l){
        indietro.addActionListener(l);
    }
     
    /**
     * 
     * @param nome nome dell'immagine
     * @return immagine
     */
    public ImageIcon caricaImmagine(String nome) {
	ClassLoader caricatore = getClass().getClassLoader();
	URL percorso = caricatore.getResource(nome);
	return new ImageIcon(percorso);
    }
}