/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicazione;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.StreamCorruptedException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class LeggiOggetto extends Observable implements Runnable{

    private ObjectInputStream reader;
    private Object message;
    private boolean running = true;
    PrintStream out;
        
    public LeggiOggetto(ObjectInputStream in, PrintStream out) {
        reader = in;
        this.out = out;
    }
    
    public void run() {
            try {
                try {
                    message = reader.readObject();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LeggiOggetto.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (StreamCorruptedException ex) {
                    System.out.println(ex.getLocalizedMessage());
                    run();
                }
                printMessage();
            } catch (IOException ex) {
                Logger.getLogger(LeggiOggetto.class.getName()).log(Level.SEVERE, null, ex);
            }
            run();
    }
    
    public void printMessage() {
        if(message != null){
            out.println(message.toString());
            this.setChanged();
            this.notifyObservers(message);
        }
    }
    
}
