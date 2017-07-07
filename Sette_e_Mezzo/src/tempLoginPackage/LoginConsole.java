package tempLoginPackage;

import comunicazione.Client;
import dominio.eccezioni.DatiNonValidiException;
import dominio.eccezioni.LoginEffettuato;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import partitaOnline.controller.PartitaOnlineController;
import partitaOnline.view.PartitaOnlineConsoleView;

public class LoginConsole {


    private BufferedReader in;
    private PrintWriter out;
    private Scanner tastiera;
    private Socket socketClient;
    private PartitaOnlineController controller;

    public LoginConsole(Socket socketClient) {
        try {
            this.socketClient = socketClient;
            tastiera = new Scanner(System.in);
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            out = new PrintWriter(socketClient.getOutputStream(), true);
        } catch (UnknownHostException ex) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.err.println("Impossibile connnettersi al server");
        }
    }

    /**
     *
     */
    public void comunica() {
        try {
            System.out.println("  ---------------------------------------------------------------------------  ");
            System.out.println("                       < SELEZIONA UN OPZIONE DAL MENU >                       ");
            System.out.println("  ---------------------------------------------------------------------------  ");
            System.out.println("                                1. Registrazione                               ");
            System.out.println("                                2. Login                                       ");
            System.out.println("                                3. Convalida                                   ");
            System.out.println("                                4. Recupero                                    ");
            System.out.print("\n");
            System.out.print("                                         ");
            String messaggio = tastiera.nextLine();
            System.out.print("\n");

            if (messaggio.toLowerCase().equals("registrazione") || messaggio.equals("1")) {
                registrazione();
            } else if (messaggio.toLowerCase().equals("login") || messaggio.equals("2")) {
                login();
            } else if (messaggio.toLowerCase().equals("convalida") || messaggio.equals("3")) {
                convalida();
            } else if (messaggio.toLowerCase().equals("recupero") || messaggio.equals("4")) {
                recupero();
            } else {
            System.err.println("Errore: La scelta effettuata non é valida.\n");
            }

            comunica();
        } catch (LoginEffettuato ex) {
            controller= new PartitaOnlineController(socketClient, in);
            new PartitaOnlineConsoleView(controller);
            while(true){
                
            }
        } catch (DatiNonValidiException ex) {
            System.err.println("Errore: alcuni dati inseriti non sono validi.");
        }

    }

    private void registrazione() throws DatiNonValidiException {
        System.out.println("          inserisci email, username e password separati da uno spazio          ");
        System.out.print("\n");
        System.out.print("            ");
        String messaggio = tastiera.nextLine();
        System.out.print("\n");
        String[] stringa_per_verifica = new String[3];
        try {

            //effettuo una verifica sul messaggio digitato: controllo che il messaggio sia di tre parole e che non sia vuoto
            stringa_per_verifica = messaggio.split(" ");
            for (String s : stringa_per_verifica) {
                if (s != null) {
                    continue;
                } else {
                    throw new dominio.eccezioni.DatiNonValidiException();
                }
            }

            String messaggio_da_inviare = "registrazione " + messaggio;
            out.println(messaggio_da_inviare);
            String risposta = in.readLine();
            System.out.println("                                         " + risposta);

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Errore: alcuni dati inseriti non sono validi.");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void login() throws LoginEffettuato {
        System.out.println("          inserisci username (o email) e password separati da uno spazio       ");
        System.out.print("\n");
        System.out.print("            ");
        String credenziali = tastiera.nextLine();
        System.out.print("\n");
        String[] stringa_per_verifica = new String[2];

        try {

            //effettuo un controllo sul messaggio digitato
            stringa_per_verifica = credenziali.split(" ");
            for (String s : stringa_per_verifica) {
                if (s != null) {
                    //do nothing
                } else {
                    System.err.println("Errore: alcuni dati inseriti non sono validi.");
                }
            }

            String messaggio_da_inviare = "login " + credenziali;
            out.println(messaggio_da_inviare);
            String risposta = in.readLine();
            System.out.println("                                         " + risposta);
            if (risposta.equals("login effetuato")) {
                throw new LoginEffettuato();
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginConsole.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Errore: alcuni dati inseriti non sono validi.");
        }

    }

    private void convalida() {
        System.out.println("                          inserisci il codice di convalida                     ");
        System.out.print("\n");
        System.out.print("                                       ");
        String codice = tastiera.nextLine();
        System.out.print("\n");

        try {

            //verifica sul messaggio digitato
            if (codice.contains(" ")) {
                System.err.println("Errore: il codice inserito non é valido.");
            }

            String messaggio_da_inviare = "convalida " + codice;
            out.println(messaggio_da_inviare);
            String risposta = in.readLine();
            System.out.println("                                         " + risposta);

        } catch (IOException ex) {
            Logger.getLogger(LoginConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void recupero() {
        System.out.println("                   inserisci l'email per il recupero della password            ");
        System.out.print("\n");
        System.out.print("                               ");
        String email = tastiera.nextLine();
        System.out.print("\n");

        try {

            //controllo sul messaggio digitato
            if (email.contains("@")) {
                //do nothing
            } else {
                System.err.println("Errore: l'email inserita non é valida");
            }
            if (email.contains(" ")) {
                System.err.println("Errore: l'email inserita non é valida");
            }

            String messaggio_da_inviare = "recupero " + email;
            out.println(messaggio_da_inviare);
            String risposta = in.readLine();
            System.out.println("                                         " + risposta);

        } catch (IOException ex) {
            Logger.getLogger(LoginConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
