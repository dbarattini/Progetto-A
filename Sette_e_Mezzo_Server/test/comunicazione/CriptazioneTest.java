/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicazione;

import net.Criptazione;
import dominio.eccezioni.ChiaveNonValida;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class CriptazioneTest {
    private Criptazione cript;
    
    public CriptazioneTest() {
    }
    
    @Before
    public void setUp() {
        cript= new Criptazione();
    }

    /**
     * Test of codificaAccount method, of class Criptazione.
     */
    @Test
    public void testCodificaAccount() {
        System.out.println("codificaAccount");
        String username = "Mario";
        String password = "123stella";
        String result = cript.codificaAccount(username, password);
        assertNotEquals(username+":"+password, result);
    }
    
    /**
     * Test of decodificaAccount method, of class Criptazione.
     */
    @Test
    public void testDecodificaAccount() {
        System.out.println("decodifica Account");
        String[] expResult= {"Mario", "123stella"};
        String criptato= cript.codificaAccount("Mario", "123stella");
        String[] result = cript.decodificaAccount(criptato);
        assertArrayEquals(expResult, result);
       
    }

    /**
     * Test of codificaMessaggio method, of class Criptazione.
     */
    @Test
    public void testCodificaMessaggio() {
        System.out.println("codifica Messaggio");
        String msg = "#La su io & te 4ever!";
        String result =cript.codificaMessaggio(msg);
        System.out.println(result+" "+msg);
        assertNotEquals(msg, result);
    }

    /**
     * Test of decodificaMessaggio method, of class Criptazione.
     */
    @Test
    public void testDecodificaMessaggio() {
        System.out.println("decodificaMessaggio");
        String expResult = "#La su io & te 4ever!";
        String msg = cript.codificaMessaggio( expResult);
        String result = cript.decodificaMessaggio(msg);
        assertEquals(expResult, result);
       
    }
    
//    /**
//     * Test of decodificaMessaggio method, of class Criptazione.
//     */
//    @Test
//    public void testDecodificaMessaggioAccentato() {
//        System.out.println("decodificaMessaggioAccentato");
//        String msg = cript.codificaMessaggio("perchè non funzionò?");
//        String expResult = "perchè non funzionò?";
//        String result = cript.decodificaMessaggio(msg);
//        System.out.println(result+"     "+expResult);
//        assertEquals(expResult, result);       
//    }
//    
    /**
     * Test of setKey method, of class Criptazione.
     */
    @Test
    public void testSetKey() {
        try {
            System.out.println("setKey");
            String key = "PaSsWoRd";
            cript.setKey(key);
            String msg1 = cript.codificaMessaggio("#La su io & te 4ever!");
            key = "Catrame1";
            cript.setKey(key);
            String msg2 = cript.codificaMessaggio("#La su io & te 4ever!");
            assertNotEquals(msg1, msg2);
        } catch (ChiaveNonValida ex) {
            fail();
        }
    }
    
    /**
     * Test of setKey method, of class Criptazione.
     */
    @Test
    public void testSetKeySbagliata() {
        try {
            System.out.println("setKey");
            String key = "passwordlunga";
            cript.setKey(key);
             fail();
        } catch (ChiaveNonValida ex) {
            assertTrue(true);
        }
    }
    
}
