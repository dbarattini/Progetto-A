package GUI;

import gioco.PartitaOffline;
import gioco.StatoGioco;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
        
    // rileva il click del mouse e, a seconda della sua posizione, decide se e quale bottone premere
    public void mouse_clickato(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if(PartitaOffline.stato_gioco == StatoGioco.menu) {
            if(controlla_posizione_mouse(mx, my, 10, 10, 10, 10))
                PartitaOffline.stato_gioco = StatoGioco.menu_pre_offline;
        }
    }
    
    // renderizza i bottoni di selezione del menù
    public void renderizza(Graphics g) {
        if(PartitaOffline.stato_gioco == StatoGioco.menu) {
            Font font_menu = new Font("arial", 1, 70);
            Font fnt_bottoni = new Font("arial", 1, 40);
            
            g.setFont(font_menu);
            g.setColor(Color.yellow);
            int larghezza_stringa = g.getFontMetrics().stringWidth("Menù");
            g.drawString("Menù", PartitaOffline.LARGHEZZA/2 - larghezza_stringa/2, 80);
            
            g.setFont(fnt_bottoni);
            g.setColor(Color.white);
            g.drawRect(PartitaOffline.LARGHEZZA/2 - 100, 160, 200, 64);
            larghezza_stringa = g.getFontMetrics().stringWidth("Gioca offline");
            g.drawString("Gioca offline", PartitaOffline.LARGHEZZA/2 - larghezza_stringa/2, 205);
			
            g.drawRect(PartitaOffline.LARGHEZZA/2 - 100, 270, 200, 64);
            larghezza_stringa = g.getFontMetrics().stringWidth("Gioca online");
            g.drawString("Gioca online", PartitaOffline.LARGHEZZA/2 - larghezza_stringa/2, 315);
            
            g.drawRect(PartitaOffline.LARGHEZZA/2 - 100, 270, 200, 64);
            larghezza_stringa = g.getFontMetrics().stringWidth("Regole di gioco");
            g.drawString("Regole di gioco", PartitaOffline.LARGHEZZA/2 - larghezza_stringa/2, 315);
            
            g.drawRect(PartitaOffline.LARGHEZZA/2 - 100, 270, 200, 64);
            larghezza_stringa = g.getFontMetrics().stringWidth("Opzioni");
            g.drawString("Opzioni", PartitaOffline.LARGHEZZA/2 - larghezza_stringa/2, 315);
        } else if(PartitaOffline.stato_gioco == StatoGioco.menu_pre_offline) {
            
        }
    }
    
    // controlla se il mouse è nel rettangolo di schermo specificato di larghezza mx + larghezza e altezza my + altezza
    public boolean controlla_posizione_mouse(int mx, int my, int x, int y, int larghezza, int altezza) {
	if(mx >= x && mx <= x + larghezza) {
            if(my >= y && my <= y + altezza)
		return true;
            else
                return false;
	} else
            return false;
    }
}