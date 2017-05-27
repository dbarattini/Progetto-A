/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicazione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Leggi extends Observable implements Runnable{
private BufferedReader reader;
    private String message;
    private boolean running = true;
    PrintStream out;
        
    public Leggi(BufferedReader in) {
        reader = in;

    }
    
    public void run() {
        while(running) {
            try {
                message = reader.readLine();
                printMessage();
            } catch (IOException ex) {
                Logger.getLogger(Leggi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void printMessage() {
        if(message != null){
            this.setChanged();
            this.notifyObservers(message);
        }
    }
    
}
