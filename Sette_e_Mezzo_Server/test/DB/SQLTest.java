/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import dominio.eccezioni.GiocatoreNonTrovato;
import dominio.eccezioni.SqlOccupato;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class SQLTest {
    private SQL sql;
    
    public SQLTest() {
    }
    
    @Before
    public void setUp() {
        sql= new SQL();
    }

  /**
     * Test of aggiungiGiocatore method, of class SQL.
     */
    @Test
    public void testAggiungiGiocatore() throws SqlOccupato {
        System.out.println("aggiungi Giocatore");
        String email = "test@test.prova";
        String password = "prova";
        String username = "ciaone";
        int fiches = 100;
        sql.aggiungiGiocatore(email, password, username, fiches);
        assertTrue(true);
    }
    
    /**
     * Test of setFiches method, of class SQL.
     */
    @Test
    public void testSetFiches() throws SqlOccupato {
        System.out.println("setFiches");
        String user = "ciaone";
        int fiches = 10;
       sql.setFiches(user, fiches);
        assertTrue(true);
    }

  
    /**
     * Test of getFiches method, of class SQL.
     */
    @Test
    public void testGetFiches() {
        try {
            System.out.println("getFiches");
            String user = "ciaone";
            int expResult = 10;
            int result = sql.getFiches(user);        
            assertEquals(expResult, result);
        } catch (SqlOccupato ex) {
            Logger.getLogger(SQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /**
     * Test of getFiches method, of class SQL.
     */
    @Test
    public void testGeteSetFiches() {
        try {
            String user = "ciaone";
            int fiches = (int) Math.round(Math.random()*1000);
            sql.setFiches(user, fiches);
            int result = sql.getFiches(user);        
            assertEquals(fiches, result);
        } catch (SqlOccupato ex) {
            Logger.getLogger(SQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of controllaPassword method, of class SQL.
     */
    @Test
    public void testControllaPasswordEsatta() throws SqlOccupato {
        System.out.println("controllaPassword");
        String user = "ciaone";
        String pw = "prova";
        boolean expResult = true;
        boolean result = sql.controllaPassword(user, pw);
        assertEquals(expResult, result);
      }
    
        /**
     * Test of controllaPassword method, of class SQL.
     */
    @Test
    public void testControllaPasswordSbagliata() throws SqlOccupato {
        System.out.println("controllaPassword");
        String user = "ciaone";
        String pw = "proa";
        boolean expResult = false;
        boolean result = sql.controllaPassword(user, pw);
        assertEquals(expResult, result);
      }

    /**
     * Test of cambiaPassword method, of class SQL.
     */
    @Test
    public void testCambiaPasswordEsatta() throws SqlOccupato {
        System.out.println("cambiaPassword");
        String user = "ciaone";
        String vecchiaPassword = "passera";
        String nuovaPassword = "prova";
        boolean expResult = true;
        boolean result = sql.cambiaPassword(user, vecchiaPassword, nuovaPassword);
        assertEquals(expResult, result);
    }
    
    /**
    *
     * Test of cambiaPassword method, of class SQL.
     */
    @Test
    public void testCambiaPasswordSbagliata() throws SqlOccupato {
        System.out.println("cambiaPassword");
        String user = "ciaone";
        String vecchiaPassword = "prva";
        String nuovaPassword = "passera";
        boolean expResult = false;
        boolean result = sql.cambiaPassword(user, vecchiaPassword, nuovaPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class SQL.
     */
    @Test
    public void testGetPassword() throws SqlOccupato {
        System.out.println("getPassword");
        String user = "ciaone";
        String password = "prova";
        String nuovaPassword = "passera";
       boolean cambia = sql.cambiaPassword(user, password, nuovaPassword);
        String result = sql.getPassword(user);
        assertEquals(nuovaPassword, result);
        
    }

    /**
     * Test of getUser method, of class SQL.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String email = "test@test.prova";
        String expResult = "ciaone";
        String result = sql.getUser(email);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of getUser method, of class SQL.
     */
    @Test
    public void testGetUserSbagliato() throws SqlOccupato  {
        try {
            System.out.println("getUser");
            String email = "test@est.prova";
            String expResult = "ciaone";
            String result = sql.getUser(email);
            fail();
        } catch (GiocatoreNonTrovato ex) {
             assertTrue(true);
        }
    }
    
    
    /**
     * Test of esisteEmail method, of class SQL.
     */
    @Test
    public void testEsisteEmail() throws SqlOccupato {
        System.out.println("esisteEmail");
        String email = "test@test.prova";
        boolean expResult = true;
        boolean result = sql.esisteEmail(email);
        assertEquals(expResult, result);
    }

      /**
     * Test of esisteEmail method, of class SQL.
     */
    @Test
    public void testEsisteEmailSbagliata() throws SqlOccupato {
        System.out.println("esisteEmail");
        String email = "test@tes.prova";
        boolean expResult = false;
        boolean result = sql.esisteEmail(email);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of esisteUsername method, of class SQL.
     */
    @Test
    public void testEsisteUsername() throws SqlOccupato {
        System.out.println("esisteUsername");
        String username = "ciaone";
        boolean expResult = true;
        boolean result =sql.esisteUsername(username);
        assertEquals(expResult, result);
    }

     /**
     * Test of esisteUsername method, of class SQL.
     */
    @Test
    public void testEsisteUsernameSbagliato() throws SqlOccupato {
        System.out.println("esisteUsername");
        String username = "ciaoe";
        boolean expResult = false;
        boolean result =sql.esisteUsername(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of aggiungiVittoria method, of class SQL.
     */
    @Test
    public void testAggiungiVittoria() throws GiocatoreNonTrovato, SqlOccupato {
        System.out.println("aggiungiVittoria");
        String user = "ciaone";
        sql.aggiungiVittoria(user);
         assertTrue(true);
    }
    
        /**
     * Test of aggiungiVittoria method, of class SQL.
     */
    @Test
    public void testAggiungiVittoriaSbagliata() throws SqlOccupato {
        try {
            System.out.println("aggiungiVittoria");
            String user = "ciane";
            sql.aggiungiVittoria(user);
            fail();
        } catch (GiocatoreNonTrovato ex) {
            assertTrue(true);
        }
    }

    /**
     * Test of getVittorie method, of class SQL.
     */
    @Test
    public void testGetVittorie() throws SqlOccupato {
        System.out.println("getVittorie");
        String user = "ciaone";
        int expResult = 1;
        int result = sql.getVittorie(user);
        if(result>expResult)
            assertTrue(true);
        else
            fail();
    }
    
}
