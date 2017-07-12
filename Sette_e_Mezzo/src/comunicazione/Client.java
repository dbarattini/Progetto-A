package comunicazione;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    private Socket socketClient;
    private InetAddress indirizzo;

    public Client() {
        
        try {
            this.indirizzo = InetAddress.getLocalHost();
//            String indirizzo = "82.50.67.103";
            System.out.println("[0] - provo a connettermi al server...");
            int port = 8080;
            socketClient = new Socket(indirizzo, port);
            System.out.println("[1] - connesso!");
        } catch (UnknownHostException ex) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.err.println("Impossibile connnettersi al server");
        }
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public void close() {
        try {
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


}
