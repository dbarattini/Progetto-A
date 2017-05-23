
package dominio.giocatori;

import dominio.eccezioni.MattaException;
import dominio.elementi_di_gioco.Carta;
import dominio.elementi_di_gioco.Mazzo;

public abstract class Bot extends Giocatore {
    
    private Mazzo mazzo;
    
    /**
     * 
     * @param nome nome del bot
     * @param fiches quantità fiches iniziali
     * @param mazzo questa variabile è il mazzo della partita, necessario per far decidere la giocata e la puntata al bot
     */
    public Bot (String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches);
        this.mazzo = mazzo;
    }
    /**
     * 
     * @return ritorna il valore numerico della carta che se pescata farebbe sballare il giocatore
     */
    protected double calcola_valore_sballo() {
        double prov = (7.5 - this.valore_mano) + 0.5;
        return prov;
    }
    /**
     * 
     * @return calcola la percentuale di sballare se si decide di chiedere una carta
     */
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
