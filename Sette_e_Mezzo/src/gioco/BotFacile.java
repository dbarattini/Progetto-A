package gioco;

import classi_dati.Giocata;
import eccezioni.MazzoRimescolatoException;

public class BotFacile extends Giocatore implements Bot {

    public BotFacile(String nome, int posizione, int fiches) {
        super(nome, posizione, fiches);
    }

    @Override
    public Mazzo gioca_mano(Mazzo mazzo) throws MazzoRimescolatoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Giocata decidi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
