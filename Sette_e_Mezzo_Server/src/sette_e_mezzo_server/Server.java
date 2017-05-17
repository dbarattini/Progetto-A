package sette_e_mezzo_server;


import giocatore.Giocatore;
import client.Partita;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.Login;


public class Server {
    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            Partita partita = new Partita();
//            Thread t = new Thread(partita);
//            t.start();
            System.out.println("Server in ascolto sulla porta: " + port);
            cercaConnessione(serverSocket, partita);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void cercaConnessione(ServerSocket serverSocket, Partita partita) throws IOException {
        while(true){
            Socket clientSocket = serverSocket.accept();  //effettua la connessione ad 1 client
            System.out.println("Connesso a " + clientSocket.getInetAddress());            
            nuovoGiocatore(clientSocket, partita);
        }
    }

    private static void nuovoGiocatore(Socket clientSocket, Partita partita) throws SocketException, IOException {
        PrintWriter toClient =
                new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader fromClient = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));        
        clientSocket.setSoTimeout(100); //timeout utilizzato nella lettura
        Login login= new Login(new Giocatore( clientSocket, fromClient, toClient), partita);
        Thread t = new Thread(login);
         t.start();
    }
}
