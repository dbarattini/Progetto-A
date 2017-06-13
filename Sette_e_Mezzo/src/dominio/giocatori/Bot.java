package dominio.giocatori;

import dominio.classi_dati.Giocata;
import dominio.elementi_di_gioco.Mazzo;

public abstract class Bot extends Giocatore {

    private Mazzo mazzo;
    private CalcoloStatistico calcolo_statistico;

    /**
     *
     * @param nome nome del bot
     * @param fiches quantità fiches iniziali
     * @param mazzo mazzo della partita, necessario per far decidere la giocata
     * e la puntata al bot
     */
    public Bot(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches);
        this.mazzo = mazzo;
        this.calcolo_statistico = new CalcoloStatistico();
    }

    @Override
    protected int decidiPuntata() {
        double percentuale_sballo = calcolo_statistico.calcolaPercentualeSballo(valore_mano, mazzo);
        double fiches = (double) this.getFiches();
        int puntata;

        if (percentuale_sballo < getPercentualePerPuntareTanto()) {
            puntata = (int) ((fiches * 30) / 100);
        } else {
            puntata = (int) ((fiches * 10) / 100);
        }
        if (puntata == 0) { //questo if mi serve perchè puntata può essere, per esempio, 0.06 e castato darebbe 0.
            puntata = this.getFiches();
        }
        return puntata;
    }

    protected abstract int getPercentualePerPuntareTanto();

    @Override
    protected Giocata decidiGiocata() {
        double percentuale_sballo = calcolo_statistico.calcolaPercentualeSballo(valore_mano, mazzo);

        if (percentuale_sballo < getPercentualePerChiedereCarta()) {
            return Giocata.Carta;
        } else {
            return Giocata.Sto;
        }
    }

    protected abstract int getPercentualePerChiedereCarta();
}
