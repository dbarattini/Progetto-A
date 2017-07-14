package dominio.pagamenti;

import dominio.giocatori.Giocatore;
import static java.lang.Math.ceil; //arrotonda per eccesso


public class PagamentoReale extends Pagamento {

    /**
     * 
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @param percentuale percentuale del pagamento
     */
    @Override
    public void normale(Giocatore pagante, Giocatore pagato, double percentuale) {
        int pagamento_percentuale;
        double pagamento;

        pagamento = (double) this.getPagamento(pagante, pagato);
        pagamento_percentuale = (int) ceil(pagamento * percentuale);
        this.paga(pagante, pagato, pagamento_percentuale);
    }

    /**
     * 
     * @param pagante giocatore che paga
     * @param pagato giocatore che viene pagato
     * @param percentuale percentuale del pagamento
     */
    @Override
    public void reale(Giocatore pagante, Giocatore pagato, double percentuale) {
        int pagamento_percentuale;
        double pagamento;

        pagamento = this.getPagamentoReale(pagante, pagato);
        pagamento_percentuale = (int) ceil(pagamento * percentuale);
        this.paga(pagante, pagato, pagamento_percentuale);
    }
    
}
