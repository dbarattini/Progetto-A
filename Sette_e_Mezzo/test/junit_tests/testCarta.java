package junit_tests;

import eccezioni.MattaException;
import org.junit.Test;
import static org.junit.Assert.*;
import elementi_di_gioco.Carta;

public class testCarta {
    
    public testCarta() {
    }
    
    @Test
    public void testgetValoreNumerico() throws MattaException{
        //Test solo con cuori ma vale per qualsiasi seme (conta solo il valore)
        Carta asso = new Carta("1", "c");
        assertEquals(1, asso.getValoreNumerico(), 0.1);
        
        Carta due = new Carta("2", "c");
        assertEquals(2, due.getValoreNumerico(), 0.1);
        
        Carta tre = new Carta("3", "c");
        assertEquals(3, tre.getValoreNumerico(), 0.1);
        
        Carta quattro = new Carta("4", "c");
        assertEquals(4, quattro.getValoreNumerico(), 0.1);
        
        Carta cinque = new Carta("5", "c");
        assertEquals(5, cinque.getValoreNumerico(), 0.1);
        
        Carta sei = new Carta("6", "c");
        assertEquals(6, sei.getValoreNumerico(), 0.1);
        
        Carta sette = new Carta("7", "c");
        assertEquals(7, sette.getValoreNumerico(), 0.1);
        
        Carta jack = new Carta("J", "c");
        assertEquals(0.5, jack.getValoreNumerico(), 0.1);
        
        Carta donna = new Carta("Q", "c");
        assertEquals(0.5, donna.getValoreNumerico(), 0.1);
        
        Carta re = new Carta("K", "c");
        assertEquals(0.5, re.getValoreNumerico(), 0.1);
    }
    
        @Test (expected = MattaException.class) 
    public void testgetValoreNumericoMattaException() throws MattaException{
        Carta matta = new Carta("K", "q");
        matta.getValoreNumerico();
    }
}
