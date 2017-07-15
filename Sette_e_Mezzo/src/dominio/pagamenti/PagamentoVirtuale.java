package dominio.pagamenti;

import dominio.giocatori.Giocatore;


public class PagamentoVirtuale extends PagamentoReale{
    
    /**
     * 
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @param puntata quantità da pagare
     */
    @Override
    protected void paga(Giocatore pagante, Giocatore pagato, int puntata) {
        if (pagante.isMazziere()) {
            pagante.punta(puntata);
        } else {
            pagato.punta(puntata);
            pagato.riscuoti(puntata);
        }
    } 
}
