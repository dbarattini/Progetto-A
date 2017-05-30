
package comunicazione;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Login {
    
    int port = 8080;
    
    Socket socketClient = null;
    DataInputStream in;
    DataOutputStream out;
    Scanner tastiera;
    
    
    public Login() {
        try {
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
            
            System.out.println("[0] - provo a connettermi al server...");
            int port = 8080;
            socketClient = new Socket (InetAddress.getLocalHost(), this.port);
            System.out.println("[1] - connesso!");
        } catch (UnknownHostException ex) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.err.println("Impossibile connnettersi al server");
        }
    }
    
    public void comunica() {
        System.out.println("[3] - cosa vuoi fare? \n\t registrazione \n\t login \n\t convalida \n\t recupero");
        tastiera = new Scanner(System.in);
        String messaggio = tastiera.nextLine();
        try {
            if (messaggio == "registrazione") {
                registrazione();
            }
            if (messaggio == "login") {
                login();
            }
            if (messaggio == "convalilda") {
                convalida();
            }
            if (messaggio == "recupero") {
                recupero();
            } 
        }catch (NoSuchElementException ex) {
            System.err.println("operazione non valida");
        }
        
                
        
    }

    private void registrazione() {
        System.out.println("[4] - inserire email, user e psw separate da uno spazio:");
        tastiera = new Scanner(System.in);
        String messaggio = tastiera.nextLine();
        String[] stringa_per_verifica = new String[3];        
        try {
            
            //effettuo una verifica sul messaggio digitato: controllo che il messaggio sia di tre parole e che non sia vuoto
            stringa_per_verifica = messaggio.split(" ");
            for (String s : stringa_per_verifica) {
                if (s != null) {
                    continue;
                }else{
                    System.err.println("messaggio inserito non valido");
                }
            }
            
            String messaggio_da_inviare = "risposta " +messaggio;
            out.writeBytes(messaggio_da_inviare);
            String risposta = in.readLine();
            System.out.println("[5] - " +risposta);
            
        } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("messaggio inserito non valido");
        } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);        
        }
    }

    
    private void login() {
        /*
        System.out.println("[4] - inserire username (o email) e psw separate da uno spazio: ");
        tastiera = new Scanner(System.in);
        String credenziali = tastiera.nextLine();
        String[] stringa_per_verifica = new String[2];
        
        try {
            
            //effettuo un controllo sul messaggio digitato
            stringa_per_verifica = credenziali.split(" ");
            for (String s : stringa_per_verifica) {
                if (s != null) {
                    continue;
                }else{
                    System.err.println("messaggio inserito non valido");
                }
            }
            
            String messaggio_da_inviare = "login " +credenziali;
            out.writeBytes(messaggio_da_inviare);
            String risposta = in.readLine();
            
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("messaggio inserito non valido");            
        }
        */
    }

    private void convalida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void recupero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
