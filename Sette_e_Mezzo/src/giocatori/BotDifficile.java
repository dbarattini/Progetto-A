
package giocatori;

import classi_dati.Giocata;
import eccezioni.MattaException;
import elementi_di_gioco.Carta;
import elementi_di_gioco.Mazzo;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BotDifficile extends Bot{
    
    public BotDifficile (String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }

    @Override
    protected int decidi_puntata() {
        double percentuale = calcola_percentuale_sballo();
        if(percentuale < 30) {
            int puntata = (int) (this.getFiches()/100)*40;
            return puntata;
        }else{
            int puntata = (int) (this.getFiches()/100)*15;
            return puntata;
        }
    }

    @Override
    protected Giocata decidi_giocata() {
        double percentuale = calcola_percentuale_sballo();
        if (percentuale < 40) {
            return Giocata.Carta;
        }else{
            return Giocata.Sto;
        }
    }
    
    
    
}
