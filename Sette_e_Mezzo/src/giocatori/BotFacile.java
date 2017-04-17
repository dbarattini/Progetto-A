package giocatori;


import classi_dati.Giocata;


public class BotFacile extends Giocatore{

    /**
     *
     * @param nome nome del bot
     * @param fiches numero di fiches iniziali
     */
    public BotFacile(String nome, int fiches) {
        super(nome, fiches);
    }
    
    @Override
    protected int decidi_puntata() {
        return 10;
    }

    @Override
    protected Giocata decidi_giocata() {
        if((valore_mano) < 6.0){
            return Giocata.Carta;
        } else{
            return Giocata.Sto;
        }
    }
    
}
