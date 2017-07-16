package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    private Socket socketClient;
    private InetAddress indirizzo;

    /**
     * apre il client e prova a connettersi al server
     */
    public Client() {
    }
    
    /**
     * 
     * @throws UnknownHostException lanciata quando l'host non viene riconosciuto
     * @throws IOException lanciata quando si verifica un errore di input-output
     */
    public void connetti() throws UnknownHostException, IOException{
            this.indirizzo = InetAddress.getLocalHost();
//            String indirizzo = "82.50.67.103";
            System.out.println("[0] - provo a connettermi al server...");
            int port = 8080;
            socketClient = new Socket(indirizzo, port);
            System.out.println("[1] - connesso!");
    }

    /**
     * 
     * @return socket del client 
     */
    public Socket getSocketClient() {
        return socketClient;
    }

    /**
     * chiude il client
     */
    public void close() {
        try {
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


}
