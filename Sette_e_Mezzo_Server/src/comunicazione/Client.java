package comunicazione;

import dominio.eccezioni.GiocatoreDisconnessoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends Observable implements Observer {
    private String username="giocatore non registrato";
    private final PrintWriter aGiocatore;
    private final BufferedReader daGiocatore; 
    private final Socket socket;
    private final ObjectOutputStream paccoPerGiocatore;
    private ObjectInputStream paccoDaGiocatore;
    private Leggi leggi;
    private LeggiOggetto leggiOggetto;
    
    public Client(Socket socket) throws IOException{
        this.daGiocatore = new BufferedReader(  new InputStreamReader(socket.getInputStream()));
        this.aGiocatore =  new PrintWriter(socket.getOutputStream(), true);
        this.paccoPerGiocatore= new ObjectOutputStream(socket.getOutputStream());
        this.paccoDaGiocatore= new ObjectInputStream(socket.getInputStream());
        this.socket = socket;  
        this.leggi=new Leggi(daGiocatore);
        this.leggiOggetto=new LeggiOggetto(paccoDaGiocatore);
        leggi.addObserver(this);
        leggiOggetto.addObserver(this);
        Thread t = new Thread(leggi);
        t.start();
        Thread m = new Thread(leggiOggetto);
        m.start();
        
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
//    
//    public Object leggiOggetto() throws IOException{
//        Object letto=null;        
//        try {
//            letto=paccoDaGiocatore.readObject();
//            return letto;
//        }  catch (ClassNotFoundException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return letto;
//    }
    
    public void scriviOggetto(Object pacco) throws IOException{
        paccoPerGiocatore.writeObject(pacco);        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(arg);
    }
    
}
