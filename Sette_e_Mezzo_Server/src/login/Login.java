/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.Email;
import DB.SQL;
import eccezioni.GiocatoreGiaPresente;
import giocatore.Giocatore;


public class Login extends Thread{
    private PrintWriter out;
    private BufferedReader in;
    private SQL sql= new SQL();
    private Socket clientSocket;
    private InetAddress clientAddress;
    private int codice;
    private String mail, pw;
    private Giocatore giocatore;

    public Login(Giocatore giocatore) throws IOException {
            this.giocatore=giocatore;
            this.out =  new PrintWriter(giocatore.getSocket().getOutputStream(), true);
            this.in = new BufferedReader( new InputStreamReader(giocatore.getSocket().getInputStream()));      
    }
      
    private int random(){
        return (int) Math.round(Math.random()*10000);
    }
    
    public void run(){
        try {
            String messaggio=in.readLine();
            String dati[]= messaggio.split(" ");
            if(dati[0].equals("registrazione")){
                convalida(dati);
            }
            else if(dati[0].equals("login")){
                if(sql.controllaPassword(dati[1], dati[2])){
                     giocatore.Scrivi("login effetuato");
                    sleep(20);
                    inizioLogin();//inizia partita
                }
                else{
                    giocatore.Scrivi("login non effetuato");
                    run();
                }
                    
            }
            else if(dati[0].equals("convalida")){
                if(Integer.valueOf(dati[1])==codice){
                    registra();
                }
                else{
                    giocatore.Scrivi("convalida errata");  
                    run();
                }
            }
            else if(dati[0].equals("recupero")){
                recuperoPw(dati);
            }
            else
                run();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void recuperoPw(String[] dati){
        mail=dati[1];
        if(!sql.esisteEmail(mail))
            giocatore.Scrivi("recupero errato");  
        else{
            Email email=new Email();
            String password=sql.getPassword(mail);
            email.inviaPassword(mail, password );
            System.out.println("email inviata");
            giocatore.Scrivi("recupero inviato");
        }
        run();
    }

    private void registra() {
        sql.aggiungiGiocatore(mail, pw, username, fiches);
        giocatore.Scrivi("registrazione effetuata");
        run();
    }

    private void convalida(String[] dati)  {
        codice=random();
        mail=dati[1];
        pw=dati[2];
        if(sql.esisteEmail(mail))
            giocatore.Scrivi("registrazione non effetuata");  
        else{
            Email email=new Email();
            email.inviaCodice(mail, codice);
            System.out.println("email inviata");
            giocatore.Scrivi("convalida inviata");
        }
        run();
    }
    
    
}
