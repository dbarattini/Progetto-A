/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import dominio.eccezioni.GiocatoreDisconnessoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Leggi extends Observable implements Runnable {

    private BufferedReader reader;
    private String message = "";
    private boolean disconnesso = false;
    PrintStream out;

    public Leggi(BufferedReader in) {
        reader = in;

    }

    /**
     * legge i messaggi
     */
    public void run() {

        try {
            message = reader.readLine();
            printMessage();

        } catch (SocketTimeoutException e) {
            run();

        } catch (IOException ex) {
            Logger.getLogger(Leggi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            run();
        }
        if (message != null) {
            run();
        }
    }

    /**
     * stampa i messaggi
     */
    public void printMessage() {
        if (message != null) {
            this.setChanged();
            this.notifyObservers(message);
        } else if (message == null) {
            disconnesso = true;
            this.setChanged();
            this.notifyObservers(new GiocatoreDisconnessoException());
        }
    }

}
