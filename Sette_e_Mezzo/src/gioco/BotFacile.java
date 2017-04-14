package gioco;

import classi_dati.Giocata;

public class BotFacile extends Giocatore{

    public BotFacile(String nome, int posizione, int fiches) {
        super(nome, posizione, fiches);
    }

    @Override
    public Giocata decidi_giocata() {
        if((valore_mano) < 6.0){
            return Giocata.Carta;
        } else{
            return Giocata.Sto;
        }
    }

    @Override
    public int decidi_puntata() {
        return this.getFiches()/10;
    }
    
}
