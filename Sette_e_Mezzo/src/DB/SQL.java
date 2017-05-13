/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import eccezioni.datoGiaPresente;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class SQL {
    private Connection c = null;
    private Statement stmt = null;
    
    public SQL(){
           creaTabella();
    }

    
    private void creaTabella(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            System.out.println("Database aperto");
            stmt = c.createStatement();
            String sql = "CREATE TABLE PROFILO " +
                          "(USERNAME TEXT PRIMARY KEY     NOT NULL,"+
                         " FICHES             INT , " + 
                         "VITTORIE INT)" ;
            stmt.executeUpdate(sql);
            chiudiDatabase();
            System.out.println("Tabella creata!");
        } catch ( Exception e ) {
             chiudiDatabase();
        }    
  }

    
    
    public  void aggiungiDato(String user, int fiches, int vittorie  ) throws datoGiaPresente
  {
       try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      String dati="VALUES ('"+user+"', "+fiches+", "+vittorie+");";

      stmt = c.createStatement();
      String sql = "INSERT INTO PROFILO (USERNAME, FICHES, VITTORIE) " +
                   dati; 
      stmt.executeUpdate(sql);      
      c.commit();
      chiudiDatabase();
      System.out.println("Dato aggiunto correttamente");
    } catch ( Exception e ) {
           System.out.println("dato già presente");
           chiudiDatabase();
           throw new datoGiaPresente(e.getMessage());           
    }
    
  }
    
    
    public void setFiches(String user, int fiches)
  {
    
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String dato= "UPDATE PROFILO set FICHES ="+fiches+" where USERNAME= '"+user+"';";
            String sql = dato;
            stmt.executeUpdate(sql);
            c.commit();
            chiudiDatabase();
            System.out.println("fiches aggiornate con successo");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            chiudiDatabase();
        }
   
  }
    
   public int getFiches( String user )
  {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);   
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PROFILO;" );
            while ( rs.next() ) {
                String  username = rs.getString("USERNAME");
                int fiches  = rs.getInt("FICHES");
                if(username.equals(user)){
                    rs.close();
                    chiudiDatabase();
                    return fiches;
                }
            }
            rs.close();
            chiudiDatabase();
            } catch ( Exception e ) {
                  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  chiudiDatabase();
            }
            return 0;
    }
   
    public void aggiungiVittoria( String user ){
            int vittorie=0;    
            try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
                    c.setAutoCommit(false);
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT * FROM PROFILO;" );
                    while ( rs.next() ) {
                        String  username = rs.getString("USERNAME");
                        int vit  = rs.getInt("VITTORIE");
                        vit++;
                        if(username.equals(user))
                            vittorie=vit;
                    }
            String dato= "UPDATE PROFILO set VITTORIE ="+vittorie+" where USERNAME='"+user+"';";
            String sql = dato;
            stmt.executeUpdate(sql);
            c.commit();
            chiudiDatabase();
            System.out.println("aggiunta vittoria");
    } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            chiudiDatabase();
    }
    
  }
    
    public int getVittorie( String user )
  {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);   
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PROFILO;" );
            while ( rs.next() ) {
                String  username = rs.getString("USERNAME");
                int vittorie  = rs.getInt("VITTORIE");
                if(username.equals(user)){
                    rs.close();
                    chiudiDatabase();
                    return vittorie;
                }
            }
            rs.close();
            chiudiDatabase();
            } catch ( Exception e ) {
                  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  chiudiDatabase();
            }
            return 0;
    }
    
    private void chiudiDatabase() {
        try {
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
