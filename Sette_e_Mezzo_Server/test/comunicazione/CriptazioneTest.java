/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicazione;

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
        String expResult = "Mario:123stella";
        String result = cript.codificaAccount(username, password);
        assertNotEquals(expResult, result);
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
        String expResult = "#La su io & te 4ever!";
        String result =cript.codificaMessaggio(msg);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of decodificaMessaggio method, of class Criptazione.
     */
    @Test
    public void testDecodificaMessaggio() {
        System.out.println("decodificaMessaggio");
        String msg = cript.codificaMessaggio("#La su io & te 4ever!");
        String expResult = "#La su io & te 4ever!";
        String result = cript.decodificaMessaggio(msg);
        assertEquals(expResult, result);
       
    }
//    
//    /**
//     * Test of decodificaMessaggio method, of class Criptazione.
//     */
//    @Test
//    public void testDecodificaMessaggioAccentato() {
//        System.out.println("decodificaMessaggioAccentato");
//        String msg = cript.codificaMessaggio("perchè non funzionò?");
//        String expResult = "perchè non funzionò?";
//        String result = cript.decodificaMessaggio(msg);
//        assertEquals(expResult, result);
//       
//    }
    
    /**
     * Test of setKey method, of class Criptazione.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        String msg1 = cript.codificaMessaggio("#La su io & te 4ever!");
        String key = "Catrame1";
        cript.setKey(key);
        String msg2 = cript.codificaMessaggio("#La su io & te 4ever!");
        assertNotEquals(msg1, msg2);
    }
    
}
