/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package login;

import comunicazione.Email;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DB.SQL;
import client.Partita;
import eccezioni.GiocatoreDisconnessoException;
import eccezioni.GiocatoreNonTrovato;
import eccezioni.SqlOccupato;
import giocatore.Giocatore;


public class Login extends Thread{
    private SQL sql= new SQL();
    private int codice;
    private String mail, password, username;
    private Giocatore giocatore;
    private Partita partita;
    private int fiches=1000;

    public Login(Giocatore giocatore, Partita partita) throws IOException {
            this.giocatore=giocatore;
            this.partita=partita;
    }
     
    private void iniziaPartita() {
        System.out.println("La partita inizia adesso");
        giocatore.setUsername(username);
        partita.aggiungiGiocatore(giocatore);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run(){
        try {            
            String messaggio;
            messaggio = giocatore.Leggi();
            if(messaggio!=null && !messaggio.equals("")){
                scomponiMessaggio(messaggio);
            }
            else
                run();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GiocatoreNonTrovato ex) {
            giocatore.Scrivi("login non effetuato");
            run();
        } catch (GiocatoreDisconnessoException ex) {
            System.out.println("Giocatore "+giocatore.getUsername()+" disconnesso");
           
        } 
    }

    private void scomponiMessaggio(String messaggio) throws NumberFormatException, GiocatoreNonTrovato, InterruptedException{
        try {   
            String dati[]= messaggio.split(" ");
            if(dati[0].equals("registrazione")){
                convalida(dati);
            }
            else if(dati[0].equals("login")){
                    gestisciLogin(dati);
            }
            else if(dati[0].equals("convalida")){
                gestisciConvalida(dati);
            }
            else if(dati[0].equals("recupero")){
                gestisciRecupero(dati);
            }
            else{
                sleep(75);
                run();
            }
        } catch (SqlOccupato ex) {
                sleep(75);
                scomponiMessaggio(messaggio);
            }
    }

    private void gestisciRecupero(String[] dati) throws SqlOccupato {
        recuperoPw(dati);
    }

    private void gestisciConvalida(String[] dati) throws NumberFormatException, SqlOccupato {
        if(Integer.valueOf(dati[1])==codice){
            registra();
        }
        else{
            giocatore.Scrivi("convalida errata");
            run();
        }
    }

    private void gestisciLogin(String[] dati) throws InterruptedException, SqlOccupato, GiocatoreNonTrovato {
        if(dati[1].contains("@"))
            username=sql.getUser(dati[1]);
        else
            username=dati[1];
        password=dati[2];
        if(sql.controllaPassword(username,password)){
            giocatore.Scrivi("login effetuato");
            sleep(20);
            iniziaPartita();
        }
        else{
            giocatore.Scrivi("login non effetuato");
            run();
        }
    }
    
    private void recuperoPw(String[] dati) throws SqlOccupato{
        mail=dati[1];
        if(!sql.esisteEmail(mail))
            giocatore.Scrivi("recupero errato");  
        else{
            Email email=new Email();
            String password=sql.getPassword(mail);
            email.inviaPassword(mail, password );
            giocatore.Scrivi("recupero inviato");
        }
        run();
    }

    private void registra() throws SqlOccupato {        
            sql.aggiungiGiocatore(mail, password, username, fiches);
            giocatore.Scrivi("registrazione effetuata");
            run();
      }

    private void convalida(String[] dati) throws SqlOccupato  {
        codice=random();
        mail=dati[1];
        username=dati[2];
        password=dati[3];
        if(sql.esisteEmail(mail))
            giocatore.Scrivi("registrazione email gia esistente");  
        else if(sql.esisteUsername(username))
            giocatore.Scrivi("registrazione username gia esistente");
        else{
            Email email=new Email();
            email.inviaCodice(mail, codice);
            giocatore.Scrivi("convalida inviata");
        }
        run();
    }

        
     private int random(){
        int max=9999, min=1000;
        int range=max-min;
        int rand=((int) Math.round(Math.random()*range))+min;        
        return rand;
    }
    
    
}
