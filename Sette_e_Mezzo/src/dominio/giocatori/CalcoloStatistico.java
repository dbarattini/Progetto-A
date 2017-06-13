package dominio.giocatori;

import dominio.eccezioni.MattaException;
import dominio.elementi_di_gioco.Carta;
import dominio.elementi_di_gioco.Mazzo;

public class CalcoloStatistico {

    /**
     *
     * @param valore_mano
     * @param mazzo
     * @return percentuale di sballare se si decide di chiedere una carta
     */
    public double calcolaPercentualeSballo(ValoreMano valore_mano, Mazzo mazzo) {
        double numero_carte_sballo = contaCarteSballo(valore_mano, mazzo);
        double percentuale_sballo = 0;

        double numero_carte_da_giocare = (double) mazzo.getCarteDaGiocare().size();
        percentuale_sballo = (numero_carte_sballo / numero_carte_da_giocare) * 100;
        return percentuale_sballo;
    }

    private double contaCarteSballo(ValoreMano valore_mano, Mazzo mazzo) {
        double valore_sballo = calcolaValoreSballo(valore_mano.getValore());
        double contatore = 0;

        for (Carta c : mazzo.getCarteDaGiocare()) {
            try {
                if (c.getValoreNumerico() >= valore_sballo) {
                    contatore += 1;
                }
            } catch (MattaException ex) {
                // la matta non conta come carta da sballo
            }
        }

        return contatore;
    }

    private double calcolaValoreSballo(double valore_mano) {
        double valore_sballo = (7.5 - valore_mano) + 0.5;
        return valore_sballo;
    }
}
