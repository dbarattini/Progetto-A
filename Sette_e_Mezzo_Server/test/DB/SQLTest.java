/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

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
    public void testAggiungiGiocatore() {
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
    public void testSetFiches() {
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
        System.out.println("getFiches");
        String user = "ciaone";
        int expResult = 10;
        int result = sql.getFiches(user);
        assertEquals(expResult, result);        
    }
    
        /**
     * Test of getFiches method, of class SQL.
     */
    @Test
    public void testGeteSetFiches() {
        String user = "ciaone";
        int fiches = (int) Math.round(Math.random()*1000);
         sql.setFiches(user, fiches);
        int result = sql.getFiches(user);
        assertEquals(fiches, result);        
    }


    /**
     * Test of controllaPassword method, of class SQL.
     */
    @Test
    public void testControllaPasswordEsatta() {
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
    public void testControllaPasswordSbagliata() {
        System.out.println("controllaPassword");
        String user = "ciaone";
        String pw = "proa";
        boolean expResult = false;
        boolean result = sql.controllaPassword(user, pw);
        assertEquals(expResult, result);
      }

//    /**
//     * Test of cambiaPassword method, of class SQL.
//     */
//    @Test
//    public void testCambiaPassword() {
//        System.out.println("cambiaPassword");
//        String user = "";
//        String vecchiaPassword = "";
//        String nuovaPassword = "";
//        SQL instance = new SQL();
//        boolean expResult = false;
//        boolean result = instance.cambiaPassword(user, vecchiaPassword, nuovaPassword);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPassword method, of class SQL.
//     */
//    @Test
//    public void testGetPassword() {
//        System.out.println("getPassword");
//        String user = "";
//        SQL instance = new SQL();
//        String expResult = "";
//        String result = instance.getPassword(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUser method, of class SQL.
//     */
//    @Test
//    public void testGetUser() throws Exception {
//        System.out.println("getUser");
//        String email = "";
//        SQL instance = new SQL();
//        String expResult = "";
//        String result = instance.getUser(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of esisteEmail method, of class SQL.
//     */
//    @Test
//    public void testEsisteEmail() {
//        System.out.println("esisteEmail");
//        String email = "";
//        SQL instance = new SQL();
//        boolean expResult = false;
//        boolean result = instance.esisteEmail(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of esisteUsername method, of class SQL.
//     */
//    @Test
//    public void testEsisteUsername() {
//        System.out.println("esisteUsername");
//        String username = "";
//        SQL instance = new SQL();
//        boolean expResult = false;
//        boolean result = instance.esisteUsername(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of aggiungiVittoria method, of class SQL.
//     */
//    @Test
//    public void testAggiungiVittoria() {
//        System.out.println("aggiungiVittoria");
//        String user = "";
//        SQL instance = new SQL();
//        instance.aggiungiVittoria(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVittorie method, of class SQL.
//     */
//    @Test
//    public void testGetVittorie() {
//        System.out.println("getVittorie");
//        String user = "";
//        SQL instance = new SQL();
//        int expResult = 0;
//        int result = instance.getVittorie(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
