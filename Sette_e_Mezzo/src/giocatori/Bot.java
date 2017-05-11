
package giocatori;

import eccezioni.MattaException;
import elementi_di_gioco.Carta;
import elementi_di_gioco.Mazzo;

public abstract class Bot extends Giocatore {
    
    private Mazzo mazzo;
    
    public Bot (String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches);
        this.mazzo = mazzo;
    }
    
    protected double calcola_valore_sballo() {
        double prov = (7.5 - this.valore_mano) + 0.5;
        return prov;
    }
    
    protected double calcola_percentuale_sballo() {
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
