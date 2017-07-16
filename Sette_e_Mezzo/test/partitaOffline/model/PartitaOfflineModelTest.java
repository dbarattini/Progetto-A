/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOffline.model;

import dominio.classi_dati.DifficoltaBot;
import dominio.giocatori.Giocatore;
import dominio.giocatori.GiocatoreUmano;
import dominio.musica.AudioPlayer;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import partitaOffline.events.GiocatoreLocaleEventListener;

/**
 *
 * @author root
 */
public class PartitaOfflineModelTest {
    private PartitaOfflineModel model;
    
    public PartitaOfflineModelTest() {
    }
    
    



    @Before
    public void setUp() {
        model= new PartitaOfflineModel(1,DifficoltaBot.Facile, 50);
    }
    

    
    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita2() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance =  new PartitaOfflineModel(5,DifficoltaBot.Facile, 50);
        instance.inizializza_partita();
        assertTrue(true);
    }
    
    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita3() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance =  new PartitaOfflineModel(12,DifficoltaBot.Difficile, 50);
        instance.inizializza_partita();
        assertTrue(true);
    }
    
    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita4() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance =  new PartitaOfflineModel(2,DifficoltaBot.Facile, 500);
        instance.inizializza_partita();
        assertTrue(true);
    }
    
    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita5() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance =  new PartitaOfflineModel(1,DifficoltaBot.Medio, 10);
        instance.inizializza_partita();
        assertTrue(true);
    }

     /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita() {
        System.out.println("inizializza_partita");
        model.inizializza_partita();
        assertTrue(true);
    }
    
//     /**
//     * Test of gioca method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGioca() {
//        System.out.println("gioca");
//        model.gioca();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


    /**
     * Test of setNomeGiocatore method, of class PartitaOfflineModel.
     */
    @Test
    public void testSetNomeGiocatore() {
        System.out.println("setNomeGiocatore");
        String nome_giocatore = "";
        PartitaOfflineModel instance = null;
        instance.setNomeGiocatore(nome_giocatore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    /**
//     * Test of getN_bot method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetN_bot() {
//        System.out.println("getN_bot");
//        PartitaOfflineModel instance = null;
//        int expResult = 0;
//        int result = instance.getN_bot();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDifficolta_bot method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetDifficolta_bot() {
//        System.out.println("getDifficolta_bot");
//        PartitaOfflineModel instance = null;
//        DifficoltaBot expResult = null;
//        DifficoltaBot result = instance.getDifficolta_bot();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFiches_iniziali method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetFiches_iniziali() {
//        System.out.println("getFiches_iniziali");
//        PartitaOfflineModel instance = null;
//        int expResult = 0;
//        int result = instance.getFiches_iniziali();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addGiocatoreLocaleEventListener method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testAddGiocatoreLocaleEventListener() {
//        System.out.println("addGiocatoreLocaleEventListener");
//        GiocatoreLocaleEventListener l = null;
//        PartitaOfflineModel instance = null;
//        instance.addGiocatoreLocaleEventListener(l);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeGiocatoreLocaleEventListener method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testRemoveGiocatoreLocaleEventListener() {
//        System.out.println("removeGiocatoreLocaleEventListener");
//        GiocatoreLocaleEventListener l = null;
//        PartitaOfflineModel instance = null;
//        instance.removeGiocatoreLocaleEventListener(l);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getGiocatori method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetGiocatori() {
//        System.out.println("getGiocatori");
//        PartitaOfflineModel instance = null;
//        ArrayList<Giocatore> expResult = null;
//        ArrayList<Giocatore> result = instance.getGiocatori();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMazziere method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetMazziere() {
//        System.out.println("getMazziere");
//        PartitaOfflineModel instance = null;
//        Giocatore expResult = null;
//        Giocatore result = instance.getMazziere();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getGiocatoreLocale method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetGiocatoreLocale() {
//        System.out.println("getGiocatoreLocale");
//        PartitaOfflineModel instance = null;
//        GiocatoreUmano expResult = null;
//        GiocatoreUmano result = instance.getGiocatoreLocale();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAudio method, of class PartitaOfflineModel.
//     */
//    @Test
//    public void testGetAudio() {
//        System.out.println("getAudio");
//        PartitaOfflineModel instance = null;
//        AudioPlayer expResult = null;
//        AudioPlayer result = instance.getAudio();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
