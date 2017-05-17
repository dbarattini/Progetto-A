/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package login;
// File Name SendEmail.java

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
    private final String nome="mipiaccionoipisi", password="123stell";
    
    public Email() {
     }
    
    /**
     *Inviare codice per registrare la mail
     * @param destinatario email del destinatario
     * @param codice codice da mandare
     */
    public void inviaCodice(String destinatario, int codice){
        String msg="Buongiornissimo,\n"
                + "Per convalidare il tuo indirizzo email inserire "+codice+" nell'aplicazione.\n"
                + "Grazie\n"
                + "Il team.\n\n\n"
                + "P.S. Venderemo il tuo indirizzo email a un hacker russo per una bottiglia di vodka :D";
        String oggetto ="Verifica mail Sette e mezzo";
        try {
            Send(nome, password, destinatario, oggetto, msg);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Invia la password al giocatore 
     * @param destinatario email del giocatore
     * @param pw password del giocatore
     */
    public void inviaPassword(String destinatario, String pw){
        String msg="Buongiornissimo,\n"
                + "Visto che sei una mezzasega e ti dobbiamo ricordare tutto ecco i tuoi codici di ingresso per Sette e mezzo:\n"
                + "Username: "+destinatario+"\n"
                + "Password: "+pw+"\n"
                + "Vedi di non dimenticarteli più!\n"
                + "Il team.\n\n\n"
                + "P.S. Venderemo il tuo indirizzo email a un hacker russo per una bottiglia di vodka :D";
        String oggetto="Recupero password Sette e mezzo";
        try {
            Send(nome, password, destinatario, oggetto, msg);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     

    
    private static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
        Email.Send(username, password, recipientEmail, "", title, message);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    private static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
    }
    
    
}
