package menuRegole;

public class Regole {

    private final String regole;

    public Regole() {
        regole = 
                  "All'inizio del gioco ogni giocatore riceve una carta, non visibile agli altri\n"
                + "giocatori. Successivamente si sceglie la puntata del round, che va da un minimo\n"
                + "di 1 a un massimo di tutto ciò che si possiede. Durante il proprio turno, si\n"
                + "può scegliere di ricevere carte o rimanere con la/le carta/e già in possesso.\n"
                + "Lo scopo del gioco è di fermarsi il più vicino possibile a 7 e mezzo, dove\n"
                + "quest'ultimo è la somma delle carte in proprio possesso (le figure valgono 1/2,\n"
                + "le carte da asso a 7 valgono il loro valore numerico). Se si va oltre 7 e\n"
                + "mezzo, si 'sballa' e si perde la puntata e il round. Si gioca solo contro il\n"
                + "banco (o mazziere), che è l'avversario da battere ed è scelto casualmente a\n"
                + "inizio gioco. In aggiunta: il re di denari è 'la matta', ovvero una carta\n"
                + "particolare che assume il valore (intero da 1 a 7, o 1/2) desiderato dal\n"
                + "giocatore, mentre in caso di sette e mezzo reale, si verrà pagati il doppio\n"
                + "della posta e, al turno successivo, si diverrà mazzieri.\n"
                + "\n"
                + "                             BUON SETTE E MEZZO!                              ";
    }

    /**
     * stampa le regole di sette e mezzo
     * 
     * @return regole 
     */
    public String getRegole() {
        return regole;
    }
}
