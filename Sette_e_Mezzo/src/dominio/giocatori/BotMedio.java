package dominio.giocatori;

import dominio.elementi_di_gioco.Mazzo;

public class BotMedio extends Bot {

    public BotMedio(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }

    @Override
    protected int getPercentualePerPuntareTanto() {
        return 45;
    }

    @Override
    protected int getPercentualePerChiedereCarta() {
        return 40;
    }
}
