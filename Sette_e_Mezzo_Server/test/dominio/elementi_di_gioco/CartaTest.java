/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.elementi_di_gioco;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class CartaTest {
    
    public CartaTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getValoreNumerico method, of class Carta.
     */
    @Test
    public void testGetValoreNumerico() throws Exception {
        System.out.println("getValoreNumerico");
        Carta instance = new Carta("Q", "c");
        double expResult = 0.5;
        double result = instance.getValoreNumerico();
        assertEquals(expResult, result, 0.0);
    }

   

   
    /**
     * Test of toString method, of class Carta.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Carta instance = new Carta("Q", "c");
        String expResult = "Qc";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getValoreNumerico method, of class Carta.
     */
    @Test
    public void testGetValoreNumerico2() throws Exception {
        System.out.println("getValoreNumerico");
        Carta instance = new Carta("5", "c");
        double expResult = 5.0;
        double result = instance.getValoreNumerico();
        assertEquals(expResult, result, 0.0);
    }

   

   
    /**
     * Test of toString method, of class Carta.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");
        Carta instance = new Carta("5", "c");
        String expResult = "5c";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
