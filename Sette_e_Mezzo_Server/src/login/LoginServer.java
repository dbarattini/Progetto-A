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
import net.HangmanThread;
import net.RemotePlayer;
import DB.SQL;
import sql.datoGiaPresente;


public class LoginServer extends Thread{
    private PrintWriter out;
    private BufferedReader in;
    private SQL sql= new SQL();
    private Socket clientSocket;
    private InetAddress clientAddress;
    private int codice;
    private String mail, pw;

    public LoginServer(Socket clientSocket,  InetAddress clientAddress) throws IOException {
            this.out =  new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));        
       
    }
    
    private void inizioPartita(){
        RemotePlayer player = new RemotePlayer(out,in, clientSocket);
        HangmanThread game = new HangmanThread(player, out, in, clientAddress);
            
        Thread t = new Thread(game);
        t.start();
    }
    
    private void mandaMessaggio(String msg){
    out.println(msg);  
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
                     mandaMessaggio("login effetuato");
                    sleep(20);
                    inizioPartita();
                }
                else{
                    mandaMessaggio("login non effetuato");
                    run();
                }
                    
            }
            else if(dati[0].equals("convalida")){
                if(Integer.valueOf(dati[1])==codice){
                    registra();
                }
                else{
                    mandaMessaggio("convalida errata");  
                    run();
                }
            }
            else if(dati[0].equals("recupero")){
                recuperoPw(dati);
            }
            else
                run();
        } catch (IOException ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (datoGiaPresente ex) {
            mandaMessaggio("registrazione non effetuata");   
            System.out.println("contatto già presente");
            run();
        }
    }
    
    private void recuperoPw(String[] dati){
        mail=dati[1];
        if(!sql.esisteEmail(mail))
            mandaMessaggio("recupero errato");  
        else{
            Email email=new Email();
            String password=sql.trovaPassword(mail);
            email.inviaPassword(mail, password );
            System.out.println("email inviata");
            mandaMessaggio("recupero inviato");
        }
        run();
    }

    private void registra() throws datoGiaPresente {
        sql.aggiungiDato(mail, pw);
        mandaMessaggio("registrazione effetuata");
        run();
    }

    private void convalida(String[] dati) throws datoGiaPresente {
        codice=random();
        mail=dati[1];
        pw=dati[2];
        if(sql.esisteEmail(mail))
            mandaMessaggio("registrazione non effetuata");  
        else{
            Email email=new Email();
            email.inviaCodice(mail, codice);
            System.out.println("email inviata");
            mandaMessaggio("convalida inviata");
        }
        run();
    }
    
    
}
