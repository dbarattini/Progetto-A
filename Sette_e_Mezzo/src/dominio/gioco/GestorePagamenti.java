package dominio.gioco;

import dominio.giocatori.Giocatore;


public class GestorePagamenti {
    /**
     * Consente i pagamenti normali.
     *
     * @param pagante
     * @param pagato
     */
    public void paga_normale(Giocatore pagante, Giocatore pagato) {
        int pagamento = getPagamento(pagante,pagato);

        this.paga(pagante, pagato, pagamento);
    }

    /**
     * Consente i pagamenti normali percentuali.
     *
     * @param pagante
     * @param avversario
     * @param pagato
     */
    public void paga_normale_percentuale(Giocatore pagante, Giocatore avversario, double pagato) {
        int pagamento_percentuale;
        double pagamento;

        pagamento = (double) this.getPagamento(pagante, avversario);
        pagamento_percentuale = (int) (pagamento * pagato);
        this.paga(pagante, avversario, pagamento_percentuale);
    }

    /**
     * Consente i pagamenti reali.
     *
     * @param pagante
     * @param pagato
     */
    public void paga_reale(Giocatore pagante, Giocatore pagato) {
        int pagamento;

        pagamento = this.getPagamentoReale(pagante, pagato);
        this.paga(pagante, pagato, pagamento);
    }

    /**
     * Consente i pagamenti reali percentuali.
     *
     * @param pagante
     * @param pagato
     * @param percentuale
     */
    public void paga_reale_percentuale(Giocatore pagante, Giocatore pagato, double percentuale) {
        int pagamento_percentuale;
        double pagamento;

        pagamento = this.getPagamentoReale(pagante, pagato);
        pagamento_percentuale = (int) (pagamento * percentuale);
        this.paga(pagante, pagato, pagamento_percentuale);
    }

    private int getPagamento(Giocatore pagante, Giocatore pagato) {
        
        if (pagante.isMazziere()) {
            return pagato.getPuntata();
        } else {
            return pagante.getPuntata();
        }
    }

    private int getPagamentoReale(Giocatore pagante, Giocatore pagato) {
        if (pagante.isMazziere()) {
            return pagato.getPuntata() * 2;
        } else {
            return pagante.aggiungi_puntata_reale();
        }
    }

    private void paga(Giocatore pagante, Giocatore pagato, int puntata) {
        if (pagante.isMazziere()) {
            pagante.punta(puntata);
            pagato.riscuoti(puntata);
        } else {
            pagato.riscuoti(puntata);
        }
    }
}
