package menuRegole;

public class Regole {

    private final String regole;

    public Regole() {
        regole = "All'inizio del gioco ogni giocatore riceve una carta, non visibile agli altri giocatori.\n"
                + "Successivamente si sceglie la puntata del round, che va da un minimo di 1 a un massimo di tutto ciò che si possiede.\n"
                + "Durante il proprio turno, si può scegliere di ricevere carte o rimanere con la/le carta/e già in possesso.\n"
                + "Lo scopo del gioco è di fermarsi il più vicino possibile a 7 e mezzo, dove quest'ultimo è la somma delle\n"
                + "carte in proprio possesso (le figure valgono 1/2, le carte da asso a 7 valgono il loro valore numerico).\n"
                + "Se si va oltre 7 e mezzo, si 'sballa' e si perde la puntata e il round.\n"
                + "Si gioca solo contro il banco (o mazziere), che è l'avversario da battere ed è scelto casualmente a inizio gioco.\n"
                + "In aggiunta: il re di denari è 'la matta', ovvero una carta particolare che\n"
                + "assume il valore (intero da 1 a 7, o 1/2) desiderato dal giocatore,\n"
                + "mentre in caso di sette e mezzo reale, si verrà pagati il doppio della posta e, al turno successivo,\n"
                + "si diverrà mazzieri.\n"
                + "Buon sette e mezzo!";
    }

    public String getRegole() {
        return regole;
    }
}
