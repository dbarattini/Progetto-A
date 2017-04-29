package GUI;

import gioco.PartitaOffline;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class FinestraDiGioco extends Canvas {
    public FinestraDiGioco(int larghezza, int altezza, String titolo, PartitaOffline game) {
		JFrame frame = new JFrame(titolo);
		frame.setPreferredSize(new Dimension(larghezza, altezza));
		frame.setMaximumSize(new Dimension(larghezza, altezza));
		frame.setMinimumSize(new Dimension(larghezza, altezza));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
	}
}