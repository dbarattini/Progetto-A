package giocatori;


import classi_dati.Giocata;


public class BotFacile extends Giocatore{

    public BotFacile(String nome, int fiches) {
        super(nome, fiches);
    }
    
    @Override
    public int decidi_puntata() {
        return (int)this.getFiches()/10;
    }

    @Override
    public Giocata decidi_giocata() {
        if((valore_mano) < 6.0){
            return Giocata.Carta;
        } else{
            return Giocata.Sto;
        }
    }
    
}
