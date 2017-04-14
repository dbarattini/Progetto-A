package junit_tests;

import classi_dati.Giocata;
import eccezioni.GiocataNonValidaException;
import eccezioni.PuntataNegativaException;
import eccezioni.PuntataNullaException;
import eccezioni.PuntataTroppoAltaException;
import gioco.GiocatoreUmano;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import org.junit.Test;
import static org.junit.Assert.*;

public class testGiocatoreUmano {
    public testGiocatoreUmano() {
    }
    
    @Test
    public void testRichiediPuntata(){        
        int fiches_iniziali = 100;
        int valore_puntato = (int) (fiches_iniziali * Math.random());        
        InputStream in = new ByteArrayInputStream(Integer.toString(valore_puntato).getBytes(StandardCharsets.UTF_8));
        GiocatoreUmano giocatore = new GiocatoreUmano("player1", 0, fiches_iniziali, in, System.out);
     
        int puntata = giocatore.richiedi_puntata();
        
        assertEquals(valore_puntato, puntata);
    }
    
    @Test (expected = InputMismatchException.class)
    public void testRichiediPuntataInputMismatchException(){
        String puntata_errata = "Puntata Errata";
        InputStream in = new ByteArrayInputStream(puntata_errata.getBytes(StandardCharsets.UTF_8));
        GiocatoreUmano giocatore = new GiocatoreUmano("player1", 0, 100, in, System.out);
        
        int puntata = giocatore.richiedi_puntata();
    }
    
    @Test (expected = PuntataTroppoAltaException.class)
    public void testPuntataTroppoAltaException() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        GiocatoreUmano giocatore = new GiocatoreUmano("player1", 0, 100, System.in, System.out);  
        giocatore.controlla_puntata(giocatore.getFiches() + 10);
    }
    
    @Test (expected = PuntataNegativaException.class)
    public void testPuntataNegativaException() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        GiocatoreUmano giocatore = new GiocatoreUmano("player1", 0, 100, System.in, System.out);  
        giocatore.controlla_puntata(-10);
    }
    
    @Test (expected = PuntataNullaException.class)
    public void testPuntataNullaException() throws PuntataTroppoAltaException, PuntataNegativaException, PuntataNullaException{
        GiocatoreUmano giocatore = new GiocatoreUmano("player1", 0, 100, System.in, System.out);  
        giocatore.controlla_puntata(0);
    }  
    
    @Test
    public void testSelezionaGiocata() throws GiocataNonValidaException{
        GiocatoreUmano giocatore;
        Giocata giocata;
        String giocate_fattibili[] = {"carta", "sto"};
        Giocata giocate_possibili[] = Giocata.values();
        
        for(int i=0; i<2; i++){
            InputStream in = new ByteArrayInputStream(giocate_fattibili[i].getBytes(StandardCharsets.UTF_8));
            giocatore = new GiocatoreUmano("player1", 0, 100, in, System.out);
            giocata = giocatore.seleziona_giocata(giocatore.richiedi_giocata());
            
            assertEquals(giocate_possibili[i], giocata);
        }
    }
    
    @Test (expected = GiocataNonValidaException.class)
    public void testSelezionaGiocataException() throws GiocataNonValidaException{
        String giocata_errata = "Giocata Errata";
        InputStream in = new ByteArrayInputStream(giocata_errata.getBytes(StandardCharsets.UTF_8));
        GiocatoreUmano giocatore = new GiocatoreUmano("player1", 0, 100, in, System.out);
        
        giocatore.seleziona_giocata(giocatore.richiedi_giocata());
    }
    
    
    
}
