package junit_tests;

import dominio.eccezioni.DatoGiaPresente;
import database.SQL;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class SQLTest {
    SQL sql;
    
    public SQLTest() {
    }
    
    
    
    @Before
    public void setUp() {
        sql= new SQL();
    }


    /**
     * Test of aggiungiDato method, of class SQL.
     */
    @Test
    public void testAggiungiDato() {
        System.out.println("aggiungiDato");
        String user = "samba";
        int fiches = 100;
        int vittorie = 0;
        try {
            sql.aggiungiDato(user, fiches, vittorie);
        } catch (DatoGiaPresente ex) {
            assertTrue(true);
        }
        assertTrue(true);
    }

    /**
     * Test of setFiches method, of class SQL.
     */
    @Test
    public void testSetFiches() {
        
            System.out.println("setFiches");
            String user = "samba";
            int fiches = 1000;
            sql.setFiches(user, fiches);
           assertTrue(true);
      
    }

    /**
     * Test of getFiches method, of class SQL.
     */
    @Test
    public void testGetFiches() {
        System.out.println("getFiches");
        String user = "samba";
        int expResult = 1000;
        int fiches = sql.getFiches(user);
        assertEquals(expResult, fiches);
        
    }

    /**
     * Test of aggiungiVittoria method, of class SQL.
     */
    @Test
    public void testAggiungiVittoria() {
        System.out.println("aggiungiVittoria");
        String user = "samba";
       sql.aggiungiVittoria(user);
       assertTrue(true);
    }

    /**
     * Test of getFiches method, of class SQL.
     */
    @Test
    public void testGetVittorie() {
        System.out.println("getVittorie");
        String user = "samba";
        int expResult = 1;
        int vittorie = sql.getVittorie(user);
        if(vittorie>expResult)
            assertTrue(true);
        else
            fail();
        
    }

    
}
