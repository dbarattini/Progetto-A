/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.giocatori;

import dominio.classi_dati.Giocata;
import dominio.classi_dati.Stato;
import dominio.elementi_di_gioco.Carta;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class GiocatoreTest {
    
    public GiocatoreTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of update method, of class Giocatore.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        Giocatore instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inizializza_mano method, of class Giocatore.
     */
    @Test
    public void testInizializza_mano() {
        System.out.println("inizializza_mano");
        Giocatore instance = null;
        instance.inizializza_mano();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniziaLetturaOggetti method, of class Giocatore.
     */
    @Test
    public void testIniziaLetturaOggetti() {
        System.out.println("iniziaLetturaOggetti");
        Giocatore instance = null;
        instance.iniziaLetturaOggetti();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inizializzaFiches method, of class Giocatore.
     */
    @Test
    public void testInizializzaFiches() {
        System.out.println("inizializzaFiches");
        Giocatore instance = null;
        instance.inizializzaFiches();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of memorizzaFiches method, of class Giocatore.
     */
    @Test
    public void testMemorizzaFiches() {
        System.out.println("memorizzaFiches");
        Giocatore instance = null;
        instance.memorizzaFiches();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prendi_carta_iniziale method, of class Giocatore.
     */
    @Test
    public void testPrendi_carta_iniziale() throws Exception {
        System.out.println("prendi_carta_iniziale");
        Carta carta = null;
        Giocatore instance = null;
        instance.prendi_carta_iniziale(carta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scrivi method, of class Giocatore.
     */
    @Test
    public void testScrivi() {
        System.out.println("scrivi");
        String msg = "";
        Giocatore instance = null;
        instance.scrivi(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leggi method, of class Giocatore.
     */
    @Test
    public void testLeggi() throws Exception {
        System.out.println("leggi");
        Giocatore instance = null;
        String expResult = "";
        String result = instance.leggi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scriviOggetto method, of class Giocatore.
     */
    @Test
    public void testScriviOggetto() {
        System.out.println("scriviOggetto");
        Object pacco = null;
        Giocatore instance = null;
        instance.scriviOggetto(pacco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of effettua_giocata method, of class Giocatore.
     */
    @Test
    public void testEffettua_giocata() {
        System.out.println("effettua_giocata");
        Giocatore instance = null;
        boolean expResult = false;
        boolean result = instance.effettua_giocata();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decidi_puntata method, of class Giocatore.
     */
    @Test
    public void testDecidi_puntata() {
        System.out.println("decidi_puntata");
        Giocatore instance = null;
        int expResult = 0;
        int result = instance.decidi_puntata();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PuntataInserita method, of class Giocatore.
     */
    @Test
    public void testPuntataInserita() {
        System.out.println("PuntataInserita");
        String puntata_effettuata = "";
        Giocatore instance = null;
        instance.PuntataInserita(puntata_effettuata);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of effettua_puntata method, of class Giocatore.
     */
    @Test
    public void testEffettua_puntata() {
        System.out.println("effettua_puntata");
        Giocatore instance = null;
        instance.effettua_puntata();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decidi_giocata method, of class Giocatore.
     */
    @Test
    public void testDecidi_giocata() {
        System.out.println("decidi_giocata");
        Giocatore instance = null;
        Giocata expResult = null;
        Giocata result = instance.decidi_giocata();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GiocataInserita method, of class Giocatore.
     */
    @Test
    public void testGiocataInserita() {
        System.out.println("GiocataInserita");
        String giocata_effettuata = "";
        Giocatore instance = null;
        instance.GiocataInserita(giocata_effettuata);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiedi_carta method, of class Giocatore.
     */
    @Test
    public void testChiedi_carta() throws Exception {
        System.out.println("chiedi_carta");
        Carta carta = null;
        Giocatore instance = null;
        instance.chiedi_carta(carta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paga method, of class Giocatore.
     */
    @Test
    public void testPaga() {
        System.out.println("paga");
        Giocatore avversario = null;
        Giocatore instance = null;
        instance.paga(avversario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pagaPercentuale method, of class Giocatore.
     */
    @Test
    public void testPagaPercentuale() {
        System.out.println("pagaPercentuale");
        Giocatore avversario = null;
        double percentuale = 0.0;
        Giocatore instance = null;
        instance.pagaPercentuale(avversario, percentuale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paga_reale method, of class Giocatore.
     */
    @Test
    public void testPaga_reale() {
        System.out.println("paga_reale");
        Giocatore avversario = null;
        Giocatore instance = null;
        instance.paga_reale(avversario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paga_reale_percentuale method, of class Giocatore.
     */
    @Test
    public void testPaga_reale_percentuale() {
        System.out.println("paga_reale_percentuale");
        Giocatore avversario = null;
        double percentuale = 0.0;
        Giocatore instance = null;
        instance.paga_reale_percentuale(avversario, percentuale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of riscuoti method, of class Giocatore.
     */
    @Test
    public void testRiscuoti() {
        System.out.println("riscuoti");
        int vincita = 0;
        Giocatore instance = null;
        instance.riscuoti(vincita);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of azzera_fiches method, of class Giocatore.
     */
    @Test
    public void testAzzera_fiches() {
        System.out.println("azzera_fiches");
        Giocatore instance = null;
        instance.azzera_fiches();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of perde method, of class Giocatore.
     */
    @Test
    public void testPerde() {
        System.out.println("perde");
        Giocatore instance = null;
        instance.perde();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haPerso method, of class Giocatore.
     */
    @Test
    public void testHaPerso() {
        System.out.println("haPerso");
        Giocatore instance = null;
        boolean expResult = false;
        boolean result = instance.haPerso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMazziere method, of class Giocatore.
     */
    @Test
    public void testIsMazziere() {
        System.out.println("isMazziere");
        Giocatore instance = null;
        boolean expResult = false;
        boolean result = instance.isMazziere();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Giocatore.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Giocatore instance = null;
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSocket method, of class Giocatore.
     */
    @Test
    public void testGetSocket() {
        System.out.println("getSocket");
        Giocatore instance = null;
        Socket expResult = null;
        Socket result = instance.getSocket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMazziere method, of class Giocatore.
     */
    @Test
    public void testSetMazziere() {
        System.out.println("setMazziere");
        boolean mazziere = false;
        Giocatore instance = null;
        instance.setMazziere(mazziere);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStato method, of class Giocatore.
     */
    @Test
    public void testSetStato() {
        System.out.println("setStato");
        Stato stato = null;
        Giocatore instance = null;
        instance.setStato(stato);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTutteLeCarte method, of class Giocatore.
     */
    @Test
    public void testGetTutteLeCarte() {
        System.out.println("getTutteLeCarte");
        Giocatore instance = null;
        ArrayList<Carta> expResult = null;
        ArrayList<Carta> result = instance.getTutteLeCarte();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUltimaCartaOttenuta method, of class Giocatore.
     */
    @Test
    public void testGetUltimaCartaOttenuta() {
        System.out.println("getUltimaCartaOttenuta");
        Giocatore instance = null;
        Carta expResult = null;
        Carta result = instance.getUltimaCartaOttenuta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValoreMano method, of class Giocatore.
     */
    @Test
    public void testGetValoreMano() {
        System.out.println("getValoreMano");
        Giocatore instance = null;
        double expResult = 0.0;
        double result = instance.getValoreMano();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFiches method, of class Giocatore.
     */
    @Test
    public void testGetFiches() {
        System.out.println("getFiches");
        Giocatore instance = null;
        int expResult = 0;
        int result = instance.getFiches();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPuntata method, of class Giocatore.
     */
    @Test
    public void testGetPuntata() {
        System.out.println("getPuntata");
        Giocatore instance = null;
        int expResult = 0;
        int result = instance.getPuntata();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCartaCoperta method, of class Giocatore.
     */
    @Test
    public void testGetCartaCoperta() {
        System.out.println("getCartaCoperta");
        Giocatore instance = null;
        Carta expResult = null;
        Carta result = instance.getCartaCoperta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Giocatore.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Giocatore instance = null;
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStato method, of class Giocatore.
     */
    @Test
    public void testGetStato() {
        System.out.println("getStato");
        Giocatore instance = null;
        Stato expResult = null;
        Stato result = instance.getStato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCarteScoperte method, of class Giocatore.
     */
    @Test
    public void testGetCarteScoperte() {
        System.out.println("getCarteScoperte");
        Giocatore instance = null;
        ArrayList<Carta> expResult = null;
        ArrayList<Carta> result = instance.getCarteScoperte();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDisconnesso method, of class Giocatore.
     */
    @Test
    public void testIsDisconnesso() {
        System.out.println("isDisconnesso");
        Giocatore instance = null;
        boolean expResult = false;
        boolean result = instance.isDisconnesso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
