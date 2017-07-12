package dominio.giocatori;

import dominio.elementi_di_gioco.Mazzo;

public class BotDifficile extends Bot {

    public BotDifficile(String nome, int fiches, Mazzo mazzo) {
        super(nome, fiches, mazzo);
    }
    
    @Override
    protected int getPercentualePerPuntareTanto() {
        return 40;
    }

    @Override
    protected int getPercentualePerChiedereCarta() {
        return 35;
    }
}
