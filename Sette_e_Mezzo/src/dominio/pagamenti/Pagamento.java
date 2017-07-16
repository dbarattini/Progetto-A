package dominio.pagamenti;

import dominio.giocatori.Giocatore;


public abstract class Pagamento {
    
    /**
     * Consente i pagamenti normali.
     *
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @param percentuale percentuale pagamento
     */
    public abstract void normale(Giocatore pagante, Giocatore pagato, double percentuale);

    /**
     * Consente i pagamenti reali.
     *
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @param percentuale percentuale pagamento
     */
    public abstract void reale(Giocatore pagante, Giocatore pagato, double percentuale);

    /**
     * 
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @return 
     */
    protected int getPagamento(Giocatore pagante, Giocatore pagato) {
        
        if (pagante.isMazziere()) {
            return pagato.getPuntata();
        } else {
            return pagante.getPuntata();
        }
    }

    /**
     * 
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @return 
     */
    protected int getPagamentoReale(Giocatore pagante, Giocatore pagato) {
        if (pagante.isMazziere()) {
            return pagato.getPuntata() * 2;
        } else {
            return pagante.aggiungiPuntataReale();
        }
    }

    /**
     * regola i conti del pagamento
     * 
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @param puntata quantità fiches
     */
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
