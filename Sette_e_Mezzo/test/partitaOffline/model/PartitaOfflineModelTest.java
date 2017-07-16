/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitaOffline.model;

import dominio.classi_dati.DifficoltaBot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        model = new PartitaOfflineModel(1, DifficoltaBot.Facile, 50);
    }

    
    
     
    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita2() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance = new PartitaOfflineModel(5, DifficoltaBot.Facile, 50);
        instance.inizializza_partita();
        assertTrue(true);
    }

    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita3() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance = new PartitaOfflineModel(12, DifficoltaBot.Difficile, 50);
        instance.inizializza_partita();
        assertTrue(true);
    }

    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita4() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance = new PartitaOfflineModel(2, DifficoltaBot.Facile, 500);
        instance.inizializza_partita();
        assertTrue(true);
    }

    /**
     * Test of inizializza_partita method, of class PartitaOfflineModel.
     */
    @Test
    public void testInizializza_partita5() {
        System.out.println("inizializza_partita");
        PartitaOfflineModel instance = new PartitaOfflineModel(1, DifficoltaBot.Medio, 10);
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

    @Test
    public void testSetNomeGiocatore() {
        System.out.println("setNomeGiocatore");
        String nome_giocatore = "prova";
        model.setNomeGiocatore(nome_giocatore);
        assertTrue(true);
    }

    /**
     * Test of setNomeGiocatore method, of class PartitaOfflineModel.
     */
    @Test
    public void testSetNomeGiocatore2() {
        System.out.println("setNomeGiocatore");
        String nome_giocatore = "CaSpItA";
        PartitaOfflineModel instance = new PartitaOfflineModel(1, DifficoltaBot.Medio, 10);
        instance.setNomeGiocatore(nome_giocatore);
        assertTrue(true);
    }

    /**
     * Test of setNomeGiocatore method, of class PartitaOfflineModel.
     */
    @Test
    public void testSetNomeGiocatore3() {
        System.out.println("setNomeGiocatore");
        String nome_giocatore = "#bellissimi";
        PartitaOfflineModel instance = new PartitaOfflineModel(1, DifficoltaBot.Medio, 10);
        instance.setNomeGiocatore(nome_giocatore);
        assertTrue(true);
    }

    /**
     * Test of setNomeGiocatore method, of class PartitaOfflineModel.
     */
    @Test
    public void testSetNomeGiocatore4() {
        System.out.println("setNomeGiocatore");
        String nome_giocatore = "O'Braian§";
        PartitaOfflineModel instance = new PartitaOfflineModel(1, DifficoltaBot.Medio, 10);
        instance.setNomeGiocatore(nome_giocatore);
        assertTrue(true);
    }

    /**
     * Test of setNomeGiocatore method, of class PartitaOfflineModel.
     */
    @Test
    public void testSetNomeGiocatore5() {
        System.out.println("setNomeGiocatore");
        String nome_giocatore = "città";
        PartitaOfflineModel instance = new PartitaOfflineModel(1, DifficoltaBot.Medio, 10);
        instance.setNomeGiocatore(nome_giocatore);
        assertTrue(true);
    }
    
    

}
