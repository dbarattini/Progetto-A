/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author samba
 */
public class ChiudiRegole extends WindowAdapter {

    public void windowClosing(WindowEvent e) {
         System.exit(0);
    }

}
