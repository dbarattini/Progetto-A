package GUI;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

public class ProvaMenuGui {
    public static void main(String[] args){
        try {
            MenuFrame menu = new MenuFrame();
        } catch (InterruptedException ex) {
            System.out.println("errore 1");
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("errore 2");
        }
    }
}
