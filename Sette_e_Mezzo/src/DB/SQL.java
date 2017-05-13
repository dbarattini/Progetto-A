/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

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

    
    public void creaTabella()
  {
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
    } catch ( Exception e ) {
            chiudiDatabase();
    }
    System.out.println("Tabella creata!");
  }

    
    
    public  void aggiungiDato(String user, int fiches, int vittorie  )
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
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      chiudiDatabase();
    }
    System.out.println("Dato aggiunto correttamente");
  }
    
    
    public void setFiches(String user, int fiches)
  {
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
      c.setAutoCommit(false);
      String dato= "UPDATE PROFILO set FICHES ="+fiches+" where USERNAME='"+user+"';";
      String sql = dato;
      stmt.executeUpdate(sql);
      c.commit();
            chiudiDatabase();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      chiudiDatabase();
    }
    System.out.println("fiches aggiornate con successo");
  }
    
     public  void getFiches( String user )
  {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
      S

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
    
    private void chiudiDatabase() {
        try {
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) { 
      
    }
    
}
