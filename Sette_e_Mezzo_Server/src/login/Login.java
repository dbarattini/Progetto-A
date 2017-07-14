/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package login;

import net.Email;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DB.SQL;
import dominio.eccezioni.EmailInesistente;
import partita.Partita;
import dominio.giocatori.Giocatore;
import dominio.eccezioni.GiocatoreDisconnessoException;
import dominio.eccezioni.GiocatoreNonTrovato;
import dominio.eccezioni.PartitaPiena;
import dominio.eccezioni.SqlOccupato;

public class Login extends Thread {

    private SQL sql = new SQL();
    private int codice;
    private String mail, password, username;
    private Giocatore giocatore;
    private Partita partita;
    private final int fichesIniziali = 1000;

    public Login(Giocatore giocatore, Partita partita) throws IOException {
        this.giocatore = giocatore;
        this.partita = partita;
    }

    private void iniziaPartita() {
        try {
            System.out.println("La partita inizia adesso");
            giocatore.setUsername(username);
            giocatore.iniziaLetturaOggetti();
            partita.aggiungiGiocatore(giocatore);
        } catch (PartitaPiena ex) {
            System.out.println("Partita piena, prego riprovare più tardi");
            giocatore.scrivi("partitaPiena");
        }

    }

    @Override
    public void run() {
        try {
            String messaggio;
            messaggio = giocatore.leggi();
            if (messaggio != null && !messaggio.equals("")) {
                scomponiMessaggio(messaggio);
            } else {
                run();
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GiocatoreNonTrovato ex) {
            giocatore.scrivi("login non effetuato");
            run();
        } catch (GiocatoreDisconnessoException ex) {
            String nome = giocatore.getNome();
            if (nome == null) {
                nome = "ignoto";
            }
            System.out.println("Giocatore " + giocatore.getNome() + " disconnesso");

        }
    }

    private void scomponiMessaggio(String messaggio) throws NumberFormatException, GiocatoreNonTrovato, InterruptedException {
        try {
            String dati[] = messaggio.split(" ");
            if (dati[0].equals("registrazione")) {
                convalida(dati);
            } else if (dati[0].equals("login")) {
                gestisciLogin(dati);
            } else if (dati[0].equals("convalida")) {
                gestisciConvalida(dati);
            } else if (dati[0].equals("recupero")) {
                gestisciRecupero(dati);
            } else {
                sleep(75);
                run();
            }
        } catch (SqlOccupato ex) {
            sleep(random(50, 100));
            scomponiMessaggio(messaggio);
        }
    }

    private void gestisciConvalida(String[] dati) throws NumberFormatException, SqlOccupato {
        if (Integer.valueOf(dati[1]) == codice) {
            registra();
        } else {
            giocatore.scrivi("convalida errata");
            run();
        }
    }

    private void gestisciLogin(String[] dati) throws InterruptedException, SqlOccupato, GiocatoreNonTrovato {
        if (sql.esisteEmail(dati[1])) {
            username = sql.getUser(dati[1]);
        } else {
            username = dati[1];
        }
        password = dati[2];
        if (sql.controllaPassword(username, password)) {
            giocatore.scrivi("login effetuato");
            sleep(20);
            iniziaPartita();
        } else {
            giocatore.scrivi("login non effetuato");
            run();
        }
    }

    private void gestisciRecupero(String[] dati) throws SqlOccupato {
        mail = dati[1];
        if (sql.esisteEmail(mail)) {
            Email email = new Email();
            String password = sql.getPassword(mail);
            try {
                email.inviaPassword(mail, sql.getUser(mail), password);
            } catch (GiocatoreNonTrovato ex) {
                //impossibile ma lo richiede
            }
            giocatore.scrivi("recupero inviato");
        } else if (sql.esisteUsername(mail)) {
            Email email = new Email();
            String veraEmail = sql.getEmail(mail);
            email.inviaPassword(veraEmail, mail, password);
            giocatore.scrivi("recupero inviato");
        } else {
            giocatore.scrivi("recupero errato");
        }
        run();
    }

    private void registra() throws SqlOccupato {
        sql.aggiungiGiocatore(mail, password, username, fichesIniziali);
        giocatore.scrivi("registrazione effetuata");
        run();
    }

    private void convalida(String[] dati) throws SqlOccupato {
        codice = random(9999, 1000);
        mail = dati[1];
        username = dati[2];
        password = dati[3];
        if (sql.esisteEmail(mail)) {
            giocatore.scrivi("registrazione email gia esistente");
        } else if (sql.esisteUsername(username)) {
            giocatore.scrivi("registrazione username gia esistente");
        } else {
            Email email = new Email();
            try {
                email.inviaCodice(mail, codice);
                giocatore.scrivi("convalida inviata");
            } catch (EmailInesistente ex) {
                giocatore.scrivi("registrazione email non valida");
            }
        }
        run();
    }

    private int random(int max, int min) {
        int range = max - min;
        int rand = ((int) Math.round(Math.random() * range)) + min;
        return rand;
    }

}
