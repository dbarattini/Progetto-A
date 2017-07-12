package dominio.giocatori;

import dominio.eccezioni.MattaException;
import dominio.eccezioni.SballatoException;
import dominio.eccezioni.SetteeMezzoException;
import dominio.eccezioni.SetteeMezzoRealeException;
import dominio.elementi_di_gioco.Carta;
import java.util.ArrayList;

public class ValoreMano {

    private double valore_mano;

    public ValoreMano() {
        this.valore_mano = 0;
    }

    public void inizializza() {
        valore_mano = 0;
    }

    /**
     * Aggiorna il valore della mano.
     *
     * @param carta_coperta prima carta pescata
     * @param carte_scoperte carte richieste dopo la prima
     */
    public void aggiorna(Carta carta_coperta, ArrayList<Carta> carte_scoperte) {
        this.valore_mano = calcolaNuovoValore(carta_coperta, carte_scoperte);
    }

    private double calcolaNuovoValore(Carta carta_coperta, ArrayList<Carta> carte_scoperte) {
        double valore_mano = 0;
        boolean matta = false;

        try {
            valore_mano = carta_coperta.getValoreNumerico();
        } catch (MattaException ex) {
            matta = true;
        }

        for (Carta carta : carte_scoperte) {
            try {
                valore_mano += carta.getValoreNumerico();
            } catch (MattaException ex) {
                matta = true;
            }
        }

        if (matta) {
            if (carte_scoperte.isEmpty() || valore_mano == 7) { //se la matta è la prima carta pescata, vale 0.5;
                valore_mano += 0.5;
            } else {
                valore_mano += Math.round(7 - valore_mano);
            }
        }

        return valore_mano;
    }

    /**
     * Controlla il valore della mano
     *
     * @param carta_coperta prima carta pescata
     * @param carte_scoperte carte richieste dopo la prima
     * @throws SballatoException Valore della mano maggiore di 7.5
     * @throws SetteeMezzoRealeException Valore della mano pari a 7.5 con due
     * sole carte dello stesso seme
     * @throws SetteeMezzoException Valore della mano pari a 7.5
     */
    public void controlla(Carta carta_coperta, ArrayList<Carta> carte_scoperte) throws SballatoException, SetteeMezzoRealeException, SetteeMezzoException {
        if (valore_mano > 7.5) {
            throw new SballatoException();
        } else if (carte_scoperte.size() == 1 && valore_mano == 7.5 && carta_coperta.getSeme().equals(carte_scoperte.get(0).getSeme())) {
            throw new SetteeMezzoRealeException();
        } else if (valore_mano == 7.5) {
            throw new SetteeMezzoException();
        }
    }

    public double getValore() {
        return valore_mano;
    }
}
