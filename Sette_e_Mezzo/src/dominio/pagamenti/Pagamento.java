package dominio.pagamenti;

import dominio.giocatori.Giocatore;


public abstract class Pagamento {
    
    /**
     * Consente i pagamenti normali.
     *
     * @param pagante
     * @param pagato
     * @param percentuale
     */
    public abstract void normale(Giocatore pagante, Giocatore pagato, double percentuale);

    /**
     * Consente i pagamenti reali.
     *
     * @param pagante
     * @param pagato
     * @param percentuale
     */
    public abstract void reale(Giocatore pagante, Giocatore pagato, double percentuale);

    protected int getPagamento(Giocatore pagante, Giocatore pagato) {
        
        if (pagante.isMazziere()) {
            return pagato.getPuntata();
        } else {
            return pagante.getPuntata();
        }
    }

    protected int getPagamentoReale(Giocatore pagante, Giocatore pagato) {
        if (pagante.isMazziere()) {
            return pagato.getPuntata() * 2;
        } else {
            return pagante.aggiungiPuntataReale();
        }
    }

    protected void paga(Giocatore pagante, Giocatore pagato, int puntata) {
        if (pagante.isMazziere()) {
            pagante.punta(puntata);
            pagato.riscuoti(puntata);
        } else {
            pagato.punta(puntata);
            pagato.riscuoti(puntata);
        }
    }
}
