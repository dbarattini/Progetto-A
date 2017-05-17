package sette_e_mezzo_server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            Partita partita = new Partita();
            Thread t = new Thread(partita);
            t.start();
            System.out.println("Server in ascolto sulla porta: " + port);
            while(true){
                Socket clientSocket = serverSocket.accept();  //effettua la connessione ad 1 client
                System.out.println("Connesso a " + clientSocket.getInetAddress());                

                PrintWriter toClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);                   
                BufferedReader fromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                
                clientSocket.setSoTimeout(100); //timeout utilizzato nella lettura
                partita.aggiungiGiocatore(new Giocatore("nome", clientSocket, fromClient, toClient));
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}