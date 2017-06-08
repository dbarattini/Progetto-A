package dominio.giocatori;

import dominio.elementi_di_gioco.Mazzo;

public class BotFacile extends Bot {

    public BotFacile(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }

    @Override
    protected int getPercentualePerPuntareTanto() {
        return 50;
    }

    @Override
    protected int getPercentualePerChiedereCarta() {
        return 50;
    }
}
