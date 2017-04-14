package gioco;

import classi_dati.Giocata;

public class BotFacile extends Giocatore{

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
