package comunicazione;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    private Socket socketClient;
    private InetAddress indirizzo;

    public Client() {
        
        try {
            this.indirizzo = InetAddress.getLocalHost();
            System.out.println("[0] - provo a connettermi al server...");
            int port = 8080;
            socketClient = new Socket(indirizzo, 8080);
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
    


}
