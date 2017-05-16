package sette_e_mezzo_server;

import eccezioni.GiocatoreDisconnessoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Giocatore {
    private final String nome;
    private final PrintWriter aGiocatore;
    private final BufferedReader daGiocatore; 
    private final Socket socket;
    
    public Giocatore(String nome, Socket socket, BufferedReader daGiocatore, PrintWriter aGiocatore){
        this.nome = nome;
        this.daGiocatore = daGiocatore;
        this.aGiocatore = aGiocatore;
        this.socket = socket;       
    }
    
    public void Scrivi(String msg){
        aGiocatore.println(msg);
    }
    
    public String Leggi() throws IOException, GiocatoreDisconnessoException{
        String letto;
        
        try{
            letto = daGiocatore.readLine();
            if(letto == null){
                throw new GiocatoreDisconnessoException();
            }
        } catch (SocketTimeoutException e){
            return null;
        }
        return letto;
    }
    
    public String getNome(){
        return nome;
    }
    
    public Socket getSocket(){
        return socket;
    }
}
