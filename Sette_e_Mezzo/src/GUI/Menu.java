package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
        
    // rileva il click del mouse e, a seconda della sua posizione, decide se e quale bottone premere
    public void mouse_clickato(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
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