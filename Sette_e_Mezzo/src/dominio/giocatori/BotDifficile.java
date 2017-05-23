
package dominio.giocatori;

import dominio.classi_dati.Giocata;
import dominio.eccezioni.MattaException;
import dominio.elementi_di_gioco.Carta;
import dominio.elementi_di_gioco.Mazzo;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BotDifficile extends Bot{
    
    public BotDifficile (String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }

    @Override
    protected int decidi_puntata() {
       double percentuale = calcola_percentuale_sballo();
        double valore = ((double)(this.getFiches())/100);
        int puntata = 0;
        
        if(percentuale < 40) {
            puntata = (int) (valore*30);
        }else{
            puntata = (int) (valore*10);
        }
        if(puntata == 0) { //questo if mi serve perchè puntata può essere, per esempio, 0.06 e castato darebbe 0.
            puntata = this.getFiches();
        }
        return puntata;
    }

    @Override
    protected Giocata decidi_giocata() {
        double percentuale = calcola_percentuale_sballo();
        if (percentuale < 35) {
            return Giocata.Carta;
        }else{
            return Giocata.Sto;
        }
    }
    
    
    
}
