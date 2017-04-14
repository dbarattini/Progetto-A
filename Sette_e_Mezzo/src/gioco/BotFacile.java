package gioco;

import classi_dati.Giocata;
import eccezioni.FineMazzoException;
import eccezioni.MazzoRimescolatoException;
import eccezioni.PuntataNegativaException;
import eccezioni.PuntataNullaException;
import eccezioni.PuntataTroppoAltaException;

public class BotFacile extends Giocatore implements Bot {

    public BotFacile(String nome, int posizione, int fiches) {
        super(nome, posizione, fiches);
    }

    @Override
    public Giocata decidi_giocata() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int decidi_puntata() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
