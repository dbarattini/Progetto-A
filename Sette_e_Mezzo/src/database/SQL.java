package database;

import dominio.eccezioni.DatoGiaPresente;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQL {

    private Connection c = null;
    private Statement stmt = null;

    public SQL() {
        creaTabella();
    }

    private void creaTabella() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            System.out.println("Database aperto");
            stmt = c.createStatement();
            String sql = "CREATE TABLE PROFILO "
                    + "(USERNAME TEXT PRIMARY KEY     NOT NULL,"
                    + " FICHES             INT , "
                    + "VITTORIE INT)";
            stmt.executeUpdate(sql);
            chiudiDatabase();
            System.out.println("Tabella creata!");
        } catch (Exception e) {
            chiudiDatabase();
        }
    }

    public void aggiungiDato(String user, int fiches, int vittorie) throws DatoGiaPresente {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            user = user.toLowerCase();
            String dati = "VALUES ('" + user + "', " + fiches + ", " + vittorie + ");";

            stmt = c.createStatement();
            String sql = "INSERT INTO PROFILO (USERNAME, FICHES, VITTORIE) "
                    + dati;
            stmt.executeUpdate(sql);
            c.commit();
            chiudiDatabase();
            System.out.println("Dato aggiunto correttamente");
        } catch (Exception e) {
            System.out.println("dato già presente");
            chiudiDatabase();
            throw new DatoGiaPresente(e.getMessage());
        }

    }

    /**
     * Permette di sapere il numero di fiches e vittorie di un utente
     *
     * @param user nome utente
     * @return in posizione 0 il numero di fiches, in posizione 1 il numero di
     * vitorie
     */
    public int[] getDati(String user) {
        int dati[] = new int[2];
        try {
            user = user.toLowerCase();
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROFILO;");
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int fiches = rs.getInt("FICHES");
                int vittorie = rs.getInt("VITTORIE");
                if (username.equals(user)) {
                    rs.close();
                    chiudiDatabase();
                    dati[0] = fiches;
                    dati[1] = vittorie;
                    return dati;
                }
            }
            rs.close();
            chiudiDatabase();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            chiudiDatabase();
        }
        dati[0] = -1;
        dati[1] = -1;
        return dati;
    }

    public void setFiches(String user, int fiches) {

        try {
            user = user.toLowerCase();
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String dato = "UPDATE PROFILO set FICHES =" + fiches + " where USERNAME= '" + user + "';";
            String sql = dato;
            stmt.executeUpdate(sql);
            c.commit();
            chiudiDatabase();
            System.out.println("fiches aggiornate con successo");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            chiudiDatabase();
        }

    }

    public int getFiches(String user) {
        try {
            user = user.toLowerCase();
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROFILO;");
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int fiches = rs.getInt("FICHES");
                if (username.equals(user)) {
                    rs.close();
                    chiudiDatabase();
                    return fiches;
                }
            }
            rs.close();
            chiudiDatabase();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            chiudiDatabase();
        }
        return 0;
    }

    public void aggiungiVittoria(String user) {
        int vittorie = 0;
        try {
            user = user.toLowerCase();
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROFILO;");
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int vit = rs.getInt("VITTORIE");
                vit++;
                if (username.equals(user)) {
                    vittorie = vit;
                }
            }
            String dato = "UPDATE PROFILO set VITTORIE =" + vittorie + " where USERNAME='" + user + "';";
            String sql = dato;
            stmt.executeUpdate(sql);
            c.commit();
            chiudiDatabase();
            System.out.println("aggiunta vittoria");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            chiudiDatabase();
        }

    }

    public int getVittorie(String user) {
        try {
            user = user.toLowerCase();
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROFILO;");
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int vittorie = rs.getInt("VITTORIE");
                if (username.equals(user)) {
                    rs.close();
                    chiudiDatabase();
                    return vittorie;
                }
            }
            rs.close();
            chiudiDatabase();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            chiudiDatabase();
        }
        return 0;
    }

    /**
     * Permette di sapere se un nome è già stato inserito nel database
     *
     * @param user nme utente
     * @return true se già esite false se non esiste
     */
    public boolean esisteNome(String user) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setteEmezzo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROFILO;");
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                username = username.toLowerCase();
                if (username.equals(user.toLowerCase())) {
                    rs.close();
                    chiudiDatabase();
                    return true;
                }
            }
            rs.close();
            chiudiDatabase();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            chiudiDatabase();
        }
        return false;
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
