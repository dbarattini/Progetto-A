package comunicazione;

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


public class Client {
    private String username="giocatore non registrato";
    private final PrintWriter aGiocatore;
    private final BufferedReader daGiocatore; 
    private final Socket socket;
    private final ObjectOutputStream paccoDaGiocatore;
    private ObjectInputStream paccoPerGiocatore;
    
    public Client(Socket socket) throws IOException{
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
    
    public Object leggiOggetto() throws IOException{
        Object letto=null;        
        try {
            letto=paccoPerGiocatore.readObject();
            return letto;
        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letto;
    }
    
    public void scriviOggetto(Object pacco) throws IOException{
        paccoDaGiocatore.writeObject(pacco);        
    }
    
}
