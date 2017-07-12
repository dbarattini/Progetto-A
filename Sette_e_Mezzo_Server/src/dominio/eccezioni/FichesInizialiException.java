package dominio.eccezioni;

import dominio.giocatori.Giocatore;


public class FichesInizialiException extends Exception {

    public FichesInizialiException() {
    }

    public FichesInizialiException(Giocatore aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
