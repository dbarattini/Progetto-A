
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
     * @param mazzo mazzo della partita, necessario per far decidere la giocata e la puntata al bot
     */
    public Bot (String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches);
        this.mazzo = mazzo;
    }
    /**
     * 
     * @return valore numerico della carta che se pescata farebbe sballare il giocatore
     */
    protected double calcola_valore_sballo() {
        double valore_sballo = (7.5 - this.valore_mano) + 0.5;
        return valore_sballo;
    }
    /**
     * 
     * @return percentuale di sballare se si decide di chiedere una carta
     */
    protected double calcola_percentuale_sballo() {
        double numero_carte_sballo = conta_carte_sballo();
        double percentuale_sballo = 0;
        
        double numero_carte_da_giocare = (double) this.mazzo.getCarteDaGiocare().size();
        percentuale_sballo = (numero_carte_sballo/numero_carte_da_giocare)*100;
        return percentuale_sballo;
    }
    
    private double conta_carte_sballo(){
        double valore_sballo = calcola_valore_sballo();
        double contatore = 0;
        
        for (Carta c : this.mazzo.getCarteDaGiocare()) {
            try {
                if(c.getValoreNumerico() >= valore_sballo) {
                    contatore += 1;
                }
            } catch (MattaException ex) {
                // la matta non conta come carta da sballo
            }
        }
        
        return contatore;
    }
    
}
