
package giocatori;

import classi_dati.Giocata;
import eccezioni.MattaException;
import elementi_di_gioco.Carta;
import elementi_di_gioco.Mazzo;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BotDifficile extends Giocatore{
    
    Mazzo mazzo;
    
    public BotDifficile(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches);
        this.mazzo = mazzo;
    }

    @Override
    protected int decidi_puntata() {
        return 15;
    }

    @Override
    protected Giocata decidi_giocata() {
        double percentuale = calcola_percentuale_sballo();
        if (percentuale < 40) {
            return Giocata.Carta;
        } else {
            return Giocata.Sto;
        }
    }
    
    private double calcola_valore_sballo() {
        double prov = (7.5 - this.valore_mano) + 0.5;
        return prov;
    }
    
    private double calcola_percentuale_sballo() {
        double sballo = calcola_valore_sballo();
        double percentuale = 0;
        double contatore = 0;
        for (Carta c : this.mazzo.getCarteDaGiocare()) {
            try {
                if(c.getValoreNumerico() >= sballo) {
                    contatore += 1;
                }
            } catch (MattaException ex) {
                // la matta non conta come carta da sballo
            }
        }
        double daGioc=(double) this.mazzo.getCarteDaGiocare().size();
        percentuale = (contatore/daGioc)*100;
        return percentuale;
    }
    
}
