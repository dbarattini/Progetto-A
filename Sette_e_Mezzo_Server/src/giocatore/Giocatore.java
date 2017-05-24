package giocatore;

import eccezioni.GiocatoreDisconnessoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Giocatore {
    private String username="giocatore non registrato";
    private final PrintWriter aGiocatore;
    private final BufferedReader daGiocatore; 
    private final Socket socket;
    private final ObjectOutputStream paccoDaGiocatore;
    private ObjectInputStream paccoPerGiocatore;
    
    public Giocatore(Socket socket) throws IOException{
        this.daGiocatore = new BufferedReader(  new InputStreamReader(socket.getInputStream()));
        this.aGiocatore =  new PrintWriter(socket.getOutputStream(), true);
        this.paccoDaGiocatore= new ObjectOutputStream(socket.getOutputStream());
        this.paccoPerGiocatore= new ObjectInputStream(socket.getInputStream());
        this.socket = socket;       
    }
    
    public void scrivi(String msg){
        aGiocatore.println(msg);
    }
    
    
    
    public String leggi() throws IOException, GiocatoreDisconnessoException{
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
    
    public Object leggiOggetto(){
        Object letto;        
        try {

            letto=paccoPerGiocatore.readObject();
            return letto;
        } catch (IOException ex) {
            Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Giocatore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getUsername(){
        return username;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }   
        
    public Socket getSocket(){
        return socket;
    }
}
