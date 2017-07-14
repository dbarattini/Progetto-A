package dominio.giocatori;

import dominio.elementi_di_gioco.Mazzo;

public class BotDifficile extends Bot {

    /**
     * 
     * @param nome nome del bot
     * @param fiches fiches del bot
     * @param mazzo mazzo della partita
     */
    public BotDifficile(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }
    
    /**
     * 
     * @return percentuale di sballare
     */
    @Override
    protected int getPercentualePerPuntareTanto() {
        return 40;
    }

    /**
     * 
     * @return percentuale di sballare
     */
    @Override
    protected int getPercentualePerChiedereCarta() {
        return 35;
    }
}
