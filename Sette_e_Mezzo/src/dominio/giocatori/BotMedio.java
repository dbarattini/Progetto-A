package dominio.giocatori;

import dominio.elementi_di_gioco.Mazzo;

public class BotMedio extends Bot {

    /**
     * 
     * @param nome nome del bot
     * @param fiches fiches del bot
     * @param mazzo mazzo della partita
     */
    public BotMedio(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }

    /**
     * 
     * @return percentuale di sballare
     */
    @Override
    protected int getPercentualePerPuntareTanto() {
        return 45;
    }

    /**
     * 
     * @return percentuale di sballare
     */
    @Override
    protected int getPercentualePerChiedereCarta() {
        return 40;
    }
}
